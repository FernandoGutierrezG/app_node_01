pipeline {
    agent any
    environment {
        VERSION = sh(returnStdout: true, script: 'date "+%Y%m%d%H%M%S"').trim()
    }
    stages {
        stage('Install dependencies') {
            steps {
                sh 'yarn install'
            }
        }
        stage('Build') {
            steps {
                sh 'yarn build'
            }
        }
        stage('Archive artifact') {
            steps {
                archiveArtifacts artifacts: 'build/**/*', fingerprint: true
            }
        }
        stage('Build and push Docker image') {
            steps {
                script {
                    docker.withRegistry('<URL>', 'docker-credentials-id') {
                        def app = docker.build("my-app:${env.VERSION}")
                        app.push()
                    }
                }
            }
        }
    }
}












pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'npm install'
                sh 'npm run build'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'build/**/*', fingerprint: true
            }
        }
        stage('Docker') {
            steps {
                script {
                    def dockerImage = docker.build("my-image:${env.BUILD_ID}")
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        dockerImage.push()
                    }
                }
            }
        }
    }
}