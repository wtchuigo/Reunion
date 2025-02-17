pipeline {
    agent any
    stages {
	stage('Checkout') {
            steps {
                // Checkout the code from the repository
                checkout scm
            }
        }
	stage('package') {
            steps {
                bat 'mvn clean compile'
            }
        }
	stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv(installationName: 'sq1') {
			script {
                   	  if (env.BRANCH_NAME == 'develop') {
                       		bat 'mvn sonar:sonar'
	                    } 
                }
            }
        }
        stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
    }
}
