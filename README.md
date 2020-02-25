# alugamazing-api
API Rest de uma locadora de itens genéricos

O alugamazing-api permite manter e administrar uma locadora generica. Ela permite administrar o cadastro de tipos de item, dos itens, dos cliente e o próprio aluguel.

### Instruções de uso :
- Clonar o projeto;
- Importar como um maven project;
- Setar as informações do banco de dados no *application.properties:
  - Esse projeto foi feito utilizando o Postgres;
- Selecionar a class *AlugamazingApplication* e executa-la como *Java Applcation*;

### Exemplo de request :
##### Metodo: *GET*
##### Recurso: http://localhost:8086/users
##### Resposta:
```[{
        "id": 1,
        "name": "Lucas ALves Schulze",
        "email": "contao@lschulze.com",
        "profile": "CLIENT"
    },
    {
        "id": 2,
        "name": "Davi Schulze",
        "email": "davi@gmail.com",
        "profile": "CLIENT"
    }]
```
### Observações :

##### Esse projeto usa [Lombok](https://projectlombok.org/) para gerar automaticamente código repetitivos como gets e sets;
##### Também é usado o [Swagger](https://swagger.io/) para gerar uma documentação interativa. Essa documentação pode ser acessada através da url: http://localhost:8086/swagger-ui.html;
##### Foi disponibilizado na pasta raiz do projeto uma collection de exemplos de requisições para ser importado utilizando o [Postman](https://www.postman.com/)
