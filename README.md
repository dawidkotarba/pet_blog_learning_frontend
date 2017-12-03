[![Build Status](https://travis-ci.org/dawidkotarba/blog.svg?branch=master)](https://travis-ci.org/dawidkotarba/blog) [![Coverage Status](https://coveralls.io/repos/github/dawidkotarba/blog/badge.svg?branch=master)](https://coveralls.io/github/dawidkotarba/blog?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/5a2448c90fb24f1d20eb4df0/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5a2448c90fb24f1d20eb4df0)

# Blog app
    An attempt to create a blog;).

#### Tech stack:
- build: Gradle (wrapper available)
- container: Spring MVC (web, data, aop, security, test)
- db: H2
- JPA: Hibernate with Querydsl
- server: embedded Tomcat (Spring Boot app)
- tests: Spock
- doc: SwaggerUI

#### Buld & run:
- sh build_and_run.sh from main folder

#### Pages (provided Tomcat runs on default 8080):
- localhost:8080/login => default login page
- localhost:8080/db => H2 console (url: jdbc:h2:mem:testdb, user: sa, pwd: <blank>)
- localhost:8080/swagger-ui.html => SwaggerUI

Admin credentials: admin : admin
