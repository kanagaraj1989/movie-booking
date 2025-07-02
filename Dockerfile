# stage 1 : Build
FROM gradle:7.6.4-jdk17 AS build
WORKDIR /home/gradle/project

# Copy only build scripts first
COPY --chown=gradle:gradle build.gradle settings.gradle gradle/ ./

# Download dependencies separately — this layer gets reused until you change build.gradle!
RUN gradle dependencies --no-daemon || return 0

# Copy the rest of your app source
COPY --chown=gradle:gradle . .
# Now build the final JAR
RUN gradle clean bootJar --no-daemon

# stage 2 : Run
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/booking-service-0.0.1-SNAPSHOT.jar app.jar
# Health check to verify the application is responding
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java","-jar","app.jar"]

# ------------------------------------
# Uses a lightweight OpenJDK 17 image as the base
# FROM openjdk:17-jdk-slim
# Defines a build-time variable pointing to your JAR file
# ARG JAR_FILE=build/libs/*.jar
# Copies the JAR file into the container and renames it to app.jar
# COPY ${JAR_FILE} app.jar
# Specifies the command to run the application
# ENTRYPOINT ["java","-jar","/app.jar"]

# --- Single stage test ---
# FROM gradle:7.6.4-jdk17
# WORKDIR /app
# COPY . .
# RUN gradle build --no-daemon
# CMD ["java", "-jar", "build/libs/booking-service-0.0.1-SNAPSHOT.jar"]