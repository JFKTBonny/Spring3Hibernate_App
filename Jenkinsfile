pipeline {
    agent any

    parameters {
        string(name: 'OLDER_VERSION', defaultValue: 'latest', trim: true)
        string(name: 'NEW_VERSION', defaultValue: 'latest', trim: true)
    }

    stages {
        stage('Cloning codebase for Security Environment Deployment') {
            steps {
                checkout scm
            }
        }

        stage('Deploying the ${NEW_VERSION} on Security Environment') {
            steps {
                sh """
                 docker run -itd --name spring3hibernate-${NEW_VERSION} \
                    santonix/spring3hibernate:${NEW_VERSION}
                """
            }
        }

        stage('Validating the ${NEW_VERSION}') {
            steps {
                sh """
                sleep 10s
                NEW_IP=\$( docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' spring3hibernate-${NEW_VERSION})
                response=\$(curl --write-out '%{http_code}' --silent --output /dev/null http://\${NEW_IP}:8080)

                if [ "\${response}" != "200" ]; then
                    echo "Application is not working fine"
                    exit 1
                fi
                """
            }
        }

        stage('Add the new version to load balancer') {
            steps {
                sh """
                 docker rm -f spring3hibernate-${NEW_VERSION} || true
                 docker run -itd --name spring3hibernate-${NEW_VERSION} \
                    --label traefik.enable=true \
                    --label 'traefik.http.routers.spring3hibernate.rule=Host(`qa-spring.santonix.com`)' \
                    --label traefik.port=8080 \
                    santonix/spring3hibernate:${NEW_VERSION}
                """
            }
        }

        stage('Removing old version') {
            steps {
                sh """
                 docker rm -f spring3hibernate-${OLDER_VERSION} || true
                """
            }
        }
    }
}
