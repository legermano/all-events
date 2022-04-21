FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} all-events.jar
ENTRYPOINT ["java", "-jar", "all-events.jar"]
EXPOSE 8080