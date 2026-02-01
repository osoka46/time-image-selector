pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
    }

    stages {
        stage('Build & Test') {
            steps {
                echo 'Testing started...'
                // start test
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            // report
            junit '**/target/surefire-reports/*.xml'
            echo 'Testing finished.'
        }
    }
}