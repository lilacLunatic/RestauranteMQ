-- Database: restaurantemq

-- DROP DATABASE restaurantemq;

CREATE DATABASE restaurantemq
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'pt_BR.UTF-8'
       LC_CTYPE = 'pt_BR.UTF-8'
       CONNECTION LIMIT = -1;

-- Table: "Cliente"

-- DROP TABLE "Cliente";

CREATE TABLE "Cliente"
(
  "ID" serial NOT NULL,
  cpf character varying,
  nome character varying,
  endereco character varying,
  telefone character varying,
  login character varying,
  senha character varying,
  CONSTRAINT pk PRIMARY KEY ("ID"),
  CONSTRAINT cliente_login_unique UNIQUE (login),
  CONSTRAINT cpf_unique UNIQUE (cpf)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Cliente"
  OWNER TO postgres;

-- Table: "Funcionario"

-- DROP TABLE "Funcionario";

CREATE TABLE "Funcionario"
(
  salario double precision,
  cpf character varying,
  nome character varying,
  endereco character varying,
  telefone character varying,
  data_de_demissao date,
  data_de_entrada date,
  "ID" serial NOT NULL,
  login character varying,
  senha character varying,
  CONSTRAINT funcionario_pk PRIMARY KEY ("ID"),
  CONSTRAINT funcionario_cpf_unique UNIQUE (cpf),
  CONSTRAINT funcionario_login_unique UNIQUE (login),
  CONSTRAINT data_de_entrada_not_null CHECK (data_de_entrada IS NOT NULL),
  CONSTRAINT entrada_antes_de_demissao CHECK (data_de_demissao IS NULL OR data_de_entrada <= data_de_demissao)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Funcionario"
  OWNER TO postgres;
-- Table: "Mesa"

-- DROP TABLE "Mesa";

CREATE TABLE "Mesa"
(
  "ID" serial NOT NULL,
  numero integer,
  lugares integer,
  CONSTRAINT mesa_pk PRIMARY KEY ("ID"),
  CONSTRAINT numero_unique UNIQUE (numero)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Mesa"
  OWNER TO postgres;

-- Table: reserva

-- DROP TABLE reserva;

CREATE TABLE reserva
(
  "ID" serial NOT NULL,
  reserva_cliente_fk integer,
  reserva_mesa_fk integer,
  data timestamp without time zone,
  CONSTRAINT reserva_pk PRIMARY KEY ("ID"),
  CONSTRAINT reserva_cliente_fk FOREIGN KEY (reserva_cliente_fk)
      REFERENCES "Cliente" ("ID") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT reserva_mesa_fk FOREIGN KEY (reserva_mesa_fk)
      REFERENCES "Mesa" ("ID") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT no_duas_reservas_ao_mesmo_tempo_por_mesa UNIQUE (reserva_mesa_fk, data)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE reserva
  OWNER TO postgres;

