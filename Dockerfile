FROM openjdk:11.0.11
ADD target/spring-e-commerce-app-docker.jar spring-e-commerce-app-docker.jar
ENTRYPOINT ["java", "-jar","/spring-e-commerce-app-docker.jar"]