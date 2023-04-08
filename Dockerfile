FROM amazoncorretto:11-alpine-jdk
MAINTAINER MauriRios
COPY target/challenge-0.0.1-SNAPSHOT.jar  challenge-app.jar
ENTRYPOINT ["java","-jar","/challenge-app.jar"]