# Desafio Técnico Builders

O desafio foi realizado atendendo aos requisitos solicitados na documentação.

##Build e Execução do projeto

###Realizar o clone do projeto
     https://github.com/felipesampaio1/builders.git

###Iniciar os containers através do comando abaixo.
     cd clients
     docker-compose -f docker-compose.yml up

###Build e execução do sistema
    ./mvnw clean install -DskipTests
    ./mvnw spring-boot:run

###Executar testes
    ./mvnw test

## Documentação Swagger
- http://localhost:8080/api/swagger-ui.html