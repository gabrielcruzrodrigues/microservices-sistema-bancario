# microservices-sistema-bancario
## sistema bancario utilizando a arquitetura de microsserviços<br>
## keycloak - authentication
imagem docker do keyclock para authenticação via token jwt:<br>
```docker run --name mskeyclock -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.1 start-dev```<br><br>
configurando o keyclock:<br>
1. acesse o painel do keycloak ``` http://localhost:8081 ``` e utilize ```usuario: admin```, ```senha: admin```
2. crie um novo realm, pode dar a ele o nome que desejar, ele ira representar a aplicação que quer ser authenticada.


<br><br>
## rabbitMQ - sistema de mensageria
imagem docker do rabbirMQ para sistema de mensageria:<br>
```docker run -it --name msrabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management```<br>
nome da queue para o avaliador de credito com rabbitMQ:<br>
```emissao-cartoes```
