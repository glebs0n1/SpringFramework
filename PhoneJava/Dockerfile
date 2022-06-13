FROM openjdk:17.0.7-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE}
PhoneJava.jar
ENTRYPOINT ["java","-jar","/PhoneJava.jar"]