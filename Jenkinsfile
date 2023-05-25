pipeline {
    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker')
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
        stage('Docker build') {
            steps {
                script {
                    sh 'echo Docker Stage'
                    sh 'docker build -t ${DOCKER_IMAGE_NAME} .'
                }
            }
        }
        stage('Docker Push') {
            steps {
                sh 'echo Deploy Stage'
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh 'docker push ${DOCKER_IMAGE_NAME}'
            }
        }
    }
}