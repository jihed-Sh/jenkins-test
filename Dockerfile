FROM openjdk:17
EXPOSE 8080
ADD target/jenkins-test.war jenkins-test.war
ENTRYPOINT ["java","-jar","/jenkins-test.war"]