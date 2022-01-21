# Desafio Técnico Builders

O desafio foi realizado atendendo aos requisitos solicitados na documentação.

## Build e Execução do projeto

### Realizar o clone do projeto
     https://github.com/felipesampaio1/builders.git

### Realizar login no AWS para permitir o pull da image
     aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 395454834028.dkr.ecr.us-east-1.amazonaws.com


### Iniciar os containers através do comando abaixo.
     cd clients
     docker-compose -f docker-compose.yml up

### Executar testes
    ./mvnw test

## Documentação Swagger
- http://localhost:8080/api/swagger-ui.html
