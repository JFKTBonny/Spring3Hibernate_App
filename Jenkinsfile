pipeline {
    agent any
    tools {
        jdk 'jdk17'
        maven 'maven3'
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
        IMAGE_NAME = "santonix/spring3hibernate"
        VERSION_FILE = "version.txt"
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
        // stage('Container Scan') {
        //     steps {
        //         snykSecurity(
        //             snykInstallation: 'snyk',
        //             snykTokenId: 'Snyk-API-token',
        //             additionalArguments: "--docker ${IMAGE_NAME}:${env.APP_VERSION} --severity-threshold=medium",
        //             failOnError: false
        //         )
        //     }
        // }

        stage('Container Scan') {
            steps {
                script {
                    // Run Trivy scan with severity threshold
                    sh """
                        trivy image --severity HIGH,CRITICAL --ignore-unfixed --format json -o trivy-report.json ${IMAGE_NAME}:${env.APP_VERSION}
                    """
                    
                    // Optionally, archive the report or parse it to fail build on vulnerabilities
                    archiveArtifacts artifacts: 'trivy-report.json', allowEmptyArchive: true
                    
                    // You can add logic here to parse JSON and fail build if vulnerabilities found
                }
            }
        }
            
        

        stage('Deploy to Artifactory') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                        docker.image("${IMAGE_NAME}:${env.APP_VERSION}").push()
                        docker.image("${IMAGE_NAME}:latest").push()
                    }
                }
            }
    }
    post {
        failure {
            echo "Build failed, notifying Dev team before deploy..."
            emailext (
            subject: "Build FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: "Build failed before Artifactory deploy. Please check the logs.",
            to: 'dev-team@example.com , jofranco1203@gmail.com'
            )
            slackSend(channel: '#dev-alerts', message: "Build FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
        }
        success {
            echo "Build succeeded, notifying QA with tag: ${env.IMAGE_TAG}"
            emailext (
            subject: "Build SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: "Build succeeded. Image tag: ${env.IMAGE_TAG}. Ready for QA testing.",
            to: 'qa-team@example.com'
            )
            slackSend(channel: '#qa-notifications', message: "Build SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}, Image tag: ${env.IMAGE_TAG}")
        }
   }
}

  
   