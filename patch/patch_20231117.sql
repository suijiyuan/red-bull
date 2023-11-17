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
VALUES ('admin', 'Qwer1234!');

-- patch for prod
CREATE
    DATABASE prod;