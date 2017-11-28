FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD build/libs/blog-0.1.0.jar blog.jar
RUN sh -c 'touch /blog.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=DEV","-Djava.security.egd=file:/dev/./urandom","-jar","/blog.jar"]