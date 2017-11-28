# Spring Playground
    This is just a simple example of Spring-based app. Dev still in progress.

[![Build Status](https://travis-ci.org/dawidkotarba/PlaygroundSpring.svg?branch=master)](https://travis-ci.org/dawidkotarba/PlaygroundSpring) [![Coverage Status](https://coveralls.io/repos/dawidkotarba/Playground/badge.svg?branch=master&service=github)](https://coveralls.io/github/dawidkotarba/Playground?branch=master)

#### Tech stack:
- build: Gradle (wrapper available)
- container: Spring MVC (web, data, aop, security, test)
- db: H2
- JPA: Hibernate with Querydsl
- server: embedded Tomcat (Spring Boot app)
- tests: TestNG, Mockito, Hamcrest, JBehave
- doc: SwaggerUI

#### Buld & run:
- sh build_and_run.sh from main folder

All logs will be stored in "/opt/apps/webdata/weblogs" folder.

#### Pages (provided Tomcat runs on default 8080):
- localhost:8080/login => default login page
- localhost:8080/db => H2 console (url: jdbc:h2:mem:testdb, user: sa, pwd: <blank>)
- localhost:8080/swagger-ui.html => SwaggerUI

Admin credentials: admin : admin

#### Links:
- Travis: https://travis-ci.org/dawidkotarba/Playground
- Coveralls: https://coveralls.io/github/dawidkotarba/Playground?branch=master

### Docker:
- Run: docker run dawidkotarba/playground_spring
- Build: docker build -t dawidkotarba/playground_spring .
