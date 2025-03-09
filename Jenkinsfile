pipeline {
    agent {
        docker {
            image 'selenium/standalone-chrome:latest'  // Utilisation d'une image Selenium avec Chrome
            args '--entrypoint=""'  // Supprime l'entrypoint pour pouvoir exécuter notre propre commande (mvn test)
        }
    }

    stages {
        // Étape pour installer les dépendances et exécuter les tests
        stage('Install Dependencies and Run Tests') {
            steps {
                // Exécuter mvn test dans le conteneur Docker
                sh 'mvn clean test'  // Vous pouvez également ajouter d'autres options selon votre configuration
            }
        }
    }
    
    post {
        always {
            // Archiver les artefacts (résultats des tests) pour que vous puissiez les consulter après l'exécution du pipeline
            archiveArtifacts artifacts: '**/target/*.xml', allowEmptyArchive: true
        }
    }
}
