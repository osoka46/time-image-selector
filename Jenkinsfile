pipeline {
    agent none

    tools {
        maven 'Maven-3.9'
    }

    stages {
        stage('Parallel Testing') {
            parallel {

                // Smoke
                stage('Run Smoke Tests') {
                    agent { label 'smoke' }
                    steps {
                        echo 'Starting smoke testing...'
                        sh 'mvn clean test -Dgroups=smoke'
                    }
                    post {
                            always {

                                junit '**/target/surefire-reports/*.xml'
                                echo 'Done.'
                            }
                        }
                }

                // Regression
                stage('Run Regression Tests') {
                    agent { label 'regression' }
                    steps {
                        echo 'Starting regression testing...'
                        sh 'mvn clean test -Dgroups=regression'
                    }
                    post {
                            always {

                                junit '**/target/surefire-reports/*.xml'
                                echo 'Done.'
                            }
                        }
                }
            }
        }
    }
}