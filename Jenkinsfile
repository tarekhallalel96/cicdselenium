pipeline {
    agent {
        docker {
            image 'maven:3.8.6-openjdk-11'
        }
    }

    environment {
        SELENIUM_GRID_URL = "http://192.168.1.96:4444"
    }

    stages {

        stage('Install Dependencies') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh 'mvn test -Dselenium.grid.url=$SELENIUM_GRID_URL'
            }
        }


 
        //  stage('Generate Allure Report') {
        //     steps {
        //         sh 'mvn allure:report'
        //     }
        // }

        // stage('Publish Allure Report') {
        //     steps {
        //         allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        //     }
        // }

        // stage('Publish Test Reports') {
        //     steps {
        //         junit '**/target/surefire-reports/*.xml'
        //     }
        // }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/surefire-reports/', fingerprint: true
        }
        failure {
            echo "❌ Les tests Selenium ont échoué."
        }
    }
    // post {
    //     always {
    //         archiveArtifacts artifacts: '**/target/allure-results/', fingerprint: true
    //     }
    //     failure {
    //         echo "❌ Les tests Selenium ont échoué."
    //     }
    // }
    
}