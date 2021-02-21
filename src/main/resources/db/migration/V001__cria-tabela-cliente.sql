CREATE TABLE cliente (
   id BIGINT NOT NULL AUTO_INCREMENT,
   nome_cliente VARCHAR(60) NOT NULL,
   email_cliente VARCHAR(60) NOT NULL,
   telefone_cliente VARCHAR(60) NOT NULL,

   PRIMARY KEY (id)
);