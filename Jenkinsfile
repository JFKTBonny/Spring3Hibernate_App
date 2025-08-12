pipeline {
    agent any
    tools {
        jdk 'jdk17'
        maven 'maven3'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '3', daysToKeepStr: '30'))
        timeout(time: 30, unit: 'MINUTES')
        disableConcurrentBuilds()
    }

    environment {
        IMAGE_NAME = "santonix/spring3hibernate"
        VERSION_FILE = "version.txt"
        DEV_TEAM_EMAIL = "dev-team@example.com"
        QA_TEAM_EMAIL = "qa-team@example.com"
        RUNNER_EMAIL = "jofranco1203@gmail.com"
        DOCKERHUB_CREDENTIALS = "dockerhub-credentials"
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

        stage('Determine Version') {
            steps {
                script {
                    if (fileExists(VERSION_FILE)) {
                        def currentVersion = readFile(VERSION_FILE).trim().toInteger()
                        env.APP_VERSION = "v${currentVersion + 1}"
                        writeFile file: VERSION_FILE, text: "${currentVersion + 1}"
                    } else {
                        env.APP_VERSION = "v1"
                        writeFile file: VERSION_FILE, text: "1"
                    }
                    echo "Building version: ${env.APP_VERSION}"
                }
            }
        }

        stage('Hadolint') {
            steps {
                sh 'hadolint Dockerfile --no-fail -f json | tee hadolint.json'
                recordIssues(tools: [hadoLint(id: 'hadolint', pattern: 'hadolint.json')])
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}:${env.APP_VERSION}")
                    docker.build("${IMAGE_NAME}:latest")
                }
            }
        }

        stage('Checkstyle Analysis') {
            steps {
                sh 'mvn checkstyle:checkstyle'
                recordIssues tools: [checkStyle(pattern: '**/checkstyle-result.xml')]
            }
        }

        stage('Unit Testing') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Build and Test with Clover') {
            steps {
                sh 'mvn clean clover:setup test clover:aggregate clover:clover'
            }
        }

        stage('Publish Clover Report') {
            steps {
                step([
                    $class: 'CloverPublisher',
                    cloverReportDir: 'target/site',
                    cloverReportFileName: 'clover.xml',
                    healthyTarget: [methodCoverage: 70, conditionalCoverage: 80, statementCoverage: 80],
                    unhealthyTarget: [methodCoverage: 50, conditionalCoverage: 50, statementCoverage: 50],
                    failingTarget: [methodCoverage: 0, conditionalCoverage: 0, statementCoverage: 0]
                ])
            }
        }

        stage('Dependency Check') {
            steps {
                sh 'mvn org.owasp:dependency-check-maven:check'
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target',
                    reportFiles: 'dependency-check-report.html',
                    reportName: 'Dependency Check'
                ])
            }
        }

        stage('Container Scan') {
            steps {
                script {
                    sh """
                        trivy image --severity HIGH,CRITICAL --ignore-unfixed --format json -o trivy-report.json ${IMAGE_NAME}:${env.APP_VERSION}
                    """
                    archiveArtifacts artifacts: 'trivy-report.json', allowEmptyArchive: true
                }
            }
        }

        stage('Deploy to Artifactory') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_TOKEN')]) {
                        sh 'docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_TOKEN'
                        docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                            docker.image("${IMAGE_NAME}:${env.APP_VERSION}").push()
                            docker.image("${IMAGE_NAME}:latest").push()
                        }
                    }
                }
            }
        }
    }

    post {
        failure {
            emailext(
                to: "${env.DEV_TEAM_EMAIL}, ${env.RUNNER_EMAIL}",
                subject: "Jenkins Build FAILED: ${currentBuild.fullDisplayName}",
                body: """Build failed in stage: ${env.STAGE_NAME}
Project: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Build URL: ${env.BUILD_URL}
"""
            )
        }
        success {
            emailext(
                to: "${env.QA_TEAM_EMAIL}, ${env.RUNNER_EMAIL}",
                subject: "Docker Image Deployed: ${IMAGE_NAME}:${env.APP_VERSION}",
                body: """Hello QA Team,

The Docker image has been successfully deployed to Docker Hub.

Image: ${IMAGE_NAME}:${env.APP_VERSION}

Regards,
Jenkins CI
"""
            )
        }
    }
}