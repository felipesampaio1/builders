FROM maven:3.6.3-openjdk-11 AS build
COPY src /home/clients/src
COPY pom.xml /home/clients
RUN mvn -f /home/clients/pom.xml clean package -DskipTests

FROM openjdk:11
COPY --from=build /home/clients/target/clients-*.jar /usr/local/lib/clients.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/clients.jar"]
