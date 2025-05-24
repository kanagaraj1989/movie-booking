# stage 1 : Build
FROM gradle:7.6.4-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle clean bootJar --no-daemon

# stage 2 : Run
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/booking-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

# Uses a lightweight OpenJDK 17 image as the base
#FROM openjdk:17-jdk-slim
# Defines a build-time variable pointing to your JAR file
#ARG JAR_FILE=build/libs/*.jar
# Copies the JAR file into the container and renames it to app.jar
#COPY ${JAR_FILE} app.jar
# Specifies the command to run the application
#ENTRYPOINT ["java","-jar","/app.jar"]