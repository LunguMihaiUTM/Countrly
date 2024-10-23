CREATE TABLE IF NOT EXISTS shapes (
    id           BIGINT       NOT NULL PRIMARY KEY,
    country_name VARCHAR(255) NOT NULL,
    image        VARCHAR(255) NOT NULL,
    country_id   BIGINT,
    CONSTRAINT FK_COUNTRY_SHAPE FOREIGN KEY (country_id) REFERENCES countries (id)
    );

ALTER TABLE shapes OWNER TO root;

CREATE SEQUENCE shape_id_seq;

ALTER SEQUENCE shape_id_seq OWNER TO root;
