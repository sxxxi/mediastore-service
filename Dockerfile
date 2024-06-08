FROM maven:latest AS installer
WORKDIR /app
COPY pom.xml pom.xml
RUN mvn clean compile

FROM installer AS builder
WORKDIR /app
COPY . .
RUN mvn package

FROM openjdk:21-jdk AS runner
WORKDIR /app
COPY --from=builder /app/target/*.jar jarjar.jar
ENTRYPOINT [ "java", "-jar", "jarjar.jar" ]