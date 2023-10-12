--liquibase formatted sql
--changeset maria_test:1

INSERT INTO shelter(address, description, name)
VALUES ('dog address', 'dog description', 'dog'),
       ('cat address', 'cat description', 'cat');