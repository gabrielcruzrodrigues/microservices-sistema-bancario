# microservices-sistema-bancario
## sistema bancario utilizando a arquitetura de microsserviços<br>
microserviço keyclock para authenticação via token jwt<br>
```docker run --name mskeyclock -p 8810:8081 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.1 start-dev```
