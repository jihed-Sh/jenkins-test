FROM openjdk:17
EXPOSE 8080
ADD target/jenkins-test.jar jenkins-test.jar
ENTRYPOINT ["java","-jar","/jenkins-test-0.0.1-SNAPSHOT.jar"]