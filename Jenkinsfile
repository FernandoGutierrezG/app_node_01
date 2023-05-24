pipeline {
    environment {
        DOCKER_IMAGE_NAME = "${DOCKER_USERNAME}/app_node:${env.BUILD_ID}"
    }
    //agent { label 'agent_nodejs' }
    //agent { label 'agent-alpine' }
    agent any
    stages {
        stage('Clean') {
            steps {
                deleteDir()
                sh 'echo ${DOCKER_IMAGE_NAME}'
            }
        }
        stage('Checkout') {
            steps {
                sh 'echo Checkout Stage'
                checkout scm
                //git 'https://github.com/FernandoGutierrezG/app_node_01.git'
            }
        }
        stage('Build') {
            steps {
                sh 'cd app/ && npm install express --save'
            }
        }
        stage('Archive') {
            steps {
                sh 'echo Archive Stage'
                archiveArtifacts artifacts: 'app/', fingerprint: true
            }
        }
        stage('Docker') {
            steps {
                script {
                    sh 'echo Docker Stage'
                    script {
                            def dockerImage = docker.build(${DOCKER_IMAGE_NAME})
                            dockerImage.push
                            }
                        }
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo Deploy Stage'
                //sh 'docker push ${env.DOCKER_IMAGE_NAME}'
            }
        }
    }
}