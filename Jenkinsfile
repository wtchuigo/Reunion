pipeline {
    agent any
    stages {
         stage('package') {
            steps {
                bat 'mvn package'
            }
        }
        stage('Test') { 
            steps {
                bat "mvn test site"
            }
            
             post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'   
                }
            }     
        }
    }
}