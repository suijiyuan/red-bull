-- patch for dev
CREATE
    DATABASE dev;

USE dev;

CREATE TABLE user
(
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255)
);

INSERT INTO user(username, password)
VALUES ('admin', '$2a$10$Pn6mU2TiM5r9uR2rjJhS5O2pVafFu.50Hwg1bb7rgdLmj8DEeufxe');

-- patch for prod
CREATE
    DATABASE prod;