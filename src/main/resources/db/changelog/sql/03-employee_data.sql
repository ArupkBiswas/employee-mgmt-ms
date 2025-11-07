--liquibase formatted sql

--changeset arup:06
ALTER TABLE employee_data ADD COLUMN user_name VARCHAR(50);

--changeset arup:07
ALTER TABLE employee_data ADD COLUMN password VARCHAR(50);

--changeset arup:08
ALTER TABLE employee_data ADD COLUMN enabled BOOLEAN DEFAULT TRUE;