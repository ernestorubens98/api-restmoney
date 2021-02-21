<h2>RUN DATABASE IN DOCKER AND START THE PROJECT</h2>
   - docker pull mysql
   - sudo docker run -p 3306:3306 --name db_ordens_servico -e MYSQL_ROOT_PASSWORD=admin -d mysql
   - mvn clean package

<h3>Flyway - Version Control Database</h3>

<h3>Jakarta EE ==> Jakarta Persistence</h3>
   - JPA - Uma especificação que fornece uma API de mapeamento de objeto relacional
   - Utilizado em bancos relacionais
   - Dentro da dependência spring-boot-starter-data-jpa possui o hibernate-core, que é uma  implementação o Jakarta Persistence
   - spring-data-jpa não é uma implementação do Jakarta Persistance, ela é apenas uma biblioteca que ajuda a criar repositórios

<h3>Jakarta EE ==> Jakarta Bean Validation</h3>
   - Faz a validação da entrada de dados
   - A dependência spring-boot-starter-web já traz a implementação do hibernate-validator

<h3>Domain Model</h3>
   - Model
   - Service
   - Repository

<h3>Represetation Model</h3>
   - DTO - Data Transfer Object
      - request
      - response
   - Model Mapper


<h3>ROUTES</h3>

<h4>CLIENTS</h4>
   - GET - http://localhost:8080/clientes
   - GET BY ID - http://localhost:8080/clientes/{id}
   - POST - http://localhost:8080/clientes
   - PUT - http://localhost:8080/clientes/{id}
   - DELETE - http://localhost:8080/clientes/{id}

<h4>ORDERS SERVICE</h4>
   - POST - http://localhost:8080/orders-service
   - GET - http://localhost:8080/orders-service

<h4>COMMENTS</h4>
   - POST - http://localhost:8080/orders-service/{orderServiceId}/comments