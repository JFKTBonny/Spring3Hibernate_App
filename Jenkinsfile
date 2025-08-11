pipeline {
    agent any
    tools {
            jdk: 'jdk17'
            maven: 'maven3'
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

        // stage('Code Stability | Build Image') {
        //     steps {
        //         script {
        //             docker.build("santonix/spring3hibernate:${env.BUILD_ID}")
        //         }
        //     }
        // }

        stage('Code Quality | Checkstyle | Hadolint') {
            parallel {
                stage('Checkstyle') {
                    // agent {
                    //     docker {
                    //         args "-v ${HOME}/.m2:/root/.m2"
                    //         image 'maven:3.8.5-jdk-11-slim'
                    //     }
                    }
                    steps {
                        git credentialsId: 'jenkins-git',branch: 'main', url: 'git@github.com:JFKTBonny/Spring3Hibernate_App.git'
                        sh 'mvn checkstyle:checkstyle'
                        recordIssues(tools: [checkStyle(pattern: '**/checkstyle-result.xml')])
                    }
                }
                stage('Hadolint') {
                    steps {
                        sh 'hadolint Dockerfile --no-fail -f json | tee -a hadolint.json'
                        recordIssues(tools: [hadoLint(pattern: 'hadolint.json')])
                    }
                }
            }
        }

        stage('Unit Testing') {
            // agent {
            //     docker {
            //         args "-v ${HOME}/.m2:/root/.m2"
            //         image 'maven:3.8.5-jdk-11-slim'
            //     }
            // }
            steps {
                git credentialsId: 'jenkins-git',branch: 'main', url: 'git@github.com:JFKTBonny/Spring3Hibernate_App.git'
                sh 'mvn test'
                recordIssues(tools: [junitParser(pattern: 'target/surefire-reports/*.xml')])
            }
        }

        stage('Code Coverage') {
            // agent {
            //     docker {
            //         args "-v ${HOME}/.m2:/root/.m2"
            //         image 'maven:3.8.5-jdk-11-slim'
            //     }
            // }
            steps {
                git credentialsId: 'jenkins-git', branch: 'main',url: 'git@github.com:JFKTBonny/Spring3Hibernate_App.git'
                sh 'mvn cobertura:cobertura'
                cobertura autoUpdateHealth: false, autoUpdateStability: false,
                         coberturaReportFile: '**/target/site/cobertura/coverage.xml',
                         conditionalCoverageTargets: '70,0,0',
                         failUnhealthy: false, failUnstable: false,
                         lineCoverageTargets: '80,0,0',
                         maxNumberOfBuilds: 0,
                         methodCoverageTargets: '80,0,0',
                         onlyStable: false,
                         sourceEncoding: 'ASCII',
                         zoomCoverageChart: false
            }
        }

        stage('Security Testing | OWASP | Snyk') {
            parallel {
                stage('Dependency Check') {
                    // agent {
                    //     docker {
                    //         args "-v ${HOME}/.m2:/root/.m2"
                    //         image 'maven:3.8.5-jdk-11-slim'
                    //     }
                    // }
                    steps {
                        git credentialsId: 'jenkins-git',branch: 'main', url: 'git@github.com:JFKTBonny/Spring3Hibernate_App.git'
                        sh 'mvn org.owasp:dependency-check-maven:check'
                        publishHTML([
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target',
                            reportFiles: 'dependency-check-report.html',
                            reportName: 'Dependency Check',
                            reportTitles: 'Spring3hibernate'
                        ])
                    }
                }
                stage('Container Scan') {
                    steps {
                        echo 'Testing...'
                        snykSecurity(
                            snykInstallation: 'snyk',
                            snykTokenId: 'Snyk-API-token',
                            additionalArguments: "--docker santonix/spring3hibernate:${env.BUILD_ID}",
                            failOnError: false
                        )
                    }
                }
                stage('Clean Workspace') {
                    steps {
                        deleteDir()
                    }
                }
            }
        }
    }

