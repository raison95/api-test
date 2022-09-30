pipeline {
    agent any
    triggers {
        githubPullRequest()
    }
    environment{
       SONAR_SERVER = 'sonarqube-server'
       SONAR_SCANNER = 'sonarqube-scanner'
    }
    stages {
        stage('code checkout') {
             steps {
                 checkout scm
             }
        }
        stage('build') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('sonarqube analysis') {
            def scannerHome = tool SONAR_SCANNER;
            withSonarQubeEnv(SONAR_SERVER) {
                sh "${scannerHome}/bin/sonar-scanner \
                    -D sonar.projectKey=api-test \
                    -D sonar.exclusions=resources/**,**/*.java"
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}