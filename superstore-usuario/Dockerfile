FROM openjdk:17-slim
EXPOSE 8081

ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} superstore-usuario.jar

ENTRYPOINT ["java","-jar","superstore-usuario.jar"]