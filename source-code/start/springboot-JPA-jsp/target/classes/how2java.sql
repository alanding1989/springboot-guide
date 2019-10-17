CREATE DATABASE how2java;

USE how2java;

CREATE TABLE category_
(
    id   INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(30),
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;

INSERT INTO category_
VALUES (NULL, 'category 1');

INSERT INTO category_
VALUES (NULL, 'category 2');

INSERT INTO category_
VALUES (NULL, 'category 3');

INSERT INTO category_
VALUES (NULL, 'category 4');
