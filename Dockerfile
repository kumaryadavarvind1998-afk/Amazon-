FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/com.amazon-0.0.1-SNAPSHOT.jar app.jar

Expose 8089

ENTRYPOINT ["java", "-jar", "app.jar"]
