FROM openjdk:14-alpine
MAINTAINER katlasik
COPY target/counter.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
