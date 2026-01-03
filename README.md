# H2 Database Configuration for Spring Boot 4.0.0+

This guide documents the steps to configure an in-memory H2 database and enable the H2 console in a Spring Boot 4.x application.

## Dependencies

For Spring Boot 4.0.0 and later, the H2 console auto-configuration has been moved to a separate module. You need to add the following dependencies to your `pom.xml`:

1.  **Spring Boot Data JPA**: Required for database auto-configuration.
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```

2.  **H2 Database**: The database itself.
    ```xml
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```

3.  **Spring Boot H2 Console** (Vital for Spring Boot 4.x+):
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-h2console</artifactId>
    </dependency>
    ```

## Configuration

Add the following to your `src/main/resources/application.properties` to enable the console:

```properties
# H2 Console Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

*Note: Explicit `spring.datasource.*` configuration is optional. Spring Boot will default to an in-memory database at `jdbc:h2:mem:testdb` with username `sa` and an empty password if not specified.*

## Usage

1.  **Build the Project**:
    It is recommended to run a clean build to ensure all dependencies are downloaded correctly.
    ```bash
    ./mvnw clean install
    ```

2.  **Start the Application**:
    ```bash
    ./mvnw spring-boot:run
    ```

3.  **Access the H2 Console**:
    *   **Browser URL**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    *   *This opens the web interface for the database.*

4.  **Database Connection Details** (for the H2 Console login screen):
    *   **Driver Class**: `org.h2.Driver`
    *   **JDBC URL**: `jdbc:h2:mem:testdb`
    *   **User Name**: `sa`
    *   **Password**: *(Leave the password field blank)*

## Troubleshooting

*   **Port 8080 already in use**: If the server fails to start with "Address already in use", kill existing Java processes:
    *   *Windows*: `taskkill /F /IM java.exe`
    *   *Linux/Mac*: `pkill -f java`



This Repo is Under Development Process only !

Feel Free to Contribute
