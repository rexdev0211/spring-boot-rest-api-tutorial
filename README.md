# Test RESTful API with Spring Boot, Mysql, JPA and Hibernate 

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/rexdev0211/spring-boot-rest-api-tutorial.git
```

**2. Create Mysql database**
```bash
create database rest_api_db
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/spring-boot-rest-api-tutorial-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/v1/articles?page=0&size=10
    
    POST /api/v1/articles
    {
        "title": "Test title",
        "author": "author",
        "content": "Test content",
        "publishDate": "2021-12-30"
    }

    GET /api/v1/articles?startDate=2021-12-21
