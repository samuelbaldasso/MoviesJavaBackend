# Backend for Movie Application with JWT Authentication and Authorization by Roles - Java / Spring Boot

This project is a backend for a movie application. It offers features such as user authentication and authorization, using JWT tokens (JSON Web Tokens) and a role system. The backend is built in Java / Spring Boot with the MySQL database via Docker.

## Feature Examples

- **User authentication**: Login and user registration system.
- **Authorization via JWT**: After logging in, users receive a JWT token for subsequent access.
- **Movie Management**: Functionalities for adding, viewing, editing and deleting movies, as well as managing tags associated with movies.
- **Unit tests**: Unit tests of all the application's controllers, repositories and services.

## Technologies Used

- **Java / Spring Boot**: Server execution environment.
- **MySQL**: Efficient database for storing user data and library information.
- **JWT (JSON Web Tokens)**: Used for authenticating and authorizing users based on roles.
- **JUnit4 / Mockito**: Used for unit testing the entire application.

## API documentation

The full API documentation is available on the `/swagger-ui/index.html` endpoint. The documentation is interactive and allows you to test the endpoints directly via the Swagger interface.

## Installation and Use Instructions


1. Clone the repository: `git clone [REPOSITORY_URL]`

2. Navigate to the project folder and install the dependencies: `cd [PROJECT_FOLDER_NAME]` and then run `mvn install` to generate the dependency update and the application JAR (if desired).

3. **Install Docker / MySQL** (Optional):
   - Download Docker from the official website: [Download Docker - Windows, for example](https://docs.docker.com/desktop/install/windows-install/).
   - Follow the installation instructions for your operating system.
   - Create a new database in a Docker container using these commands:
   
   `docker pull mysql`
   
   `docker run -d -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=taskdb --name mysqldb -p 3307:3306 mysql:8.0`
   
5. Start the server: run the application from its main class in your favorite IDE (I recommend Intellij IDEA Community or Ultimate).

6. Go to `http://localhost:8080/swagger-ui/index.html` in your browser to view the API documentation.

---

Developed with ❤️ by Samuel Baldasso
