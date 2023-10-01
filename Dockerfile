FROM eclipse-temurin:17-jdk-alpine
VOLUME /app
COPY target/FitLyfe-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]