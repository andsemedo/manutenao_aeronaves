#baixar a imagem do maven com jdk 17
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/manutencao_aeronave-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","app.jar"]