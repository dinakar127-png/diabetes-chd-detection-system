CREATE DATABASE chd_system;

USE chd_system;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50),
    age INT,
    glucose DOUBLE,
    bp DOUBLE,
    cholesterol DOUBLE
);

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    specialization VARCHAR(50)
);

CREATE TABLE reports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    risk VARCHAR(20)
);