pipeline {
    agent any
    tools {
        jdk 'jdk17'
        maven 'maven3'
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

        stage('Code Quality | Checkstyle | Hadolint') {
            parallel {
                stage('Checkstyle') {
                    steps {
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
            steps {
                sh 'mvn test'
                recordIssues(tools: [junitParser(pattern: 'target/surefire-reports/*.xml')])
            }
        }

        stage('Code Coverage') {
            steps {
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
                    steps {
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
                        echo 'Skipping Snyk scan because image build is not included currently'
                        // Uncomment and adjust below after you build your Docker image
                        // snykSecurity(
                        //    snykInstallation: 'snyk',
                        //    snykTokenId: 'Snyk-API-token',
                        //    additionalArguments: "--docker santonix/spring3hibernate:${env.BUILD_ID}",
                        //    failOnError: false
                        // )
                    }
                }
            }
        }
    }
}
