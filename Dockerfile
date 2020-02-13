FROM openjdk:8-jdk-alpine
EXPOSE 8081
COPY ./build/libs/grader-1.0.jar grader-docker.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker", "grader-docker.jar"]
