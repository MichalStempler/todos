# the base image
FROM amazoncorretto:21

# the JAR file path
ARG JAR_FILE=target/*.jar

# Copy the JAR file from the build context into the Docker image
COPY ${JAR_FILE} todos-app-0.0.1-SNAPSHOT.jar

CMD apt-get update -y

# Set the default command to run the Java application
ENTRYPOINT ["java","-jar", "/todos-app-0.0.1-SNAPSHOT.jar"]