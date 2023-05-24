pipeline {
    //agent { label 'agent_nodejs' }
    //agent { label 'agent-alpine' }
    agent any
    stages {
        stage('Clean') {
            steps {
                deleteDir()
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
                archiveArtifacts artifacts: 'build/**/*', fingerprint: true
            }
        }
        stage('Docker') {
            steps {
                script {
                    sh 'echo Docker Stage'
                    //docker.build('your-image-name')
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo Deploy Stage'
                //sh 'docker push your-image-name'
            }
        }
    }
}