pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git(
                    url: 'git@github.com:JFKTBonny/Spring3Hibernate_App.git',
                    branch: 'main',
                    credentialsId: 'jenkins-github'
                )
            }
        }
        stage('Code Stability') {
            environment {
                JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
                PATH = "${JAVA_HOME}/bin:${env.PATH}"
            }
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Code Quality') {
            environment {
                JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
                PATH = "${JAVA_HOME}/bin:${env.PATH}"
            }
            steps {
                sh 'mvn checkstyle:checkstyle'
                recordIssues(tools: [checkStyle(pattern: '**/checkstyle-result.xml')])
            }
        }
        stage('Unit Testing') {
            environment {
                JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
                PATH = "${JAVA_HOME}/bin:${env.PATH}"
            }
            steps {
                sh 'mvn test'
                recordIssues(tools: [junitParser(pattern: 'target/surefire-reports/*.xml')])
            }
        }
        stage('Security Testing') {
            environment {
                JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
                PATH = "${JAVA_HOME}/bin:${env.PATH}"
            }
            steps {
                sh 'mvn org.owasp:dependency-check-maven:check'
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: false,
                    reportDir: 'target',
                    reportFiles: 'dependency-check-report.html',
                    reportName: 'Dependency Check Report',
                    reportTitles: ''
                ])
            }
        }
        stage('Sonarqube Analysis') {
            environment {
                JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
                PATH = "${JAVA_HOME}/bin:${env.PATH}"
            }
            steps {
                sh "mvn sonar:sonar -Dsonar.host.url=${SONAR_URL} -Dsonar.login=${SONAR_USER} -Dsonar.password=${SONAR_PASSWORD} -Dsonar.java.binaries=."
            }
        }
        stage('Uploading Artifact') {
            environment {
                JAVA_HOME = '/usr/lib/jvm/java-11-openjdk-amd64'
                PATH = "${JAVA_HOME}/bin:${env.PATH}"
            }
            steps {
                nexusArtifactUploader artifacts: [[
                    artifactId: 'spring3hibernate',
                    classifier: '',
                    file: 'target/Spring3HibernateApp.war',
                    type: 'war'
                ]],
                credentialsId: 'nexus-creds',
                groupId: 'org',
                nexusUrl: 'http://34.210.179.48:8081/',
                nexusVersion: 'nexus3',
                protocol: 'http',
                repository: 'spring3hibernate',
                version: 'v0.1'
            }
        }
        stage('Deploying to Dev Environment') {
            environment {
                JAVA_HOME = '/usr/lib/jvm/java-11-openjdk-amd64'
                PATH = "${JAVA_HOME}/bin:${env.PATH}"
            }
            steps {
                echo 'Deployment'
                // Uncomment the line below to enable deployment
                // sh "ansible-playbook -i hosts playbook.yaml -e nexus_artifact_url=${NEXUS_URL}/repository/spring3hibernate/org/spring3hibernate/v0.1/spring3hibernate-v0.1.war"
            }
        }
        stage('DB Update') {
            environment {
                JAVA_HOME = '/usr/lib/jvm/java-11-openjdk-amd64'
                PATH = "${JAVA_HOME}/bin:${env.PATH}"
            }
            steps {
                sh 'mvn flyway:migrate'
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for more details.'
        }
    }
}