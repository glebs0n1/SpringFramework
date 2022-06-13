FROM openjdk:11.0.7-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} medus.jar
ENTRYPOINT ["java","-jar","/medus.jar"]