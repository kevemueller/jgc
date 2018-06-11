--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 9.6.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: gcfull; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE gcfull WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE gcfull OWNER TO postgres;

\connect gcfull

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
    guid character varying(32) NOT NULL,
    name character varying(2048) NOT NULL,
    account_type character varying(2048) NOT NULL,
    commodity_guid character varying(32),
    commodity_scu integer NOT NULL,
    non_std_scu integer NOT NULL,
    parent_guid character varying(32),
    code character varying(2048),
    description character varying(2048),
    hidden integer,
    placeholder integer
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- Name: billterms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.billterms (
    guid character varying(32) NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048) NOT NULL,
    refcount integer NOT NULL,
    invisible integer NOT NULL,
    parent character varying(32),
    type character varying(2048) NOT NULL,
    duedays integer,
    discountdays integer,
    discount_num bigint,
    discount_denom bigint,
    cutoff integer
);


ALTER TABLE public.billterms OWNER TO postgres;

--
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    guid character varying(32) NOT NULL,
    root_account_guid character varying(32) NOT NULL,
    root_template_guid character varying(32) NOT NULL
);


ALTER TABLE public.books OWNER TO postgres;

--
-- Name: budget_amounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.budget_amounts (
    id integer NOT NULL,
    budget_guid character varying(32) NOT NULL,
    account_guid character varying(32) NOT NULL,
    period_num integer NOT NULL,
    amount_num bigint NOT NULL,
    amount_denom bigint NOT NULL
);


ALTER TABLE public.budget_amounts OWNER TO postgres;

--
-- Name: budget_amounts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.budget_amounts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.budget_amounts_id_seq OWNER TO postgres;

--
-- Name: budget_amounts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.budget_amounts_id_seq OWNED BY public.budget_amounts.id;


--
-- Name: budgets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.budgets (
    guid character varying(32) NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    num_periods integer NOT NULL
);


ALTER TABLE public.budgets OWNER TO postgres;

--
-- Name: commodities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.commodities (
    guid character varying(32) NOT NULL,
    namespace character varying(2048) NOT NULL,
    mnemonic character varying(2048) NOT NULL,
    fullname character varying(2048),
    cusip character varying(2048),
    fraction integer NOT NULL,
    quote_flag integer NOT NULL,
    quote_source character varying(2048),
    quote_tz character varying(2048)
);


ALTER TABLE public.commodities OWNER TO postgres;

--
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    guid character varying(32) NOT NULL,
    name character varying(2048) NOT NULL,
    id character varying(2048) NOT NULL,
    notes character varying(2048) NOT NULL,
    active integer NOT NULL,
    discount_num bigint NOT NULL,
    discount_denom bigint NOT NULL,
    credit_num bigint NOT NULL,
    credit_denom bigint NOT NULL,
    currency character varying(32) NOT NULL,
    tax_override integer NOT NULL,
    addr_name character varying(1024),
    addr_addr1 character varying(1024),
    addr_addr2 character varying(1024),
    addr_addr3 character varying(1024),
    addr_addr4 character varying(1024),
    addr_phone character varying(128),
    addr_fax character varying(128),
    addr_email character varying(256),
    shipaddr_name character varying(1024),
    shipaddr_addr1 character varying(1024),
    shipaddr_addr2 character varying(1024),
    shipaddr_addr3 character varying(1024),
    shipaddr_addr4 character varying(1024),
    shipaddr_phone character varying(128),
    shipaddr_fax character varying(128),
    shipaddr_email character varying(256),
    terms character varying(32),
    tax_included integer,
    taxtable character varying(32)
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees (
    guid character varying(32) NOT NULL,
    username character varying(2048) NOT NULL,
    id character varying(2048) NOT NULL,
    language character varying(2048) NOT NULL,
    acl character varying(2048) NOT NULL,
    active integer NOT NULL,
    currency character varying(32) NOT NULL,
    ccard_guid character varying(32),
    workday_num bigint NOT NULL,
    workday_denom bigint NOT NULL,
    rate_num bigint NOT NULL,
    rate_denom bigint NOT NULL,
    addr_name character varying(1024),
    addr_addr1 character varying(1024),
    addr_addr2 character varying(1024),
    addr_addr3 character varying(1024),
    addr_addr4 character varying(1024),
    addr_phone character varying(128),
    addr_fax character varying(128),
    addr_email character varying(256)
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- Name: entries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.entries (
    guid character varying(32) NOT NULL,
    date timestamp without time zone NOT NULL,
    date_entered timestamp without time zone,
    description character varying(2048),
    action character varying(2048),
    notes character varying(2048),
    quantity_num bigint,
    quantity_denom bigint,
    i_acct character varying(32),
    i_price_num bigint,
    i_price_denom bigint,
    i_discount_num bigint,
    i_discount_denom bigint,
    invoice character varying(32),
    i_disc_type character varying(2048),
    i_disc_how character varying(2048),
    i_taxable integer,
    i_taxincluded integer,
    i_taxtable character varying(32),
    b_acct character varying(32),
    b_price_num bigint,
    b_price_denom bigint,
    bill character varying(32),
    b_taxable integer,
    b_taxincluded integer,
    b_taxtable character varying(32),
    b_paytype integer,
    billable integer,
    billto_type integer,
    billto_guid character varying(32),
    order_guid character varying(32)
);


ALTER TABLE public.entries OWNER TO postgres;

--
-- Name: gnclock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gnclock (
    hostname character varying(255),
    pid integer
);


ALTER TABLE public.gnclock OWNER TO postgres;

--
-- Name: invoices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoices (
    guid character varying(32) NOT NULL,
    id character varying(2048) NOT NULL,
    date_opened timestamp without time zone,
    date_posted timestamp without time zone,
    notes character varying(2048) NOT NULL,
    active integer NOT NULL,
    currency character varying(32) NOT NULL,
    owner_type integer,
    owner_guid character varying(32),
    terms character varying(32),
    billing_id character varying(2048),
    post_txn character varying(32),
    post_lot character varying(32),
    post_acc character varying(32),
    billto_type integer,
    billto_guid character varying(32),
    charge_amt_num bigint,
    charge_amt_denom bigint
);


ALTER TABLE public.invoices OWNER TO postgres;

--
-- Name: jobs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jobs (
    guid character varying(32) NOT NULL,
    id character varying(2048) NOT NULL,
    name character varying(2048) NOT NULL,
    reference character varying(2048) NOT NULL,
    active integer NOT NULL,
    owner_type integer,
    owner_guid character varying(32)
);


ALTER TABLE public.jobs OWNER TO postgres;

--
-- Name: lots; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lots (
    guid character varying(32) NOT NULL,
    account_guid character varying(32),
    is_closed integer NOT NULL
);


ALTER TABLE public.lots OWNER TO postgres;

--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    guid character varying(32) NOT NULL,
    id character varying(2048) NOT NULL,
    notes character varying(2048) NOT NULL,
    reference character varying(2048) NOT NULL,
    active integer NOT NULL,
    date_opened timestamp without time zone NOT NULL,
    date_closed timestamp without time zone NOT NULL,
    owner_type integer NOT NULL,
    owner_guid character varying(32) NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: prices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prices (
    guid character varying(32) NOT NULL,
    commodity_guid character varying(32) NOT NULL,
    currency_guid character varying(32) NOT NULL,
    date timestamp without time zone NOT NULL,
    source character varying(2048),
    type character varying(2048),
    value_num bigint NOT NULL,
    value_denom bigint NOT NULL
);


ALTER TABLE public.prices OWNER TO postgres;

--
-- Name: recurrences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recurrences (
    id integer NOT NULL,
    obj_guid character varying(32) NOT NULL,
    recurrence_mult integer NOT NULL,
    recurrence_period_type character varying(2048) NOT NULL,
    recurrence_period_start date NOT NULL,
    recurrence_weekend_adjust character varying(2048) NOT NULL
);


ALTER TABLE public.recurrences OWNER TO postgres;

--
-- Name: recurrences_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recurrences_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recurrences_id_seq OWNER TO postgres;

--
-- Name: recurrences_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recurrences_id_seq OWNED BY public.recurrences.id;


--
-- Name: schedxactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.schedxactions (
    guid character varying(32) NOT NULL,
    name character varying(2048),
    enabled integer NOT NULL,
    start_date date,
    end_date date,
    last_occur date,
    num_occur integer NOT NULL,
    rem_occur integer NOT NULL,
    auto_create integer NOT NULL,
    auto_notify integer NOT NULL,
    adv_creation integer NOT NULL,
    adv_notify integer NOT NULL,
    instance_count integer NOT NULL,
    template_act_guid character varying(32) NOT NULL
);


ALTER TABLE public.schedxactions OWNER TO postgres;

--
-- Name: slots; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.slots (
    id integer NOT NULL,
    obj_guid character varying(32) NOT NULL,
    name character varying(4096) NOT NULL,
    slot_type integer NOT NULL,
    int64_val bigint,
    string_val character varying(4096),
    double_val double precision,
    timespec_val timestamp without time zone,
    guid_val character varying(32),
    numeric_val_num bigint,
    numeric_val_denom bigint,
    gdate_val date
);


ALTER TABLE public.slots OWNER TO postgres;

--
-- Name: slots_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.slots_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.slots_id_seq OWNER TO postgres;

--
-- Name: slots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.slots_id_seq OWNED BY public.slots.id;


--
-- Name: splits; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.splits (
    guid character varying(32) NOT NULL,
    tx_guid character varying(32) NOT NULL,
    account_guid character varying(32) NOT NULL,
    memo character varying(2048) NOT NULL,
    action character varying(2048) NOT NULL,
    reconcile_state character varying(1) NOT NULL,
    reconcile_date timestamp without time zone,
    value_num bigint NOT NULL,
    value_denom bigint NOT NULL,
    quantity_num bigint NOT NULL,
    quantity_denom bigint NOT NULL,
    lot_guid character varying(32)
);


ALTER TABLE public.splits OWNER TO postgres;

--
-- Name: taxtable_entries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.taxtable_entries (
    id integer NOT NULL,
    taxtable character varying(32) NOT NULL,
    account character varying(32) NOT NULL,
    amount_num bigint NOT NULL,
    amount_denom bigint NOT NULL,
    type integer NOT NULL
);


ALTER TABLE public.taxtable_entries OWNER TO postgres;

--
-- Name: taxtable_entries_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.taxtable_entries_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.taxtable_entries_id_seq OWNER TO postgres;

--
-- Name: taxtable_entries_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.taxtable_entries_id_seq OWNED BY public.taxtable_entries.id;


--
-- Name: taxtables; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.taxtables (
    guid character varying(32) NOT NULL,
    name character varying(50) NOT NULL,
    refcount bigint NOT NULL,
    invisible integer NOT NULL,
    parent character varying(32)
);


ALTER TABLE public.taxtables OWNER TO postgres;

--
-- Name: transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions (
    guid character varying(32) NOT NULL,
    currency_guid character varying(32) NOT NULL,
    num character varying(2048) NOT NULL,
    post_date timestamp without time zone,
    enter_date timestamp without time zone,
    description character varying(2048)
);


ALTER TABLE public.transactions OWNER TO postgres;

--
-- Name: vendors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vendors (
    guid character varying(32) NOT NULL,
    name character varying(2048) NOT NULL,
    id character varying(2048) NOT NULL,
    notes character varying(2048) NOT NULL,
    currency character varying(32) NOT NULL,
    active integer NOT NULL,
    tax_override integer NOT NULL,
    addr_name character varying(1024),
    addr_addr1 character varying(1024),
    addr_addr2 character varying(1024),
    addr_addr3 character varying(1024),
    addr_addr4 character varying(1024),
    addr_phone character varying(128),
    addr_fax character varying(128),
    addr_email character varying(256),
    terms character varying(32),
    tax_inc character varying(2048),
    tax_table character varying(32)
);


ALTER TABLE public.vendors OWNER TO postgres;

--
-- Name: versions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.versions (
    table_name character varying(50) NOT NULL,
    table_version integer NOT NULL
);


ALTER TABLE public.versions OWNER TO postgres;

--
-- Name: budget_amounts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.budget_amounts ALTER COLUMN id SET DEFAULT nextval('public.budget_amounts_id_seq'::regclass);


--
-- Name: recurrences id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recurrences ALTER COLUMN id SET DEFAULT nextval('public.recurrences_id_seq'::regclass);


--
-- Name: slots id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.slots ALTER COLUMN id SET DEFAULT nextval('public.slots_id_seq'::regclass);


--
-- Name: taxtable_entries id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.taxtable_entries ALTER COLUMN id SET DEFAULT nextval('public.taxtable_entries_id_seq'::regclass);


--
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.accounts (guid, name, account_type, commodity_guid, commodity_scu, non_std_scu, parent_guid, code, description, hidden, placeholder) FROM stdin;
56203fe974784ae9b223915b87fe7f2a	Root Account	ROOT	16c6e723ccfc48acab65d887a783203d	1000000	0	\N			0	0
ca8d3a13f4ac4289ac8f07a4c4d83cb3	Assets	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	56203fe974784ae9b223915b87fe7f2a		Assets	0	1
c1cc5c7eda2f4a7f9970329b39b65129	Investments	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	ca8d3a13f4ac4289ac8f07a4c4d83cb3		Investments	0	1
758a7b3ec38442cfaabd8e9456404ae9	Spouse Retirement	BANK	16c6e723ccfc48acab65d887a783203d	1000000	0	c1cc5c7eda2f4a7f9970329b39b65129		Spouse Retirement	0	1
19536be59be5439fa22484cdefe77187	Bond	STOCK	16c6e723ccfc48acab65d887a783203d	1000000	0	758a7b3ec38442cfaabd8e9456404ae9		Bond	0	0
f3f18d66cfae4c26a6167ed36f84e5ec	Stock	STOCK	16c6e723ccfc48acab65d887a783203d	1000000	0	758a7b3ec38442cfaabd8e9456404ae9		Stock	0	0
af950e4958664f5eb7074f7f68246215	Market Index	MUTUAL	16c6e723ccfc48acab65d887a783203d	1000000	0	758a7b3ec38442cfaabd8e9456404ae9		Market Index	0	0
6ebf2885febe4e35b6109ea4dbd35945	Mutual Fund	MUTUAL	16c6e723ccfc48acab65d887a783203d	1000000	0	758a7b3ec38442cfaabd8e9456404ae9		Mutual Fund	0	0
496fbd416c0141bc813d1db3299a6b11	Retirement	BANK	16c6e723ccfc48acab65d887a783203d	1000000	0	c1cc5c7eda2f4a7f9970329b39b65129		Retirement	0	1
f95547d42724452b97e94ca97b9e1706	Bond	STOCK	16c6e723ccfc48acab65d887a783203d	1000000	0	496fbd416c0141bc813d1db3299a6b11		Bond	0	0
1ef72225385a49c3b7b6a4c3765d7533	Stock	STOCK	16c6e723ccfc48acab65d887a783203d	1000000	0	496fbd416c0141bc813d1db3299a6b11		Stock	0	0
169f4afa56984ff8a8da770cd62a0c0b	Market Index	MUTUAL	16c6e723ccfc48acab65d887a783203d	1000000	0	496fbd416c0141bc813d1db3299a6b11		Market Index	0	0
450ddbd99a44485cb37c5a93809f14d5	Mutual Fund	MUTUAL	16c6e723ccfc48acab65d887a783203d	1000000	0	496fbd416c0141bc813d1db3299a6b11		Mutual Fund	0	0
109cc7c40b2d4681ac5fcc45be9e3d8d	Brokerage Account	BANK	16c6e723ccfc48acab65d887a783203d	1000000	0	c1cc5c7eda2f4a7f9970329b39b65129		Brokerage Account	0	0
dfb2bffe31f4467895d8f7244c7a452f	Bond	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	109cc7c40b2d4681ac5fcc45be9e3d8d		Bond	0	0
e30a4f1807714f1f9013f9499ae51da8	Stock	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	109cc7c40b2d4681ac5fcc45be9e3d8d		Stock	0	0
41f20e36dccb4c06b58adfd45f5ac2da	Market Index	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	109cc7c40b2d4681ac5fcc45be9e3d8d		Market Index	0	0
d58a768e1a45422cb93bc3b6783e7db7	Mutual Fund	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	109cc7c40b2d4681ac5fcc45be9e3d8d		Mutual Fund	0	0
d050ff027e12460e813edf0022926a3d	Fixed Assets	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	ca8d3a13f4ac4289ac8f07a4c4d83cb3		Fixed Assets	0	1
5d8fb2ab67034c44bc410f053818c4e3	House	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	d050ff027e12460e813edf0022926a3d		House	0	0
0101f4b6b5f9430796ad19517784f88e	Other Asset	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	d050ff027e12460e813edf0022926a3d		Other Asset	0	0
51840d97060647b28585bdc3353ccca8	Vehicle	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	d050ff027e12460e813edf0022926a3d		Vehicle	0	0
a4895e2846c14f0682496fb23cc9ebe7	Current Assets	ASSET	16c6e723ccfc48acab65d887a783203d	1000000	0	ca8d3a13f4ac4289ac8f07a4c4d83cb3		Current Assets	0	1
29359f49a953461a8478efed64179c22	Checking Account	BANK	16c6e723ccfc48acab65d887a783203d	1000000	0	a4895e2846c14f0682496fb23cc9ebe7		Checking Account	0	0
bd1a26a1d89f4e6f979def3f80a743e2	Savings Account	BANK	16c6e723ccfc48acab65d887a783203d	1000000	0	a4895e2846c14f0682496fb23cc9ebe7		Savings Account	0	0
f4967425d826499b875afa370bbcedd9	Cash in Wallet	CASH	16c6e723ccfc48acab65d887a783203d	1000000	0	a4895e2846c14f0682496fb23cc9ebe7		Cash in Wallet	0	0
73c75473a2cf4e5e9bd9382aafc6ed37	Bank CD	BANK	16c6e723ccfc48acab65d887a783203d	1000000	0	a4895e2846c14f0682496fb23cc9ebe7		Bank CD	0	0
7f2e9a5a903842ca968700f55f14a406	Money Market	BANK	16c6e723ccfc48acab65d887a783203d	1000000	0	a4895e2846c14f0682496fb23cc9ebe7		Money Market	0	0
f5af0ff8bb014d089c5cd4a70d0d6f2e	Petty Cash	CASH	16c6e723ccfc48acab65d887a783203d	1000000	0	a4895e2846c14f0682496fb23cc9ebe7		Petty Cash	0	0
e0c85373d3014ab49154e36017f5dba6	Accounts Receivable	RECEIVABLE	16c6e723ccfc48acab65d887a783203d	1000000	0	ca8d3a13f4ac4289ac8f07a4c4d83cb3		Accounts Receivable	0	0
ddcac4c181774f95aa3f0088387c0b26	Income	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	56203fe974784ae9b223915b87fe7f2a		Income	0	1
72ecff556a12489c939252f0e52d3561	Salary (Spouse)	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	ddcac4c181774f95aa3f0088387c0b26		Salary (Spouse)	0	0
17e37d2f76c945859f9d2f5b6f0c6aac	Dividend Income	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	ddcac4c181774f95aa3f0088387c0b26		Dividend Income	0	0
85f45f3fe37e401f8a73dcda930101da	Interest Income	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	ddcac4c181774f95aa3f0088387c0b26		Interest Income	0	0
a97b05ccb4b849ada19187ca8c20f968	Bond Interest	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	85f45f3fe37e401f8a73dcda930101da		Bond Interest	0	0
4848afd84f7e4d5ca7146e04bdd3bf26	Checking Interest	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	85f45f3fe37e401f8a73dcda930101da		Checking Interest	0	0
9c972b17023f483b926d7cbc036efd75	Other Interest	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	85f45f3fe37e401f8a73dcda930101da		Other Interest	0	0
27bc6c7d9e574754b0ff5b3294bfc09e	Savings Interest	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	85f45f3fe37e401f8a73dcda930101da		Savings Interest	0	0
7a197468ea4e4cf891f35396970526d4	CD Interest	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	85f45f3fe37e401f8a73dcda930101da		CD Interest	0	0
63e6c7c379f3432ea0452a624ce4a641	Money Market Interest	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	85f45f3fe37e401f8a73dcda930101da		Money Market Interest	0	0
231ed113ee3146b1862b0aacac51951f	Bonus	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	ddcac4c181774f95aa3f0088387c0b26		Bonus	0	0
2d92f19d17d54871af430fb1c1e4658d	Gifts Received	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	ddcac4c181774f95aa3f0088387c0b26		Gifts Received	0	0
aa59938675604bb59a0908ead72daa75	Other Income	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	ddcac4c181774f95aa3f0088387c0b26		Other Income	0	0
9598951a1c5041559ef85a769ff63553	Salary	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	ddcac4c181774f95aa3f0088387c0b26		Salary	0	0
da352701cd94487aa061d6f22c3193a4	Reimbursed Expenses	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	ddcac4c181774f95aa3f0088387c0b26		Reimbursed Expenses	0	0
7c4c8fb8ca0d4025b8393b20711af76f	Sales	INCOME	16c6e723ccfc48acab65d887a783203d	1000000	0	ddcac4c181774f95aa3f0088387c0b26		Sales	0	0
ed5fd6c903484dee8945fa58c556f8f6	Expenses	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	56203fe974784ae9b223915b87fe7f2a		Expenses	0	1
17f727786e2b47b195184101d2591857	Taxes (Spouse)	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Taxes (Spouse)	0	0
5ebcc6d1e64c4ac48e5b250e96f53f7e	Federal	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	17f727786e2b47b195184101d2591857		Federal	0	0
79b43beb5e7d4c198b7919930c7a7664	Medicare	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	17f727786e2b47b195184101d2591857		Medicare	0	0
9c9461b817004335bfb32fdf002737d9	Other Tax	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	17f727786e2b47b195184101d2591857		Other Tax	0	0
99915f6499e2411ebc5687b72d29871e	Social Security	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	17f727786e2b47b195184101d2591857		Social Security	0	0
16f72bfac9234b1fbb97c747a842e31a	State/Province	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	17f727786e2b47b195184101d2591857		State/Province	0	0
5ae83789a7064bd5bbde0352d2bbdcdb	Insurance	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Insurance	0	0
11ce0118ae064d4da89ca2d7a0db7b4b	Rental Insurance	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	5ae83789a7064bd5bbde0352d2bbdcdb		Rental Insurance	0	0
9e01950998e94f38bab344e22e17603e	Home Insurance	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	5ae83789a7064bd5bbde0352d2bbdcdb		Home Insurance	0	0
7cb06b30f29b4f52a02eb194aa78122f	Auto Insurance	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	5ae83789a7064bd5bbde0352d2bbdcdb		Auto Insurance	0	0
7b4ece0d56524c6a9ca254cc39d502a6	Health Insurance	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	5ae83789a7064bd5bbde0352d2bbdcdb		Health Insurance	0	0
bbd7733806624802ad1173fd3e1e3b64	Life Insurance	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	5ae83789a7064bd5bbde0352d2bbdcdb		Life Insurance	0	0
3dd9fc7eba044345aa463fd8c43cb79c	Disability Insurance	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	5ae83789a7064bd5bbde0352d2bbdcdb		Disability Insurance	0	0
69e7c5f38ef447f2af48a6b1b3b1868a	Liability Insurance	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	5ae83789a7064bd5bbde0352d2bbdcdb		Liability Insurance	0	0
dced3f67f4d04afdb8473d3e2088fb46	Workers Comp	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	5ae83789a7064bd5bbde0352d2bbdcdb		Workers Comp	0	0
078c09729c404847a86486a4c0fe4e10	Rent	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Rent	0	0
1fd94ca760a34eddbef7822629283907	Interest	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Interest	0	0
0eb9dd0575594cc8aff9f9d4dc1f1f22	Other Interest	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	1fd94ca760a34eddbef7822629283907		Other Interest	0	0
17c995e81ef34daabb285cfbcf5ae729	Mortgage Interest	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	1fd94ca760a34eddbef7822629283907		Mortgage Interest	0	0
aa29600158f1407f9fd202042ca5c901	Education Loan Interest	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	1fd94ca760a34eddbef7822629283907		Education Loan Interest	0	0
7029e5e471484a3a8ddf96100267f0bb	Vehicle Loan Interest	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	1fd94ca760a34eddbef7822629283907		Vehicle Loan Interest	0	0
ebc0f477d5e7457fa893657766ad04b5	Commissions	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Commissions	0	0
77e7fc23b9574cccb33095bcee603ea2	Home Repair	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Home Repair	0	0
ceff745aaa924138ae6f8f1cb2c2e93b	Taxes	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Taxes	0	0
803ee71655a044acaf83afb19e730007	Property Tax	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		Property Tax	0	0
143d71812b1d438283a61ac39f7a354c	Federal	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		Federal	0	0
510084cfd7754886bc99d7592f90322a	Medicare	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		Medicare	0	0
eef125bbae2b4af8a1d52caba1630d86	Other Tax	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		Other Tax	0	0
323e21407e49490a8d291b8a093c1c2b	Social Security	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		Social Security	0	0
d888b10d73b1400baee2b737e8e36213	State/Province	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		State/Province	0	0
1604d88ccbe8473797578dfd820e0a9a	Emp-FICA	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		Employer SS/Med	0	0
3a61b6f621c442538f71ea2a60a1d6b2	FICA	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		SS/Med	0	0
299208d6905c4d90b4047afc7fe185f2	FUTA	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		FUTA	0	0
d7b3918d22a0473eb2a6009b0ff22fa6	Local	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		Local	0	0
b6dd4efe97df4ff5af80d3253f436821	Property	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ceff745aaa924138ae6f8f1cb2c2e93b		Property	0	0
c99d0f9d509c4a8890dbdcb2b88375a0	Adjustment	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Adjustment	0	0
6cc039d67a0a46a6a14eb3404fc4c4a4	Auto	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Auto	0	0
ede210957cc143939082ad4ee3147561	Fees	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	6cc039d67a0a46a6a14eb3404fc4c4a4		Fees	0	0
fea8353f3ffe41368d9be10459bf4833	Gas	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	6cc039d67a0a46a6a14eb3404fc4c4a4		Gas	0	0
a522071f744b48c1919f6a67b0f27f67	Parking	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	6cc039d67a0a46a6a14eb3404fc4c4a4		Parking	0	0
0c1ba6d9a7f1440b9c07a5e3656df654	Repair and Maintenance	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	6cc039d67a0a46a6a14eb3404fc4c4a4		Repair and Maintenance	0	0
77c842113357433ab360e7a53e2b41ef	Bank Service Charge	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Bank Service Charge	0	0
a9ca63e9131c49ac8b35edcf440e21e2	Books	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Books	0	0
0e1ab53686374c3794c1a0420bc148cd	Cable	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Cable	0	0
74b046a17ad34365924b1a74237c640f	Charity	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Charity	0	0
fec470623f12409daa1c5fa19e9e0c1b	Clothes	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Clothes	0	0
1131bd5ef1c84a74a31331361c09f097	Computer	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Computer	0	0
291f946d54ce4e15a1342f7e2c3ad827	Dining	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Dining	0	0
4efac14bd3b44462bd97bcce4a43ea65	Education	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Education	0	0
e1f28c86ad5d4e5b90f035ef70365a03	Entertainment	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Entertainment	0	0
316d0a93cef64d85a6a9a10bd9a65517	Music/Movies	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e1f28c86ad5d4e5b90f035ef70365a03		Music/Movies	0	0
b8e41a3d27eb4b9e8d09f77ac394162f	Recreation	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e1f28c86ad5d4e5b90f035ef70365a03		Recreation	0	0
9c7be73406f84ce5ade1d3450ed199c3	Travel	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e1f28c86ad5d4e5b90f035ef70365a03		Travel	0	0
dd7fa1ef8b7e491bb87fe8be5dfb76ad	Gifts	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Gifts	0	0
f24df30e941d4f4fa6c0d5152207685c	Groceries	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Groceries	0	0
918a114638aa49babb78c674e1d29316	Hobbies	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Hobbies	0	0
331b630a8a1344799d23ea6d078930f9	Laundry/Dry Cleaning	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Laundry/Dry Cleaning	0	0
42b77b1c9cef46249072a03318a2b9ad	Medical Expenses	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Medical Expenses	0	0
cfcf6b47a5ef4f32a996f47ac701e07c	Miscellaneous	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Miscellaneous	0	0
31e10bbd76ac491999b78a905d08f99c	Online Services	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Online Services	0	0
ce79c533ead742359708519fb78ed2df	Phone	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Phone	0	0
b425be26bd9d48188e56c8064f2a6e69	Public Transportation	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Public Transportation	0	0
33625d0670424e928d87b42e3452c875	Subscriptions	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Subscriptions	0	0
50c9f0caf7f74e04828134e7091c30e1	Supplies	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Supplies	0	0
e3b0045e62ee45feb4e039815d3c1f78	Utilities	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Utilities	0	0
9f58dc38de0a45038572b4808b2dbb5b	Electric	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e3b0045e62ee45feb4e039815d3c1f78		Electric	0	0
8a35b717670e476fb85e69215a8ff4bb	Garbage collection	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e3b0045e62ee45feb4e039815d3c1f78		Garbage collection	0	0
824208392fb94fb49c02bd2635942c55	Gas	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e3b0045e62ee45feb4e039815d3c1f78		Gas	0	0
54ef863922914a3bada82f8a97731ed1	Water	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e3b0045e62ee45feb4e039815d3c1f78		Water	0	0
7e45b81fdf0f4c0c887631cfe1ebde59	Cable	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e3b0045e62ee45feb4e039815d3c1f78		Cable	0	0
8216fe0f37634662bb8974523a538c16	Cell Phone	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e3b0045e62ee45feb4e039815d3c1f78		Cell Phone	0	0
ce892a4e6c9a49acbee0cec27b09f5b9	Internet	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e3b0045e62ee45feb4e039815d3c1f78		Internet	0	0
9cf92e86a8224997a49d89c9e1f389f3	Phone	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e3b0045e62ee45feb4e039815d3c1f78		Phone	0	0
4dc00c8e65e84e6f9f5c8d9832ac9587	Childcare	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Childcare	0	0
ce92006eb2f64d70bc47b6dc0b6e028c	Cash Discounts	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Cash Discounts	0	0
87fccbd4c5604031aa5bc71da0d03c51	Depreciation	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Depreciation	0	0
2e7edb5457844b9080e5de937d3d4d30	Dues and Subscriptions	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Dues and Subscriptions	0	0
805ea1deed754c2e8e8e164276e77d2e	Equipment Rental	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Equipment Rental	0	0
74f23d5df84c4921b229d86a585b2f5c	Licenses and Permits	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Licenses and Permits	0	0
1f2f5cea27ed4500bd421f7cf434fbc3	Office Supplies	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Office Supplies	0	0
676f930e53aa457f9f752b29ee890e2d	Outside Services	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Outside Services	0	0
0413a5e094614da1b6344529ccfaf190	Payroll Expenses	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Payroll Expenses	0	0
8c6bb573dd4944218b3bb0c2f9a2efce	Postage and Delivery	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Postage and Delivery	0	0
d728384a60904384ab21232a362bb3c4	Printing and Reproduction	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Printing and Reproduction	0	0
577e7d66d0ce42fbb8627e77379fe881	Professional Fees	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Professional Fees	0	0
0915a327b48b4d489ede4ce22b8fd475	Accounting	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	577e7d66d0ce42fbb8627e77379fe881		Accounting	0	0
1669f7b986f441309e9be90ba5f1fe87	Legal Fees	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	577e7d66d0ce42fbb8627e77379fe881		Legal Fees	0	0
4d341a22417c4146b56ce6dd231f849b	Repairs	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Repairs	0	0
bbd557094b0b44a48eed4dafbc8c1ada	Building Repairs	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	4d341a22417c4146b56ce6dd231f849b		Building Repairs	0	0
6d351bf84fa643a4bf46a102896a9e27	Computer Repairs	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	4d341a22417c4146b56ce6dd231f849b		Computer Repairs	0	0
fd5a663c661c4513bdd04115ac30d27e	Equipment Repairs	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	4d341a22417c4146b56ce6dd231f849b		Equipment Repairs	0	0
c1983b00bf70427299bc62f82e1282a6	Janitorial Expenses	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	4d341a22417c4146b56ce6dd231f849b		Janitorial Expenses	0	0
e987f81d5bfb4ea5bdb707be9d61bd9c	Travel and Entertainment	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	ed5fd6c903484dee8945fa58c556f8f6		Travel and Entertainment	0	0
1952b087f28f49f6bebb61c14b083fdb	Entertainment	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e987f81d5bfb4ea5bdb707be9d61bd9c		Entertainment	0	0
dd39fb0fef914b46ac9c0fde3b29a5ad	Meals	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e987f81d5bfb4ea5bdb707be9d61bd9c		Meals	0	0
e5c770a14268468c95d3273cc8ad131a	Travel	EXPENSE	16c6e723ccfc48acab65d887a783203d	1000000	0	e987f81d5bfb4ea5bdb707be9d61bd9c		Travel	0	0
8c3dabb4c5634f0ea6fc1fd25c808593	Liabilities	LIABILITY	16c6e723ccfc48acab65d887a783203d	1000000	0	56203fe974784ae9b223915b87fe7f2a		Liabilities	0	1
eb5cee73de06495caeeffb2f73f076a9	Loans	LIABILITY	16c6e723ccfc48acab65d887a783203d	1000000	0	8c3dabb4c5634f0ea6fc1fd25c808593		Loans	0	1
d0e707726d194c92a80d473a7cac4686	Other Loan	LIABILITY	16c6e723ccfc48acab65d887a783203d	1000000	0	eb5cee73de06495caeeffb2f73f076a9		Other Loan	0	0
acf2f0a22856450b90230722edc174e1	Mortgage Loan	LIABILITY	16c6e723ccfc48acab65d887a783203d	1000000	0	eb5cee73de06495caeeffb2f73f076a9		Mortgage Loan	0	0
e376060add8c47b08a43ca69d7845339	Education Loan	LIABILITY	16c6e723ccfc48acab65d887a783203d	1000000	0	eb5cee73de06495caeeffb2f73f076a9		Education Loan	0	0
81207f95f5dd4ada89dfadfc1319ad9e	Vehicle Loan	LIABILITY	16c6e723ccfc48acab65d887a783203d	1000000	0	eb5cee73de06495caeeffb2f73f076a9		Vehicle Loan	0	0
014748ec4c5c4d8ba541f12e7de95a4e	Credit Card	CREDIT	16c6e723ccfc48acab65d887a783203d	1000000	0	8c3dabb4c5634f0ea6fc1fd25c808593		Credit Card	0	0
a1b1916a52a344398ab5663c74863e43	Accounts Payable	PAYABLE	16c6e723ccfc48acab65d887a783203d	1000000	0	8c3dabb4c5634f0ea6fc1fd25c808593		Accounts Payable	0	0
37d800dcfd674e50ba278e254a570835	Equity	EQUITY	16c6e723ccfc48acab65d887a783203d	1000000	0	56203fe974784ae9b223915b87fe7f2a		Equity	0	1
a6233b07bb0e4fcfbe5ea1e722c71dd1	Opening Balances	EQUITY	16c6e723ccfc48acab65d887a783203d	1000000	0	37d800dcfd674e50ba278e254a570835		Opening Balances	0	0
36ca32f12d1d49f798eadbebe0b1d654	Retained Earnings	EQUITY	16c6e723ccfc48acab65d887a783203d	1000000	0	37d800dcfd674e50ba278e254a570835		Retained Earnings	0	0
19cf223f9aeb4238878eaab32affdf80	Voucher account	PAYABLE	30a880ea7bf4480abd9f4c8ef36c53a4	100	0	56203fe974784ae9b223915b87fe7f2a			0	0
30c1c6695fd648cda3fea52e9fedcea8	Trading	TRADING	16c6e723ccfc48acab65d887a783203d	1000000	0	56203fe974784ae9b223915b87fe7f2a			0	1
52357eb2511043eb9de9afa8c31fd9eb	CURRENCY	TRADING	16c6e723ccfc48acab65d887a783203d	1000000	0	30c1c6695fd648cda3fea52e9fedcea8			0	1
44b043cfc15f40bfa86c67c992f1b248	XTS	TRADING	16c6e723ccfc48acab65d887a783203d	1000000	0	52357eb2511043eb9de9afa8c31fd9eb			0	0
2919adfb93c241f09f797f20ec29bbae	EUR	TRADING	30a880ea7bf4480abd9f4c8ef36c53a4	100	0	52357eb2511043eb9de9afa8c31fd9eb			0	0
98a9c04176f14e5b85d2e44789a00347	Accounts Receivable	RECEIVABLE	30a880ea7bf4480abd9f4c8ef36c53a4	100	0	56203fe974784ae9b223915b87fe7f2a			0	0
7c71e280524d4931a176b347ef4bf7fa	Accounts Payable	PAYABLE	30a880ea7bf4480abd9f4c8ef36c53a4	100	0	56203fe974784ae9b223915b87fe7f2a			0	0
77dc4bf36bcd4759aff16b17078c9fe1	Template Root	ROOT	f5513d9e0ee3453397e18e38bd008021	1	0	\N			0	0
cf7752af6493429383418c5c21d510ff	d31b2c5744404b2a8ad1712c97181e93	BANK	f5513d9e0ee3453397e18e38bd008021	1	0	77dc4bf36bcd4759aff16b17078c9fe1			0	0
\.


--
-- Data for Name: billterms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.billterms (guid, name, description, refcount, invisible, parent, type, duedays, discountdays, discount_num, discount_denom, cutoff) FROM stdin;
93c759bf61dc407f8376bb1d3ff510df	Billing Term	14d	0	1	30d54130bffb4834b73929b3ea505727	GNC_TERM_TYPE_DAYS	14	1	300000	100000	0
30d54130bffb4834b73929b3ea505727	Billing Term	14d	1	0	\N	GNC_TERM_TYPE_DAYS	14	1	300000	100000	0
\.


--
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.books (guid, root_account_guid, root_template_guid) FROM stdin;
0c17c22b814942a6b6a092807f0dca04	56203fe974784ae9b223915b87fe7f2a	77dc4bf36bcd4759aff16b17078c9fe1
\.


--
-- Data for Name: budget_amounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.budget_amounts (id, budget_guid, account_guid, period_num, amount_num, amount_denom) FROM stdin;
1	9254b27b6c24412583aa2f9f72e1ec57	ca8d3a13f4ac4289ac8f07a4c4d83cb3	0	1000000	1000000
2	9254b27b6c24412583aa2f9f72e1ec57	ca8d3a13f4ac4289ac8f07a4c4d83cb3	1	2	1
3	9254b27b6c24412583aa2f9f72e1ec57	ca8d3a13f4ac4289ac8f07a4c4d83cb3	2	-4	1
4	9254b27b6c24412583aa2f9f72e1ec57	d050ff027e12460e813edf0022926a3d	3	5	1
5	9254b27b6c24412583aa2f9f72e1ec57	37d800dcfd674e50ba278e254a570835	0	0	1
6	9254b27b6c24412583aa2f9f72e1ec57	37d800dcfd674e50ba278e254a570835	1	0	1
7	9254b27b6c24412583aa2f9f72e1ec57	37d800dcfd674e50ba278e254a570835	2	0	1
8	9254b27b6c24412583aa2f9f72e1ec57	37d800dcfd674e50ba278e254a570835	3	0	1
\.


--
-- Name: budget_amounts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.budget_amounts_id_seq', 8, true);


--
-- Data for Name: budgets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.budgets (guid, name, description, num_periods) FROM stdin;
9254b27b6c24412583aa2f9f72e1ec57	Unnamed Budget		12
\.


--
-- Data for Name: commodities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.commodities (guid, namespace, mnemonic, fullname, cusip, fraction, quote_flag, quote_source, quote_tz) FROM stdin;
16c6e723ccfc48acab65d887a783203d	CURRENCY	XTS	Code for testing purposes	963	1000000	1	currency	
30a880ea7bf4480abd9f4c8ef36c53a4	CURRENCY	EUR	Euro	978	100	1	currency	
f5513d9e0ee3453397e18e38bd008021	template	template	template	template	1	0	\N	
\.


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customers (guid, name, id, notes, active, discount_num, discount_denom, credit_num, credit_denom, currency, tax_override, addr_name, addr_addr1, addr_addr2, addr_addr3, addr_addr4, addr_phone, addr_fax, addr_email, shipaddr_name, shipaddr_addr1, shipaddr_addr2, shipaddr_addr3, shipaddr_addr4, shipaddr_phone, shipaddr_fax, shipaddr_email, terms, tax_included, taxtable) FROM stdin;
5af9758d55294f76b7cb4deaa0fb6b37	Customer1 Company Name	Customer Number 1	Customer1 Notes	1	0	1	10000	100	30a880ea7bf4480abd9f4c8ef36c53a4	0	Customer1 Company Billing Address Name	Customer1 Company Billing Address Address1	Customer1 Company Billing Address Address2	Customer1 Company Billing Address Address3	Customer1 Company Billing Address Address4	Customer1 Company Billing Address Phone	Customer1 Company Billing Address Fax	Customer1 Company Billing Address Email	Customer1 Shipping Address Name	Customer1 Shipping Address Address1	Customer1 Shipping Address Address2	Customer1 Shipping Address Address3	Customer1 Shipping Address Address4	Customer1 Shipping Address Phone	Customer1 Shipping Address Fax	Customer1 Shipping Address Email	\N	3	\N
a299a1399b38487590cf6e497ffcb9ce	Company Name 2	Customer Number 2	Company 2 with taxtable	1	0	1	0	1	30a880ea7bf4480abd9f4c8ef36c53a4	1	Company Billing Name 2	Company Billing Addres Line 1															30d54130bffb4834b73929b3ea505727	1	460c2dd21283475e900857dbb556c62b
\.


--
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employees (guid, username, id, language, acl, active, currency, ccard_guid, workday_num, workday_denom, rate_num, rate_denom, addr_name, addr_addr1, addr_addr2, addr_addr3, addr_addr4, addr_phone, addr_fax, addr_email) FROM stdin;
205fef6073c74d3d896eb51ad1cc8a63	number2	Number2	dog sign language		1	30a880ea7bf4480abd9f4c8ef36c53a4	014748ec4c5c4d8ba541f12e7de95a4e	800000	100000	10000	100	Name2	Address2				Phone2	Fax2	Email2
\.


--
-- Data for Name: entries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.entries (guid, date, date_entered, description, action, notes, quantity_num, quantity_denom, i_acct, i_price_num, i_price_denom, i_discount_num, i_discount_denom, invoice, i_disc_type, i_disc_how, i_taxable, i_taxincluded, i_taxtable, b_acct, b_price_num, b_price_denom, bill, b_taxable, b_taxincluded, b_taxtable, b_paytype, billable, billto_type, billto_guid, order_guid) FROM stdin;
f379e93807e245fbb9d2b8fa80fbdf24	2018-05-31 10:00:00	2018-05-31 13:23:27	Customer Invoice Description 2	Material		500000	1000000	9c972b17023f483b926d7cbc036efd75	111000000	1000000	3000000	1000000	ebe95fb5a819433ba474065b6363c795	PERCENT	POSTTAX	0	0	\N	\N	0	1	\N	1	0	\N	1	0	\N	\N	\N
cb404b8de6d54317b9f79c25a4ae7138	2018-05-31 10:00:00	2018-05-31 13:28:33	Vendor Bill Description 1	Hours		10000000	1000000	\N	0	1	0	1	\N	PERCENT	PRETAX	1	0	\N	1131bd5ef1c84a74a31331361c09f097	154000000	1000000	6f51053db2ab4c3ea188575a4273eb77	0	1	\N	1	0	\N	\N	\N
c39d07ec1a004fe78f43c35726e0f731	2018-05-31 10:00:00	2018-05-31 13:23:00	Customer Invoice Description 1			1000000	1000000	7a197468ea4e4cf891f35396970526d4	1000000	1000000	100000000	1000000	ebe95fb5a819433ba474065b6363c795	VALUE	PRETAX	0	0	\N	\N	0	1	\N	1	0	\N	1	0	\N	\N	\N
210a891b58e64d2295e3092f24ba12e3	2018-05-27 10:00:00	2018-05-27 19:44:17	Voucher Entry	Hours		1000000	1000000	\N	0	1	0	1	\N	PERCENT	PRETAX	1	0	\N	74b046a17ad34365924b1a74237c640f	123000000	1000000	695aa2171ea849288bd04f450dd46134	1	0	\N	1	0	\N	\N	\N
\.


--
-- Data for Name: gnclock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gnclock (hostname, pid) FROM stdin;
\.


--
-- Data for Name: invoices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invoices (guid, id, date_opened, date_posted, notes, active, currency, owner_type, owner_guid, terms, billing_id, post_txn, post_lot, post_acc, billto_type, billto_guid, charge_amt_num, charge_amt_denom) FROM stdin;
695aa2171ea849288bd04f450dd46134	Voucher1	2018-05-26 22:00:00	2018-05-26 22:00:00		1	30a880ea7bf4480abd9f4c8ef36c53a4	5	205fef6073c74d3d896eb51ad1cc8a63	93c759bf61dc407f8376bb1d3ff510df	BillingId	73b82779a05045108af38e0976d5b9ff	91c617d1fd064241829b2670e5d048b2	19cf223f9aeb4238878eaab32affdf80	\N	\N	0	1
ebe95fb5a819433ba474065b6363c795	CustomerInvoice1	2018-05-30 22:00:00	2018-05-30 22:00:00		1	30a880ea7bf4480abd9f4c8ef36c53a4	3	9c0931e6d87048ebbf028419f6ddb5f8	93c759bf61dc407f8376bb1d3ff510df	Billing ID	ac69030bd4d048d7905c44f31403752f	e032fb63be754277ace507d6812e5ed7	98a9c04176f14e5b85d2e44789a00347	\N	\N	0	1
6f51053db2ab4c3ea188575a4273eb77	000002	2018-05-30 22:00:00	2018-05-30 22:00:00		1	30a880ea7bf4480abd9f4c8ef36c53a4	3	1fdecfd586d2427ebb80d3e4433fc0a4	\N	Vendor Billing ID	ede709ada33e4455ba868c0ad71ba288	4d382524af7b4114b07f435d1437039f	7c71e280524d4931a176b347ef4bf7fa	3	9c0931e6d87048ebbf028419f6ddb5f8	0	1
\.


--
-- Data for Name: jobs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jobs (guid, id, name, reference, active, owner_type, owner_guid) FROM stdin;
1fdecfd586d2427ebb80d3e4433fc0a4	000007	Vendor Job 1		1	4	a036ab8d2c994c9eaa10ea0897f8d947
9c0931e6d87048ebbf028419f6ddb5f8	JobNumber 1	Job 1		1	2	5af9758d55294f76b7cb4deaa0fb6b37
\.


--
-- Data for Name: lots; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lots (guid, account_guid, is_closed) FROM stdin;
e032fb63be754277ace507d6812e5ed7	98a9c04176f14e5b85d2e44789a00347	0
91c617d1fd064241829b2670e5d048b2	19cf223f9aeb4238878eaab32affdf80	0
4d382524af7b4114b07f435d1437039f	7c71e280524d4931a176b347ef4bf7fa	0
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (guid, id, notes, reference, active, date_opened, date_closed, owner_type, owner_guid) FROM stdin;
\.


--
-- Data for Name: prices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prices (guid, commodity_guid, currency_guid, date, source, type, value_num, value_denom) FROM stdin;
77dae70cd85f40c2abaad698e00f9571	30a880ea7bf4480abd9f4c8ef36c53a4	16c6e723ccfc48acab65d887a783203d	2018-06-04 10:59:00	user:price	\N	187970	10000
f8c7ecbd692f4e849de82e5ea8d2d93a	30a880ea7bf4480abd9f4c8ef36c53a4	16c6e723ccfc48acab65d887a783203d	2018-05-31 10:59:00	user:price	\N	188049	10000
bd36ba26d5ba4055ad1e91d029de0aac	30a880ea7bf4480abd9f4c8ef36c53a4	16c6e723ccfc48acab65d887a783203d	2018-05-27 10:59:00	user:xfer-dialog	transaction	188049	10000
\.


--
-- Data for Name: recurrences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recurrences (id, obj_guid, recurrence_mult, recurrence_period_type, recurrence_period_start, recurrence_weekend_adjust) FROM stdin;
1	d31b2c5744404b2a8ad1712c97181e93	1	month	2018-05-01	forward
2	d31b2c5744404b2a8ad1712c97181e93	1	month	2018-05-15	back
3	9254b27b6c24412583aa2f9f72e1ec57	1	month	2018-05-01	none
\.


--
-- Name: recurrences_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recurrences_id_seq', 3, true);


--
-- Data for Name: schedxactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.schedxactions (guid, name, enabled, start_date, end_date, last_occur, num_occur, rem_occur, auto_create, auto_notify, adv_creation, adv_notify, instance_count, template_act_guid) FROM stdin;
d31b2c5744404b2a8ad1712c97181e93	Scheduled Transaction Forever	1	2018-05-27	\N	2018-06-01	0	0	1	1	1	2	4	cf7752af6493429383418c5c21d510ff
\.


--
-- Data for Name: slots; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.slots (id, obj_guid, name, slot_type, int64_val, string_val, double_val, timespec_val, guid_val, numeric_val_num, numeric_val_denom, gdate_val) FROM stdin;
1	0c17c22b814942a6b6a092807f0dca04	counters	9	0	\N	\N	1970-01-01 00:00:00	627e41458d2c46f0aaed202d9952d92e	0	1	\N
2	627e41458d2c46f0aaed202d9952d92e	counters/gncBill	1	2	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
3	627e41458d2c46f0aaed202d9952d92e	counters/gncCustomer	1	2	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
4	627e41458d2c46f0aaed202d9952d92e	counters/gncEmployee	1	3	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
5	627e41458d2c46f0aaed202d9952d92e	counters/gncExpVoucher	1	4	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
6	627e41458d2c46f0aaed202d9952d92e	counters/gncInvoice	1	5	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
7	627e41458d2c46f0aaed202d9952d92e	counters/gncJob	1	7	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
8	627e41458d2c46f0aaed202d9952d92e	counters/gncOrder	1	7	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
9	627e41458d2c46f0aaed202d9952d92e	counters/gncVendor	1	9	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
10	0c17c22b814942a6b6a092807f0dca04	features	9	0	\N	\N	1970-01-01 00:00:00	04cfbbcf6544418ca408a183f08f693b	0	1	\N
11	04cfbbcf6544418ca408a183f08f693b	features/ISO-8601 formatted date strings in SQLite3 databases.	4	0	Use ISO formatted date-time strings in SQLite3 databases (requires at least GnuCash 2.6.20)	\N	1970-01-01 00:00:00	\N	0	1	\N
12	04cfbbcf6544418ca408a183f08f693b	features/Number Field Source	4	0	User specifies source of 'num' field'; either transaction number or split action (requires at least GnuCash 2.5.0)	\N	1970-01-01 00:00:00	\N	0	1	\N
13	0c17c22b814942a6b6a092807f0dca04	options	9	0	\N	\N	1970-01-01 00:00:00	8d8c1c9702dc4facafbe0953afe9ef84	0	1	\N
14	8d8c1c9702dc4facafbe0953afe9ef84	options/Accounts	9	0	\N	\N	1970-01-01 00:00:00	46b60085572c4591a6199a30f0220434	0	1	\N
15	46b60085572c4591a6199a30f0220434	options/Accounts/Day Threshold for Read-Only Transactions (red line)	2	0	\N	1	1970-01-01 00:00:00	\N	0	1	\N
16	46b60085572c4591a6199a30f0220434	options/Accounts/Use Split Action Field for Number	4	0	t	\N	1970-01-01 00:00:00	\N	0	1	\N
17	46b60085572c4591a6199a30f0220434	options/Accounts/Use Trading Accounts	4	0	t	\N	1970-01-01 00:00:00	\N	0	1	\N
18	8d8c1c9702dc4facafbe0953afe9ef84	options/Budgeting	9	0	\N	\N	1970-01-01 00:00:00	90dc490cff2149c0b0ac129c4d0b9389	0	1	\N
19	8d8c1c9702dc4facafbe0953afe9ef84	options/Business	9	0	\N	\N	1970-01-01 00:00:00	b13bb6b9cfc046aca08e858105e1e857	0	1	\N
20	b13bb6b9cfc046aca08e858105e1e857	options/Business/Company Address	4	0	Company Address	\N	1970-01-01 00:00:00	\N	0	1	\N
21	b13bb6b9cfc046aca08e858105e1e857	options/Business/Company Contact Person	4	0	Company Contact Person	\N	1970-01-01 00:00:00	\N	0	1	\N
22	b13bb6b9cfc046aca08e858105e1e857	options/Business/Company Email Address	4	0	Company Email Address	\N	1970-01-01 00:00:00	\N	0	1	\N
23	b13bb6b9cfc046aca08e858105e1e857	options/Business/Company Fax Number	4	0	Company Fax Number	\N	1970-01-01 00:00:00	\N	0	1	\N
24	b13bb6b9cfc046aca08e858105e1e857	options/Business/Company ID	4	0	Company ID	\N	1970-01-01 00:00:00	\N	0	1	\N
25	b13bb6b9cfc046aca08e858105e1e857	options/Business/Company Name	4	0	Company Name	\N	1970-01-01 00:00:00	\N	0	1	\N
26	b13bb6b9cfc046aca08e858105e1e857	options/Business/Company Phone Number	4	0	Company Phone Number	\N	1970-01-01 00:00:00	\N	0	1	\N
27	b13bb6b9cfc046aca08e858105e1e857	options/Business/Company Website URL	4	0	Company Website URL	\N	1970-01-01 00:00:00	\N	0	1	\N
28	ca8d3a13f4ac4289ac8f07a4c4d83cb3	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
29	c1cc5c7eda2f4a7f9970329b39b65129	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
30	758a7b3ec38442cfaabd8e9456404ae9	notes	4	0	IRA, 401(k), or other retirement	\N	1970-01-01 00:00:00	\N	0	1	\N
31	758a7b3ec38442cfaabd8e9456404ae9	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
32	496fbd416c0141bc813d1db3299a6b11	notes	4	0	IRA, 401(k), or other retirement	\N	1970-01-01 00:00:00	\N	0	1	\N
33	496fbd416c0141bc813d1db3299a6b11	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
34	d050ff027e12460e813edf0022926a3d	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
35	a4895e2846c14f0682496fb23cc9ebe7	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
36	ddcac4c181774f95aa3f0088387c0b26	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
37	ed5fd6c903484dee8945fa58c556f8f6	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
38	8c3dabb4c5634f0ea6fc1fd25c808593	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
39	eb5cee73de06495caeeffb2f73f076a9	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
40	37d800dcfd674e50ba278e254a570835	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
41	19cf223f9aeb4238878eaab32affdf80	color	4	0	Not Set	\N	1970-01-01 00:00:00	\N	0	1	\N
42	30c1c6695fd648cda3fea52e9fedcea8	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
43	52357eb2511043eb9de9afa8c31fd9eb	placeholder	4	0	true	\N	1970-01-01 00:00:00	\N	0	1	\N
44	98a9c04176f14e5b85d2e44789a00347	color	4	0	Not Set	\N	1970-01-01 00:00:00	\N	0	1	\N
45	7c71e280524d4931a176b347ef4bf7fa	color	4	0	Not Set	\N	1970-01-01 00:00:00	\N	0	1	\N
46	fef195f41b1745f283a30b3491c2c063	date-posted	10	0	\N	\N	1970-01-01 00:00:00	\N	0	1	2018-06-04
47	fef195f41b1745f283a30b3491c2c063	notes	4	0	Voided transaction	\N	1970-01-01 00:00:00	\N	0	1	\N
48	fef195f41b1745f283a30b3491c2c063	trans-read-only	4	0	Transaction Voided	\N	1970-01-01 00:00:00	\N	0	1	\N
49	fef195f41b1745f283a30b3491c2c063	void-reason	4	0	Nada	\N	1970-01-01 00:00:00	\N	0	1	\N
50	fef195f41b1745f283a30b3491c2c063	void-time	4	0	2018-06-04 08:02:56.000000 +0200	\N	1970-01-01 00:00:00	\N	0	1	\N
51	7c69214f951948dab1ea29daabf3a4da	void-former-amount	3	0	\N	\N	1970-01-01 00:00:00	\N	42000000	1000000	\N
52	7c69214f951948dab1ea29daabf3a4da	void-former-value	3	0	\N	\N	1970-01-01 00:00:00	\N	42000000	1000000	\N
53	f0b26fa3de154d1b88a02812c27a3171	void-former-amount	3	0	\N	\N	1970-01-01 00:00:00	\N	-42000000	1000000	\N
54	f0b26fa3de154d1b88a02812c27a3171	void-former-value	3	0	\N	\N	1970-01-01 00:00:00	\N	-42000000	1000000	\N
55	2f60388d3a8c48cf8a8aa997ffba83ca	date-posted	10	0	\N	\N	1970-01-01 00:00:00	\N	0	1	2018-05-27
56	ac69030bd4d048d7905c44f31403752f	gncInvoice	9	0	\N	\N	1970-01-01 00:00:00	7d602a9b7b424c07b724ba837b1a2b65	0	1	\N
57	7d602a9b7b424c07b724ba837b1a2b65	gncInvoice/invoice-guid	5	0	\N	\N	1970-01-01 00:00:00	ebe95fb5a819433ba474065b6363c795	0	1	\N
58	ac69030bd4d048d7905c44f31403752f	trans-date-due	6	0	\N	\N	2018-06-13 22:00:00	\N	0	1	\N
59	ac69030bd4d048d7905c44f31403752f	trans-read-only	4	0	Generated from an invoice. Try unposting the invoice.	\N	1970-01-01 00:00:00	\N	0	1	\N
60	ac69030bd4d048d7905c44f31403752f	trans-txn-type	4	0	I	\N	1970-01-01 00:00:00	\N	0	1	\N
61	73b82779a05045108af38e0976d5b9ff	gncInvoice	9	0	\N	\N	1970-01-01 00:00:00	4dfa79f20b494f4f83eec36b5f55dff1	0	1	\N
62	4dfa79f20b494f4f83eec36b5f55dff1	gncInvoice/invoice-guid	5	0	\N	\N	1970-01-01 00:00:00	695aa2171ea849288bd04f450dd46134	0	1	\N
63	73b82779a05045108af38e0976d5b9ff	trans-date-due	6	0	\N	\N	2018-06-09 22:00:00	\N	0	1	\N
64	73b82779a05045108af38e0976d5b9ff	trans-read-only	4	0	Generated from an invoice. Try unposting the invoice.	\N	1970-01-01 00:00:00	\N	0	1	\N
65	73b82779a05045108af38e0976d5b9ff	trans-txn-type	4	0	I	\N	1970-01-01 00:00:00	\N	0	1	\N
66	379d8417a42147d08bfefa639e7b2b7d	assoc_uri	4	0	file:///C:/Users/keve/Desktop/GnuCashFileLink.txt	\N	1970-01-01 00:00:00	\N	0	1	\N
67	379d8417a42147d08bfefa639e7b2b7d	date-posted	10	0	\N	\N	1970-01-01 00:00:00	\N	0	1	2018-06-04
68	e1095eb78c83435abe2e167d71f4b973	assoc_uri	4	0	https://www.duckduckgo.com	\N	1970-01-01 00:00:00	\N	0	1	\N
69	e1095eb78c83435abe2e167d71f4b973	date-posted	10	0	\N	\N	1970-01-01 00:00:00	\N	0	1	2018-06-04
70	ede709ada33e4455ba868c0ad71ba288	gncInvoice	9	0	\N	\N	1970-01-01 00:00:00	810b9d1782224f3fb169ae0d95fa7617	0	1	\N
71	810b9d1782224f3fb169ae0d95fa7617	gncInvoice/invoice-guid	5	0	\N	\N	1970-01-01 00:00:00	6f51053db2ab4c3ea188575a4273eb77	0	1	\N
72	ede709ada33e4455ba868c0ad71ba288	trans-date-due	6	0	\N	\N	2018-05-30 22:00:00	\N	0	1	\N
73	ede709ada33e4455ba868c0ad71ba288	trans-read-only	4	0	Generated from an invoice. Try unposting the invoice.	\N	1970-01-01 00:00:00	\N	0	1	\N
74	ede709ada33e4455ba868c0ad71ba288	trans-txn-type	4	0	I	\N	1970-01-01 00:00:00	\N	0	1	\N
75	e0b5a80df5cf407d80c933e9474f0eda	sched-xaction	9	0	\N	\N	1970-01-01 00:00:00	9c17d2aa39704e699da11320c56db766	0	1	\N
76	9c17d2aa39704e699da11320c56db766	sched-xaction/account	5	0	\N	\N	1970-01-01 00:00:00	73c75473a2cf4e5e9bd9382aafc6ed37	0	1	\N
77	9c17d2aa39704e699da11320c56db766	sched-xaction/credit-formula	4	0	100/99	\N	1970-01-01 00:00:00	\N	0	1	\N
78	9c17d2aa39704e699da11320c56db766	sched-xaction/credit-numeric	3	0	\N	\N	1970-01-01 00:00:00	\N	100	99	\N
79	9c17d2aa39704e699da11320c56db766	sched-xaction/debit-formula	4	0		\N	1970-01-01 00:00:00	\N	0	1	\N
80	9c17d2aa39704e699da11320c56db766	sched-xaction/debit-numeric	3	0	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
81	a981b4e48fba450aaaba69a290bb19a8	sched-xaction	9	0	\N	\N	1970-01-01 00:00:00	ab81dcc7400d42eca38776551b239f09	0	1	\N
82	ab81dcc7400d42eca38776551b239f09	sched-xaction/account	5	0	\N	\N	1970-01-01 00:00:00	f5af0ff8bb014d089c5cd4a70d0d6f2e	0	1	\N
83	ab81dcc7400d42eca38776551b239f09	sched-xaction/credit-formula	4	0		\N	1970-01-01 00:00:00	\N	0	1	\N
84	ab81dcc7400d42eca38776551b239f09	sched-xaction/credit-numeric	3	0	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
85	ab81dcc7400d42eca38776551b239f09	sched-xaction/debit-formula	4	0	100/99	\N	1970-01-01 00:00:00	\N	0	1	\N
86	ab81dcc7400d42eca38776551b239f09	sched-xaction/debit-numeric	3	0	\N	\N	1970-01-01 00:00:00	\N	100	99	\N
87	9254b27b6c24412583aa2f9f72e1ec57	37d800dcfd674e50ba278e254a570835	9	0	\N	\N	1970-01-01 00:00:00	12069dc7d0c14bdcb990fa3c8da56fab	0	1	\N
88	12069dc7d0c14bdcb990fa3c8da56fab	37d800dcfd674e50ba278e254a570835/0	3	0	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
89	12069dc7d0c14bdcb990fa3c8da56fab	37d800dcfd674e50ba278e254a570835/1	3	0	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
90	12069dc7d0c14bdcb990fa3c8da56fab	37d800dcfd674e50ba278e254a570835/2	3	0	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
91	12069dc7d0c14bdcb990fa3c8da56fab	37d800dcfd674e50ba278e254a570835/3	3	0	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
92	9254b27b6c24412583aa2f9f72e1ec57	ca8d3a13f4ac4289ac8f07a4c4d83cb3	9	0	\N	\N	1970-01-01 00:00:00	b666a6cbb5094244a3113f2e09b4be7f	0	1	\N
93	b666a6cbb5094244a3113f2e09b4be7f	ca8d3a13f4ac4289ac8f07a4c4d83cb3/0	3	0	\N	\N	1970-01-01 00:00:00	\N	1000000	1000000	\N
94	b666a6cbb5094244a3113f2e09b4be7f	ca8d3a13f4ac4289ac8f07a4c4d83cb3/1	3	0	\N	\N	1970-01-01 00:00:00	\N	2	1	\N
95	b666a6cbb5094244a3113f2e09b4be7f	ca8d3a13f4ac4289ac8f07a4c4d83cb3/2	3	0	\N	\N	1970-01-01 00:00:00	\N	-4	1	\N
96	9254b27b6c24412583aa2f9f72e1ec57	d050ff027e12460e813edf0022926a3d	9	0	\N	\N	1970-01-01 00:00:00	07c8aa53a2c34242bbca9309940d7392	0	1	\N
97	07c8aa53a2c34242bbca9309940d7392	d050ff027e12460e813edf0022926a3d/3	3	0	\N	\N	1970-01-01 00:00:00	\N	5	1	\N
98	e032fb63be754277ace507d6812e5ed7	gncInvoice	9	0	\N	\N	1970-01-01 00:00:00	7e73a9ccfe7c4c15a767774a0390cd70	0	1	\N
99	7e73a9ccfe7c4c15a767774a0390cd70	gncInvoice/invoice-guid	5	0	\N	\N	1970-01-01 00:00:00	ebe95fb5a819433ba474065b6363c795	0	1	\N
100	e032fb63be754277ace507d6812e5ed7	title	4	0	Invoice CustomerInvoice1	\N	1970-01-01 00:00:00	\N	0	1	\N
101	91c617d1fd064241829b2670e5d048b2	gncInvoice	9	0	\N	\N	1970-01-01 00:00:00	2d580041fbe046fe8470f4a8cbd45976	0	1	\N
102	2d580041fbe046fe8470f4a8cbd45976	gncInvoice/invoice-guid	5	0	\N	\N	1970-01-01 00:00:00	695aa2171ea849288bd04f450dd46134	0	1	\N
103	91c617d1fd064241829b2670e5d048b2	title	4	0	Expense Voucher1	\N	1970-01-01 00:00:00	\N	0	1	\N
104	4d382524af7b4114b07f435d1437039f	gncInvoice	9	0	\N	\N	1970-01-01 00:00:00	f546fe19fe4b430b9d5225a893d01085	0	1	\N
105	f546fe19fe4b430b9d5225a893d01085	gncInvoice/invoice-guid	5	0	\N	\N	1970-01-01 00:00:00	6f51053db2ab4c3ea188575a4273eb77	0	1	\N
106	4d382524af7b4114b07f435d1437039f	title	4	0	Bill 000002	\N	1970-01-01 00:00:00	\N	0	1	\N
107	5af9758d55294f76b7cb4deaa0fb6b37	last-posted-to-acct	5	0	\N	\N	1970-01-01 00:00:00	98a9c04176f14e5b85d2e44789a00347	0	1	\N
108	205fef6073c74d3d896eb51ad1cc8a63	last-posted-to-acct	5	0	\N	\N	1970-01-01 00:00:00	19cf223f9aeb4238878eaab32affdf80	0	1	\N
109	695aa2171ea849288bd04f450dd46134	credit-note	1	0	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
110	ebe95fb5a819433ba474065b6363c795	credit-note	1	0	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
111	6f51053db2ab4c3ea188575a4273eb77	credit-note	1	0	\N	\N	1970-01-01 00:00:00	\N	0	1	\N
112	a036ab8d2c994c9eaa10ea0897f8d947	last-posted-to-acct	5	0	\N	\N	1970-01-01 00:00:00	7c71e280524d4931a176b347ef4bf7fa	0	1	\N
\.


--
-- Name: slots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.slots_id_seq', 112, true);


--
-- Data for Name: splits; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.splits (guid, tx_guid, account_guid, memo, action, reconcile_state, reconcile_date, value_num, value_denom, quantity_num, quantity_denom, lot_guid) FROM stdin;
7c69214f951948dab1ea29daabf3a4da	fef195f41b1745f283a30b3491c2c063	74b046a17ad34365924b1a74237c640f		TestVoid	v	1970-01-01 00:00:00	0	1000000	0	1000000	\N
f0b26fa3de154d1b88a02812c27a3171	fef195f41b1745f283a30b3491c2c063	1ef72225385a49c3b7b6a4c3765d7533			v	1970-01-01 00:00:00	0	1000000	0	1000000	\N
639f51db71174c4896529afedc447136	4e52e0838a48472baa2b5f0c1208194b	5d8fb2ab67034c44bc410f053818c4e3			n	1970-01-01 00:00:00	888889	1000000	888889	1000000	\N
2cdfedead1694f4fad2d07510074a50e	4e52e0838a48472baa2b5f0c1208194b	a6233b07bb0e4fcfbe5ea1e722c71dd1			n	1970-01-01 00:00:00	-888889	1000000	-888889	1000000	\N
12328db464814f23b520bfa5ee68d55f	fdb93329c0c544eb8b9d76c34e8b10d2	0101f4b6b5f9430796ad19517784f88e			n	1970-01-01 00:00:00	9000000	1000000	9000000	1000000	\N
391feaf7a1de4b4495bdc282d001e076	fdb93329c0c544eb8b9d76c34e8b10d2	a6233b07bb0e4fcfbe5ea1e722c71dd1			n	1970-01-01 00:00:00	-9000000	1000000	-9000000	1000000	\N
604d9e25d9564b53bbe80397282e81ba	9a60deb515b2481d995bf8564cdc6120	51840d97060647b28585bdc3353ccca8			n	1970-01-01 00:00:00	10000000	1000000	10000000	1000000	\N
acb8e499439b4280b1cca27d358e1bfc	9a60deb515b2481d995bf8564cdc6120	a6233b07bb0e4fcfbe5ea1e722c71dd1			n	1970-01-01 00:00:00	-10000000	1000000	-10000000	1000000	\N
5e4925bc36144f60bbbee3c38ca097ce	caf9d605a2c647b1a7f137de8d5172cd	29359f49a953461a8478efed64179c22			n	1970-01-01 00:00:00	4000000	1000000	4000000	1000000	\N
fa456918226e4539bfedd8058c9040bf	caf9d605a2c647b1a7f137de8d5172cd	a6233b07bb0e4fcfbe5ea1e722c71dd1			n	1970-01-01 00:00:00	-4000000	1000000	-4000000	1000000	\N
a8aae3d4f38247f395f322d49c6e4002	b505ba28c1d240f180810921605207d5	bd1a26a1d89f4e6f979def3f80a743e2			n	1970-01-01 00:00:00	7000000	1000000	7000000	1000000	\N
629d85a9d5de437bae2d1f236209ba4d	b505ba28c1d240f180810921605207d5	a6233b07bb0e4fcfbe5ea1e722c71dd1			n	1970-01-01 00:00:00	-7000000	1000000	-7000000	1000000	\N
226d9f2759d74b9a8a756d43a1ac6437	dba92b694ef14dafb66a1a07140a59cd	f4967425d826499b875afa370bbcedd9			n	1970-01-01 00:00:00	3000000	1000000	3000000	1000000	\N
ffbaa4aa21ae484db5ae97784a7ec1ae	dba92b694ef14dafb66a1a07140a59cd	a6233b07bb0e4fcfbe5ea1e722c71dd1			n	1970-01-01 00:00:00	-3000000	1000000	-3000000	1000000	\N
61d0112afc78477982eafa4a79a2a876	84523593888a4d929f26b7855161f90c	f5af0ff8bb014d089c5cd4a70d0d6f2e	Wallet decrease	Decrease	n	1970-01-01 00:00:00	101	100	1010000	1000000	\N
f487cc300418423393439539f1f41f2d	84523593888a4d929f26b7855161f90c	73c75473a2cf4e5e9bd9382aafc6ed37	ATM deposit	ATM Deposit	n	1970-01-01 00:00:00	-101	100	-1010000	1000000	\N
8692e68bb5e24329ab643b1ad15b56a6	d0bd3c246bb24d14a50b4cf2f9c90889	f5af0ff8bb014d089c5cd4a70d0d6f2e	Wallet decrease	Decrease	n	1970-01-01 00:00:00	101	100	1010000	1000000	\N
14e470b3f48143b0867fd014628f30af	d0bd3c246bb24d14a50b4cf2f9c90889	73c75473a2cf4e5e9bd9382aafc6ed37	ATM deposit	ATM Deposit	n	1970-01-01 00:00:00	-101	100	-1010000	1000000	\N
cad1a89e5a70494cb18aeae88e83a4f9	2f60388d3a8c48cf8a8aa997ffba83ca	73c75473a2cf4e5e9bd9382aafc6ed37			n	1970-01-01 00:00:00	2000000	1000000	2000000	1000000	\N
add1ab9549594911a49c4fd8e0ec76c5	2f60388d3a8c48cf8a8aa997ffba83ca	a6233b07bb0e4fcfbe5ea1e722c71dd1			n	1970-01-01 00:00:00	-2000000	1000000	-2000000	1000000	\N
82945ed3873d4b03bc304b66d47d43d2	fcbaa674062d4109827d9e5b1dca1830	f5af0ff8bb014d089c5cd4a70d0d6f2e	Wallet decrease	Decrease	n	1970-01-01 00:00:00	101	100	1010000	1000000	\N
b3a7603b31874ee0a71f9897f84228b5	fcbaa674062d4109827d9e5b1dca1830	73c75473a2cf4e5e9bd9382aafc6ed37	ATM deposit	ATM Deposit	n	1970-01-01 00:00:00	-101	100	-1010000	1000000	\N
f73603d84dea4aeca54d93c57cd267ff	cf9dfb46589a4321ba2baa7ce187b4fd	7f2e9a5a903842ca968700f55f14a406			n	1970-01-01 00:00:00	5000000	1000000	5000000	1000000	\N
6dddf785fd9446218e502a157aaba702	cf9dfb46589a4321ba2baa7ce187b4fd	a6233b07bb0e4fcfbe5ea1e722c71dd1			n	1970-01-01 00:00:00	-5000000	1000000	-5000000	1000000	\N
5f4af8db1c724af6997d3fa17e0af917	6e5a47ca46fc4604b4ce0717019987c1	f5af0ff8bb014d089c5cd4a70d0d6f2e			n	1970-01-01 00:00:00	6000000	1000000	6000000	1000000	\N
8eb540347c0b46b793734e067fbff641	6e5a47ca46fc4604b4ce0717019987c1	a6233b07bb0e4fcfbe5ea1e722c71dd1			n	1970-01-01 00:00:00	-6000000	1000000	-6000000	1000000	\N
97182f3bceb144fda5f8b317f7799054	d8680125d48b4b8c9c4a411b202dcba1	e0c85373d3014ab49154e36017f5dba6			n	1970-01-01 00:00:00	1000000	1000000	1000000	1000000	\N
921c6cdd21cd470f865f8f309276f3a9	d8680125d48b4b8c9c4a411b202dcba1	a6233b07bb0e4fcfbe5ea1e722c71dd1			n	1970-01-01 00:00:00	-1000000	1000000	-1000000	1000000	\N
4992775711c747acbcc508627e3bc39f	ac69030bd4d048d7905c44f31403752f	7a197468ea4e4cf891f35396970526d4	Post Description	CustomerInvoice1	n	1970-01-01 00:00:00	9900	100	1861686670	1000000	\N
c287698811164656b6fb8ff411085857	ac69030bd4d048d7905c44f31403752f	2919adfb93c241f09f797f20ec29bbae			n	1970-01-01 00:00:00	4516	100	4516	100	\N
deb4679616cd4fdf89cde82e65b0b4e9	ac69030bd4d048d7905c44f31403752f	9c972b17023f483b926d7cbc036efd75	Post Description	CustomerInvoice1	n	1970-01-01 00:00:00	-5384	100	-1012456670	1000000	\N
0131114e1bb549d2b1dc6a2a0ef91d60	ac69030bd4d048d7905c44f31403752f	98a9c04176f14e5b85d2e44789a00347	Post Description	CustomerInvoice1	n	1970-01-01 00:00:00	-4516	100	-4516	100	e032fb63be754277ace507d6812e5ed7
f34c952a90c448958cebd16c6b3438bb	ac69030bd4d048d7905c44f31403752f	44b043cfc15f40bfa86c67c992f1b248			n	1970-01-01 00:00:00	-4516	100	-849230000	1000000	\N
37c7d7328e2b4280a67d2931bc5a9ae3	73b82779a05045108af38e0976d5b9ff	74b046a17ad34365924b1a74237c640f	Voucher post	Voucher1	n	1970-01-01 00:00:00	12300	100	2313000000	1000000	\N
a934b615786942cfa3be4917dc0916fb	73b82779a05045108af38e0976d5b9ff	2919adfb93c241f09f797f20ec29bbae			n	1970-01-01 00:00:00	12300	100	12300	100	\N
e81193f8ed6a4e148d8b487ed7e30adb	73b82779a05045108af38e0976d5b9ff	19cf223f9aeb4238878eaab32affdf80	Voucher post	Voucher1	n	1970-01-01 00:00:00	-12300	100	-12300	100	91c617d1fd064241829b2670e5d048b2
487c8c2fe3a14c29835744d9e8d3e5de	73b82779a05045108af38e0976d5b9ff	44b043cfc15f40bfa86c67c992f1b248			n	1970-01-01 00:00:00	-12300	100	-2313000000	1000000	\N
b9cb89fc01554de58d04fdf08798cd4b	379d8417a42147d08bfefa639e7b2b7d	19cf223f9aeb4238878eaab32affdf80			n	1970-01-01 00:00:00	125000000	1000000	665	100	\N
8a3a0c0acdd249758933f28467dcaea8	379d8417a42147d08bfefa639e7b2b7d	44b043cfc15f40bfa86c67c992f1b248			n	1970-01-01 00:00:00	125000000	1000000	125000000	1000000	\N
75566a68b3f9416cabd0bd22d25044dc	379d8417a42147d08bfefa639e7b2b7d	74b046a17ad34365924b1a74237c640f		TestFileLink	n	1970-01-01 00:00:00	-125000000	1000000	-125000000	1000000	\N
31c3c6402fa34d44b281f6b070afd3ff	379d8417a42147d08bfefa639e7b2b7d	2919adfb93c241f09f797f20ec29bbae			n	1970-01-01 00:00:00	-125000000	1000000	-665	100	\N
9ec65bbe3c5945b990f4a0da5ff4fe24	e1095eb78c83435abe2e167d71f4b973	74b046a17ad34365924b1a74237c640f		TestFile	n	1970-01-01 00:00:00	100000000	1000000	100000000	1000000	\N
7859e68d336f4c46898af4859f4c0bd3	e1095eb78c83435abe2e167d71f4b973	2919adfb93c241f09f797f20ec29bbae			n	1970-01-01 00:00:00	100000000	1000000	532	100	\N
b011073f7c1547d8a568ed77219800d6	e1095eb78c83435abe2e167d71f4b973	19cf223f9aeb4238878eaab32affdf80			n	1970-01-01 00:00:00	-100000000	1000000	-532	100	\N
d3892e09a65d4030adfabc0147032d2d	e1095eb78c83435abe2e167d71f4b973	44b043cfc15f40bfa86c67c992f1b248			n	1970-01-01 00:00:00	-100000000	1000000	-100000000	1000000	\N
9f6276a8d7fe468cae5193b2d33f7256	ede709ada33e4455ba868c0ad71ba288	1131bd5ef1c84a74a31331361c09f097	Vendor Bill Description	000002	n	1970-01-01 00:00:00	154000	100	28959550000	1000000	\N
e00d9899f87e4dd3abe89d97bcab53e3	ede709ada33e4455ba868c0ad71ba288	2919adfb93c241f09f797f20ec29bbae			n	1970-01-01 00:00:00	154000	100	154000	100	\N
4304beede096470c9af217b529eba83c	ede709ada33e4455ba868c0ad71ba288	7c71e280524d4931a176b347ef4bf7fa	Vendor Bill Description	000002	n	1970-01-01 00:00:00	-154000	100	-154000	100	4d382524af7b4114b07f435d1437039f
f150f82a0fb44f83990172c1b84cc435	ede709ada33e4455ba868c0ad71ba288	44b043cfc15f40bfa86c67c992f1b248			n	1970-01-01 00:00:00	-154000	100	-28959550000	1000000	\N
e0b5a80df5cf407d80c933e9474f0eda	f24e627a523b40759aa68b3547defd15	cf7752af6493429383418c5c21d510ff	ATM deposit	ATM Deposit	n	1970-01-01 00:00:00	0	100	0	1	\N
a981b4e48fba450aaaba69a290bb19a8	f24e627a523b40759aa68b3547defd15	cf7752af6493429383418c5c21d510ff	Wallet decrease	Decrease	n	1970-01-01 00:00:00	0	100	0	1	\N
\.


--
-- Data for Name: taxtable_entries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.taxtable_entries (id, taxtable, account, amount_num, amount_denom, type) FROM stdin;
1	460c2dd21283475e900857dbb556c62b	eef125bbae2b4af8a1d52caba1630d86	500000	100000	2
2	d79c2b3fa6e34922a6d635deaf30ef56	d7b3918d22a0473eb2a6009b0ff22fa6	133333	100000	1
\.


--
-- Name: taxtable_entries_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.taxtable_entries_id_seq', 2, true);


--
-- Data for Name: taxtables; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.taxtables (guid, name, refcount, invisible, parent) FROM stdin;
460c2dd21283475e900857dbb556c62b	Sales Tax 5%	1	0	\N
d79c2b3fa6e34922a6d635deaf30ef56	Sales Tax 4/3$	1	0	\N
\.


--
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions (guid, currency_guid, num, post_date, enter_date, description) FROM stdin;
fef195f41b1745f283a30b3491c2c063	16c6e723ccfc48acab65d887a783203d		2018-06-04 10:59:00	2018-06-04 06:02:47	Test void transaction
4e52e0838a48472baa2b5f0c1208194b	16c6e723ccfc48acab65d887a783203d		2018-05-27 10:59:00	2018-05-27 19:32:29	Opening Balance
fdb93329c0c544eb8b9d76c34e8b10d2	16c6e723ccfc48acab65d887a783203d		2018-05-27 10:59:00	2018-05-27 19:32:29	Opening Balance
9a60deb515b2481d995bf8564cdc6120	16c6e723ccfc48acab65d887a783203d		2018-05-27 10:59:00	2018-05-27 19:32:29	Opening Balance
caf9d605a2c647b1a7f137de8d5172cd	16c6e723ccfc48acab65d887a783203d		2018-05-27 10:59:00	2018-05-27 19:32:29	Opening Balance
b505ba28c1d240f180810921605207d5	16c6e723ccfc48acab65d887a783203d		2018-05-27 10:59:00	2018-05-27 19:32:29	Opening Balance
dba92b694ef14dafb66a1a07140a59cd	16c6e723ccfc48acab65d887a783203d		2018-05-27 10:59:00	2018-05-27 19:32:30	Opening Balance
84523593888a4d929f26b7855161f90c	30a880ea7bf4480abd9f4c8ef36c53a4	STF1	2018-05-01 10:59:00	2018-05-27 19:35:14	Scheduled
d0bd3c246bb24d14a50b4cf2f9c90889	30a880ea7bf4480abd9f4c8ef36c53a4	STF1	2018-05-15 10:59:00	2018-05-27 19:35:14	Scheduled
2f60388d3a8c48cf8a8aa997ffba83ca	16c6e723ccfc48acab65d887a783203d		2018-05-27 10:59:00	2018-05-27 19:32:30	Opening Balance
fcbaa674062d4109827d9e5b1dca1830	30a880ea7bf4480abd9f4c8ef36c53a4	STF1	2018-06-01 10:59:00	2018-05-27 19:35:14	Scheduled
cf9dfb46589a4321ba2baa7ce187b4fd	16c6e723ccfc48acab65d887a783203d		2018-05-27 10:59:00	2018-05-27 19:32:30	Opening Balance
6e5a47ca46fc4604b4ce0717019987c1	16c6e723ccfc48acab65d887a783203d		2018-05-27 10:59:00	2018-05-27 19:32:30	Opening Balance
d8680125d48b4b8c9c4a411b202dcba1	16c6e723ccfc48acab65d887a783203d		2018-05-27 10:59:00	2018-05-27 19:32:30	Opening Balance
ac69030bd4d048d7905c44f31403752f	30a880ea7bf4480abd9f4c8ef36c53a4	Invoice	2018-05-30 10:59:00	2018-05-31 13:27:03	Customer1 Company Name
73b82779a05045108af38e0976d5b9ff	30a880ea7bf4480abd9f4c8ef36c53a4	Expense	2018-05-26 10:59:00	2018-05-27 19:45:03	Name2
379d8417a42147d08bfefa639e7b2b7d	16c6e723ccfc48acab65d887a783203d		2018-06-04 10:59:00	2018-06-04 05:59:41	Test file link
e1095eb78c83435abe2e167d71f4b973	16c6e723ccfc48acab65d887a783203d		2018-06-04 10:59:00	2018-06-04 06:00:52	Test file url
ede709ada33e4455ba868c0ad71ba288	30a880ea7bf4480abd9f4c8ef36c53a4	Bill	2018-05-30 10:59:00	2018-05-31 13:29:01	Vendor 2
f24e627a523b40759aa68b3547defd15	30a880ea7bf4480abd9f4c8ef36c53a4	STF1	2018-05-27 10:59:00	2018-05-27 19:35:14	Scheduled
\.


--
-- Data for Name: vendors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vendors (guid, name, id, notes, currency, active, tax_override, addr_name, addr_addr1, addr_addr2, addr_addr3, addr_addr4, addr_phone, addr_fax, addr_email, terms, tax_inc, tax_table) FROM stdin;
a036ab8d2c994c9eaa10ea0897f8d947	Vendor 2	Vendor Number 2	Vendor Notes	30a880ea7bf4480abd9f4c8ef36c53a4	1	1	Payment Address Name	Payment Address Address1	Payment Address Address2	Payment Address Address3	Payment Address Address4	Payment Address Phone	Payment Address Fax	Payment Address Email	\N	YES	\N
\.


--
-- Data for Name: versions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.versions (table_name, table_version) FROM stdin;
Gnucash	3000001
Gnucash-Resave	19920
books	1
commodities	1
accounts	1
budgets	1
budget_amounts	1
prices	3
transactions	4
splits	4
slots	4
recurrences	2
schedxactions	1
lots	2
billterms	2
customers	2
employees	2
entries	4
invoices	4
jobs	1
orders	1
taxtables	2
taxtable_entries	3
vendors	1
\.


--
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (guid);


--
-- Name: billterms billterms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.billterms
    ADD CONSTRAINT billterms_pkey PRIMARY KEY (guid);


--
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (guid);


--
-- Name: budget_amounts budget_amounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.budget_amounts
    ADD CONSTRAINT budget_amounts_pkey PRIMARY KEY (id);


--
-- Name: budgets budgets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.budgets
    ADD CONSTRAINT budgets_pkey PRIMARY KEY (guid);


--
-- Name: commodities commodities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commodities
    ADD CONSTRAINT commodities_pkey PRIMARY KEY (guid);


--
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (guid);


--
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (guid);


--
-- Name: entries entries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entries
    ADD CONSTRAINT entries_pkey PRIMARY KEY (guid);


--
-- Name: invoices invoices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_pkey PRIMARY KEY (guid);


--
-- Name: jobs jobs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobs
    ADD CONSTRAINT jobs_pkey PRIMARY KEY (guid);


--
-- Name: lots lots_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lots
    ADD CONSTRAINT lots_pkey PRIMARY KEY (guid);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (guid);


--
-- Name: prices prices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prices
    ADD CONSTRAINT prices_pkey PRIMARY KEY (guid);


--
-- Name: recurrences recurrences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recurrences
    ADD CONSTRAINT recurrences_pkey PRIMARY KEY (id);


--
-- Name: schedxactions schedxactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schedxactions
    ADD CONSTRAINT schedxactions_pkey PRIMARY KEY (guid);


--
-- Name: slots slots_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.slots
    ADD CONSTRAINT slots_pkey PRIMARY KEY (id);


--
-- Name: splits splits_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.splits
    ADD CONSTRAINT splits_pkey PRIMARY KEY (guid);


--
-- Name: taxtable_entries taxtable_entries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.taxtable_entries
    ADD CONSTRAINT taxtable_entries_pkey PRIMARY KEY (id);


--
-- Name: taxtables taxtables_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.taxtables
    ADD CONSTRAINT taxtables_pkey PRIMARY KEY (guid);


--
-- Name: transactions transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (guid);


--
-- Name: vendors vendors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vendors
    ADD CONSTRAINT vendors_pkey PRIMARY KEY (guid);


--
-- Name: versions versions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.versions
    ADD CONSTRAINT versions_pkey PRIMARY KEY (table_name);


--
-- Name: slots_guid_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX slots_guid_index ON public.slots USING btree (obj_guid);


--
-- Name: splits_account_guid_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX splits_account_guid_index ON public.splits USING btree (account_guid);


--
-- Name: splits_tx_guid_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX splits_tx_guid_index ON public.splits USING btree (tx_guid);


--
-- Name: tx_post_date_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX tx_post_date_index ON public.transactions USING btree (post_date);


--
-- PostgreSQL database dump complete
--

