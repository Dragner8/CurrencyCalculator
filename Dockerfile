FROM maven:3.6-jdk-8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM  openjdk:8-jdk-alpine
COPY wait-for-mysql.sh .
RUN chmod +x wait-for-mysql.sh
RUN apk update && apk add bash
RUN apk add --update mysql mysql-client && apk add --no-cache mariadb-dev
COPY --from=build /app/target/CurrencyCalculator-0.0.1-SNAPSHOT.jar /app/CurrencyCalculator-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","/app/CurrencyCalculator-0.0.1-SNAPSHOT.jar"]