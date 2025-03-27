# Use the maven:3.8.6-openjdk-17 base image
FROM maven:3.8.3-openjdk-17-slim

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the project files
COPY src ./src

# Package the application
RUN mvn package

# Use a lightweight JDK image to run the application
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the packaged jar file from the build stage
COPY --from=0 /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]