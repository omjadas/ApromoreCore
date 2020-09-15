FROM maven:3-jdk-8-alpine as build

WORKDIR /app

COPY . .

RUN mvn -B -T 1C package -DskipTests

FROM rennergabor/virgo-tomcat-server:3.6.4
