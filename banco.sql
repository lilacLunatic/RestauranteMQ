--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.2
-- Dumped by pg_dump version 9.3.2
-- Started on 2015-03-27 14:50:16

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
-- TOC entry 189 (class 1259 OID 43193)
-- Name: Cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Cliente" (
    "ID" integer NOT NULL,
    cpf character varying,
    nome character varying,
    endereco character varying,
    telefone character varying,
    login character varying,
    senha character varying
);


ALTER TABLE public."Cliente" OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 43191)
-- Name: Cliente_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Cliente_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Cliente_ID_seq" OWNER TO postgres;

--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 188
-- Name: Cliente_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Cliente_ID_seq" OWNED BY "Cliente"."ID";


--
-- TOC entry 191 (class 1259 OID 43208)
-- Name: Funcionario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Funcionario" (
    salario double precision,
    cpf character varying,
    nome character varying,
    endereco character varying,
    telefone character varying,
    data_de_demissao date,
    data_de_entrada date,
    "ID" integer NOT NULL,
    login character varying,
    senha character varying,
    CONSTRAINT data_de_entrada_not_null CHECK ((data_de_entrada IS NOT NULL)),
    CONSTRAINT entrada_antes_de_demissao CHECK (((data_de_demissao IS NULL) OR (data_de_entrada <= data_de_demissao)))
);


ALTER TABLE public."Funcionario" OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 43206)
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
-- TOC entry 2102 (class 0 OID 0)
-- Dependencies: 190
-- Name: Funcionario_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Funcionario_ID_seq" OWNED BY "Funcionario"."ID";


--
-- TOC entry 197 (class 1259 OID 43256)
-- Name: Ingrediente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Ingrediente" (
    "ID" integer NOT NULL,
    "QuantidadeEstoque" integer,
    nome character varying,
    "Unidade" character varying
);


ALTER TABLE public."Ingrediente" OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 43254)
-- Name: Ingrediente_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Ingrediente_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Ingrediente_ID_seq" OWNER TO postgres;

--
-- TOC entry 2103 (class 0 OID 0)
-- Dependencies: 196
-- Name: Ingrediente_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Ingrediente_ID_seq" OWNED BY "Ingrediente"."ID";


--
-- TOC entry 199 (class 1259 OID 43267)
-- Name: ItemDeMenu; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "ItemDeMenu" (
    "ID" integer NOT NULL,
    "Nome" character varying,
    "Preco" real,
    categoria character varying,
    "QuantidadeEstoque" integer
);


ALTER TABLE public."ItemDeMenu" OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 43265)
-- Name: ItemDeMenu_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "ItemDeMenu_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."ItemDeMenu_ID_seq" OWNER TO postgres;

--
-- TOC entry 2104 (class 0 OID 0)
-- Dependencies: 198
-- Name: ItemDeMenu_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "ItemDeMenu_ID_seq" OWNED BY "ItemDeMenu"."ID";


--
-- TOC entry 203 (class 1259 OID 43289)
-- Name: Item_possui_Ingrediente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Item_possui_Ingrediente" (
    "ID" integer NOT NULL,
    "Quantidade" integer,
    "Item" integer,
    "Ingrediente" integer
);


ALTER TABLE public."Item_possui_Ingrediente" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 43287)
-- Name: Item_possui_Ingrediente_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Item_possui_Ingrediente_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Item_possui_Ingrediente_ID_seq" OWNER TO postgres;

--
-- TOC entry 2105 (class 0 OID 0)
-- Dependencies: 202
-- Name: Item_possui_Ingrediente_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Item_possui_Ingrediente_ID_seq" OWNED BY "Item_possui_Ingrediente"."ID";


--
-- TOC entry 193 (class 1259 OID 43225)
-- Name: Mesa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Mesa" (
    "ID" integer NOT NULL,
    numero integer,
    lugares integer
);


ALTER TABLE public."Mesa" OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 43223)
-- Name: Mesa_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Mesa_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Mesa_ID_seq" OWNER TO postgres;

--
-- TOC entry 2106 (class 0 OID 0)
-- Dependencies: 192
-- Name: Mesa_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Mesa_ID_seq" OWNED BY "Mesa"."ID";


--
-- TOC entry 201 (class 1259 OID 43278)
-- Name: Pedido; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Pedido" (
    "Data" timestamp without time zone,
    "Endereco" character varying,
    "Observacoes" character varying,
    "ID" integer NOT NULL,
    "Cliente" integer
);


ALTER TABLE public."Pedido" OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 43276)
-- Name: Pedido_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Pedido_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Pedido_ID_seq" OWNER TO postgres;

--
-- TOC entry 2107 (class 0 OID 0)
-- Dependencies: 200
-- Name: Pedido_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Pedido_ID_seq" OWNED BY "Pedido"."ID";


--
-- TOC entry 205 (class 1259 OID 43307)
-- Name: Pedido_possui_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Pedido_possui_item" (
    "ID" integer NOT NULL,
    "Pedido" integer,
    "Item" integer
);


ALTER TABLE public."Pedido_possui_item" OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 43305)
-- Name: Pedido_possui_item_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Pedido_possui_item_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Pedido_possui_item_ID_seq" OWNER TO postgres;

--
-- TOC entry 2108 (class 0 OID 0)
-- Dependencies: 204
-- Name: Pedido_possui_item_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Pedido_possui_item_ID_seq" OWNED BY "Pedido_possui_item"."ID";


--
-- TOC entry 195 (class 1259 OID 43235)
-- Name: reserva; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reserva (
    "ID" integer NOT NULL,
    reserva_cliente_fk integer,
    reserva_mesa_fk integer,
    data timestamp without time zone
);


ALTER TABLE public.reserva OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 43233)
-- Name: reserva_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "reserva_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."reserva_ID_seq" OWNER TO postgres;

--
-- TOC entry 2109 (class 0 OID 0)
-- Dependencies: 194
-- Name: reserva_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "reserva_ID_seq" OWNED BY reserva."ID";


--
-- TOC entry 1922 (class 2604 OID 43196)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Cliente" ALTER COLUMN "ID" SET DEFAULT nextval('"Cliente_ID_seq"'::regclass);


--
-- TOC entry 1923 (class 2604 OID 43211)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Funcionario" ALTER COLUMN "ID" SET DEFAULT nextval('"Funcionario_ID_seq"'::regclass);


--
-- TOC entry 1928 (class 2604 OID 43259)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Ingrediente" ALTER COLUMN "ID" SET DEFAULT nextval('"Ingrediente_ID_seq"'::regclass);


--
-- TOC entry 1929 (class 2604 OID 43270)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "ItemDeMenu" ALTER COLUMN "ID" SET DEFAULT nextval('"ItemDeMenu_ID_seq"'::regclass);


--
-- TOC entry 1931 (class 2604 OID 43292)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Item_possui_Ingrediente" ALTER COLUMN "ID" SET DEFAULT nextval('"Item_possui_Ingrediente_ID_seq"'::regclass);


--
-- TOC entry 1926 (class 2604 OID 43228)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Mesa" ALTER COLUMN "ID" SET DEFAULT nextval('"Mesa_ID_seq"'::regclass);


--
-- TOC entry 1930 (class 2604 OID 43281)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Pedido" ALTER COLUMN "ID" SET DEFAULT nextval('"Pedido_ID_seq"'::regclass);


--
-- TOC entry 1932 (class 2604 OID 43310)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Pedido_possui_item" ALTER COLUMN "ID" SET DEFAULT nextval('"Pedido_possui_item_ID_seq"'::regclass);


--
-- TOC entry 1927 (class 2604 OID 43238)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva ALTER COLUMN "ID" SET DEFAULT nextval('"reserva_ID_seq"'::regclass);


--
-- TOC entry 2078 (class 0 OID 43193)
-- Dependencies: 189
-- Data for Name: Cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Cliente" ("ID", cpf, nome, endereco, telefone, login, senha) FROM stdin;



--
-- TOC entry 2110 (class 0 OID 0)
-- Dependencies: 188
-- Name: Cliente_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Cliente_ID_seq"', 1, false);


--
-- TOC entry 2080 (class 0 OID 43208)
-- Dependencies: 191
-- Data for Name: Funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Funcionario" (salario, cpf, nome, endereco, telefone, data_de_demissao, data_de_entrada, "ID", login, senha) FROM stdin;



--
-- TOC entry 2111 (class 0 OID 0)
-- Dependencies: 190
-- Name: Funcionario_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Funcionario_ID_seq"', 1, false);


--
-- TOC entry 2086 (class 0 OID 43256)
-- Dependencies: 197
-- Data for Name: Ingrediente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Ingrediente" ("ID", "QuantidadeEstoque", nome, "Unidade") FROM stdin;



--
-- TOC entry 2112 (class 0 OID 0)
-- Dependencies: 196
-- Name: Ingrediente_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Ingrediente_ID_seq"', 1, false);


--
-- TOC entry 2088 (class 0 OID 43267)
-- Dependencies: 199
-- Data for Name: ItemDeMenu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "ItemDeMenu" ("ID", "Nome", "Preco", categoria, "QuantidadeEstoque") FROM stdin;



--
-- TOC entry 2113 (class 0 OID 0)
-- Dependencies: 198
-- Name: ItemDeMenu_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"ItemDeMenu_ID_seq"', 1, false);


--
-- TOC entry 2092 (class 0 OID 43289)
-- Dependencies: 203
-- Data for Name: Item_possui_Ingrediente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Item_possui_Ingrediente" ("ID", "Quantidade", "Item", "Ingrediente") FROM stdin;



--
-- TOC entry 2114 (class 0 OID 0)
-- Dependencies: 202
-- Name: Item_possui_Ingrediente_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Item_possui_Ingrediente_ID_seq"', 1, false);


--
-- TOC entry 2082 (class 0 OID 43225)
-- Dependencies: 193
-- Data for Name: Mesa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Mesa" ("ID", numero, lugares) FROM stdin;



--
-- TOC entry 2115 (class 0 OID 0)
-- Dependencies: 192
-- Name: Mesa_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Mesa_ID_seq"', 1, false);


--
-- TOC entry 2090 (class 0 OID 43278)
-- Dependencies: 201
-- Data for Name: Pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Pedido" ("Data", "Endereco", "Observacoes", "ID", "Cliente") FROM stdin;



--
-- TOC entry 2116 (class 0 OID 0)
-- Dependencies: 200
-- Name: Pedido_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Pedido_ID_seq"', 1, false);


--
-- TOC entry 2094 (class 0 OID 43307)
-- Dependencies: 205
-- Data for Name: Pedido_possui_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Pedido_possui_item" ("ID", "Pedido", "Item") FROM stdin;
\.


--
-- TOC entry 2117 (class 0 OID 0)
-- Dependencies: 204
-- Name: Pedido_possui_item_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Pedido_possui_item_ID_seq"', 1, false);


--
-- TOC entry 2084 (class 0 OID 43235)
-- Dependencies: 195
-- Data for Name: reserva; Type: TABLE DATA; Schema: public; Owner: postgres
--
/*
COPY reserva ("ID", reserva_cliente_fk, reserva_mesa_fk, data) FROM stdin;
\.


--
-- TOC entry 2118 (class 0 OID 0)
-- Dependencies: 194
-- Name: reserva_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"reserva_ID_seq"', 1, false);

*/
--
-- TOC entry 1956 (class 2606 OID 43275)
-- Name: Item_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "ItemDeMenu"
    ADD CONSTRAINT "Item_pk" PRIMARY KEY ("ID");


--
-- TOC entry 1960 (class 2606 OID 43294)
-- Name: Item_possui_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Item_possui_Ingrediente"
    ADD CONSTRAINT "Item_possui_pk" PRIMARY KEY ("ID");


--
-- TOC entry 1962 (class 2606 OID 43312)
-- Name: Pedido_possui_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Pedido_possui_item"
    ADD CONSTRAINT "Pedido_possui_pk" PRIMARY KEY ("ID");


--
-- TOC entry 1934 (class 2606 OID 43203)
-- Name: cliente_login_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Cliente"
    ADD CONSTRAINT cliente_login_unique UNIQUE (login);


--
-- TOC entry 1936 (class 2606 OID 43205)
-- Name: cpf_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Cliente"
    ADD CONSTRAINT cpf_unique UNIQUE (cpf);


--
-- TOC entry 1940 (class 2606 OID 43220)
-- Name: funcionario_cpf_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Funcionario"
    ADD CONSTRAINT funcionario_cpf_unique UNIQUE (cpf);


--
-- TOC entry 1942 (class 2606 OID 43222)
-- Name: funcionario_login_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Funcionario"
    ADD CONSTRAINT funcionario_login_unique UNIQUE (login);


--
-- TOC entry 1944 (class 2606 OID 43218)
-- Name: funcionario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Funcionario"
    ADD CONSTRAINT funcionario_pk PRIMARY KEY ("ID");


--
-- TOC entry 1954 (class 2606 OID 43264)
-- Name: ingrediente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Ingrediente"
    ADD CONSTRAINT ingrediente_pk PRIMARY KEY ("ID");


--
-- TOC entry 1946 (class 2606 OID 43230)
-- Name: mesa_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Mesa"
    ADD CONSTRAINT mesa_pk PRIMARY KEY ("ID");


--
-- TOC entry 1950 (class 2606 OID 43242)
-- Name: no_duas_reservas_ao_mesmo_tempo_por_mesa; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT no_duas_reservas_ao_mesmo_tempo_por_mesa UNIQUE (reserva_mesa_fk, data);


--
-- TOC entry 1948 (class 2606 OID 43232)
-- Name: numero_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Mesa"
    ADD CONSTRAINT numero_unique UNIQUE (numero);


--
-- TOC entry 1958 (class 2606 OID 43286)
-- Name: pedido_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Pedido"
    ADD CONSTRAINT pedido_pk PRIMARY KEY ("ID");


--
-- TOC entry 1938 (class 2606 OID 43201)
-- Name: pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Cliente"
    ADD CONSTRAINT pk PRIMARY KEY ("ID");


--
-- TOC entry 1952 (class 2606 OID 43240)
-- Name: reserva_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_pk PRIMARY KEY ("ID");


--
-- TOC entry 1965 (class 2606 OID 43323)
-- Name: Cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Pedido"
    ADD CONSTRAINT "Cliente_fk" FOREIGN KEY ("Cliente") REFERENCES "Cliente"("ID");


--
-- TOC entry 1966 (class 2606 OID 43295)
-- Name: Ingrediente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Item_possui_Ingrediente"
    ADD CONSTRAINT "Ingrediente_fk" FOREIGN KEY ("Ingrediente") REFERENCES "Ingrediente"("ID");


--
-- TOC entry 1967 (class 2606 OID 43300)
-- Name: Item_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Item_possui_Ingrediente"
    ADD CONSTRAINT "Item_fk" FOREIGN KEY ("Item") REFERENCES "ItemDeMenu"("ID");


--
-- TOC entry 1968 (class 2606 OID 43313)
-- Name: Item_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Pedido_possui_item"
    ADD CONSTRAINT "Item_fk" FOREIGN KEY ("Item") REFERENCES "ItemDeMenu"("ID");


--
-- TOC entry 1969 (class 2606 OID 43318)
-- Name: Pedido_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Pedido_possui_item"
    ADD CONSTRAINT "Pedido_fk" FOREIGN KEY ("Pedido") REFERENCES "Pedido"("ID");


--
-- TOC entry 1963 (class 2606 OID 43243)
-- Name: reserva_cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_cliente_fk FOREIGN KEY (reserva_cliente_fk) REFERENCES "Cliente"("ID");


--
-- TOC entry 1964 (class 2606 OID 43248)
-- Name: reserva_mesa_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_mesa_fk FOREIGN KEY (reserva_mesa_fk) REFERENCES "Mesa"("ID");


--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-03-27 14:50:16

--
-- PostgreSQL database dump complete
--

