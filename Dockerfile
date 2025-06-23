# Estágio 1: Build da Aplicação com Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Execução da Aplicação em um ambiente Java enxuto
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ControleDeFilmes-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 10000
CMD ["java", "-jar", "app.jar"]
