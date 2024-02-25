# Real Estate Management System API

## Project Overview

The Real Estate Management System API is a RESTful service using Spring Boot, PostgreSQL, and Docker, with deployment on AWS. This API provides functionalities for managing property listings and user authentication.

### Key Features

* CRUD operations for property listings
* Basic property search with filters
* Agent and Client specific features
* Secure endpoints with basic authentication

## Getting Started

### Prerequisites

* JDK 11 or newer
* Maven 3.6+
* Docker
* PostgreSQL installed locally or access to a PostgreSQL database
* an AWS account for deployment

### Local Setup

1. Clone the repository
```bash
git clone https://github.com/<your-username>/realestateapi.git
```
2. Navigate to the project directory
```bash
cd realestateapi
```
3. Cofigure `application.properties`
* Set your local PostgreSQL database connection details in `src/main/resources/application.properties`.

### Running the Application

1. Build the project with Maven
```bash
mvn clean install
```
2. Run the spring boot application
```bash
mvn spring-boot:run
```
The API will be available at `http://localhost:8080`.

## Dockerization

1. Build the Docker Image
```bash
docker build -t realestateapi .`
```
2. Run the application using Docker
```bash
docker run -p 8080:8080 realestateapi
```

## Testing

Execute unit and integration tests by running:
```bash
mvn test
```

## CI/CD with GitHub Actions

This project includes a GitHub Actions workflow for automated testing, building, and pushing the Docker image to AWS ECR. The workflow is defined in `.github/workflows/main.yml`.

## Deployment to AWS

1. **AWS RDS**: Set up a PostgreSQL instance on RDS and update your application's production configuration to use this database.
2. **AWS ECR**: Push your Docker image to an Elastic Container Registry repository.
3. **AWS EC2**: Application should be deployed to an EC2 instance
