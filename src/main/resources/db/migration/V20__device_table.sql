CREATE TABLE device(
    id                  serial          PRIMARY KEY,
    device_name         VARCHAR(50)     NOT NULL,
    operating_system    VARCHAR(50)     NOT NULL,
    ip_address          VARCHAR(50)     NOT NULL,
    access_level        TINYINT         NOT NULL,
    processor           VARCHAR(50)     NOT NULL,
    portable            BOOLEAN         NOT NULL,
)