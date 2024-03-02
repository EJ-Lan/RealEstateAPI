# Real Estate Management System API

## Project Overview

The Real Estate Management System API is a RESTful service using Spring Boot, PostgreSQL, and Docker, with deployment on AWS. This API provides functionalities for managing property listings.

### Key Features

* CRUD operations for property, agent, and client
* Secure endpoints with basic authentication
* Layered Architecture Pattern
* JavaDocs for documentation
* Dockerized application
* Tests for controller, repository, and service classes
* H2 database for tests
* Connection to AWS RDS with PostgreSQL for the main database
* Docker image stored in AWS ECR (Elastic Container Registry)
* Deployment of Dockerized application from AWS ECR to AWS EC2
* CI/CD workflow for automated testing, building, and deployment with GitHub Actions

## Accessing the Deployed API

The deployed API is accessible at `http://54.92.236.158:8080/api`. The API is secured with basic authentication, requiring a username and password for access.

### Authentication Details

- **Username**: `user`
- **Password**: `password`

Please replace the above credentials with the actual ones for your API.

### Endpoints

Here are some of the available endpoints:

- **Agents**:
  - `POST /api/agents` - Creates a new agent.
  - `GET /api/agents/{id}` - Retrieves an agent by ID.
  - `PUT /api/agents/{id}` - Updates an existing agent.
  - `DELETE /api/agents/{id}` - Deletes an agent by ID.
  - `GET /api/agents` - Retrieves all agents.

- **Clients**:
  - `POST /api/clients` - Creates a new client.
  - `GET /api/clients/{id}` - Retrieves a client by ID.
  - `PUT /api/clients/{id}` - Updates an existing client.
  - `DELETE /api/clients/{id}` - Deletes a client by ID.
  - `GET /api/clients` - Retrieves all clients.

- **Properties**:
  - `POST /api/properties` - Creates a new property.
  - `GET /api/properties/{id}` - Retrieves a property by ID.
  - `PUT /api/properties/{id}` - Updates an existing property.
  - `DELETE /api/properties/{id}` - Deletes a property by ID.
  - `GET /api/properties` - Retrieves all properties.

To interact with the API, you can use tools such as `curl`, `wget`, or any API client like Postman. Here's an example using `curl` to fetch all agents:

```bash
curl -u user:password http://54.92.236.158:8080/api/agents
```

Please ensure that your requests are made over HTTPS in a production environment to secure data transmission.

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
git clone https://github.com/EJ-Lan/realestateapi.git
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
docker build -t realestateapi .
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

## Workflow

This project incorporates a CI/CD workflow utilizing GitHub Actions, outlined in `.github/workflows/main.yml`. The workflow automates testing, building, Docker image creation, and deployment processes across multiple environments. Here's a breakdown of what each part of the workflow accomplishes:

### Build, Test, and Push Docker Image

- **Checkout Code**: The workflow starts by checking out the latest code from the main branch.
- **Set up JDK 17**: Prepares the environment to use Java Development Kit version 17, ensuring compatibility with the project's Java version.
- **Build with Maven**: Executes the Maven build lifecycle, including compiling source code and packaging the binary into a JAR file.
- **Run Tests**: Runs unit and integration tests to ensure code changes haven't introduced regressions.
- **Dockerize Application and Push to Docker Hub**: Builds a Docker image for the application and pushes it to Docker Hub, making the image available for deployment.

### ECR and EC2 Deployment

- **Install AWS CLI**: Installs the AWS Command Line Interface, which is essential for interacting with AWS services.
- **Configure AWS CLI**: Sets up the AWS CLI with the necessary credentials and default region, enabling access to AWS resources.
- **Pull Docker Image from Docker Hub**: Pulls the latest Docker image from Docker Hub to ensure the deployment uses the most recent version of the application.
- **Authenticate Docker to ECR**: Logs in to Amazon ECR with Docker, allowing Docker to push and pull images from ECR.
- **Tag Docker Image for ECR**: Tags the Docker image pulled from Docker Hub for pushing to Amazon ECR.
- **Push Docker Image to ECR**: Pushes the tagged Docker image to a repository in Amazon ECR, making it available for deployment on AWS.
- **Create SSH directory and Write Private Key**: Prepares for SSH connections to the EC2 instance by setting up SSH keys.
- **Cleanup Unused Docker Images on EC2**: Connects to the EC2 instance and removes any unused Docker images to free up space.
- **Stop and Remove Existing Docker Containers**: Stops any running containers of the application on the EC2 instance and removes them, ensuring that the new deployment will start fresh.
- **Pull Docker Image on EC2**: Pulls the latest Docker image from Amazon ECR to the EC2 instance.
- **Cleanup Previous Docker Containers**: Removes any previous containers of the application to avoid conflicts with the new deployment.
- **Run Docker Container on EC2**: Starts a new Docker container with the application on the EC2 instance, mapping the container port to the host port as specified.
- **Continuous Cleanup of Old Docker Images**: After deploying the new Docker container, this step cleans up old Docker images on the EC2 instance. It first removes all unused (dangling) images and then removes any images not currently used by running containers. This step ensures optimal disk space usage by retaining only the necessary Docker images.

This CI/CD workflow ensures that every push to the main branch triggers an automated sequence of actions that test, build, and deploy the latest version of the application, facilitating continuous integration and continuous deployment practices.
