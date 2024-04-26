pipeline
{
    agent any
    tools
    {
        maven 'maven_3_9_6'
    }
    environment
    {
        SCANNER_HOME= tool 'sonar-scanner'
    }
    stages
    {
        stage('Checkout project from git')
        {
            steps
            {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jihed-Sh/jenkins-test']])
            }
        }
        stage('Code Compile')
        {
            steps
            {
                sh 'mvn clean compile'
            }
        }
        stage('SonarCube Analysis')
        {
            steps
            {
                withSonarQubeEnv('sonar-scanner')
                {
                    sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=jenkins-test \
                     -Dsonar.java.binaries=.\
                       -Dsonar.projectKey=jenkins-test'''
//                    sh 'mvn sonar:sonar'
                }
            }
        }
//         stage('Trivy Scan')
//         {
//             steps
//                 {
//                     sh "trivy fs --security-checks vuln,config /root/.jenkins/workspace/jenkins-test"
//                 }
//         }
        stage('Build')
        {
            steps
                {
                    sh "mvn clean install"
                }
        }
        stage('Build docker image')
        {
            steps
            {
                script
                {
                    sh 'docker build -t  theonlyjihed/jenkins-test .'
                }
            }
        }
        stage('Push Docker Image to Docker Hub')
        {
            steps
            {
                script
                 {
                    withCredentials([usernamePassword(credentialsId: 'docker-cred', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')])
                    {
                        sh "docker login -u $DOCKERHUB_USERNAME -p \$DOCKERHUB_PASSWORD"
                        // sh "docker tag jihed/jenkins-test jihed/jenkins-test:latest"
                        sh "docker push theonlyjihed/jenkins-test:latest"
                    }
                }
            }
        }
        stage('Deploying to Kubernetes')
        {
              steps
              {
//                 script
//                 {
//                   kubernetesDeploy(configs: "deployment.yaml",
//                                                  "service.yaml")
//                 }
                script
                 {
                      sh "sed -i 's,TEST_IMAGE_NAME,theonlyjihed/jenkins-test,' deployment/deployment.yaml"
                      sh "cat deployment.yaml"
                      sh "kubectl --kubeconfig=/home/ec2-user/config get pods"
                      sh "kubectl --kubeconfig=/home/ec2-user/config apply -f deployment/deployment.yaml"
                }
              }
        }
    }
}