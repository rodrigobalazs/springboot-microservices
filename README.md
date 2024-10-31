## Spring Boot Microservices
This repository contains an Spring Boot Store Application.

### 🔧 Technology Stack

```
Java 17
Spring Boot 3 ( REST API )
Spring Cloud OpenFeign
Spring Data ( MySQL )
Misc Libraries (  Maven  /  Docker  /  SpringDoc OpenAPI  /  Spring Email / Apache Commons  /  Lombok  )
```

### ⚒️ Getting Started

```bash
# clone the project
git clone https://github.com/rodrigobalazs/springboot-microservices.git;

# start a mysql docker container associated to the Stock App/Microservice
docker run --name stock_db -e MYSQL_DATABASE=stock_db -e MYSQL_USER=<db_user> \
    -e MYSQL_PASSWORD=<db_password> -e MYSQL_ROOT_PASSWORD=<db_password> \
    -p 3306:3306 -d mysql:latest;

# make sure to update stock-microservice/src/main/resources/application.properties with the <db_user> and <db_password> defined
# in the previous point
spring.datasource.username=<db_user>
spring.datasource.password=<db_password>


# start an additional mysql docker container associated to the Order App/Microservice ( note => the port has changed from 3306 to 3307 to avoid collisions between the docker DB containers )
docker run --name order_db -e MYSQL_DATABASE=order_db -e MYSQL_USER=<db_user> \
    -e MYSQL_PASSWORD=<db_password> -e MYSQL_ROOT_PASSWORD=<db_password> \
    -p 3307:3306 -d mysql:latest;

# make sure to update orders-microservice/src/main/resources/application.properties with the <db_user> and <db_password> defined
# in the previous point
spring.datasource.username=<db_user>
spring.datasource.password=<db_password>


# compile all the apps/microservices ..
cd springboot-microservices;
mvn clean install;

# starts the Stock app/microservice ..
cd springboot-microservices/stock-microservice; mvn spring-boot:run;

# starts the Order app/microservice ..
<open a new terminal window> cd springboot-microservices/orders-microservice; mvn spring-boot:run;

# starts the Notifications app/microservice ..
<open a new terminal window> cd springboot-microservices/notifications-microservice; mvn spring-boot:run;
```


### 🔍 API Documentation / Swagger

- the Orders App/Microservice API documentation could be accessed from => http://localhost:8081/swagger-ui/index.html

- the Stock App/Microservice API documentation could be accessed from => http://localhost:8082/swagger-ui/index.html

- the Notifications App/Microservice API documentation could be accessed from => http://localhost:8083/swagger-ui/index.html
