FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/examen_1-0.0.1.jar
COPY ${JAR_FILE} app_examen_1.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app_examen_1.jar"]