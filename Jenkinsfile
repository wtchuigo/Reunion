pipeline {
    agent any 
    stages {
        stage('Compile and Clean') { 
            steps {

                bat "mvn clean compile"
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