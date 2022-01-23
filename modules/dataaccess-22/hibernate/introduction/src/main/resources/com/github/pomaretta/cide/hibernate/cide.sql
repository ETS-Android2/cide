CREATE DATABASE IF NOT EXISTS cide;

USE cide;

CREATE TABLE IF NOT EXISTS `Albumes` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `titulo` varchar(255) NOT NULL,
    `autor` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);