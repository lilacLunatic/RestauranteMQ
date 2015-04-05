--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.1
-- Started on 2015-04-05 09:00:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;


--
-- TOC entry 2063 (class 1262 OID 12029)
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

--CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE postgres OWNER TO postgres;

--\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2064 (class 1262 OID 12029)
-- Dependencies: 2063
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

--CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 191 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 191
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 190 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 190
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = public, pg_catalog;


--
-- TOC entry 172 (class 1259 OID 32783)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cliente (
    id integer NOT NULL,
    cpf character varying,
    nome character varying,
    endereco character varying,
    telefone character varying,
    login character varying,
    senha character varying
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 32789)
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
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 173
-- Name: Cliente_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Cliente_ID_seq" OWNED BY cliente.id;


--
-- TOC entry 174 (class 1259 OID 32791)
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
-- TOC entry 175 (class 1259 OID 32799)
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
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 175
-- Name: Funcionario_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Funcionario_ID_seq" OWNED BY funcionario.id;


--
-- TOC entry 176 (class 1259 OID 32801)
-- Name: ingrediente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ingrediente (
    id integer NOT NULL,
    quantidadeestoque integer,
    nome character varying,
    unidade character varying
);


ALTER TABLE public.ingrediente OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 32807)
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
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 177
-- Name: Ingrediente_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Ingrediente_ID_seq" OWNED BY ingrediente.id;


--
-- TOC entry 178 (class 1259 OID 32809)
-- Name: itemdemenu; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE itemdemenu (
    id integer NOT NULL,
    nome character varying,
    preco real,
    categoria character varying,
    quantidadeestoque integer
);


ALTER TABLE public.itemdemenu OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 32815)
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
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 179
-- Name: ItemDeMenu_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "ItemDeMenu_ID_seq" OWNED BY itemdemenu.id;


--
-- TOC entry 180 (class 1259 OID 32817)
-- Name: itempossuiingrediente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE itempossuiingrediente (
    id integer NOT NULL,
    quantidade integer,
    item integer,
    ingrediente integer
);


ALTER TABLE public.itempossuiingrediente OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 32820)
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
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 181
-- Name: Item_possui_Ingrediente_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Item_possui_Ingrediente_ID_seq" OWNED BY itempossuiingrediente.id;


--
-- TOC entry 182 (class 1259 OID 32822)
-- Name: mesa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mesa (
    id integer NOT NULL,
    numero integer,
    lugares integer
);


ALTER TABLE public.mesa OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 32825)
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
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 183
-- Name: Mesa_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Mesa_ID_seq" OWNED BY mesa.id;


--
-- TOC entry 184 (class 1259 OID 32827)
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pedido (
    data timestamp without time zone,
    endereco character varying,
    observacoes character varying,
    id integer NOT NULL,
    cliente integer
);


ALTER TABLE public.pedido OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 32833)
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
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 185
-- Name: Pedido_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Pedido_ID_seq" OWNED BY pedido.id;


--
-- TOC entry 186 (class 1259 OID 32835)
-- Name: pedidopossuiitem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pedidopossuiitem (
    id integer NOT NULL,
    pedido integer,
    item integer
);


ALTER TABLE public.pedidopossuiitem OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 32838)
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
-- TOC entry 2076 (class 0 OID 0)
-- Dependencies: 187
-- Name: Pedido_possui_item_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Pedido_possui_item_ID_seq" OWNED BY pedidopossuiitem.id;


--
-- TOC entry 188 (class 1259 OID 32840)
-- Name: reserva; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reserva (
    id integer NOT NULL,
    reserva_cliente_fk integer,
    reserva_mesa_fk integer,
    data timestamp without time zone
);


ALTER TABLE public.reserva OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 32843)
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
-- TOC entry 2077 (class 0 OID 0)
-- Dependencies: 189
-- Name: reserva_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "reserva_ID_seq" OWNED BY reserva.id;


--
-- TOC entry 1885 (class 2604 OID 32845)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente ALTER COLUMN id SET DEFAULT nextval('"Cliente_ID_seq"'::regclass);


--
-- TOC entry 1886 (class 2604 OID 32846)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario ALTER COLUMN id SET DEFAULT nextval('"Funcionario_ID_seq"'::regclass);


--
-- TOC entry 1890 (class 2604 OID 32847)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ingrediente ALTER COLUMN id SET DEFAULT nextval('"Ingrediente_ID_seq"'::regclass);


--
-- TOC entry 1891 (class 2604 OID 32848)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itemdemenu ALTER COLUMN id SET DEFAULT nextval('"ItemDeMenu_ID_seq"'::regclass);


--
-- TOC entry 1892 (class 2604 OID 32849)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itempossuiingrediente ALTER COLUMN id SET DEFAULT nextval('"Item_possui_Ingrediente_ID_seq"'::regclass);


--
-- TOC entry 1893 (class 2604 OID 32850)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mesa ALTER COLUMN id SET DEFAULT nextval('"Mesa_ID_seq"'::regclass);


--
-- TOC entry 1894 (class 2604 OID 32851)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido ALTER COLUMN id SET DEFAULT nextval('"Pedido_ID_seq"'::regclass);


--
-- TOC entry 1895 (class 2604 OID 32852)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidopossuiitem ALTER COLUMN id SET DEFAULT nextval('"Pedido_possui_item_ID_seq"'::regclass);


--
-- TOC entry 1896 (class 2604 OID 32853)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva ALTER COLUMN id SET DEFAULT nextval('"reserva_ID_seq"'::regclass);


--
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 173
-- Name: Cliente_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Cliente_ID_seq"', 1, false);


--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 175
-- Name: Funcionario_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Funcionario_ID_seq"', 1, false);


--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 177
-- Name: Ingrediente_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Ingrediente_ID_seq"', 1, false);


--
-- TOC entry 2081 (class 0 OID 0)
-- Dependencies: 179
-- Name: ItemDeMenu_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"ItemDeMenu_ID_seq"', 1, false);


--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 181
-- Name: Item_possui_Ingrediente_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Item_possui_Ingrediente_ID_seq"', 1, false);


--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 183
-- Name: Mesa_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Mesa_ID_seq"', 1, false);


--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 185
-- Name: Pedido_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Pedido_ID_seq"', 1, false);


--
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 187
-- Name: Pedido_possui_item_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Pedido_possui_item_ID_seq"', 1, false);


--
-- TOC entry 2041 (class 0 OID 32783)
-- Dependencies: 172
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2043 (class 0 OID 32791)
-- Dependencies: 174
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2045 (class 0 OID 32801)
-- Dependencies: 176
-- Data for Name: ingrediente; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2047 (class 0 OID 32809)
-- Dependencies: 178
-- Data for Name: itemdemenu; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2049 (class 0 OID 32817)
-- Dependencies: 180
-- Data for Name: itempossuiingrediente; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2051 (class 0 OID 32822)
-- Dependencies: 182
-- Data for Name: mesa; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2053 (class 0 OID 32827)
-- Dependencies: 184
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2055 (class 0 OID 32835)
-- Dependencies: 186
-- Data for Name: pedidopossuiitem; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2057 (class 0 OID 32840)
-- Dependencies: 188
-- Data for Name: reserva; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 189
-- Name: reserva_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"reserva_ID_seq"', 1, false);


--
-- TOC entry 1912 (class 2606 OID 32855)
-- Name: Item_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY itemdemenu
    ADD CONSTRAINT "Item_pk" PRIMARY KEY (id);


--
-- TOC entry 1914 (class 2606 OID 32857)
-- Name: Item_possui_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY itempossuiingrediente
    ADD CONSTRAINT "Item_possui_pk" PRIMARY KEY (id);


--
-- TOC entry 1922 (class 2606 OID 32859)
-- Name: Pedido_possui_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pedidopossuiitem
    ADD CONSTRAINT "Pedido_possui_pk" PRIMARY KEY (id);


--
-- TOC entry 1898 (class 2606 OID 32861)
-- Name: cliente_login_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_login_unique UNIQUE (login);


--
-- TOC entry 1900 (class 2606 OID 32863)
-- Name: cpf_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cpf_unique UNIQUE (cpf);


--
-- TOC entry 1904 (class 2606 OID 32865)
-- Name: funcionario_cpf_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_cpf_unique UNIQUE (cpf);


--
-- TOC entry 1906 (class 2606 OID 32867)
-- Name: funcionario_login_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_login_unique UNIQUE (login);


--
-- TOC entry 1908 (class 2606 OID 32869)
-- Name: funcionario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pk PRIMARY KEY (id);


--
-- TOC entry 1910 (class 2606 OID 32871)
-- Name: ingrediente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ingrediente
    ADD CONSTRAINT ingrediente_pk PRIMARY KEY (id);


--
-- TOC entry 1916 (class 2606 OID 32873)
-- Name: mesa_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mesa
    ADD CONSTRAINT mesa_pk PRIMARY KEY (id);


--
-- TOC entry 1924 (class 2606 OID 32875)
-- Name: no_duas_reservas_ao_mesmo_tempo_por_mesa; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT no_duas_reservas_ao_mesmo_tempo_por_mesa UNIQUE (reserva_mesa_fk, data);


--
-- TOC entry 1918 (class 2606 OID 32877)
-- Name: numero_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mesa
    ADD CONSTRAINT numero_unique UNIQUE (numero);


--
-- TOC entry 1920 (class 2606 OID 32879)
-- Name: pedido_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pk PRIMARY KEY (id);


--
-- TOC entry 1902 (class 2606 OID 32881)
-- Name: pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT pk PRIMARY KEY (id);


--
-- TOC entry 1926 (class 2606 OID 32883)
-- Name: reserva_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_pk PRIMARY KEY (id);


--
-- TOC entry 1929 (class 2606 OID 32884)
-- Name: Cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT "Cliente_fk" FOREIGN KEY (cliente) REFERENCES cliente(id);


--
-- TOC entry 1927 (class 2606 OID 32889)
-- Name: Ingrediente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itempossuiingrediente
    ADD CONSTRAINT "Ingrediente_fk" FOREIGN KEY (ingrediente) REFERENCES ingrediente(id);


--
-- TOC entry 1928 (class 2606 OID 32894)
-- Name: Item_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itempossuiingrediente
    ADD CONSTRAINT "Item_fk" FOREIGN KEY (item) REFERENCES itemdemenu(id);


--
-- TOC entry 1930 (class 2606 OID 32899)
-- Name: Item_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidopossuiitem
    ADD CONSTRAINT "Item_fk" FOREIGN KEY (item) REFERENCES itemdemenu(id);


--
-- TOC entry 1931 (class 2606 OID 32904)
-- Name: Pedido_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidopossuiitem
    ADD CONSTRAINT "Pedido_fk" FOREIGN KEY (pedido) REFERENCES pedido(id);


--
-- TOC entry 1932 (class 2606 OID 32909)
-- Name: reserva_cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_cliente_fk FOREIGN KEY (reserva_cliente_fk) REFERENCES cliente(id);


--
-- TOC entry 1933 (class 2606 OID 32914)
-- Name: reserva_mesa_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_mesa_fk FOREIGN KEY (reserva_mesa_fk) REFERENCES mesa(id);


--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-04-05 09:00:39

--
-- PostgreSQL database dump complete
--

