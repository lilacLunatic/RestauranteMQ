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

CREATE TABLE cliente (
    ID integer NOT NULL,
    cpf character varying,
    nome character varying,
    endereco character varying,
    telefone character varying,
    login character varying,
    senha character varying
);


ALTER TABLE public.cliente OWNER TO postgres;

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

ALTER SEQUENCE "Cliente_ID_seq" OWNED BY cliente.ID;


--
-- TOC entry 191 (class 1259 OID 43208)
-- Name: Funcionario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE funcionario (
    salario double precision,
    cpf character varying,
    nome character varying,
    endereco character varying,
    telefone character varying,
    data_de_demissao date,
    data_de_entrada date,
    ID integer NOT NULL,
    login character varying,
    senha character varying,
    CONSTRAINT data_de_entrada_not_null CHECK ((data_de_entrada IS NOT NULL)),
    CONSTRAINT entrada_antes_de_demissao CHECK (((data_de_demissao IS NULL) OR (data_de_entrada <= data_de_demissao)))
);


ALTER TABLE public.funcionario OWNER TO postgres;

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

ALTER SEQUENCE "Funcionario_ID_seq" OWNED BY funcionario.ID;


--
-- TOC entry 197 (class 1259 OID 43256)
-- Name: Ingrediente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ingrediente (
    ID integer NOT NULL,
    quantidadeEstoque integer,
    nome character varying,
    unidade character varying
);


ALTER TABLE public.ingrediente OWNER TO postgres;

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

ALTER SEQUENCE "Ingrediente_ID_seq" OWNED BY ingrediente.ID;


--
-- TOC entry 199 (class 1259 OID 43267)
-- Name: ItemDeMenu; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE itemDeMenu (
    ID integer NOT NULL,
    nome character varying,
    preco real,
    categoria character varying,
    quantidadeEstoque integer
);


ALTER TABLE public.itemDeMenu OWNER TO postgres;

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

ALTER SEQUENCE "ItemDeMenu_ID_seq" OWNED BY itemDeMenu.ID;


--
-- TOC entry 203 (class 1259 OID 43289)
-- Name: Item_possui_Ingrediente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE itemPossuiIngrediente (
    ID integer NOT NULL,
    quantidade integer,
    item integer,
    ingrediente integer
);


ALTER TABLE public.itemPossuiIngrediente OWNER TO postgres;

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

ALTER SEQUENCE "Item_possui_Ingrediente_ID_seq" OWNED BY itemPossuiIngrediente.ID;


--
-- TOC entry 193 (class 1259 OID 43225)
-- Name: Mesa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mesa (
    ID integer NOT NULL,
    numero integer,
    lugares integer
);


ALTER TABLE public.mesa OWNER TO postgres;

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

ALTER SEQUENCE "Mesa_ID_seq" OWNED BY mesa.ID;


--
-- TOC entry 201 (class 1259 OID 43278)
-- Name: Pedido; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pedido (
    data timestamp without time zone,
    endereco character varying,
    observacoes character varying,
    ID integer NOT NULL,
    cliente integer
);


ALTER TABLE public.pedido OWNER TO postgres;

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

ALTER SEQUENCE "Pedido_ID_seq" OWNED BY pedido.ID;


--
-- TOC entry 205 (class 1259 OID 43307)
-- Name: Pedido_possui_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pedidoPossuiItem (
    ID integer NOT NULL,
    pedido integer,
    item integer
);


ALTER TABLE public.pedidoPossuiItem OWNER TO postgres;

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

ALTER SEQUENCE "Pedido_possui_item_ID_seq" OWNED BY pedidoPossuiItem.ID;


--
-- TOC entry 195 (class 1259 OID 43235)
-- Name: reserva; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reserva (
    ID integer NOT NULL,
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

ALTER SEQUENCE "reserva_ID_seq" OWNED BY reserva.ID;


--
-- TOC entry 1922 (class 2604 OID 43196)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente ALTER COLUMN ID SET DEFAULT nextval('"Cliente_ID_seq"'::regclass);


--
-- TOC entry 1923 (class 2604 OID 43211)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario ALTER COLUMN ID SET DEFAULT nextval('"Funcionario_ID_seq"'::regclass);


--
-- TOC entry 1928 (class 2604 OID 43259)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ingrediente ALTER COLUMN ID SET DEFAULT nextval('"Ingrediente_ID_seq"'::regclass);


--
-- TOC entry 1929 (class 2604 OID 43270)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itemDeMenu ALTER COLUMN ID SET DEFAULT nextval('"ItemDeMenu_ID_seq"'::regclass);


--
-- TOC entry 1931 (class 2604 OID 43292)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itemPossuiIngrediente ALTER COLUMN ID SET DEFAULT nextval('"Item_possui_Ingrediente_ID_seq"'::regclass);


--
-- TOC entry 1926 (class 2604 OID 43228)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mesa ALTER COLUMN ID SET DEFAULT nextval('"Mesa_ID_seq"'::regclass);


--
-- TOC entry 1930 (class 2604 OID 43281)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido ALTER COLUMN ID SET DEFAULT nextval('"Pedido_ID_seq"'::regclass);


--
-- TOC entry 1932 (class 2604 OID 43310)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidoPossuiItem ALTER COLUMN ID SET DEFAULT nextval('"Pedido_possui_item_ID_seq"'::regclass);


--
-- TOC entry 1927 (class 2604 OID 43238)
-- Name: ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva ALTER COLUMN ID SET DEFAULT nextval('"reserva_ID_seq"'::regclass);


--
-- TOC entry 2078 (class 0 OID 43193)
-- Dependencies: 189
-- Data for Name: Cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--
/*
COPY cliente (ID, cpf, nome, endereco, telefone, login, senha) FROM stdin;



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

COPY funcionario (salario, cpf, nome, endereco, telefone, data_de_demissao, data_de_entrada, ID, login, senha) FROM stdin;



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

COPY ingrediente (ID, quantidadeEstoque, nome, unidade) FROM stdin;



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

COPY itemDeMenu (ID, nome, preco, categoria, quantidadeEstoque) FROM stdin;



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

COPY itemPossuiIngrediente (ID, quantidade, item, ingrediente) FROM stdin;



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

COPY mesa (ID, numero, lugares) FROM stdin;



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

COPY pedido (data, endereco, observacoes, ID, cliente) FROM stdin;



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

COPY pedidoPossuiItem (ID, pedido, item) FROM stdin;
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

COPY reserva (ID, reserva_cliente_fk, reserva_mesa_fk, data) FROM stdin;
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

ALTER TABLE ONLY itemDeMenu
    ADD CONSTRAINT "Item_pk" PRIMARY KEY (ID);


--
-- TOC entry 1960 (class 2606 OID 43294)
-- Name: Item_possui_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY itemPossuiIngrediente
    ADD CONSTRAINT "Item_possui_pk" PRIMARY KEY (ID);


--
-- TOC entry 1962 (class 2606 OID 43312)
-- Name: Pedido_possui_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pedidoPossuiItem
    ADD CONSTRAINT "Pedido_possui_pk" PRIMARY KEY (ID);


--
-- TOC entry 1934 (class 2606 OID 43203)
-- Name: cliente_login_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_login_unique UNIQUE (login);


--
-- TOC entry 1936 (class 2606 OID 43205)
-- Name: cpf_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cpf_unique UNIQUE (cpf);


--
-- TOC entry 1940 (class 2606 OID 43220)
-- Name: funcionario_cpf_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_cpf_unique UNIQUE (cpf);


--
-- TOC entry 1942 (class 2606 OID 43222)
-- Name: funcionario_login_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_login_unique UNIQUE (login);


--
-- TOC entry 1944 (class 2606 OID 43218)
-- Name: funcionario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pk PRIMARY KEY (ID);


--
-- TOC entry 1954 (class 2606 OID 43264)
-- Name: ingrediente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ingrediente
    ADD CONSTRAINT ingrediente_pk PRIMARY KEY (ID);


--
-- TOC entry 1946 (class 2606 OID 43230)
-- Name: mesa_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mesa
    ADD CONSTRAINT mesa_pk PRIMARY KEY (ID);


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

ALTER TABLE ONLY mesa
    ADD CONSTRAINT numero_unique UNIQUE (numero);


--
-- TOC entry 1958 (class 2606 OID 43286)
-- Name: pedido_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pk PRIMARY KEY (ID);


--
-- TOC entry 1938 (class 2606 OID 43201)
-- Name: pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT pk PRIMARY KEY (ID);


--
-- TOC entry 1952 (class 2606 OID 43240)
-- Name: reserva_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_pk PRIMARY KEY (ID);


--
-- TOC entry 1965 (class 2606 OID 43323)
-- Name: Cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT "Cliente_fk" FOREIGN KEY (cliente) REFERENCES cliente(ID);


--
-- TOC entry 1966 (class 2606 OID 43295)
-- Name: Ingrediente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itemPossuiIngrediente
    ADD CONSTRAINT "Ingrediente_fk" FOREIGN KEY (ingrediente) REFERENCES ingrediente(ID);


--
-- TOC entry 1967 (class 2606 OID 43300)
-- Name: Item_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itemPossuiIngrediente
    ADD CONSTRAINT "Item_fk" FOREIGN KEY (item) REFERENCES itemDeMenu(ID);


--
-- TOC entry 1968 (class 2606 OID 43313)
-- Name: Item_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidoPossuiItem
    ADD CONSTRAINT "Item_fk" FOREIGN KEY (item) REFERENCES itemDeMenu(ID);


--
-- TOC entry 1969 (class 2606 OID 43318)
-- Name: Pedido_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidoPossuiItem
    ADD CONSTRAINT "Pedido_fk" FOREIGN KEY (pedido) REFERENCES pedido(ID);


--
-- TOC entry 1963 (class 2606 OID 43243)
-- Name: reserva_cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_cliente_fk FOREIGN KEY (reserva_cliente_fk) REFERENCES cliente(ID);


--
-- TOC entry 1964 (class 2606 OID 43248)
-- Name: reserva_mesa_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_mesa_fk FOREIGN KEY (reserva_mesa_fk) REFERENCES mesa(ID);


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

