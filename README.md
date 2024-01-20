# Project Notes

## Project Overview

This backend application is developed using Spring Boot and Java 17. It serves as a simple API for managing accounts and cards.
The application utilizes postgreSQL data storage with mock data generation for testing purposes.

## Project Structure

The project is organized into the following key components:

- **controllers:** Contains REST controllers for handling HTTP requests.
- **entities:** Entity classes representing the data model.
- **repositories:** Data repositories for database interactions.
- **service:** Service classes for business logic implementation.
- **config:** Configuration classes, including Swagger and Actuator configurations.


## Java Version

The application is developed using Java 17, taking advantage of the latest features and improvements in the Java language.

## Actuator Integration

Spring Boot Actuator is integrated to provide operational information about the application. The following endpoints are exposed:

- `/actuator/health`: Health check endpoint.
- `/actuator/info`: Custom information about the application.

## Docker Integration

The application is containerized using Docker for easy deployment and portability. The Dockerfile contains instructions for building the Docker image.

## Production Considerations

In a production environment, the application should be configured with appropriate database settings, logging levels, and security measures. Load balancing and scaling considerations should also be taken into account for handling increased traffic.

## Testing Approach

The application is tested using JUnit for unit tests and Mockito for mocking dependencies. Integration tests ensure the proper functioning of key components.
## Swagger API Documentation

Swagger is integrated into the application to provide interactive API documentation.

### Swagger UI

The Swagger UI is accessible at the following URL:

- **Local Environment:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Swagger Open API Specification

The Swagger Open API Specification (OAS) file can be accessed directly:

- **Open API Specification (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)


### Prerequisites

- Java 17 installed
- Docker installed

### Build and Run

1. Clone the repository: `git clone https://github.com/AlbertNjaneKimani/account-wallet.git`
2. Build the application: `./mvnw clean install`
3. Build Docker image: `docker build -t account-wallet-service .`
4. Run Docker container: `docker run -p 8080:8080 account-wallet-service`

## Security Considerations

Sensitive information such as database credentials should be handled securely. The application should be configured with HTTPS in a production environment to ensure secure communication.

## Future Improvements

- Implement user authentication and authorization.
- Enhance error handling and logging.
- Implement a more sophisticated data storage solution for scalability.

## Overall Architecture

The application follows a simple three-tier architecture with controllers, services, and repositories. RESTful principles are adhered to for API design.

