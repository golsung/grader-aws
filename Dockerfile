FROM openjdk:8-jdk-alpine
EXPOSE 8081
COPY ./build/libs/kdt-docker-example-0.0.1-SNAPSHOT.jar app-mysql.jar
ENTRYPOINT ["java","-jar","app-mysql.jar"]
