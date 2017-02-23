# Spring Boot Testsuite

## REST Service with Apache Tomcat

* Component : [Spring Boot Starter Web](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-starters/spring-boot-starter-web), [Spring Boot Starter Web Tomcat](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-starters/spring-boot-starter-tomcat)
* Framework : Spring Web, Spring Web MVC
* Container : Apache Tomcat

**Description**

A REST endpoint is exposed by Spring Boot using as Java Web Container Apache Tomcat. The REST endpoint `/greeting` returns a message to say hello world with an id which is increment
for each call.

```
http http://localhost:8080/greeting
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Date: Thu, 23 Feb 2017 08:11:08 GMT
Transfer-Encoding: chunked

{
    "content": "Hello, World!",
    "id": 1
}
```

## Access your data using a JDBC Driver

* Component : [Spring Boot Starter Data JPA](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-starters/spring-boot-starter-data-jpa)
* Framework : Hibernate

**Description**

The Test use case should be able to boot strap a Database in memory as H2, create some records using an import.sql file, the datasource to access the database should be defined. The use case will include 2 entities; a [CD Catalog](https://github.com/redhat-microservices/lab_swarm-openshift/blob/master/solution/cdservice/src/main/java/org/cdservice/model/Catalog.java) and TO BE DEFINED.
A Service class should be defined & contain CRUD methods. No transaction support is required.

References:

- [Spring How To guide](https://spring.io/guides/gs/accessing-data-jpa/)
- [Spring Boot doc](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html)
- [DataSource configuration](http://blog.anthavio.net/2016/03/fun-with-spring-boot-auto-configuration.html)
