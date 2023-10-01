FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

COPY ./backend/src ./src
COPY ./pom.xml .

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/FitLyfe-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]