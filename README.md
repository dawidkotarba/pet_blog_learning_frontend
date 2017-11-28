# Blog app
    An attempt to create a blog;).

#### Tech stack:
- build: Gradle (wrapper available)
- container: Spring MVC (web, data, aop, security, test)
- db: H2
- JPA: Hibernate with Querydsl
- server: embedded Tomcat (Spring Boot app)
- tests: TestNG, Mockito, Hamcrest, JBehave -> to be replaced by spock
- doc: SwaggerUI

#### Buld & run:
- sh build_and_run.sh from main folder

#### Pages (provided Tomcat runs on default 8080):
- localhost:8080/login => default login page
- localhost:8080/db => H2 console (url: jdbc:h2:mem:testdb, user: sa, pwd: <blank>)
- localhost:8080/swagger-ui.html => SwaggerUI

Admin credentials: admin : admin