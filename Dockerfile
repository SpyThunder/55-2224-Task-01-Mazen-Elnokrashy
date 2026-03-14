# 1. Base Image
FROM eclipse-temurin:25.0.2_10-jdk

# 2. Working directory
WORKDIR /app

# 3. Copy the JAR from the target folder into app.jar
# Note: Ensure you have run './mvnw package' first so the target folder exists
COPY target/Mazen_Elnokrashy-0.0.1-SNAPSHOT.jar app.jar

# 4. Copy both JSON data files into a dedicated data directory
COPY src/main/resources/notes.json /data/notes.json
COPY src/main/resources/users.json /data/users.json

# 5. Environment variables
ENV USER_NAME=Docker_Mazen_Elnokrashy
ENV ID=Docker_55_2224

# 6. Expose the necessary port (Spring Boot default)
EXPOSE 8080

# 7. Specify the commands to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]