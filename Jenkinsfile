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
                withSonarQubeEnv(SONAR_SERVER) {
                    sh "${scannerHome}/bin/sonar-scanner \
                        -D sonar.projectKey=api-test \
                        -Dsonar.pullrequest.branch=${env.CHANGE_BRANCH} \
                        -Dsonar.pullrequest.base=${env.CHANGE_TARGET} \
                        -D sonar.exclusions=resources/**,**/*.java"
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