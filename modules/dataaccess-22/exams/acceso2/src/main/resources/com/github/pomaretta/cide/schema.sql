CREATE DATABASE IF NOT EXISTS cide;
USE cide;

-- CREATE MASTER TABLES

CREATE TABLE IF NOT EXISTS department (
    id                  INT NOT NULL AUTO_INCREMENT,
    name                VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS person (
    id                  INT NOT NULL AUTO_INCREMENT,
    nif                 VARCHAR(9) NOT NULL,
    name                VARCHAR(255) NOT NULL,
    first_lastname      VARCHAR(255) NOT NULL,
    second_lastname     VARCHAR(255) NOT NULL,
    birthdate           DATE NOT NULL,
    gender              VARCHAR(255) NOT NUll,
    telephone           VARCHAR(255) NOT NULL,
    UNIQUE (nif),
    PRIMARY KEY (id)
);

-- CREATE DATA OBJECTS

CREATE TABLE IF NOT EXISTS teacher (
    id                  INT NOT NULL AUTO_INCREMENT,
    person_id           INT NOT NULL,
    department_id       INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE,
    FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE CASCADE
);