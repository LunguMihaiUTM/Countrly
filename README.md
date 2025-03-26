# Countrly - Spring Boot Application (RESTful API)

Countrly is a RESTful API-based web application built with Spring Boot. It is designed for a game where players guess a country based on its shape. If a guess is incorrect, the game provides feedback on the direction and distance to the correct country.

## Technologies Used

- **Java 17** - Main programming language
- **Spring Boot** - Application framework
- **Spring Web** - REST API module
- **Spring Data JPA** - Database access management
- **PostgreSQL** - Database used
- **Maven** - Build and dependency management
- **Spring Security & JWT** - Authentication and authorization
- **Swagger (Springdoc OpenAPI)** - API documentation

## API Endpoints

**Swagger Interface**

<img src="images/Swagger.png" width="50%" />

### Authentication API

| Method | Endpoint       | Description          |
| ------ | -------------- | -------------------- |
| POST   | /auth/register | Registers a new user |
| POST   | /auth/login    | Authenticates a user |

**Example of input for Register API**

<img src="images/auth_input.png" width="40%" />

**Example of output for Register API, The API response with a JWT(Tokeb)**

<img src="images/auth_output.png" width="100%" />

### Country API

| Method | Endpoint                     | Description                                            |
| ------ | ---------------------------- | ------------------------------------------------------ |
| GET    | /country                     | Retrieves all countries                                |
| POST   | /country/complexity/{filter} | Retrieves countries by complexity (Easy, Medium, Hard) |

### Location API

| Method | Endpoint                             | Description                                                          |
| ------ | ------------------------------------ | -------------------------------------------------------------------- |
| GET    | /location/get-info-between-countries | Gets information between two given countries: direction and distance |

**Example of input for Location API. Input 2 countries:**

<img src="images/location_input.png" width="50%" />

**Example of output for Location API. Output: The distance in km and the direction:**

<img src="images/location_output.png" width="50%" />

### Rank API

| Method | Endpoint                          | Description                |
| ------ | --------------------------------- | -------------------------- |
| PATCH  | /country/users/{userId}/increment | Increments rank for a user |
| GET    | /country/all                      | Retrieves all user ranks   |

### User API

| Method | Endpoint       | Description                       |
| ------ | -------------- | --------------------------------- |
| GET    | /user/{userId} | Retrieves user details by ID      |
| GET    | /user/all      | Retrieves all users (for testing) |

### Helper API (New)

| Method | Endpoint                            | Description                                                         |
| ------ | ----------------------------------- | ------------------------------------------------------------------- |
| GET    | /helper/populate-shapes-and-countries | Populates the countries and shapes tables, use after saving country coordinates. |
| GET    | /helper/save-countries             | Saves country coordinates data into the countries-data table.       |

#### 1. Database Population

- **Method:** `populateDataBase()`
- **Description:** Populates the database with country and shape data by matching country names with image files in the `shapes` folder.

#### 2. Save All Countries

- **Method:** `saveAllCountries()`
- **Description:** Fetches a list of countries, retrieves their geographical coordinates using the Nominatim API, and saves the data into the `CountryData` table.

## Authentication & Authorization

The application uses JWT (JSON Web Token) for secure authentication:

- **User Authentication:** Users log in using credentials, receiving a JWT token.
- **Token Validation:** The application validates JWT tokens on each request.
- **Spring Security:** Manages authentication using `UserDetailsService` and `AuthenticationProvider`.

### Swagger API Documentation

The API provides an interactive Swagger UI for exploring and testing endpoints:

- **URL:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI Specification:** Available at `/v3/api-docs`

## Exception Handling

The application handles exceptions globally:

- **BadCredentialsException** - 401 UNAUTHORIZED for incorrect login credentials.
- **AuthException** - 400 BAD REQUEST for authentication failures.
- **GameException** - 400 BAD REQUEST for game-related errors.

## Installation and Running

### 1. Clone the Repository

```bash
git clone https://github.com/LunguMihaiUTM/Countrly.git
cd Countrly
```


### 2. Database Configuration

Ensure PostgreSQL is running and update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/countrly
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

The application will be available at: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## Contribution

To contribute:

1. Fork the repository
2. Create a new branch
3. Submit a pull request

