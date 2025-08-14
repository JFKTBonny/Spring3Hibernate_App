pipeline {
    agent any
    
    tools {
        jdk 'jdk17'
        maven 'maven3'
        snyk  'snyk'
    }

    parameters {
        string(name: 'VERSION', defaultValue: 'latest', trim: true)
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '3', daysToKeepStr: '30'))
        timeout(time: 5, unit: 'MINUTES')
        disableConcurrentBuilds()
    }

    environment {
        SNYK_API_TOKEN = "SNYK_API_TOKEN"
        IMAGE_NAME = "santonix/spring3hibernate"
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Code Checkout') {
            steps {
                git credentialsId: 'jenkins-git', branch: 'main', url: 'git@github.com:JFKTBonny/Spring3Hibernate_App.git'
            }
        }


        stage('Pull Image for QA') {
            steps {
                sh "docker pull ${IMAGE_NAME}:${VERSION}"
            }
        }

        stage('Snyk Security Scan') {
            steps {
               withCredentials([string(credentialsId: 'SNYK_API_TOKEN', variable: 'SNYK_API_TOKEN')]) {
                    sh '''
                        # docker build --target builder -t spring3hibernate-builder:v1 .

                         # Build final runtime stage
                         # docker build -t spring3hibernate:v1 .

                        # Scan builder stage
                        docker run --rm -e SNYK_API_TOKEN=$SNYK_API_TOKEN \
                        -v /var/run/docker.sock:/var/run/docker.sock \
                        snyk/snyk:docker snyk test spring3hibernate-builder:v1 --severity-threshold=medium

                        # Scan runtime stage
                        docker run --rm -e SNYK_API_TOKEN=$SNYK_API_TOKEN \
                        -v /var/run/docker.sock:/var/run/docker.sock \
                        snyk/snyk:docker snyk test spring3hibernate:v1 --severity-threshold=medium
                      '''
                    }
            } 
        }       
        stage('Test Image Before Deployment') {
            steps {
                sh """
                    docker rm -f spring3hibernate-test || true
                    docker run -d --name spring3hibernate-test -p 18080:8080 ${IMAGE_NAME}:${VERSION}

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
                            ${IMAGE_NAME}:${VERSION}
                    else
                         docker rm -f spring3hibernate
                         docker run -itd --name spring3hibernate \
                            --label traefik.enable=true \
                            --label 'traefik.http.routers.spring3hibernate.rule=Host(`qa-spring.santonix.com`)' \
                            --label traefik.port=8080 \
                            ${IMAGE_NAME}:${VERSION}
                    fi
                """
            }
        }
    }
}
