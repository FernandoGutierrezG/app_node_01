pipeline {
    //agent { label 'agent_nodejs' }
    //agent { label 'agent-alpine' }
    agent any
    stages {
        stage('Checkout') {
            steps {
                sh 'echo Checkout Stage'
                git 'https://github.com/FernandoGutierrezG/app_node_01.git'
            }
        }
        stage('Build') {
            steps {
                sh 'pwd'
                sh 'ls'
                sh 'ls app/'
                sh 'cd app/'
                sh 'ls'
                sh 'npm install express --save'
                sh 'ls'
                sh 'cd ..'
                sh 'pwd'
                sh 'ls'
                sh 'ls app/'
            }
        }
        stage('Archive') {
            steps {
                sh 'echo Archive Stage'
                //archiveArtifacts artifacts: 'build/**/*', fingerprint: true
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