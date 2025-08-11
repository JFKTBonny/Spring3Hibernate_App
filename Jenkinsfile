pipeline {
    agent { label 'deployment' }

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
                    --label 'traefik.http.routers.spring3hibernate.rule=Host(`green-pt-spring.opstree.com`)' \
                    --label traefik.port=8080 \
                    opstree/spring3hibernate:${GREEN_VERSION}
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
    }
}
