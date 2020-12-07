FROM openjdk
WORKDIR /app
COPY target/github-0.0.1-SNAPSHOT.jar /app/github.jar
ENTRYPOINT ["java", "-jar", "github.jar"]