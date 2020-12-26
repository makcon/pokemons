FROM adoptopenjdk/openjdk15:alpine-slim

COPY application/target/application-*.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]