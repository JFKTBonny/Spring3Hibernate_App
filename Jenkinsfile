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

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}:${env.APP_VERSION}")
                    docker.build("${IMAGE_NAME}:latest")
                }
            }
        }

        stage('Code Quality | Checkstyle | Hadolint') {
            parallel {
                stage('Checkstyle') {
                    steps {
                        sh 'mvn checkstyle:checkstyle'
                        recordIssues(
                            tools: [checkStyle(id: 'checkstyle', pattern: '**/checkstyle-result.xml')],
                            qualityGates: [[threshold: 1, type: 'TOTAL', unstable: true, failure: false]]
                        )
                    }
                }
                stage('Hadolint') {
                    steps {
                        sh 'hadolint Dockerfile --no-fail -f json | tee hadolint.json'
                        recordIssues(tools: [hadoLint(id: 'hadolint', pattern: 'hadolint.json')])
                    }
                }
            }
        }

        stage('Unit Testing') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Test & Coverage') {
            steps {
                sh 'mvn clean clover:setup test clover:aggregate clover:clover'  // or phpunit command
            }
        }
        stage('Publish Clover Report') {
            steps {
                publishCoverage adapters: [
                    cloverAdapter('target/site/clover/clover.xml')
                ]
            }
        }

        stage('Security Testing | OWASP | Snyk') {
            parallel {
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
                        snykSecurity(
                            snykInstallation: 'snyk',
                            snykTokenId: 'Snyk-API-token',
                            additionalArguments: "--docker ${IMAGE_NAME}:${env.APP_VERSION}",
                            failOnError: false
                        )
                    }
                }
            }
        }

        stage('Push to Registry') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                        docker.image("${IMAGE_NAME}:${env.APP_VERSION}").push()
                        docker.image("${IMAGE_NAME}:latest").push()
                    }
                }
            }
        }
    }
}
