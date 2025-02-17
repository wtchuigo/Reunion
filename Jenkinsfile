pipeline {
    agent any
    stages {
	 stage('package') {
            steps {
                bat 'mvn clean compile'
            }
        }
         
        //SonarQube
            stage('Scan') {
                steps {
                    withSonarQubeEnv(installationName: 'sq1') {
			    sonarWaitForQualityGate()
                        // bat 'mvn sonar:sonar'
		        }
		    }
		}
    }
}
