FROM amazoncorretto:18
ARG JAR_FILE="target/SE-Reisebuchungen-0.0.1-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]