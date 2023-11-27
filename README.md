<a name="readme-top"></a>
<div align="center">
  <br/>
  <h3><b>Sistema de Denúncia Ambiental</b></h3>
</div>

# 📗 Conteúdo

- [📖 Sobre o Projeto](#about-project)
  - [🛠 Implementado com](#built-with)
    - [Tech Stack](#tech-stack)
    - [Features Principais](#key-features)
  - [🚀 Live Demo](#live-demo)
  - [💻 Iniciando](#getting-started)
  - [Utilizand API](#utilizando-api)
     - [Cadastro e Login]
     - [Listagem de Denúncia]
     - [Cadastrar, Editar e Deletar Denúnica]
  - [Futuras Alterações](#futuras-alteracoes)
- [👥 Authors](#authors)
- 
# 📖 [Sistema de Denúncia Ambiental] <a name="about-project"></a>

> Reposítorio que contém a parte de APIs de um Sistema para realização de denúncias ambientais, criado em 2 dias juntamente à parte de Frontend (https://github.com/HenriqqG/sistema-denuncia-ambiental) como parte de um desafio

**[Sistema de Denúncia Ambiental]** é um projeto realizado para estudos e aprendizados relacinados à: 
- Criação de um Projeto do Zero;
- Utilização e Implementação de autenticadores como JWT e OAuth2
- Funcionamento de um projeto baseado em APIs

## 🛠 Implementado com <a name="built-with"></a>

### Tech Stack <a name="tech-stack"></a>

<details>
  <summary>Framework</summary>
  <ul>
    <li><a href="https://spring.io/projects/spring-boot">Spring Boot</a></li>
  </ul>
</details>

<details>
<summary>Database</summary>
  <ul>
    <li><a href="https://www.h2database.com/html/main.html">H2 Database</a></li>
  </ul>
</details>

### Features Principais <a name="key-features"></a>

- **[Autenticação JWT e OAuth2]**
- **[Cadastro e Login de Usuário]**
- **[Cadastro, Consulta e Edição de Denúncias por Usuário]**

<p align="right">(<a href="#readme-top">de volta ao topo</a>)</p>

## 🚀 Live Demo <a name="live-demo"></a>

- [Link para Utilização da API e seus serviços](https://sistema-denuncia-ambiental-e61aaa6cb4cb.herokuapp.com/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## 💻 Iniciando <a name="getting-started"></a>

Não tem muito segredo para rodar o projeto localmento, basta apenas cloná-lo e rodar! Para utilizar os serviços disponibilizados no link de Live Demo

## Utilizando a API <a name="utilizando-api"></a>
### Cadastro e Login

#### ``` POST /auth/register ``` 

Recebe um body no formato:
```
{
    "cpf":"12345678901",
    "password":"senha123",
    "role":"DENUNCIANTE"
}
 ```

#### ``` POST /auth/login ``` 

Recebe um body no formato:
```
{
    "cpf":"12345678901",
    "password":"senha123"
}
 ```
e provém um response no formato:
```
{
    "user": {
        "userId": 1,
        "username": "12345678901",
        "password": "$2a$10$zN3jy9RY3EEYGkeLjOiMYev3Nio7FU7fBCZcIafL070y9RE4W/EOa",
        "authorities": [
            {
                "roleId": 1,
                "authority": "DENUNCIANTE"
            }
        ],
        "enabled": true,
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true
    },
    "jwt": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiMDU0OTc0OTExMDMiLCJleHAiOjE3MDExNTQ2MDIsImlhdCI6MTcwMTA2ODIwMiwicm9sZXMiOiJERU5VTkNJQU5URSJ9.WiO711twyySCuk57nBgKHsoMNqVxM0nQkF_aM7cD71HeyngM6IXZkiViv44c4rs3a0TgUXm23fN5v_oW-oHKsy4Ck0MtLlPyw1lqnG3O4OMkYCiD3kWqJdLAd5fV4qIQwo-zURoJ_9MmOIM1kmTCJplMKQzIQpeynTMOeXRZAEd0SqujGuPGSD_zx0u-0nLGN-33SJ9vrkmd2oDF16Aon3n5-oAgmkZihePoI8UWGf3W5WYxhb5HLJaeIA5IbthmpkDRytsgmTL2Zl4eJeL9cjdZoctjxD0eA5ZtxAO6OSQV7RLapAbgqNqLYYXIKZWjNyDI0NxayxWKR7MyQU7izw"
}
 ```
### Listagem de Denúncia

#### ``` GET /denunciante/listar ```
Irá listar todas as denúncias, utilizado por cadastros do tipo ANALISTA

#### ``` GET /denunciante/listar-por-cpf/{cpf} ```
Irá listar apenas denúncias feitas sobre o cadastro de um CPF específico, utilizado por cadastros do tipo DENUNCIANTE

#### ``` POST /denunciante/filtrar ```
receberá um body com as informações de filtro no formato
```
{
  "numrProtocolo":"",
  "municipio":"",
  "dataIncidente":"",
  "dataCadastro":"",
  "categoria":"",
  "status":""
}
```
e proverá uma lista de Denúncias

### Cadastrar, Editar e Deletar Denúnica

#### ``` GET /denunciante/buscar-por-numrprotocolo/{numrProtocolo} ```
Utilizado para trazer informações de uma denúncia no momento da Ediçao, receberá apenas o número de protocoloca da denúncia na URL

#### ``` POST /denunciante/cadastrar-andamento ```
Utilizado por cadastros do tipo ANALISTA, receberá um body no seguinte formato
```
{
    "id" : ,
    "cpfAnalista":""
    "dataCadastroAndamento":"",
    "status":"",
    "descricao":"",
    "parecerTecnico":"",
    "idDenuncia":""
}
```

#### ``` POST /denunciante/cadastrar ```
Utilizada no momento do cadastro de uma nova denúncia, receberá um body no seguinte formato, contendo as informações da denúncia
```
{
    "id" : ,
    "numrProtocolo":""
    "rua":"",
    "denunciante":"",
    "bairro":"",
    "municipio":"",
    "cep":"",
    "referencia":"",
    "latitude":"",
    "longitude":"",
    "texto":"",
    "status":"",
    "parecertecnico":"",
    "dataIncidente":"",
    "dataCadastro":"",
    "provavelAutor":"",
    "categoria":"",
    "cpfDenunciante":""
}
```

#### ``` PUT /denunciante/editar/{id} ```
Utilizada para persistir as alterações de uma denúncia, receberá o ID da denúncia em questão e um body no seguinte formato, contendo as informações da denúncia
```
{
    "id" : ,
    "numrProtocolo":""
    "rua":"",
    "denunciante":"",
    "bairro":"",
    "municipio":"",
    "cep":"",
    "referencia":"",
    "latitude":"",
    "longitude":"",
    "texto":"",
    "status":"",
    "parecertecnico":"",
    "dataIncidente":"",
    "dataCadastro":"",
    "provavelAutor":"",
    "categoria":"",
    "cpfDenunciante":""
}
```

#### ``` DELETE /denunciante/deletar/{id} ```
Utilizada para exclusão de uma denúncia, receberá o ID da Denúncia na URL


<p align="right">(<a href="#readme-top">de volta ao topo</a>)</p>

## Futuras Alterações <a name="futuras-alteracoes"></a>

- Pequenas alterações em alguns endpoints para melhor funcionamento, como o de exclusão de Denúncias
- Reorganização do projeto para melhor entendimento da estrutura

## 👥 Authors <a name="authors"></a>

- GitHub: [@GagnoHenriqq](https://github.com/githubhandle
- LinkedIn: [LinkedIn](https://linkedin.com/in/henriquegagnoporto/)

<p align="right">(<a href="#readme-top">de volta ao topo</a>)</p>
