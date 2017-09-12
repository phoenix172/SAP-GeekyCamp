CREATE DATABASE Notes;
CREATE TABLE Notes(
    id SERIAL PRIMARY KEY,
    content varchar(255) NOT NULL
);