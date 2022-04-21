# Como rodar o projeto

## Backend
### Gerando o .jar
* Na pasta `backend`, necessário rodar o comando para gerar o .jar do projeto: `./mvnw clean package -DskipTests`

## Frontend
### Variáveis de ambiente
* Para conseguir rodar o front, é necessário preencher as variáveis de ambiente do projeto. Para isso, na pasta `frontend`, crie um arquivo chamado `.env.local` ou `.env.[mode].local` (onde mode pode ser "development", "production" ou "test" ), copie o conteúdo do arquivo `.env.example` e preencha o valor das variáveis.

## Containers
### Variáveis
* Na raiz do projeto, necessário criar um arquivo chamado `.env` e copiar os conteúdos do arquivo `.env.example` e preencher as variáveis.

### Executar os containers
* Se os containers ainda não estiverem construídos ou se precisa reconstruir eles, pode gerar eles com o comando: `docker-compose build`
* Para iniciar os containers, utilize o comando: `docker-compose up -d`
* Para parar os containers, utilize o comando: `docker-compose stop`
* Se for a primeira vez iniciando os containers, pode ser que o banco de dados necessário ainda não exite e o container a aplicação não irá funcionar, para isso, conecte no banco de dados com o usuário, senha e porta configurado no `docker-compose.yml` e crie o banco com o comando SQL `'CREATE DATABASE allevents'`

### Testes
* Para realizar testes, é possível utilizar a SQL contida no caminho `'backend/src/main/resource/db/mock/insert.sql'`