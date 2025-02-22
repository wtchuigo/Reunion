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
    stage('SonarQube Analysis') {
    def mvn = tool 'Default Maven';
    withSonarQubeEnv() {
      bat "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=jenkins-reunion-key -Dsonar.projectName='jenkins-reunion'"
    }
  }
	stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv(installationName: 'sq1') {
			script {
                   	  if (env.BRANCH_NAME == 'master') {
                       		bat 'mvn sonar:sonar'
	                    }
			}
                }
            }
        }
    }
}
