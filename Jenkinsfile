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
                        bat 'mvn sonar:sonar'
		        }
		    }
		}
    }
}
