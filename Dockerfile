FROM openjdk:17-alpine

WORKDIR /app

COPY . /app

EXPOSE 8080

CMD ["java","-jar", "ms-account-wallet-0.0.1-SNAPSHOT.jar"]