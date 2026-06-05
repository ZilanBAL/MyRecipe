FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /workspace

COPY pom.xml .
run mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jre

RUN apt-get update && apt-get install -y curl

WORKDIR /app

COPY --from=build /workspace/target/*jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]










