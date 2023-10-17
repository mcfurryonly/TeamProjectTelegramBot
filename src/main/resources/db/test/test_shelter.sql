--liquibase formatted sql

--changeset Dima:1
create schema if not exists shelter;

--changeset Dima:2
create table if not exists shelter.shelter(
    address varchar(255),
    description varchar(255),
    name varchar(255)
    );

--changeset maria_test:3
INSERT INTO shelter.shelter(address, description, name)
VALUES ('dog address', 'dog description', 'dog'),
       ('cat address', 'cat description', 'cat');