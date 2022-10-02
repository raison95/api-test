pipeline {
    agent any
    triggers {
        githubPush()
    }
    environment{
       SONAR_SERVER = 'sonarqube-server'
       def scannerHome = tool 'sonarqube-scanner'
    }
    stages {
        stage('code checkout') {
             steps {
                 checkout scm
             }
        }
        stage('build') {
            steps {
//                 sh './gradlew clean build'
                echo 'build success'
            }
        }
        stage('sonarqube analysis') {
            steps {
//                 withSonarQubeEnv() {
//                     sh "./gradlew clean test sonarqube"
//                 }
                withSonarQubeEnv(SONAR_SERVER) {
                    sh "${scannerHome}/bin/sonar-scanner \
                        -D sonar.projectKey=api-test \
                        -D sonar.scm.disabled=True \
                        -D sonar.exclusions=**/*.java"
                }
            }
        }
//         stage("Quality Gate") {
//             steps {
//                 timeout(time: 1, unit: 'HOURS') {
//                     waitForQualityGate abortPipeline: true
//                 }
//             }
//         }
    }
}