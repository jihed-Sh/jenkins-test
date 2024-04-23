pipeline {
    agent any
    tools{
        maven 'maven_3_9_6'
    }
    stages{
        stage('checkout project from git'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jihed-Sh/jenkins-test']])
            }
        }
                stage('Build Maven'){
            steps{

                sh 'mvn clean install'
            }
        }
         stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t  theonlyjihed/jenkins-test .'
                }
            }
        }
      stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-cred', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        sh "docker login -u $DOCKERHUB_USERNAME -p \$DOCKERHUB_PASSWORD"
                        // sh "docker tag jihed/jenkins-test jihed/jenkins-test:latest"
                        sh "docker push theonlyjihed/jenkins-test:latest"
                    }
                }
            }
      }
      stage('Deploy to Tomcat') {
                  steps {
                      script {
                          sh '''
                              WAR_FILE=$(ls target/*.war)
                              curl -v -u '' -T $WAR_FILE http://localhost:5050/manager/text/deploy?path=/myapp
                          '''
                      }
                  }
              }


    }
}