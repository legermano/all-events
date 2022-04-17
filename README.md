# Como rodar o projeto

### Gerando o .jar
* Na raiz do projeto rodar o comando para gerar o .jar do projeto: `./mvnw clean package -DskipTests`
* Ainda na raiz do projeto, mover o .jar para a pasta do docker utilizando o comando: `cp target/${nome_jar}.jar src/main/docker`

### Rodando os containers
* Para rodar os comandos do docker, necessário estar a pasta dele, se estiver na raiz do projeto, pode usar o comando: `cd src/main/docker`
* Se os containers ainda não estiverem construídos, pode gerar eles com o comando: `docker-compose build`
* Para iniciar os containers, utilize o comando: `docker-compose up -d`
* Para parar os containers, utilize o comando: `docker-compose stop`
* Se for a primeira vez iniciando os containers, pode ser que o banco de dados necessário ainda não exite e o container a aplicação não irá funcionar, para isso, conecte no banco de dados com o usuário, senha e porta configurado no `docker-compose.yml` e crie o banco com o comando SQL `'CREATE DATABASE allevents'`

### Testes
* Para realizar testes, é possível utilizar a SQL contida no caminho `'src/main/resource/db/mock/insert.sql'`