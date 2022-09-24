FROM openjdk:11-jre-slim

EXPOSE 8761

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eurekaapplication.jar

ENTRYPOINT ["java","-jar","eurekaapplication.jar"]