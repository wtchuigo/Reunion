pipeline {
    agent any
    stages {
	 stage('package') {
            steps {
                bat 'mvn clean install'
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
    }
}
