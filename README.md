# Gerenciamento de Obras
O projeto consiste em criar uma API de gerenciamento de obras na linguagem de programação Java, permitindo o cadastro de responsáveis, obras públicas e obras privadas, além de fornecer consultas para listar obras ordenadas pelo seu número, filtrar por tipo de obra e listar obras relacionadas a um responsável.

## ⚙️ Funcionalidades
- [x] Cadastro de responsáveis com: código, CPF e nome.
- [x] Cadastro de obras públicas com: número, data de cadastro, descrição, data de início, data de fim e informações sobre os responsáveis envolvidos.
- [x] Cadastro de obras privadas com: número, data de cadastro, descrição, zona (rural ou urbana), área total e informações sobre os responsáveis envolvidos.
- [x] Consulta de todas as obras ordenadas pelo número.
- [x] Listagem de obras públicas ou privadas
- [x] Listagem de todas as obras relacionadas a um responsável, com base no código do responsável.

## 🛠️ Tecnologias
* [Java 17](https://www.oracle.com/java/)
* [Spring Boot 3](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [MySQL](https://www.mysql.com/)
* [Hibernate](https://hibernate.org/)
* [Flyway](https://flywaydb.org/)
* [Lombok](https://projectlombok.org/)
* [Swagger](https://swagger.io/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

## 📖 Modelo Conceitual DER
Para melhor entendimento do projeto, foi montado um modelo conceitual DER.

![image](https://github.com/rodrigoandarefilho/GerenciamentoObras/assets/32442551/6c244706-7218-4565-b6e2-35a6ed59f7e3)

## 📋 Pré-requisitos
Precisa ter instalado e configurado:

* SGBD MySQL (8.0.33 MySQL Community Server - GPL)
* Java 17
* IntelliJ (versão: 2023.1.5)
* Realizar o clone do repositório e de preferência deixar na pasta C: do computador

### É recomendado ao instalar o SGBD MySQL, as recomendações a seguir:
⚠️ Mas fique tranquilo, caso já tenha instalado com outras configurações, ensino modificar o endereco/porta, user e password.
1. DataBase: GERENCIAMENTO_OBRAS
2. porta: 3306  
3. user: root  
4. senha: 1234

## 📋 Instalação / Configuração
#### Após ter realizado download, instalado e configurado os pré-requisitos realize os seguintes passos para executar a API:
1. Ao abrir o IntelliJ e abrir o projeto no mesmo, podemos realizar de duas maneiras a execução do projeto (Figura abaixo)

![image](https://github.com/rodrigoandarefilho/GerenciamentoObras/assets/32442551/8d26a112-b252-427c-9240-a91e12339780)

Primeira forma de executar o projeto:
1. Clique na lateral direita em "M" (Maven) > depois na aba "obras" > dê dois cliques em "package". Desta forma é criado um arquivo .jar
2. Na pasta em que clonou, navegue em "obras" > "target" e encontrará um arquivo chamado "obras-0.0.1-SNAPSHOT"
3. Abra o terminal dentro da pasta "target" e realize o comando abaixo:
java "-Dspring.profiles.active=prod" -DDATASOURCE_URL=jdbc:mysql://localhost/GERENCIAMENTO_OBRAS -DDATASOURCE_USERNAME=root -DDATASOURCE_PASSWORD=1234 -jar target/api-0.0.1-SNAPSHOT.jar
⚠️ CASO TENHA ENDEREÇO, USUARIO OU SENHA DIFERENTES DO RECOMENDADO SEGUIR OS PRÓXIMOS PASSOS:
* Substituir <endereco: porta>, <username> e <password>, pela sua configuração local !
- [x] Pronto, problema resolvido, caso seu problema seja endereço, user ou senha diferente do recomendado.

Segunda forma de executar o projeto:
1.1. Na lateral esquerda abra as seguintes abas "src" > "main" > "java" > "br.com.publica.obras"
1.2. Clique na seta "verde" e em seguida execute o projeto.







