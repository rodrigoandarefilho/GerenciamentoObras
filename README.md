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

## 📋 Pré-requisitos / Configuração
Precisa ter instalado e configurado:

* MySQL
* Java
* IntelliJ

# Ajustes para o SGBD MySQL:
DataBase: GERENCIAMENTO_OBRAS
Plataform: postegres  
porta: 3306  
user: root  
senha: 1234





