## Task creation example using  Spring Boot "Microservice" Example Project

This uses Java / Maven / Spring Boot (version 1.5.6) application which is a starter for creating a microservice complete with built-in health check, metrics and much more.

## How to Run 

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean install -U```
* Once successfully built, you can run the service by:
```
java -jar -Dspring.profiles.active=test target/spring-boot-rest-example-0.4.0.war
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this
```
2017-08-30 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
2017-08-30 17:31:23.097  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```

### Create a user

```
POST /taskmanager/v1/user
Accept: application/json
Content-Type: application/json

{
  "address": "string",
  "emailId": "abc@domain.com",
  "phone": "(999)-999-9999"
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8090/taskmanager/v1/user/1
```

### Create a task for a user

```
POST /taskmanager/v1/tasks
Accept: application/json
Content-Type: application/json

{
  "dueTime": "2018-02-01_01:00",
  "id": 0,
  "reminderTime": "2018-02-01_01:00",
  "taskObjectStoreURL": "string",
  "user_id": 1
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8090/taskmanager/v1/tasks/1
```

### Retrieve the list of tasks by userId

```
GET /taskmanager/v1/user/{id}
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)

{
  "emailId": "abc@domain.com",
  "phone": "(999)-999-9999",
  "address": "string",
  "tasks": [
    {
      "id": 1,
      "user_id": 1,
      "taskObjectStoreURL": "string",
      "dueTime": "2018-02-01_01:00",
      "reminderTime": "2018-02-01_01:00",
      "taskId": 1
    }
  ],
  "userId": 1
}

```


### To view Swagger 2 API docs

Run the server and browse to localhost:8090/swagger-ui.html

# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:

**/metrics** Shows “metrics” information for the current application.

**/health** Shows application health information.

**/info** Displays arbitrary application info.

**/configprops** Displays a collated list of all @ConfigurationProperties.

**/mappings** Displays a collated list of all @RequestMapping paths.

**/beans** Displays a complete list of all the Spring Beans in your application.

**/env** Exposes properties from Spring’s ConfigurableEnvironment.

**/trace** Displays trace information (by default the last few HTTP requests).

### To view your H2 in-memory datbase

The 'test' profile runs on H2 in-memory database. To view and query the database you can browse to http://localhost:8090/h2-console. Default username is 'sa' with a blank password. Make sure you disable this in your production profiles. For more, see https://goo.gl/U8m62X
