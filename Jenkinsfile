pipeline {
    //agent { label 'agent_nodejs' }
    //agent { label 'agent-alpine' }
    agent any
    stages {
        stage('Clean') {
            steps {
                deleteDir()
                script  {
                    NAME = '${BUILD_NUMBER}variable'
                    echo $NAME
                }
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
                    sh 'pwd && ls'
                    
                    script {
                            def dockerImage = docker.build("app_node:${env.BUILD_ID}")
                            }
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