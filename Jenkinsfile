pipeline {
    agent {
        label 'maven-java17'
    } 
    stages {
         stage('Clean and Install') {
            steps {
                bat 'mvn clean install'
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