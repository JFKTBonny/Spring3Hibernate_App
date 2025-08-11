pipeline {
    agent any

    parameters {
        string(
            name: 'BASELINE_VERSION',
            defaultValue: 'latest',
            trim: true,
            description: 'Older version'
        )
        string(
            name: 'CANARY_VERSION',
            defaultValue: 'latest',
            trim: true,
            description: 'Newer version'
        )
    }

    stages {
        stage('Cloning codebase for UAT Environment Deployment') {
            steps {
                checkout scm
            }
        }

        stage('Deploy the Canary and Baseline versions on UAT Environment') {
            steps {
                sh """
                sudo docker run -itd --name spring3hibernate-canary \
                    --label traefik.enable=true \
                    --label 'traefik.http.routers.spring3hibernate-canary.rule=Host(`uat-spring.opstree.com`)' \
                    --label traefik.port=8080 \
                    --label traefik.weight=10 \
                    --label traefik.backend=app_weighted \
                  opstree/spring3hibernate:${CANARY_VERSION}

                sudo docker run -itd --name spring3hibernate-baseline \
                    --label traefik.enable=true \
                    --label 'traefik.http.routers.spring3hibernate-baseline.rule=Host(`uat-spring.opstree.com`)' \
                    --label traefik.port=8080 \
                    --label traefik.weight=90 \
                    --label traefik.backend=app_weighted \
                  opstree/spring3hibernate:${BASELINE_VERSION}
                """
            }
        }

        stage('Validation for Application') {
            steps {
                script {
                    def returnValue = input(
                        message: 'Do you want to proceed further?',
                        parameters: [
                            choice(name: 'action', choices: ['Rollback', 'Proceed'])
                        ]
                    )

                    if (returnValue == 'Rollback') {
                        stage('Rollback complete traffic to Baseline') {
                            sh """
                            sudo docker rm -f spring3hibernate-baseline || true
                            sudo docker run -itd --name spring3hibernate-baseline \
                                --label traefik.enable=true \
                                --label 'traefik.http.routers.spring3hibernate-baseline.rule=Host(`uat-spring.opstree.com`)' \
                                --label traefik.port=8080 \
                                --label traefik.weight=100 \
                                --label traefik.backend=app_weighted \
                              opstree/spring3hibernate:${BASELINE_VERSION}
                            sudo docker rm -f spring3hibernate-canary || true
                            """
                        }
                    } else if (returnValue == 'Proceed') {
                        stage('Shifting all traffic to Canary') {
                            sh """
                            sudo docker rm -f spring3hibernate-canary || true
                            sudo docker run -itd --name spring3hibernate-canary \
                                --label traefik.enable=true \
                                --label 'traefik.http.routers.spring3hibernate-baseline.rule=Host(`uat-spring.opstree.com`)' \
                                --label traefik.port=8080 \
                                --label traefik.weight=100 \
                                --label traefik.backend=app_weighted \
                              opstree/spring3hibernate:${CANARY_VERSION}
                            """
                        }
                    }
                }
            }
        }
    }
}
