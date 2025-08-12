pipeline {
    agent any
    
    tools {
        jdk 'jdk17'
        maven 'maven3'
    }
    parameters {
        string(name: 'VERSION', defaultValue: 'latest', trim: true)
    }
    options {
        // Keep only last 10 builds or 30 days
        buildDiscarder(logRotator(numToKeepStr: '3', daysToKeepStr: '30'))

        // Timeout build if it runs longer than 30 minutes
        timeout(time: 5, unit: 'MINUTES')

        // Add timestamps to console output
        // timestamps()

        // Retry the whole pipeline 2 times on failure
        // retry(2)

        // Disable concurrent builds (queue new builds instead)
        disableConcurrentBuilds()
    }

    

    environment {
        SNYK_API_TOKEN = "snyk-token" // Store this in Jenkins Credentials
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }
        stage('Pull Image for QA') {
            steps {
                sh """
                    echo "Pulling image santonix/spring3hibernate:${VERSION}"
                    docker pull santonix/spring3hibernate:${VERSION}
                """
            }
        }

         stage('Snyk Security Scan') {
            steps {
                snykSecurity(
                    snykApiToken: "${snyk-token}",
                    dockerImage: "${IMAGE_NAME}:${env.APP_VERSION}",
                    failOnIssues: true,
                    severityThreshold: 'medium'
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
