FROM debian:buster-slim
MAINTAINER MM

RUN apt-get update
RUN apt-get install -y openjdk-11-jre curl maven

ADD target/server-1.0-jar-with-dependencies.jar /app/
ADD quoteDB.db /app/

WORKDIR /app
EXPOSE 5000
VOLUME /data
ENTRYPOINT ["java", "-jar", "server-1.0-jar-with-dependencies.jar"]