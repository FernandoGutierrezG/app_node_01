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