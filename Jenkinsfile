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
        stage('build') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('sonarqube analysis for master branch') {
            steps {
                withSonarQubeEnv(SONAR_SERVER) {
                    sh "${scannerHome}/bin/sonar-scanner \
                        -D sonar.projectKey=api-test \
                        -D sonar.sourceEncoding=UTF-8 \
                        -D sonar.language=java \
                        -D sonar.sources=src/main/java \
                        -D sonar.test=src/test/java \
                        -D sonar.java.binaries=build/classes"
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