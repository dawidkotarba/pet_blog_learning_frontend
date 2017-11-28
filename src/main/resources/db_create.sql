-- USERS
CREATE USER IF NOT EXISTS SA
  SALT '6a0e04afeac6bccc' HASH '065c94fe8fb12546403bd50ab883a59cd664b25ceb9f7bd8a1420a74099d0d3b' ADMIN;

-- SEQUENCE
CREATE SEQUENCE users_seq INCREMENT BY 1;
CREATE SEQUENCE countries_seq INCREMENT BY 1;
CREATE SEQUENCE neighbours_seq INCREMENT BY 1;
CREATE SEQUENCE cities_seq INCREMENT BY 1;

-- TABLES
CREATE TABLE users (
  id INT NOT NULL DEFAULT nextval('users_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  enabled  BOOLEAN     NOT NULL,
  role     VARCHAR(20) NOT NULL
);

CREATE TABLE cities (
  id INT NOT NULL DEFAULT nextval('cities_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  name VARCHAR(50) NOT NULL UNIQUE,
  country INT,
  population INT
);

CREATE TABLE countries (
  id INT NOT NULL DEFAULT nextval('countries_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  name       VARCHAR(50) NOT NULL UNIQUE,
  capital    INT,
  area       INT,
  population INT,
  currency   VARCHAR(3),
  FOREIGN KEY (capital) REFERENCES cities(id)
);

CREATE TABLE neighbours (
  id INT NOT NULL DEFAULT nextval('neighbours_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  country    INT NOT NULL,
  neighbour  INT NOT NULL,
  FOREIGN KEY (country) REFERENCES countries(id),
  FOREIGN KEY (neighbour) REFERENCES countries(id)
);

-- INDEXES
CREATE INDEX users_index on users(id);
CREATE INDEX cities_index on cities(id, name);
CREATE INDEX countries_index on countries(id, name);

-- VIEWS
CREATE VIEW POPULATION_DENSITY AS
SELECT ct.name country, ct.population / ct.area population_density
FROM countries ct JOIN cities cp ON ct.capital = cp.id
order by population_density desc;

CREATE VIEW COUNTRIES_WITHOUT_NEIGHBOURS AS
SELECT c.name countries_wo_neighbour
FROM countries c WHERE c.id NOT IN (SELECT country FROM neighbours);

CREATE VIEW COUNTRIES_AND_CAPS AS
SELECT ct.name countries, cp.name cities
FROM countries ct JOIN countries cp ON ct.capital = cp.id;