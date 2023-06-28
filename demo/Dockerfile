# Use the official Gradle image as the base image
FROM gradle:jdk11 as builder

# Set the working directory in the container
WORKDIR /app

COPY . .

# Build the application with Gradle
RUN gradle build

# Use the official OpenJDK image as the base image for the runtime container
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/demo-0.0.1-SNAPSHOT.jar .

# Run the Java application
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]