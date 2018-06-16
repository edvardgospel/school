CREATE SCHEMA school;

CREATE TABLE school.`role` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));
  
CREATE TABLE school.`user` (
  id INT NOT NULL AUTO_INCREMENT,
  role_id INT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id),
  CONSTRAINT role_id
    FOREIGN KEY (role_id)
    REFERENCES school.`role` (id));
	
CREATE TABLE school.`subject` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  grade INT NULL,
  PRIMARY KEY (id));

CREATE TABLE school.`teaching` (
  user_id INT NOT NULL,
  subject_id INT NOT NULL,
  CONSTRAINT user_id
    FOREIGN KEY (user_id)
    REFERENCES school.`user` (id),
  CONSTRAINT subject_id
    FOREIGN KEY (subject_id)
    REFERENCES school.`subject` (id));
	
CREATE TABLE school.`timetable` (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NULL,
  subject_id INT NULL,
  day INT NULL,
  time INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT userid
    FOREIGN KEY (user_id)
    REFERENCES school.`user` (id),
  CONSTRAINT subjectid
    FOREIGN KEY (subject_id)
    REFERENCES school.`subject` (id));
	
INSERT INTO role (id,name)
VALUES (1,'admin');

INSERT INTO role (id,name)
VALUES (2,'teacher');

INSERT INTO user (id,role_id,name)
values (1,1,'Eros Edvard');