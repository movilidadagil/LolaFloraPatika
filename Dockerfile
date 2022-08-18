FROM maven:3.5.4-jdk-8-alpine as maven
COPY pom.xml /pom.xml
COPY . /app
RUN mvn package
WORKDIR /app
