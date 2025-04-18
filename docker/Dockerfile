FROM maven:3.9-eclipse-temurin-17-alpine as build
WORKDIR /app

# Copia solo il POM per sfruttare la cache di Maven
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia il codice sorgente
COPY application ./application

# Compila il progetto
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]