FROM openjdk:20-jdk-slim

WORKDIR /app

COPY target/auth-0.0.1.jar /app/pro_task_auth.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/pro_task_auth.jar"]