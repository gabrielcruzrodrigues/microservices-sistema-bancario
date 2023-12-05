# microservices-sistema-bancario
## sistema bancario utilizando a arquitetura de microsserviços<br>
## keycloak - authentication
imagem docker do keyclock para authenticação via token jwt:<br>
```docker run --name mskeyclock -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.1 start-dev```<br><br>
configurando o keyclock:<br>
1. inicie a imagem docker na sua maquina.
2. acesse o painel do keycloak ``` http://localhost:8081 ``` e utilize ```usuario: admin```, ```senha: admin```
3. crie um novo realm, pode dar a ele o nome que desejar, ele ira representar a aplicação que quer ser authenticada.
4. crie um novo client
   * no campo client ID vc pode dar o nome que quiser
   * no campo name, coloque um nome para identificar o cliente dentro do keycloak
5. na coluna settings procure pelo campo Capability config e abilite as seguintes configurações:
    * Cliente authentication
    * Authorization
    * Standard flow
    * Direct access grants
    * Service accounts roles
6. na mesma coluna settings, no campo capability config, verifique se os sequintes campos estão desativados:
    * Implicit flow
    * OAuth 2.0 Device Authorization Grant
    * OIDC CIBA Grant

<br><br>
## rabbitMQ - sistema de mensageria
imagem docker do rabbirMQ para sistema de mensageria:<br>
```docker run -it --name msrabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management```<br>
nome da queue para o avaliador de credito com rabbitMQ:<br>
```emissao-cartoes```
