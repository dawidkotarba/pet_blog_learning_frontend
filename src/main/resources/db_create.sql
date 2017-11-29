-- USERS
CREATE USER IF NOT EXISTS SA
  SALT '6a0e04afeac6bccc' HASH '065c94fe8fb12546403bd50ab883a59cd664b25ceb9f7bd8a1420a74099d0d3b' ADMIN;

-- SEQUENCE
CREATE SEQUENCE users_seq INCREMENT BY 1;
CREATE SEQUENCE authors_seq INCREMENT BY 1;
CREATE SEQUENCE posts_seq INCREMENT BY 1;
CREATE SEQUENCE comments_seq INCREMENT BY 1;

-- TABLES
CREATE TABLE users (
  id INT NOT NULL DEFAULT nextval('users_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  username VARCHAR(50) NOT NULL UNIQUE,
  firstname VARCHAR(50),
  lastname VARCHAR(50),
  password VARCHAR(50) NOT NULL,
  enabled  BOOLEAN     NOT NULL,
  role     VARCHAR(20) NOT NULL
);

CREATE TABLE authors (
  id INT NOT NULL DEFAULT nextval('authors_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  username VARCHAR(50) NOT NULL UNIQUE,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL
);

CREATE TABLE posts (
  id INT NOT NULL DEFAULT nextval('posts_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  author INT,
  subject VARCHAR(256) NOT NULL UNIQUE,
  body CLOB,
  published DATE,
  FOREIGN KEY (author) REFERENCES authors(id)
);

CREATE TABLE comments (
  id INT NOT NULL DEFAULT nextval('comments_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  author VARCHAR(50),
  subject VARCHAR(256),
  body CLOB,
  post INT,
  FOREIGN KEY (post) REFERENCES posts(id)
);

---- INDEXES
CREATE INDEX users_index on users(id);
CREATE INDEX authors_index on authors(id);
CREATE INDEX posts_index on posts(id);
--
---- VIEWS
--CREATE VIEW POPULATION_DENSITY AS
--SELECT ct.name country, ct.population / ct.area population_density
--FROM countries ct JOIN cities cp ON ct.capital = cp.id
--order by population_density desc;
--
--CREATE VIEW COUNTRIES_WITHOUT_NEIGHBOURS AS
--SELECT c.name countries_wo_neighbour
--FROM countries c WHERE c.id NOT IN (SELECT country FROM neighbours);
--
--CREATE VIEW COUNTRIES_AND_CAPS AS
--SELECT ct.name countries, cp.name cities
--FROM countries ct JOIN countries cp ON ct.capital = cp.id;