FROM openjdk:8-jre-alpine3.9
COPY target/clients-*.jar /clients.jar
EXPOSE 8080
CMD ["java", "-jar", "/marseille.jar"]
