pipeline {
    agent none // do not assign a global node because we want specific nodes for parallel tasks

    tools {
        // Managed in 'Manage Jenkins -> Tools'
        maven 'Maven-3.9'
    }

    stages {
        stage('Parallel Testing') {
            parallel {

                // --- BRANCH 1: SMOKE TESTS ---
                stage('Run Smoke Tests') {
                    agent { label 'smoke' } // Runs strictly on the worker labeled 'smoke'
                    steps {
                        echo 'Smoke Testing Started on Worker-Smoke...'
                        // Run only tests tagged with @Tag("smoke")
                        sh 'mvn clean test -Dgroups=smoke'
                    }
                    post {
                        always {
                            // Process test results directly on the smoke node
                            junit '**/target/surefire-reports/*.xml'
                            echo 'Smoke Test Reports Archived.'
                        }
                    }
                }

                // --- BRANCH 2: REGRESSION TESTS ---
                stage('Run Regression Tests') {
                    agent { label 'regression' } // Runs strictly on the worker labeled 'regression'
                    steps {
                        echo 'Regression Testing Started on Worker-Regression...'
                        // Run only tests tagged with @Tag("regression")
                        sh 'mvn clean test -Dgroups=regression'
                    }
                    post {
                        always {
                            // Process test results directly on the regression node
                            junit '**/target/surefire-reports/*.xml'
                            echo 'Regression Test Reports Archived.'
                        }
                    }
                }
            }
        }

        // --- FINAL STAGE: NOTIFICATION ---
        stage('Final Notification') {
            agent any // This step can run on any available node (or built-in node)
            steps {
                script {
                    echo 'Sending Email Notification...'
                    // PLEASE UPDATE THE EMAIL ADDRESS BELOW
                    mail to: 'tursubidonu1@gmail.com',
                         subject: "Build Status: ${currentBuild.currentResult} - Job: ${env.JOB_NAME}",
                         body: """
                            Hello,

                            The automated test execution is complete.

                            Result: ${currentBuild.currentResult}
                            Build Number: ${env.BUILD_NUMBER}
                            Job URL: ${env.BUILD_URL}

                            Regards,
                            Jenkins Automation System
                         """
                }
            }
        }
    }
}