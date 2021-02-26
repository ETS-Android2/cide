DROP DATABASE IF EXISTS chatting;
CREATE DATABASE chatting;
USE chatting;

-- %%%%%%%%%%%%%%%%%%
-- RESET TABLES %%%%%
-- %%%%%%%%%%%%%%%%%%

DROP TABLE IF EXISTS 
    user
    ,message
    ,room
    ,message_transaction;

-- %%%%%%%%%%%%%%%%%%
-- CREATING TABLES %%
-- %%%%%%%%%%%%%%%%%%

SELECT "CREATING TABLES" AS INFO;

-- %%%%%%%%%%%%%%%%%%

SELECT "CREATING [user] TABLE" AS INFO;

CREATE TABLE user(
    id              INT AUTO_INCREMENT,
    creation_date   DATETIME DEFAULT NOW(),
    username        VARCHAR(50) NOT NULL,
    password        VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);

-- %%%%%%%%%%%%%%%%%%

SELECT "CREATING [message] TABLE" AS INFO;

CREATE TABLE message(
    id              INT AUTO_INCREMENT NOT NULL,
    creation_date   DATETIME DEFAULT NOW(),
    user            INT NOT NULL,
    content         TEXT(1024) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user) REFERENCES user(id)       
);

-- %%%%%%%%%%%%%%%%%%

SELECT "CREATING [room] TABLE" AS INFO;

CREATE TABLE room(
    id              INT AUTO_INCREMENT NOT NULL,
    creation_date   DATETIME DEFAULT NOW(),
    user            INT NOT NULL,
    title           VARCHAR(50) NOT NULL,
    description     TEXT(1024) NOT NULL,
    visibility      BOOLEAN DEFAULT true,
    password        VARCHAR(50) DEFAULT '',
    PRIMARY KEY (id),
    FOREIGN KEY (user) REFERENCES user(id)
);

-- %%%%%%%%%%%%%%%%%%

SELECT "CREATING [message_transaction] TABLE" AS INFO;

CREATE TABLE message_transaction(
    id              INT AUTO_INCREMENT NOT NULL,
    creation_date   DATETIME DEFAULT NOW(),
    room            INT NOT NULL,
    message         INT NOT NULL,
    user            INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (room) REFERENCES room(id),
    FOREIGN KEY (message) REFERENCES message(id),
    FOREIGN KEY (user) REFERENCES user(id)
);