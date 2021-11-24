--
-- Banco de dados: algafoods
--

-- --------------------------------------------------------

--
-- Estrutura da tabela city
--

CREATE TABLE city (
  id bigint(20) NOT NULL,
  name varchar(255) NOT NULL,
  state_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela groups
--

CREATE TABLE groups (
  id bigint(20) NOT NULL,
  name varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela group_permission
--

CREATE TABLE groups_permission (
  group_id bigint(20) NOT NULL,
  permission_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela kitchen
--

CREATE TABLE kitchen (
  id bigint(20) NOT NULL,
  name varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela payment_method
--

CREATE TABLE payment_method (
  id bigint(20) NOT NULL,
  name varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela permission
--

CREATE TABLE permission (
  id bigint(20) NOT NULL,
  description varchar(255) NOT NULL,
  name varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela product
--

CREATE TABLE product (
  id bigint(20) NOT NULL,
  description varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  price decimal(19,2) NOT NULL,
  restaurant_id bigint(20) NOT NULL,
  status_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela restaurant
--

CREATE TABLE restaurant (
  id bigint(20) NOT NULL,
  address_complement varchar(255) DEFAULT NULL,
  address_number varchar(255) DEFAULT NULL,
  address_public_place varchar(255) DEFAULT NULL,
  address_zip_code varchar(255) DEFAULT NULL,
  created_at datetime(6) NOT NULL,
  freight bigint(20) NOT NULL,
  name varchar(255) NOT NULL,
  updated_at datetime(6) NOT NULL,
  address_city_id bigint(20) DEFAULT NULL,
  kitchen_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela restaurant_payment
--

CREATE TABLE restaurant_payment (
  restaurant_id bigint(20) NOT NULL,
  payment_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela restaurant_product
--

CREATE TABLE restaurant_product (
  restaurant_id bigint(20) NOT NULL,
  product_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela state
--

CREATE TABLE state (
  id bigint(20) NOT NULL,
  name varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela status
--

CREATE TABLE status (
  id bigint(20) NOT NULL,
  description varchar(255) NOT NULL,
  state bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela user
--

CREATE TABLE user (
  id bigint(20) NOT NULL,
  email varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  created_at datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela user_groups
--

CREATE TABLE user_groups (
  user_id bigint(20) NOT NULL,
  group_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;