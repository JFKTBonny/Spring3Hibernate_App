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
                    latest_version=$(curl -Is "https://github.com/snyk/cli/releases/latest" | grep "^location" | sed s#.*tag/##g | tr -d "\\r")
                    echo "Latest Snyk CLI Version: ${latest_version}"

                    snyk_cli_dl_linux="https://github.com/snyk/cli/releases/download/${latest_version}/snyk-linux"
                    echo "Download URL: ${snyk_cli_dl_linux}"

                    curl -Lo /usr/local/bin/snyk "${snyk_cli_dl_linux}"
                    chmod +x /usr/local/bin/snyk
                    snyk -v
                '''
            }
        }

        stage('Authorize Snyk CLI') {
            steps {
                sh 'snyk auth ${SNYK_API_TOKEN}'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Snyk Container') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'snyk container test sebsnyk/juice-shop --file=Dockerfile --sarif-file-output=results-container.sarif'
                }
                recordIssues tool: sarif(name: 'Snyk Container', id: 'snyk-container', pattern: 'results-container.sarif')
            }
        }

        stage('Snyk Test using Snyk CLI') {
            steps {
                sh 'snyk test'
            }
        }
    }
}
