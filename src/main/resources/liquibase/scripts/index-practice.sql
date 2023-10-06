-- liquibase formatted sql

-- changeset Dmitry:1
create schema if not exists telegram_bot;

-- changeset Dmitry:2
create table if not exists team_project(
    id bigSerial primary key ,
    user_id bigInt not null,
);