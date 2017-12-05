-- TABLES
CREATE TABLE users (
  id INT NOT NULL DEFAULT nextval('users_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  uuid VARCHAR(36) NOT NULL UNIQUE,
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
  uuid VARCHAR(36) NOT NULL UNIQUE,
  username VARCHAR(50) NOT NULL UNIQUE,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL
);

CREATE TABLE posts (
  id INT NOT NULL DEFAULT nextval('posts_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  uuid VARCHAR(36) NOT NULL UNIQUE,
  author_id INT,
  subject VARCHAR(256) NOT NULL UNIQUE,
  body CLOB,
  published DATE,
  FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE comments (
  id INT NOT NULL DEFAULT nextval('comments_seq') PRIMARY KEY,
  version INT NOT NULL DEFAULT 0,
  uuid VARCHAR(36) NOT NULL UNIQUE,
  author VARCHAR(50),
  subject VARCHAR(256),
  body CLOB,
  post_id INT,
  published DATE,
  FOREIGN KEY (post_id) REFERENCES posts(id)
);