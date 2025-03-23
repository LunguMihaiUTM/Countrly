CREATE TABLE IF NOT EXISTS users (
    id          BIGINT       NOT NULL PRIMARY KEY,
    username    VARCHAR(255) NOT NULL UNIQUE,
    email       VARCHAR(255) NOT NULL UNIQUE,
    role        VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL

);

ALTER TABLE users OWNER TO root;

CREATE SEQUENCE user_id_seq;

ALTER SEQUENCE user_id_seq OWNER TO root;