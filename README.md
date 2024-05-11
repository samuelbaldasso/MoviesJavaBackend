# Backend para Aplicação de Filmes com Autenticação JWT e Autorização por Roles - Java / Spring Boot

Este projeto é um backend para uma aplicação de filmes. Ele oferece funcionalidades como autenticação e autorização de usuários, utilizando tokens JWT (JSON Web Tokens) e um sistema de roles. O backend é construído em Java / Spring Boot com o banco de dados MySQL via Docker.

## Exemplos de Recursos

- **Autenticação de Usuário**: Sistema de login e registro de usuários.
- **Autorização via JWT**: Após o login, os usuários recebem um token JWT para acessos subsequentes.
- **Gerenciamento de Filmes**: Funcionalidades para adicionar, visualizar, editar e deletar filmes, assim como gerenciar tags associadas aos filmes.
- **Testes unitários**: Testes unitários de todos os controllers, repositories e serviços da aplicação.

## Tecnologias Utilizadas

- **Java / Spring Boot**: Ambiente de execução do servidor.
- **MySQL**: Banco de dados eficiente para armazenar os dados dos usuários e informações da biblioteca.
- **JWT (JSON Web Tokens)**: Utilizado para a autenticação e autorização de usuários com base em roles.
- **JUnit4 / Mockito**: Utilizados para os testes unitários de toda a aplicação.

## Documentação da API

A documentação completa das APIs está disponível no endpoint `/swagger-ui/index.html`. A documentação é interativa e permite testar os endpoints diretamente pela interface do Swagger.

## Instruções de Instalação e Uso

1. Clone o repositório: `git clone [URL_DO_REPOSITORIO]`

2. Navegue até a pasta do projeto e instale as dependências: `cd [NOME_DA_PASTA_DO_PROJETO]` e depois execute `mvn install` para gerar a atualização das dependências e o JAR da aplicação (caso desejar).

3. **Instalação do Docker / MySQL** (Opcional):
   - Baixe o Docker do site oficial: [Download Docker - Windows, por exemplo](https://docs.docker.com/desktop/install/windows-install/).
   - Siga as instruções de instalação para o seu sistema operacional.
   - Crie um novo banco de dados em um contâiner Docker utilizando este comando:
  
   `docker run -d -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=taskdb --name mysqldb -p 3307:3306 mysql:8.0`
   
5. Inicie o servidor: execute a aplicação pela classe main da mesma na sua IDE preferida (recomendo Intellij IDEA Community ou Ultimate).

6. Acesse `http://localhost:8080/swagger-ui/index.html` em seu navegador para visualizar a documentação da API.

---

Desenvolvido com ❤️ por Samuel Baldasso
