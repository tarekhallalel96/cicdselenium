pipeline {
    agent {
        docker {
            image 'maven:3.8.6-openjdk-11' // Image avec Maven et Java
            args '-v /dev/shm:/dev/shm'  // Réduit les erreurs de mémoire avec Chrome
        }
    }
    environment {
        BROWSER = "chrome"
    }
    stages {

        stage('Build & Test') {
            steps {
                sh 'mvn clean test -Dbrowser=${BROWSER}' // Exécute les tests Selenium
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
    }
}
