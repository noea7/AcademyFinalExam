CREATE TABLE IF NOT EXISTS AUTOSERVICE (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    ADDRESS VARCHAR(255) NOT NULL,
    MANAGER VARCHAR(255)
);