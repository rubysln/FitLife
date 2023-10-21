FROM maven:3.8-openjdk-17-slim AS builder

WORKDIR /app

COPY ./backend/src ./backend/src
COPY ./pom.xml .

RUN mvn clean package

RUN cp target/FitLyfe-0.0.1-SNAPSHOT.jar app.jar

RUN rm -rf target
RUN rm -rf ~/.m2

ENV JAVA_OPTS=""

CMD ["java", "-jar", "app.jar"]