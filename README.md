# microservices-sistema-bancario
## sistema bancario utilizando a arquitetura de microsserviços<br>
## keycloak - authentication
imagem docker do keyclock para authenticação via token jwt:<br>
```docker run --name mskeyclock -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.1 start-dev```

### Configurando o keyclock:<br>

1. inicie a imagem docker na sua maquina.
2. acesse o painel do keycloak ``` http://localhost:8081 ``` e utilize ```usuario: admin```, ```senha: admin```
3. crie um novo realm, pode dar a ele o nome que desejar, ele ira representar a aplicação que quer ser authenticada.
4. crie um novo client
   * no campo client ID vc pode dar o nome que quiser
   * no campo name, coloque um nome para identificar o cliente dentro do keycloak
5. na coluna ```settings``` procure pelo campo ```Capability config``` e abilite as seguintes configurações:
    * Cliente authentication
    * Authorization
    * Standard flow
    * Direct access grants
    * Service accounts roles
6. na mesma coluna ```settings```, no campo ```capability config```, verifique se os sequintes campos estão desativados:
    * Implicit flow
    * OAuth 2.0 Device Authorization Grant
    * OIDC CIBA Grant
7. na coluna settings, na área Access settings, encontre o campo ```Valid redirect URIs``` e adicione a url ```http://localhost:8080```, não esqueça de salvar as alterações.
<br><br>
### configurando a api para se authenticar com o keycloak:<br>
1. no painel do keycloak procure pelo campo ```Realm settings```, na coluna ```general``` clique em ```OpenID Endpoint Configuration```
2. das urls que vão aparecer, copie a url da chave ```"issuer"```: ```http://localhost:8081/realms/sistema-bank```
3. dentro do microserviço gateway, navegue ate o arquivo ```aplication.yml``` e adicione o conteudo do campo ```"issuer"``` no campo ```issuer-uri```, dentro da chave ```security```

<br><br>
## rabbitMQ - sistema de mensageria
imagem docker do rabbirMQ para sistema de mensageria:<br>
```docker run -it --name msrabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management```<br>
nome da queue para o avaliador de credito com rabbitMQ:<br>
```emissao-cartoes```

<br><br>
## gerando imagem docker dos microserviços
Os microserviços contem um Dockerfile que permite gerar um container docker do microserviço, facilitando a criação de novas instancias e economizando processamento da maquina, pois vc não precisaria da IDE para rodar o codigo.<br>
### criar imagem do microserviço
acesse o terminal na pasta raiz do microserviço e execute o seguinte codigo:<br>
```docker build --tag NomeAleatórioDoContainer .```<br>
### iniciar o container do microserviço
acesse o terminal na pasta raiz do microserviço e execute o seguinte codigo:<br>
```docker run --name NomeAleatorioDoConteiner -p 8761:8761 NomeDaImagemCriada```<br><br>
OBS: as portas devem variar para não gerar conflito.<br>

## endpoints
### * GET - gerar token JWT para conseguir acessar as rotas
  * para conseguir o token, vc precisa usar o Oauth2, se estiver no postman, vá até a coluna ```authorization```, selecione ```Oauth2```<br>, então vão aparecer alguns campos a serem preenchidos.
1. no campo ```Grant type```, altere para ```client credentials```<br>
2. no painel do keycloak procure por ```Realm settings```, na coluna ```general``` clique em ```OpenID Endpoint Configuration```
3. das urls que vão aparecer, copie a url da chave ```"token_endpoint"```: ```http://localhost:8081/realms/sistema-bank/protocol/openid-connect/token``` e cole no campo ```Access Token URL``` no formulário de requisição do postman<br>
4. no painel do keycloak procure por ```Clients```, selecione o cliente que você criou no momento de configurar o keycloak, copie o seu ```Client ID``` e cole no campo ```Client ID``` no formulário de requisição do postman<br>
5. no painel do keycloak procure por ```Clients > Credentials```, copie a ```Client Secret``` e cole no campo ```Client Secret``` no formulário de requisição do postman<br>
6. clique em ```get new access token```

