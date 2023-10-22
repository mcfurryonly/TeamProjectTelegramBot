-- liquibase formatted sql

--changeset lBorisov:1
create schema if not exists telegram_bot;
--changeset lBorisov:2
create table visitor
(
    id primary key ,
    name not null,
    phoneNumber not null,
    visitCount not null
);
--changeset lBorisov:3
create table cat_shelter
(id primary key,
name not null,
age not null,
gender not null,
photo not null
);
--changeset lBorisov:4
create table dog_shelter
(id primary key,
name not null,
age null null,
size not null,
gender not null,
photo not null
);
--changeset lBorisov:5
create table volunteer
(id primary key,
name not null,
shelter not null
);
--changeset lBorisov:6
create table animal_adopter
(id primary key,
name not null,
date timestamp not null,
id animal not null
);
