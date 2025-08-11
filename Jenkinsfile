pipeline {
    agent any

    parameters {
        string(name: 'VERSION', defaultValue: 'latest', trim: true)
    }

    stages {
        stage('Cloning codebase for QA Deployment') {
            steps {
                checkout scm
            }
        }

        stage('Deploying on QA Environment') {
            steps {
                sh """
                if !  docker inspect spring3hibernate > /dev/null 2>&1; then
                     docker run -itd --name spring3hibernate \
                        --label traefik.enable=true \
                        --label 'traefik.http.routers.spring3hibernate.rule=Host(`qa-spring.santonix.com`)' \
                        --label traefik.port=8080 \
                        santonix/spring3hibernate:${VERSION}
                else
                     docker rm -f spring3hibernate
                     docker run -itd --name spring3hibernate \
                        --label traefik.enable=true \
                        --label 'traefik.http.routers.spring3hibernate.rule=Host(`qa-spring.santonix.com`)' \
                        --label traefik.port=8080 \
                        santonix/spring3hibernate:${VERSION}
                fi
                """
            }
        }
    }
}
