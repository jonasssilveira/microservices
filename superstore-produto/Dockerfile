FROM openjdk:17-slim

EXPOSE 8080

ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} superstoreprodutolication.jar

ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","superstoreprodutolication.jar"]