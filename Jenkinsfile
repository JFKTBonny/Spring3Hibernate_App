pipeline {
    agent any

    parameters {
        string(
            name: 'GREEN_VERSION',
            defaultValue: 'latest',
            trim: true,
            description: 'Green is newer version'
        )
        string(
            name: 'BLUE_VERSION',
            defaultValue: 'latest',
            trim: true,
            description: 'Blue is older version'
        )
    }

    stages {
        stage('Cloning codebase for Security Environment Deployment') {
            steps {
                checkout scm
            }
        }

        stage('Deploying the Green version on PT Environment') {
            steps {
                sh """
                sudo docker run -itd --name spring3hibernate-green \
                    --label traefik.enable=true \
                    --label 'traefik.http.routers.spring3hibernate.rule=Host(`green-pt-spring.santonix.com`)' \
                    --label traefik.port=8080 \
                    santonix/spring3hibernate:${GREEN_VERSION}
                """
            }
        }

        stage('Pause pipeline for Green version validation') {
            steps {
                script {
                    input(message: 'Do you want to proceed further?')
                }
            }
        }

        stage('Cut-over of traffic on Green version from Blue version') {
            steps {
                sh """
                aws route53 change-resource-record-sets \
                    --hosted-zone-id Z10166071X5VNARP9PUCV \
                    --change-batch file://blue-green-performance/green-update.json
                """
            }
        }

        stage('Validation of Performance Testing Environment') {
            steps {
                script {
                    def returnValue = input(
                        message: 'Do you want to proceed further?',
                        parameters: [
                            choice(name: 'action', choices: ['Rollback', 'Proceed'])
                        ]
                    )

                    if (returnValue == 'Rollback') {
                        stage('Rollback in case of failure') {
                            sh """
                            aws route53 change-resource-record-sets \
                                --hosted-zone-id Z10166071X5VNARP9PUCV \
                                --change-batch file://blue-green-performance/blue-update.json
                            """
                        }
                    } else if (returnValue == 'Proceed') {
                        stage('Live the traffic') {
                            sh """
                            sudo docker rm -f spring3hibernate-green
                            sudo docker run -itd --name spring3hibernate-blue \
                                --label traefik.enable=true \
                                --label 'traefik.http.routers.spring3hibernate.rule=Host(`blue-pt-spring.santonix.com`)' \
                                --label traefik.port=8080 \
                                santonix/spring3hibernate:${GREEN_VERSION}
                            """
                        }
                    }
                }
            }
        }
    }
}
