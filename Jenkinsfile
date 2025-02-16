pipeline {
    agent any
    stages {
         
        //SonarQube
            stage('Scan') {
                steps {
                    withSonarQubeEnv(installationName: 'sq1') {
                        bat './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:5.0.0.4389:sonar'
		        }
		    }
		}
	    stage('package') {
            steps {
                bat 'mvn package'
            }
        }
    }
}
