pipeline {
    agent {
        docker {
            image 'selenium-chrome-maven' // Utilise l'image Docker que tu viens de créer
            args '-v /dev/shm:/dev/shm' // Réduit les problèmes de mémoire avec Chrome
        }
    }

    environment {
        // Si nécessaire, tu peux définir d'autres variables d'environnement
    }

    stages {
        stage('Build & Test') {
            steps {
                // Lancer les tests Maven avec Selenium dans le conteneur
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            // Actions après les tests, comme l'archivage des artefacts ou la gestion des logs
            echo 'Tests terminés'
        }
    }
}
