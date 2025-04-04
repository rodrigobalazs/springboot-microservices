## Spring Boot Microservices
This repository contains an example of Microservices interaction using Spring Boot / Spring Cloud OpenFeing.<br/><br/>
-The app is an Store Application which contains internally 3 Apps/Microservices ( Orders, Stock and Notifications ).
The goal is to provide some REST APIs to manage Orders and Products.<br/><br/>
-The main entry-point is the POST operation http://localhost:8081/orders/place-order associated to the
````Orders Microservice```` ( the POST method triggers the creation/placement of new Orders ).<br/><br/>
-After call the previous POST operation, the ````Orders Microservice```` will call the ````Stock Microservice```` to
check and decrement the stock availability of the requested Items.<br/><br/>
-Finally, the ````Orders Microservice```` will persist into the database the new Order, and will call  the
````Notifications Microservice```` to dispatch  a 'New Order' notification email to the Customer (in case the SMTP email
server it´s configured)<br/>
#### Rate Limiter and Circuit Breaker ( Microservices Design Patterns ) =>
-As an additional feature the ````Notifications Microservice```` it´s configured to use the ````Rate Limiter```` design
pattern ( allows 1 request POST /send-new-order-notification every 10 seconds ) and the
````Stock Microservice```` it´s configured to use the ````Circuit Breaker```` design pattern ( will be triggered in case an
exception is produced over the endpoint GET /get-products )<br/><br/>

### 🔧 Technology Stack

```
Java 17
Spring Boot 3 ( REST API )
Spring Cloud OpenFeign / Resilence4j
Spring Data ( MySQL )
Misc Libraries (  Maven  /  Docker  /  SpringDoc OpenAPI  /  Spring Email  /  Lombok  )
```

<br/>

### ⚒️ Getting Started

```bash
# clone the project
git clone https://github.com/rodrigobalazs/springboot-microservices.git;

# start a mysql docker container associated to the Stock Microservice
docker run --name stock_db -e MYSQL_DATABASE=stock_db -e MYSQL_USER=<user> \
    -e MYSQL_PASSWORD=<pass> -e MYSQL_ROOT_PASSWORD=<pass> \
    -p 3306:3306 -d mysql:latest;

# make sure to update stock-microservice/src/main/resources/application.properties with
# the MYSQL_USER and MYSQL_PASSWORD defined in the previous point
spring.datasource.username=<user>
spring.datasource.password=<pass>


# start an additional mysql docker container associated to the Order Microservice
# note: the port has changed from 3306 to 3307 to avoid collisions between the docker DBs
docker run --name order_db -e MYSQL_DATABASE=order_db -e MYSQL_USER=<user> \
    -e MYSQL_PASSWORD=<pass> -e MYSQL_ROOT_PASSWORD=<pass> \
    -p 3307:3306 -d mysql:latest;

# make sure to update orders-microservice/src/main/resources/application.properties with
# the MYSQL_USER and MYSQL_PASSWORD defined in the previous point
spring.datasource.username=<user>
spring.datasource.password=<pass>

# <optional step> configure a Gmail account to act as the New Order notification 
# email SMTP server =>
-the Gmail account needs to have 2-Step verification (2FA) enabled
https://myaccount.google.com > search for App Passwords > create a new app "store_app" 
> copy the value of the generated <app_password>
-update notifications-microservice/src/main/resources/application.properties with =>
spring.mail.username=<gmail_account_used_to_generate_the_app>
spring.mail.password=<app_password>

# compile all the apps/microservices ..
cd springboot-microservices;
mvn clean install;

# starts the Stock microservice ..
cd stock-microservice; mvn spring-boot:run;

# starts the Order microservice ..
<open a new terminal window>
cd ../orders-microservice; mvn spring-boot:run;

# starts the Notifications microservice ..
<open a new terminal window>
cd ../notifications-microservice; mvn spring-boot:run;
```
<br/>

### 💡 Orders Microservice ( API Examples )

#### 1. Place a new Order =>

-Place a new Order based on the provided Quote details ( customer email + Items to purchase ).<br/>
This operation will also decrement the quantity in stock of the purchased Item(s) and will send a
New Order email notification ( in case email notification is configured )
```
curl -X POST http://localhost:8081/orders/place-order  \
     -H 'accept: application/json'  \
     -H 'Content-Type: application/json' -d \
'{
    "customerEmail": "somecustomer@emailtest.com",
    "items": [
        {
            "productName": "Rustic Sofa",
            "requestedQuantity": 2
        },
        {
            "productName": "Office Chair Flexi Seat",
            "requestedQuantity": 1
        }
    ]
}';
```

#### 2. Retrieves all Orders =>
```
curl -X GET http://localhost:8081/orders/get-orders -H 'accept: application/json';
```

<br/>

### 🔍 Orders Microservice ( API Documentation / Swagger )

http://localhost:8081/swagger-ui/index.html

![](https://github.com/rodrigobalazs/springboot-microservices/blob/main/orders-microservice/src/main/resources/static/orders_app_swagger.png)

<br/>

### 💡 Stock Microservice ( API Examples )

#### 1. Retrieves all Products =>
```
curl -X GET http://localhost:8082/stock/get-products -H 'accept: application/json';
```

#### 2. Retrieves whether the requested quantity of the Product given as parameter is in Stock or not =>
```
curl -X GET "http://localhost:8082/stock/is-in-stock?productName=Rustic+Sofa&requestedQuantity=100" \
     -H 'accept: application/json';
```

#### 3. Decrease the available quantity of the Product given as parameter =>
```
curl -X PUT "http://localhost:8082/stock/decrease-product-available-quantity?productName=Rustic+Sofa&quantityToDecrease=1" \
     -H 'accept: application/json';
```

<br/>

### 💡 Stock Microservice ( how to test 'Circuit Breaker' )

#### In order to test whether the 'Circuit Breaker' works as expected over the Stock Microservice, modify StockController.java to force a RuntimeException =>
```
public ResponseEntity<List<Product>> getProducts() {
    throw new RuntimeException("forced exception to test whether the Circuit Breaker
         it´s being triggered as expected");
}
```

#### after that, execute this API endpoint =>
```
curl -X GET http://localhost:8082/stock/get-products -H 'accept: application/json';
```
-the IDE console should display a log trace similar to this one =>
```
c.r.n.c.StockController: Circuit Breaker has been triggered for Stock Microservice´s
StockController, executing circuitBreakerFallback() instead getProducts()
```
<br/>

### 🔍 Stock Microservice ( API Documentation / Swagger )

http://localhost:8082/swagger-ui/index.html

![](https://github.com/rodrigobalazs/springboot-microservices/blob/main/stock-microservice/src/main/resources/static/stock_app_swagger.png)

<br/>

### 💡 Notifications Microservice ( API Examples )

#### 1. Sends a new Order Notification Email =>
```
curl -X POST "http://localhost:8083/emailNotifications/send-new-order-notification" \
     -d "customerEmail=somecustomer@emailtest.com" \
     -d "orderId=100" \
     -H 'accept: application/json';
```
<br/>

### 💡 Notifications Microservice ( how to test 'Rate Limiter' )

#### In order to test whether the 'Rate Limiter' works as expected over the Notifications Microservice, execute 2 or more calls of this API endpoint in less than 10 seconds =>
```
curl -X POST "http://localhost:8083/emailNotifications/send-new-order-notification" \
     -d "customerEmail=somecustomer@emailtest.com" \
     -d "orderId=100" \
     -H 'accept: application/json';
```
-the IDE console should display a log trace similar to this one =>
```
c.r.n.c.EmailNotificationController: Rate Limiter has been triggered for Notifications
Microservice´s EmailNotificationController, executing rateLimiterFallback()
instead sendNewOrderNotification().
```
<br/>

### 🔍 Notifications Microservice ( API Documentation / Swagger )

http://localhost:8083/swagger-ui/index.html

![](https://github.com/rodrigobalazs/springboot-microservices/blob/main/notifications-microservice/src/main/resources/static/notifications_app_swagger.png)

