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

                    curl -Lo snyk "${snyk_cli_dl_linux}"
                    chmod +x snyk
                '''
                script {
                    env.PATH = "${pwd()}:${env.PATH}"
                }
            }
        }

        stage('Authorize Snyk CLI') {
            steps {
                sh 'snyk auth ${SNYK_API_TOKEN}'
            }
        }

        stage('Build Jar') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:${VERSION} .'
            }
        }

        stage('Snyk Container Scan') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'snyk container test ${IMAGE_NAME}:${VERSION} --sarif-file-output=results-container.sarif'
                }
                recordIssues tool: sarif(name: 'Snyk Container', id: 'snyk-container', pattern: 'results-container.sarif')
            }
        }

        stage('Snyk Code/Dependency Test') {
            steps {
                sh 'snyk test'
            }
        }
    }
}


// pipeline {
//     agent any

//     tools {
//         jdk 'jdk17'
//         maven 'maven3'
//     }

//     parameters {
//         string(name: 'VERSION', defaultValue: 'latest', trim: true)
//     }

//     options {
//         buildDiscarder(logRotator(numToKeepStr: '3', daysToKeepStr: '30'))
//         timeout(time: 5, unit: 'MINUTES')
//         disableConcurrentBuilds()
//     }

//     environment {
//         IMAGE_NAME = "santonix/spring3hibernate"
//         SNYK_API_TOKEN = credentials('SNYK_API_TOKEN')

//     }

//     stages {
//         stage('Clean Workspace') {
//             steps {
//                 deleteDir()
//             }
//         }

//         stage('Code Checkout') {
//             steps {
//                 git credentialsId: 'jenkins-git', branch: 'main', url: 'git@github.com:JFKTBonny/Spring3Hibernate_App.git'
//             }
//         }

//         stage('Download Latest Snyk CLI') {
//             steps {
//                 sh '''
//                     latest_version=$(curl -Is "https://github.com/snyk/cli/releases/latest" | grep "^location" | sed s#.*tag/##g | tr -d "\\r")
//                     echo "Latest Snyk CLI Version: ${latest_version}"

//                     snyk_cli_dl_linux="https://github.com/snyk/cli/releases/download/${latest_version}/snyk-linux"
//                     echo "Download URL: ${snyk_cli_dl_linux}"

//                     curl -Lo ./snyk "${snyk_cli_dl_linux}"
//                     chmod +x ./snyk
//                 '''
//                 // Make sure our custom snyk binary is found first
//                 script {
//                     env.PATH = "${pwd()}:${env.PATH}"
//                 }
//             }
//         }

//         stage('Authorize Snyk CLI') {
//             steps {
//                 sh 'snyk auth ${SNYK_API_TOKEN}'
//             }
//         }
        
//         stage('Build') {
//             steps {
//                 sh 'mvn package'
//             }
//         }

//         stage('Build Image') {
//             steps {
//                 sh 'docker build -t ${IMAGE_NAME}:${VERSION} .'
//             }
//         }

//         stage('Snyk Container image related') {
//             steps {
//                 catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
//                     sh 'snyk container test ${IMAGE_NAME}:${VERSION} --sarif-file-output=results-container.sarif'
//                 }
//                 recordIssues tool: sarif(name: 'Snyk Container', id: 'snyk-container', pattern: 'results-container.sarif')
//             }
//         }

//         stage('Snyk Container Dockerfile related') {
//             steps {
//                 catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
//                     sh 'snyk container test sebsnyk/juice-shop --file=Dockerfile --sarif-file-output=results-container.sarif'
//                 }
//                 recordIssues tool: sarif(name: 'Snyk Container', id: 'snyk-container', pattern: 'results-container.sarif')
//             }
//         }

//         stage('Snyk Test using Snyk CLI') {
//             steps {
//                 sh 'snyk test'
//             }
//         }
//     }
// }
