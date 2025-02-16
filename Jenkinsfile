pipeline {
    agent any
    environment {    
		POM_PATH = 'pom.xml'
    }
    stages {
         stage('package') {
            steps {
                bat 'mvn package'
            }
        }
        //SonarQube
            stage('Scan') {
                steps {
                    withSonarQubeEnv(installationName: 'sq1') {
                        bat './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:5.0.0.4389:sonar'
		        }
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
