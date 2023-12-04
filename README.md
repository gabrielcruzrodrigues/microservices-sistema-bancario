# microservices-sistema-bancario
## sistema bancario utilizando a arquitetura de microsserviços<br>
microserviço keyclock para authenticação via token jwt<br>
```docker run --name mskeyclock -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.1 start-dev```
<br><br>
microserviço rabbirMQ para sistema de mensageria:<br>
```docker run -it --name msrabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management```<br>
nome da fila para o avaliador de credito com rabbitMQ:<br>
```emissao-cartoes```
