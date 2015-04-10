--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.1
-- Started on 2015-04-10 10:02:48

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 32929)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE funcionario (
    salario double precision,
    cpf character varying,
    nome character varying,
    endereco character varying,
    telefone character varying,
    data_de_demissao date,
    data_de_entrada date,
    id integer NOT NULL,
    login character varying,
    senha character varying,
    administrador boolean DEFAULT false NOT NULL,
    CONSTRAINT data_de_entrada_not_null CHECK ((data_de_entrada IS NOT NULL)),
    CONSTRAINT entrada_antes_de_demissao CHECK (((data_de_demissao IS NULL) OR (data_de_entrada <= data_de_demissao)))
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 32938)
-- Name: Funcionario_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Funcionario_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Funcionario_ID_seq" OWNER TO postgres;

--
-- TOC entry 1991 (class 0 OID 0)
-- Dependencies: 173
-- Name: Funcionario_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Funcionario_ID_seq" OWNED BY funcionario.id;


--
-- TOC entry 1869 (class 2604 OID 32985)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario ALTER COLUMN id SET DEFAULT nextval('"Funcionario_ID_seq"'::regclass);


--
-- TOC entry 1992 (class 0 OID 0)
-- Dependencies: 173
-- Name: Funcionario_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Funcionario_ID_seq"', 3, true);


--
-- TOC entry 1985 (class 0 OID 32929)
-- Dependencies: 172
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO funcionario (salario, cpf, nome, endereco, telefone, data_de_demissao, data_de_entrada, id, login, senha, administrador) VALUES (40000, '11111111111', 'admin', 'admin_endereco', 'admin_telefon', NULL, '2015-04-10', 3, 'admin', 'admin', true);


--
-- TOC entry 1873 (class 2606 OID 33004)
-- Name: funcionario_cpf_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_cpf_unique UNIQUE (cpf);


--
-- TOC entry 1875 (class 2606 OID 33006)
-- Name: funcionario_login_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_login_unique UNIQUE (login);


--
-- TOC entry 1877 (class 2606 OID 33008)
-- Name: funcionario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pk PRIMARY KEY (id);


-- Completed on 2015-04-10 10:02:48

--
-- PostgreSQL database dump complete
--

