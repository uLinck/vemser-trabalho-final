CREATE TABLE "ENDERECO" (
  "id_endereco" NUMBER NOT NULL,
  "rua" VARCHAR2(50) NOT NULL,
  "cidade" VARCHAR2(50) NOT NULL,
  "estado" VARCHAR(50) NOT NULL,
  "pais" VARCHAR(50) NOT NULL,
  "complemento" VARCHAR(50) NOT NULL,
  "numero" NUMBER NOT NULL,
  "cep" CHAR(9) NOT NULL,
  PRIMARY KEY ("id_endereco")
);

CREATE TABLE "CLIENTE" (
  "id_cliente" NUMBER NOT NULL,
  "nome" VARCHAR2(100) NOT NULL,
  "cpf" CHAR(11) UNIQUE NOT NULL,
  "telefone" CHAR(14) NOT NULL,
  "email" VARCHAR2(100) NOT NULL,
  "tipo_cliente" NUMBER NOT NULL,
  PRIMARY KEY ("id_cliente")
);

CREATE TABLE "IMOVEL" (
  "id_imovel" NUMBER NOT NULL,
  "valor_mensal" DECIMAL(10,2) NOT NULL,
  "condominio" DECIMAL(10,2) NOT NULL,
  "alugado" CHAR(1) NOT NULL,
  "tipo_imovel" NUMBER NOT NULL,
  "id_endereco" NUMBER NOT NULL,
  "id_cliente" NUMBER NOT NULL,
  PRIMARY KEY ("id_imovel"),
  CONSTRAINT "FK_IMOVEL.id_endereco"
    FOREIGN KEY ("id_endereco")
      REFERENCES "ENDERECO"("id_endereco"),
  CONSTRAINT "FK_IMOVEL.id_cliente"
    FOREIGN KEY ("id_cliente")
      REFERENCES "CLIENTE"("id_cliente")
);

CREATE TABLE "CONTRATO" (
  "id_contrato" NUMBER NOT NULL,
  "valor_aluguel" DECIMAL(10,2) NOT NULL,
  "data_entrada" DATE NOT NULL,
  "data_vencimento" DATE NOT NULL,
  "id_locatario" NUMBER NOT NULL,
  "id_locador" NUMBER NOT NULL,
  "id_imovel"      NUMBER NOT NULL,
  PRIMARY KEY ("id_contrato"),
  CONSTRAINT "FK_CONTRATO.id_imovel"
    FOREIGN KEY ("id_imovel")
      REFERENCES "IMOVEL"("id_imovel"),
  CONSTRAINT "FK_CONTRATO.id_locatario"
    FOREIGN KEY ("id_locatario")
      REFERENCES "CLIENTE"("id_cliente"),
  CONSTRAINT "FK_CONTRATO.id_locador"
    FOREIGN KEY ("id_locador")
      REFERENCES "CLIENTE"("id_cliente")
);

CREATE SEQUENCE seq_contrato
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE SEQUENCE seq_endereco
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE SEQUENCE seq_cliente
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
 
CREATE SEQUENCE seq_imovel
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

INSERT INTO CLIENTE ("id_cliente", "nome" , "cpf" , "telefone" , "email" , "tipo_cliente")
VALUES (seq_cliente.nextval, 'Paulo Sergio', '00000000001', '55011555554444', 'paulo@gmail.com', 1);

INSERT INTO CLIENTE ("id_cliente", "nome" , "cpf" , "telefone" , "email" , "tipo_cliente")
VALUES (seq_cliente.nextval, 'Lucas Miguel', '00000000002', '55011555553333', 'lucas@gmail.com', 2);

INSERT INTO ENDERECO("id_endereco", "rua", "cidade", "estado", "pais", "complemento", "numero", "cep")
VALUES(seq_endereco.nextval, 'Avenida João Pessoa', 'Porto Alegre', 'Rio Grande do Sul','Brasil' ,'Apartamento', 239, '56435-987')
 
INSERT INTO IMOVEL("id_imovel","valor_mensal","condominio","alugado","tipo_imovel","id_endereco","id_cliente")
VALUES(seq_imovel.nextval, 100.0, 0, 'T', 1, 1, 1);

INSERT INTO CONTRATO ("id_contrato","valor_aluguel","data_entrada","data_vencimento","id_locatario","id_locador","id_imovel")
VALUES(seq_contrato.nextval, 1500, TO_DATE('13/10/2022','dd/mm/yyyy'),TO_DATE('15/06/2023','dd/mm/yyyy'),2,1,1);

SELECT * FROM CONTRATO;

