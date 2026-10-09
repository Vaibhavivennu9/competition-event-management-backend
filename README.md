# Competition and Event Management Backend

A RESTful backend application developed using Java and Spring Boot to manage competitions, activities, participants, teams, registrations, and event-day participation.

## 🚀 Features

- Manage competitions and their activities.
- Create and retrieve participant records.
- Create and manage teams and team members.
- Support individual and team-based registrations.
- Validate registration types and prevent duplicate registrations.
- Record event-day participation and check-in.
- Handle exceptions using custom exceptions and centralized exception handling.
- Test REST APIs using Postman.

## 🛠️ Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **ORM:** Hibernate
- **Persistence:** Spring Data JPA
- **Database:** MySQL
- **Build Tool:** Maven
- **API Testing:** Postman
- **Version Control:** Git and GitHub

## 🏗️ Project Architecture

The application follows a layered architecture:

- **Controller Layer:** Handles HTTP requests and responses.
- **Service Layer:** Implements business logic and validation.
- **Repository Layer:** Communicates with the database through Spring Data JPA.
- **Entity Layer:** Defines database entities and relationships.
- **DTO Layer:** Defines request and response objects for selected modules.
- **Exception Layer:** Handles application errors using custom exceptions and a global exception handler.

### Request Flow

`Client / Postman → Controller → Service → Repository → Hibernate / JPA → MySQL`

## 📦 Main Modules

| Module | Purpose |
|---|---|
| Competition | Manages competition details |
| Activity | Manages activities associated with competitions |
| Participant | Stores individual participant information |
| Team | Represents teams participating in competitions |
| TeamMember | Associates participants with teams |
| Registration | Records individual or team registrations for activities |
| Participation | Records event-day check-in or participation |

## 🔄 Registration Workflow

1. The client submits a registration request.
2. The controller forwards the request to the service layer.
3. The service validates the registration type.
4. Individual registration requires a participant, while team registration requires a team.
5. The service checks for duplicate registrations for the same activity.
6. Valid registrations are saved to the database through the repository layer.
7. Event-day participation is tracked separately from registration.

## ⚠️ Exception Handling

The project includes custom exceptions such as `ResourceNotFoundException` and `AlreadyParticipatedException`, along with centralized exception handling.

These components provide structured error responses for missing resources, duplicate participation, and invalid operations, according to the configured exception mappings.

## ⚙️ Setup and Installation

### Prerequisites

- Java JDK compatible with the project configuration
- MySQL
- Maven or the included Maven Wrapper

### 1. Clone the repository

```bash
git clone https://github.com/Vaibhavivennu9/competition-event-management-backend.git
cd competition-event-management-backend
```

### 2. Configure MySQL

Create a database in MySQL and configure `src/main/resources/application.properties`.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/competition_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Set `DB_USERNAME` and `DB_PASSWORD` in your environment before running the application. Ensure that the MySQL JDBC driver is configured in `pom.xml`.

**Security note:** Never commit actual database passwords, tokens, or other credentials to GitHub.

### 3. Run the application

On Windows, use the Maven Wrapper if available:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, if Maven is installed:

```bash
mvn spring-boot:run
```

The default Spring Boot port is `8080`, unless configured otherwise.

## 🧪 API Testing

Postman can be used to test the REST endpoints.

Recommended test scenarios:

- Create and retrieve competitions, activities, participants, and teams.
- Test valid and invalid registration requests.
- Verify duplicate registration validation.
- Test participation check-in and repeated check-in.
- Test nonexistent resource IDs.
- Verify HTTP status codes, response bodies, and database updates.

## 🔮 Future Enhancements

- Implement authentication and authorization using Spring Security.
- Develop a frontend application.
- Apply DTOs consistently across all modules.
- Add comprehensive automated tests and request validation.
- Strengthen participation check-in validations.
- Introduce managed database migrations and production configuration.

## 👨‍💻 Author

Developed as a backend project to practice REST API development, layered architecture, database relationships, business logic, exception handling, and API testing.
