# Votação de pauta em assembléia

API REST desenvolvida em Java que expões operações para criar pautas e receber votos de associados.
Banco de dados Postgresql mantém as pautas e votações recebidas

### Documentação das operações pode ser acessada pela Url:
http://localhost:8080/swagger-ui.html

### Subindo a aplicação local

Utilize docker-compose up para subir a imagem do banco de dados. As tabelas que devem ser criadas estão no arquivo src/main/resources/schema.sql

Para subir o banco de dados, acesse o diretorio root do projeto e execute o comando *docker-compose up* pelo terminal. 

Gradle deve ser usado para build do projeto : https://gradle.org/install e execução dos testes

Para subir a aplicação, execute a classe Main.java. 

