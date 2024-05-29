FROM maven:latest AS installer
WORKDIR /app
COPY pom.xml pom.xml
RUN mvn clean compile

FROM installer AS builder
WORKDIR /app
COPY . .
RUN mvn package

FROM openjdk:21-jdk AS runner
ENV RABBITMQ_HOST=docker.for.mac.localhost

WORKDIR /app
COPY --from=builder /app/target/*.jar jarjar.jar
CMD  java -jar jarjar.jar