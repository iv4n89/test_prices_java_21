FROM maven:3.9.11-eclipse-temurin-21-noble AS build
WORKDIR /app
COPY pom.xml .
COPY domain /app/domain
COPY application /app/application
COPY infrastructure /app/infrastructure
COPY presentation /app/presentation
RUN mvn -B package -DskipTests

FROM eclipse-temurin:21.0.8_9-jre-ubi10-minimal
COPY --from=build /app/presentation/target/Price-Service.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]