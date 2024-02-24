# Use a base image with Maven to build the application
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Use a base image with Java 17 installed for the runtime environment
FROM openjdk:17-alpine AS runtime
WORKDIR /app
COPY --from=build /app/target/realestateapi-0.0.1-SNAPSHOT.jar /app/realestateapi.jar
EXPOSE 8080
CMD ["java", "-jar", "realestateapi.jar"]