CREATE TABLE ENDERECO (
id_endereco NUMBER NOT NULL,
rua VARCHAR2(50) NOT NULL,
cidade VARCHAR2(50) NOT NULL,
estado VARCHAR2(50) NOT NULL,
pais VARCHAR2(50) NOT NULL,
complemento VARCHAR2(50),
numero NUMBER NOT NULL,
cep CHAR(9) NOT NULL,
PRIMARY KEY (id_endereco)
);

CREATE TABLE CLIENTE (
id_cliente NUMBER NOT NULL,
 nome VARCHAR2(100) NOT NULL,
 cpf CHAR(11) UNIQUE NOT NULL,
 telefone CHAR(14) NOT NULL,
 email VARCHAR2(100) NOT NULL,
 tipo_cliente NUMBER NOT NULL,
 ativo CHAR(1) NOT NULL,
 PRIMARY KEY (id_cliente)
);

CREATE TABLE IMOVEL (
id_imovel NUMBER NOT NULL,
valor_mensal DECIMAL(10,2) NOT NULL,
condominio DECIMAL(10,2) NOT NULL,
alugado CHAR(1) NOT NULL,
qntd_quartos SMALLINT,
qntd_banheiros SMALLINT,
permite_animais CHAR(1) NOT NULL,
salao_de_festas CHAR(1) NOT NULL,
numero_de_vagas SMALLINT,
area_de_lazer CHAR(1) NOT NULL,
garagem CHAR(1) NOT NULL,
tipo_imovel NUMBER NOT NULL,
id_endereco NUMBER NOT NULL,
id_cliente NUMBER NOT NULL,
ativo CHAR(1) NOT NULL,
PRIMARY KEY (id_imovel),
CONSTRAINT FK_IMOVEL_ENDERECO
    FOREIGN KEY (id_endereco)
        REFERENCES ENDERECO(id_endereco),
CONSTRAINT FK_IMOVEL_CLIENTE
    FOREIGN KEY (id_cliente)
        REFERENCES CLIENTE(id_cliente)
);

CREATE TABLE CONTRATO(
 id_contrato NUMBER NOT NULL,
 valor_aluguel DECIMAL(10,2) NOT NULL,
 data_entrada DATE NOT NULL,
 data_vencimento DATE NOT NULL,
 id_locatario NUMBER NOT NULL,
 id_locador NUMBER NOT NULL,
 id_imovel      NUMBER NOT NULL,
 ativo CHAR(1) NOT NULL,
 PRIMARY KEY (id_contrato),
 CONSTRAINT FK_CONTRATO_IMOVEL
     FOREIGN KEY (id_imovel)
         REFERENCES IMOVEL(id_imovel),
 CONSTRAINT FK_CONTRATO_LOCATARIO
     FOREIGN KEY (id_locatario)
         REFERENCES CLIENTE(id_cliente),
 CONSTRAINT FK_CONTRATO_LOCADOR
     FOREIGN KEY (id_locador)
         REFERENCES CLIENTE(id_cliente)
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



