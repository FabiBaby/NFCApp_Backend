# Use the official Maven image to create a build artifact.
# This is a multi-stage build. It first uses Maven to build the project.
# Updated to use JDK 17 for compatibility with your dependencies.
FROM maven:3.8-openjdk-17 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Now, deploy the artifact in the official Java runtime container
# Updated to use OpenJDK 17 for running the application.
FROM openjdk:17-oracle
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
