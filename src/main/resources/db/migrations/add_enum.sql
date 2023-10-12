--liquibase formatted sql
--changeset maria:1

CREATE TYPE type_rule AS ENUM('ACCESS_TO_TERRITORY', 'IN_TERRITORY',
    'COMMUNICATION_WITH_ANIMALS', 'MEETING_WITH_ANIMALS');

CREATE TYPE type_recommendation AS ENUM('ANIMAL_TRANSPORTATION','BIG_HOUSE','SMALL_HOUSE',
    'HOUSE_FOR_DISABLED', 'ADVISE_OF_CYNOLOGIST', 'CYNOLOGISTS', 'SAFETY_PRECAUTIONS');
