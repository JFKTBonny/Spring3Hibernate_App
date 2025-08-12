pipeline {
    agent any

    parameters {
        string(name: 'VERSION', defaultValue: 'latest', trim: true)
    }

    environment {
        SNYK_API_TOKEN = credentials('Snyk-API-token') // Store this in Jenkins Credentials
    }

    stages {
        stage('Pull Image for QA') {
            steps {
                sh """
                    echo "Pulling image santonix/spring3hibernate:${VERSION}"
                    docker pull santonix/spring3hibernate:${VERSION}
                """
            }
        }

        stage('Container Scan') {
            steps {
                snykSecurity(
                    snykInstallation: 'snyk',
                    snykTokenId: 'Snyk-API-token',
                    additionalArguments: "--docker ${IMAGE_NAME}:${env.APP_VERSION} --severity-threshold=high",
                    failOnError: false
                )
            }
        }

        stage('Test Image Before Deployment') {
            steps {
                sh """
                    docker rm -f spring3hibernate-test || true
                    docker run -d --name spring3hibernate-test -p 18080:8080 santonix/spring3hibernate:${VERSION}

                    echo "Waiting for app to start..."
                    sleep 15

                    echo "Running health check..."
                    if ! curl --fail --silent http://localhost:18080 > /dev/null; then
                        echo "❌ Health check failed"
                        docker logs spring3hibernate-test
                        exit 1
                    fi

                    echo "✅ Image passed health check."
                    docker rm -f spring3hibernate-test
                """
            }
        }

        stage('Deploy on QA Environment') {
            steps {
                sh """
                    if ! docker inspect spring3hibernate > /dev/null 2>&1; then
                         docker run -itd --name spring3hibernate \
                            --label traefik.enable=true \
                            --label 'traefik.http.routers.spring3hibernate.rule=Host(`qa-spring.santonix.com`)' \
                            --label traefik.port=8080 \
                            santonix/spring3hibernate:${VERSION}
                    else
                         docker rm -f spring3hibernate
                         docker run -itd --name spring3hibernate \
                            --label traefik.enable=true \
                            --label 'traefik.http.routers.spring3hibernate.rule=Host(`qa-spring.santonix.com`)' \
                            --label traefik.port=8080 \
                            santonix/spring3hibernate:${VERSION}
                    fi
                """
            }
        }
    }
}
