--liquibase formatted sql

--changeset arup:03
CREATE TABLE department (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

--changeset arup:04
ALTER TABLE department ADD COLUMN created_at TIMESTAMP;

--changeset arup:05
ALTER TABLE department DROP COLUMN created_at;


