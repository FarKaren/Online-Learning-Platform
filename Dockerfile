FROM openjdk:17-jdk-slim
COPY build/.*jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]