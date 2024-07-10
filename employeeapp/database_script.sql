--
-- PostgreSQL database dump
--

-- Dumped from database version 14.12 (Ubuntu 14.12-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 16.3

-- Started on 2024-07-10 12:36:33

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 212 (class 1259 OID 40986)
-- Name: authorities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authorities (
    username text NOT NULL,
    authority text NOT NULL
);


ALTER TABLE public.authorities OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 40971)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    id integer NOT NULL,
    first_name text,
    last_name text,
    email text
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 40970)
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.employee ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 211 (class 1259 OID 40978)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username text NOT NULL,
    password text,
    enabled boolean DEFAULT false
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3327 (class 0 OID 40986)
-- Dependencies: 212
-- Data for Name: authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.authorities (username, authority) FROM stdin;
john	ROLE_EMPLOYEE
mary	ROLE_EMPLOYEE
mary	ROLE_MANAGER
susan	ROLE_EMPLOYEE
susan	ROLE_MANAGER
susan	ROLE_ADMIN
\.


--
-- TOC entry 3325 (class 0 OID 40971)
-- Dependencies: 210
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee (id, first_name, last_name, email) FROM stdin;
1	Leslie	Andrews	leslie@luv2code.com
2	Emma	Baumgarten	emma@luv2code.com
3	Avani	Gupta	avani@luv2code.com
4	Yuri	Petrov	yuri@luv2code.com
5	Juan	Vega	juan@luv2code.com
\.


--
-- TOC entry 3326 (class 0 OID 40978)
-- Dependencies: 211
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (username, password, enabled) FROM stdin;
john	{bcrypt}$2a$12$YyRzioSzz4hpI.cH7TVM6ufUusUFBg7C7ggJhe7z8vNB1g6tkL6WO	t
mary	{bcrypt}$2a$12$KbevkOK1Cgs8zFyAZacbD.rwiCbaME9OOxHhUxoRgM2F/PdlimLw2	t
susan	{bcrypt}$2a$12$4wMENy2x1qgYxH8xziSDxeaGn2cIZLtXkFmVMlexlK0238RATm9dK	t
\.


--
-- TOC entry 3334 (class 0 OID 0)
-- Dependencies: 209
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employee_id_seq', 9, true);


--
-- TOC entry 3183 (class 2606 OID 40992)
-- Name: authorities authorities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT authorities_pkey PRIMARY KEY (username, authority);


--
-- TOC entry 3179 (class 2606 OID 40977)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- TOC entry 3181 (class 2606 OID 40985)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);


--
-- TOC entry 3184 (class 2606 OID 40993)
-- Name: authorities username; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT username FOREIGN KEY (username) REFERENCES public.users(username);


--
-- TOC entry 3333 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2024-07-10 12:36:33

--
-- PostgreSQL database dump complete
--

