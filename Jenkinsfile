pipeline {
    agent {
        docker {
            image 'maven:3.8.6-openjdk-11' // Utilisation de Maven + Java
            args '-v /dev/shm:/dev/shm' 
        }
    }
    environment {
        BROWSER = "chrome"
        SELENIUM_URL = "http://localhost:4444"  // URL du WebDriver dans le conteneur Selenium
    }
    stages {
        stage('Start Selenium WebDriver') {
            steps {
                sh 'docker run -d -p 4444:4444 --name selenium-webdriver selenium/standalone-chrome' 
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean test -Dbrowser=${BROWSER} -Dwebdriver.remote.url=${SELENIUM_URL}' 
            }
        }
    }
    post {
        always {
            sh 'docker stop selenium-webdriver && docker rm selenium-webdriver' // Arrête et supprime Selenium après exécution
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
    }
}
