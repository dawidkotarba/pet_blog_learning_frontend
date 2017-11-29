#!/bin/bash
gradle build
java -jar -Dspring.profiles.active=DEV build/libs/blog-0.1.0.jar
