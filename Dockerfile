FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:24-slim-bullseye

WORKDIR /app

COPY --from=build /target/reunion-0.0.2-SNAPSHOT.jar /app/reunion-services.jar

ENTRYPOINT ["java", "-jar", "/app/reunion-services.jar"]