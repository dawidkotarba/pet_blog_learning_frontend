[![Build Status](https://travis-ci.org/dawidkotarba/blog.svg?branch=master)](https://travis-ci.org/dawidkotarba/blog) [![Coverage Status](https://coveralls.io/repos/github/dawidkotarba/blog/badge.svg?branch=master)](https://coveralls.io/github/dawidkotarba/blog?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/5a2448c90fb24f1d20eb4df0/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5a2448c90fb24f1d20eb4df0)
[![codebeat badge](https://codebeat.co/badges/f338bb0c-2160-4829-b2b5-3f202053f3fd)](https://codebeat.co/projects/github-com-dawidkotarba-blog-master)
[![Sputnik](https://sputnik.ci/conf/badge)](https://sputnik.ci/app#/builds/dawidkotarba/blog)

# Blog app
    An attempt to create a blog;)

#### Tech stack:
##### General:
- gradle
- H2 DB
- Liquibase
- JPA + Hibernate + Querydsl
- Ehcache
- Spring Boot (with Spring AOP, security, MVC for REST, HATEOAS, test)
- Spock
- Angular 4 with PrimeNG

##### Tools:
- Docker
- Lombok
- Checkstyle
- Vavr (todo)
- Swagger

##### CI pipeline:
- Travis
- Sputnik
- Snyk
- Codebeat
- Coveralls
- Versioneye
- Docker Hub

#### Buld & run:
- sh build_and_run.sh from main folder

#### Docker build:
- Pull image from docker hub: docker pull dawidkotarba/blog

#### Pages (provided Tomcat runs on default 8080):
- localhost:8080/login => default login page
- localhost:8080/db => H2 console (url: jdbc:h2:mem:testdb, user: sa, pwd: <blank>)
- localhost:8080/swagger-ui.html => SwaggerUI

Admin credentials: admin : admin
