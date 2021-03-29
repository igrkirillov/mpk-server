FROM openjdk:11-jre-slim

LABEL maintainer="Kirillova Darya"
LABEL description="MPK Server"

ENV HTTP_PORT 8080

EXPOSE ${HTTP_PORT}

USER root

RUN ln -sf /usr/share/zoneinfo/Europe/Moscow /etc/localtime && echo "Europe/Moscow" > /etc/timezone

RUN mkdir -p /app
COPY target/mpk-server-spring-boot.jar /app/

RUN mkdir -p /app/logs

WORKDIR /app

CMD [ "java", \
    "-Dspring.profiles.active=prod", \
    "-Xms32m", \
    "-Xmx128m", \
    "-XX:ErrorFile=/logs/java_error%p.log", \
    "-DlogPath=/app/logs", \
    "-Dfile.encoding=UTF8", \
    "-DhttpPort=${HTTP_PORT}", \
    "-jar", "/app/mpk-server-spring-boot.jar"]