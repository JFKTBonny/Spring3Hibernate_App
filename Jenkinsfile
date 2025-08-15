pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
        // snyk 'snyk'
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
        IMAGE_NAME = "santonix/spring3hibernate"
        SNYK_API_TOKEN = credentials('SNYK_API_TOKEN')
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

        

        stage('Download Latest Snyk CLI') {
            steps {
                sh '''
                    latest_version=$(curl -Is "https://github.com/snyk/cli/releases/latest" | grep "^location" | sed s#.*tag/##g | tr -d "\r")
                    echo "Latest Snyk CLI Version: ${latest_version}"

                    snyk_cli_dl_linux="https://github.com/snyk/cli/releases/download/${latest_version}/snyk-linux"
                    echo "Download URL: ${snyk_cli_dl_linux}"

                    curl -Lo ./snyk "${snyk_cli_dl_linux}"
                    chmod +x snyk
                    ls -la
                    ./snyk -v
                '''
            }
        }

         stage('Build') {
            steps {
              sh 'mvn package'
            }
        }

        stage('Snyk Test using Snyk CLI') {
            steps {
                sh './snyk test'
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
                    docker rm -f spring3hibernate || true
                    docker run -itd --name spring3hibernate \
                        --label traefik.enable=true \
                        --label 'traefik.http.routers.spring3hibernate.rule=Host(\`qa-spring.santonix.com\`)' \
                        --label traefik.port=8080 \
                        ${IMAGE_NAME}:${VERSION}
                """
            }
        }
    }
}
