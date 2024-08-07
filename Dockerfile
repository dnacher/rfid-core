FROM openjdk:11-jre-slim

COPY target/rfid-core-1.0.0-SNAPSHOT.jar /app/rfid-core-1.0.0-SNAPSHOT.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/app/rfid-core-1.0.0-SNAPSHOT.jar"]
