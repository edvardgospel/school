CREATE SCHEMA school;

CREATE TABLE school.`user` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45),
  role VARCHAR(45),
  PRIMARY KEY (id)
);

CREATE TABLE school.`subject` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45),
  grade INT,
  PRIMARY KEY (id)
);

CREATE TABLE school.`teaching` (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  subject_id INT NOT NULL,
  PRIMARY KEY (id,user_id,subject_id),
  FOREIGN KEY (user_id) REFERENCES school.`user`(id),
  FOREIGN KEY (subject_id) REFERENCES school.`subject`(id)
);

CREATE TABLE school.`timetable` (
  id INT NOT NULL AUTO_INCREMENT,
  teaching_id INT NOT NULL,
  day VARCHAR(45),
  time INT,
  PRIMARY KEY (id),
  FOREIGN KEY (teaching_id) REFERENCES school.`teaching`(id)
);

INSERT INTO user (id,name,role) VALUES (1,'Edvard Eros','admin');

INSERT INTO subject(id,name,grade) VALUES (1,'math',1);
INSERT INTO subject(id,name,grade) VALUES (2,'math',2);
INSERT INTO subject(id,name,grade) VALUES (3,'math',3);
INSERT INTO subject(id,name,grade) VALUES (4,'math',4);
INSERT INTO subject(id,name,grade) VALUES (5,'art',1);
INSERT INTO subject(id,name,grade) VALUES (6,'art',2);
INSERT INTO subject(id,name,grade) VALUES (7,'art',3);
INSERT INTO subject(id,name,grade) VALUES (8,'art',4);
INSERT INTO subject(id,name,grade) VALUES (9,'phisics',1);
INSERT INTO subject(id,name,grade) VALUES (10,'phisics',2);
INSERT INTO subject(id,name,grade) VALUES (11,'phisics',3);
INSERT INTO subject(id,name,grade) VALUES (12,'phisics',4);
INSERT INTO subject(id,name,grade) VALUES (13,'p.e.',1);
INSERT INTO subject(id,name,grade) VALUES (14,'p.e.',2);
INSERT INTO subject(id,name,grade) VALUES (15,'p.e.',3);
INSERT INTO subject(id,name,grade) VALUES (16,'p.e.',4);
INSERT INTO subject(id,name,grade) VALUES (17,'music',1);
INSERT INTO subject(id,name,grade) VALUES (18,'music',2);
INSERT INTO subject(id,name,grade) VALUES (19,'music',3);
INSERT INTO subject(id,name,grade) VALUES (20,'music',4);
INSERT INTO subject(id,name,grade) VALUES (21,'literature',1);
INSERT INTO subject(id,name,grade) VALUES (22,'literature',2);
INSERT INTO subject(id,name,grade) VALUES (23,'literature',3);
INSERT INTO subject(id,name,grade) VALUES (24,'literature',4);
INSERT INTO subject(id,name,grade) VALUES (25,'english',1);
INSERT INTO subject(id,name,grade) VALUES (26,'english',2);
INSERT INTO subject(id,name,grade) VALUES (27,'english',3);
INSERT INTO subject(id,name,grade) VALUES (28,'english',4);
INSERT INTO subject(id,name,grade) VALUES (29,'history',1);
INSERT INTO subject(id,name,grade) VALUES (30,'history',2);
INSERT INTO subject(id,name,grade) VALUES (31,'history',3);
INSERT INTO subject(id,name,grade) VALUES (32,'history',4);
INSERT INTO subject(id,name,grade) VALUES (33,'geography',1);
INSERT INTO subject(id,name,grade) VALUES (34,'geography',2);
INSERT INTO subject(id,name,grade) VALUES (35,'geography',3);
INSERT INTO subject(id,name,grade) VALUES (36,'geography',4);
INSERT INTO subject(id,name,grade) VALUES (37,'biology',1);
INSERT INTO subject(id,name,grade) VALUES (38,'biology',2);
INSERT INTO subject(id,name,grade) VALUES (39,'biology',3);
INSERT INTO subject(id,name,grade) VALUES (40,'biology',4);
INSERT INTO subject(id,name,grade) VALUES (41,'chemistry',1);
INSERT INTO subject(id,name,grade) VALUES (42,'chemistry',2);
INSERT INTO subject(id,name,grade) VALUES (43,'chemistry',3);
INSERT INTO subject(id,name,grade) VALUES (44,'chemistry',4);
INSERT INTO subject(id,name,grade) VALUES (45,'hungarian',1);
INSERT INTO subject(id,name,grade) VALUES (46,'hungarian',2);
INSERT INTO subject(id,name,grade) VALUES (47,'hungarian',3);
INSERT INTO subject(id,name,grade) VALUES (48,'hungarian',4);
INSERT INTO subject(id,name,grade) VALUES (49,'religion',1);
INSERT INTO subject(id,name,grade) VALUES (50,'religion',2);
INSERT INTO subject(id,name,grade) VALUES (51,'religion',3);
INSERT INTO subject(id,name,grade) VALUES (52,'religion',4);