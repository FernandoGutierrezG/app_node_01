pipeline {
    agent { label 'agent-alpine' }
    stages {
        stage('Checkout') {
            steps {
                sh 'echo Checkout Stage'
                git 'https://github.com/FernandoGutierrezG/app_node_01.git'
            }
        }
        stage('Build') {
            steps {
                sh 'npm install'
                sh 'npm run build'
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