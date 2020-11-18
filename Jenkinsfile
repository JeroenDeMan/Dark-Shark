pipeline {
    agent any
    tools {
        jdk 'jdk-12.0.2'
        maven 'Maven'
    }
    stages {
        stage('Build 🚀') {
            steps {
               sh 'mvn clean test-compile'
            }
        }
        stage('Tests') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true test'
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
