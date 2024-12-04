CREATE TABLE IF NOT EXISTS countries_data (
                                         id          BIGINT       NOT NULL PRIMARY KEY,
                                         name        VARCHAR(255) NOT NULL,
                                         latitude      DOUBLE PRECISION NOT NULL,
                                         longitude      DOUBLE PRECISION NOT NULL
    );

ALTER TABLE countries_data OWNER TO postgres;

CREATE SEQUENCE country_data_id_seq;

ALTER SEQUENCE country_data_id_seq OWNER TO postgres;