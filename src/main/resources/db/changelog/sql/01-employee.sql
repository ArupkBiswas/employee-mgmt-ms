--liquibase formatted sql

--changeset arup:01
CREATE TABLE employee (
    id INT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100)
);

--changeset arup:02
ALTER TABLE employee ADD COLUMN created_at TIMESTAMP;

--rollback ALTER TABLE employee DROP COLUMN created_at;