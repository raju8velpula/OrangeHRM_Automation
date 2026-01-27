
pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.1'   // Name as configured in Jenkins -> Global Tool Configuration
        jdk 'JDK11'           // Optional if using a configured JDK
    }

    stages {
        stage('Compile') {
            steps {
              sh 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                echo "Testing..."
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploying..."
            }
        }
    }
}
