# Use a base image with OpenJDK installed
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/realestateapi-0.0.1-SNAPSHOT.jar /app/realestateapi.jar

# Specify the command to run your application
CMD ["java", "-jar", "realestateapi.jar"]

