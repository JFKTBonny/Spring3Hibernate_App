pipeline {
    agent { label 'deployment' }

    parameters {
        string(name: 'VERSION', defaultValue: 'latest', trim: true)
    }

    stages {
        stage('Cloning codebase for QA Deployment') {
            steps {
                checkout scm
            }
        }

        stage('Deploying on QA Environment') {
            steps {
                sh """
                if ! sudo docker inspect spring3hibernate > /dev/null 2>&1; then
                    sudo docker run -itd --name spring3hibernate \
                        --label traefik.enable=true \
                        --label 'traefik.http.routers.spring3hibernate.rule=Host(`qa-spring.opstree.com`)' \
                        --label traefik.port=8080 \
                        opstree/spring3hibernate:${VERSION}
                else
                    sudo docker rm -f spring3hibernate
                    sudo docker run -itd --name spring3hibernate \
                        --label traefik.enable=true \
                        --label 'traefik.http.routers.spring3hibernate.rule=Host(`qa-spring.opstree.com`)' \
                        --label traefik.port=8080 \
                        opstree/spring3hibernate:${VERSION}
                fi
                """
            }
        }
    }
}
