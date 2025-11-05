CREATE TABLE IF NOT EXISTS ranks (
    id          BIGINT            NOT NULL PRIMARY KEY,
    name        VARCHAR(255),
    rating      DOUBLE PRECISION,
    user_id     BIGINT,
    CONSTRAINT FK_USER_RANK FOREIGN KEY (user_id) REFERENCES users (id)
    );

ALTER TABLE ranks OWNER TO postgres;

CREATE SEQUENCE rank_id_seq;

ALTER SEQUENCE rank_id_seq OWNER TO postgres;