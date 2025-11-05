CREATE TABLE IF NOT EXISTS countries (
    id          BIGINT       NOT NULL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL
    );

ALTER TABLE countries OWNER TO postgres;

CREATE SEQUENCE country_id_seq;

ALTER SEQUENCE country_id_seq OWNER TO postgres;