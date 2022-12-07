-- check and create the schema/database if needed
CREATE DATABASE IF NOT EXISTS MEMS;

-- to explicity select the Database schema for the table creation further
USE MEMS;

-- create a table for the User
CREATE TABLE IF NOT EXISTS User
(
  Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  FirstName VARCHAR(20) NOT NULL,
  LastName VARCHAR(20) NOT NULL,
  UserName VARCHAR(20) UNIQUE NOT NULL,
  Password VARCHAR(50) NOT NULL,
  IsActive CHAR(1) DEFAULT 'N' COMMENT 'A flag to denote whether an User is active'
);

-- DML for Sample Inserts

INSERT INTO User (`FirstName`, `LastName`, `UserName`, `Password`) VALUES
('Arun', 'Prasad', 'Arun007', 'Arun@007');
INSERT INTO User (`FirstName`, `LastName`, `UserName`, `Password`) VALUES
('Karthik', 'Kiran', 'Karthik08', 'Karthik@08');
