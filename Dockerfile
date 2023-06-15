# syntax=docker/dockerfile:1

FROM openjdk:22-jdk-slim

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN  ./mvnw dependency:resolve
COPY src ./src
#RUN ./mvnw spring-javaformat:apply
#CMD ["./mvnw", "spring-boot:run"]
CMD ["./mvnw", "clean","package","-e"]
SHELL [ "ls -ltr" ]
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} weatherapp-0.0.1-SNAPSHOT.jar
SHELL [ "pwd" ]
ENTRYPOINT ["java","-jar","weatherapp-0.0.1-SNAPSHOT.jar"]
