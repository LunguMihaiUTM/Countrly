CREATE TABLE IF NOT EXISTS complexities (
    id          BIGINT        NOT NULL PRIMARY KEY,
    complexity  VARCHAR(255),
    country_id  BIGINT,
    CONSTRAINT FK_COUNTRY_COMPLEXITY FOREIGN KEY (country_id) REFERENCES countries (id)
    );

ALTER TABLE complexities OWNER TO root;

CREATE SEQUENCE complexity_id_seq;

ALTER SEQUENCE complexity_id_seq OWNER TO root;