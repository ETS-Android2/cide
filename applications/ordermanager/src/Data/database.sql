DROP DATABASE IF EXISTS order_manager;
CREATE DATABASE order_manager;
USE order_manager;

CREATE TABLE client (
    id              INT NOT NULL,
    name            VARCHAR(20) NOT NULL,
    name_2          VARCHAR(20) NULL,
    lastname        VARCHAR(30) NOT NULL,
    lastname_2      VARCHAR(30) NULL,
    street_address  VARCHAR(50) NOT NULL,
    mail_address    VARCHAR(50) NOT NULL,
    phone_number    VARCHAR(9) NOT NULL,
    UNIQUE (mail_address,phone_number),
    PRIMARY KEY (id)
);

-- TODO Add manufacturer
-- TODO Add category
CREATE TABLE product (
    id              INT NOT NULL AUTO_INCREMENT,
    title           VARCHAR(255) NOT NULL,
    description     VARCHAR(255) NULL,
    price           FLOAT NOT NULL,
    stock           INT DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE client_order (
    id              INT NOT NULL AUTO_INCREMENT,
    date            TIMESTAMP DEFAULT NOW(),
    client          INT NOT NULL,
    FOREIGN KEY (client) REFERENCES client(id),
    PRIMARY KEY (id,client)
);

CREATE TABLE order_product (
    order_id        INT NOT NULL,
    product         INT NOT NULL,
    quantity        INT NOT NULL,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES client_order(id) ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product) REFERENCES  product(id) ON DELETE CASCADE,
    PRIMARY KEY (order_id,product)
);