CREATE TABLE IF NOT EXISTS USER_TABLE (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    USERNAME VARCHAR(255) UNIQUE NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    ROLE VARCHAR(255)
);