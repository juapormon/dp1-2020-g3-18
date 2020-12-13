-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('a','a',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (99,'a','admin');

INSERT INTO users(username,password,enabled) VALUES ('josrojrom1','pepe12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'josrojrom1','dean');

INSERT INTO users(username,password,enabled) VALUES ('luis','luis',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'luis','owner');

INSERT INTO users(username,password,enabled) VALUES ('juapormon','juapormon',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'juapormon','owner');

INSERT INTO users(username,password,enabled) VALUES ('juloga1','juloga1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'juloga1','teacher');

INSERT INTO users(username,password,enabled) VALUES ('mamapon1','mamapon1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'mamapon1','teacher');

INSERT INTO users(username,password,enabled) VALUES ('tomas','tomas12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'tomas','student');

INSERT INTO users(username,password,enabled) VALUES ('fran','fran12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'fran','student');

INSERT INTO users(username,password,enabled) VALUES ('s','s',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'s','student');

INSERT INTO users(username,password,enabled) VALUES ('julian','julian',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'julian','teacher');

INSERT INTO users(username,password,enabled) VALUES ('pop','pop',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'pop','teacher');

INSERT INTO users(username,password,enabled) VALUES ('macarron32','macarron32',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'macarron32','teacher');

INSERT INTO users(username,password,enabled) VALUES ('ambrella','ambrella',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'ambrella','teacher');

INSERT INTO users(username,password,enabled) VALUES ('abe','abe',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'abe','teacher');

INSERT INTO students(id,first_name,last_name,username) VALUES (1, 'Tomás Francisco', 'Rodríguez Rodríguez', 'tomas');
INSERT INTO students(id,first_name,last_name,username) VALUES (66, 'administrador', 'Rodríguez Rodríguez', 'a');
INSERT INTO students(id,first_name,last_name,username) VALUES (2, 'Francisco', 'Fernández', 'fran');
INSERT INTO students(id,first_name,last_name,username) VALUES (3, 'Serperior', 'Surmano', 's');

INSERT INTO departments(id,name) VALUES (111,'math');
INSERT INTO departments(id,name) VALUES (222,'moth');

INSERT INTO teachers(id,first_name,last_name,username) VALUES (1, 'Julián', 'Locuelo García', 'julian');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (2, 'Popench', 'De los bosques Hernández', 'pop');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (3, 'Macarena', 'Arejo Pínchez', 'macarron32');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (4, 'Ambrosio', 'Ansiado Augusto', 'ambrella');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (5, 'Abelle', 'Lambert Ginni', 'abe');

INSERT INTO teachers_departments VALUES (1,111);
INSERT INTO teachers_departments VALUES (2,111);
INSERT INTO teachers_departments VALUES (3,222);
INSERT INTO teachers_departments VALUES (4,222);
INSERT INTO teachers_departments VALUES (5,222);

INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (1, 1, 'Comentario del score 1',1,1);


INSERT INTO deans(id,first_name,last_name,username) VALUES (1, 'Pedro', 'Javieles', 'admin1');

INSERT INTO colleges(id,name,city) VALUES (1,'etsii', 'Sevilla');


INSERT INTO professionalexperiences(id,university,comment) VALUES (1, 'Universidad de Sevilla', 'Comentario de prueba 1.');

INSERT INTO researchexperiences(id,research) VALUES (1, 'Investigación sobre como sobre como cocinar arroz sin que se te pegue con un boli y un transportador');

INSERT INTO teachingexperiences(id,titulation,comment) VALUES (1, 'Graduado en ciencias sociales y económicas del gramo y medio', 'Comentario de prueba a ver que sale...');

INSERT INTO externalevaluations(id,note,comment) VALUES (1, 8, 'Otro comentario de prueba a ver que sale...');

INSERT INTO personalexperiences(id,res_experience,tea_experience,pro_experience, ext_evaluation) VALUES (1,1,1,1,1);

INSERT INTO reports(id,reason) VALUES (1,'Comportamiento abusivo');


INSERT INTO subjects(id,name,curso) VALUES (111,'Diseño y pruebas', 3);
INSERT INTO subjects(id,name,curso) VALUES (112,'Fundamentos Fisicos de la Informatica', 1);
INSERT INTO subjects(id,name,curso) VALUES (113,'Matematica Discreta', 2);
INSERT INTO subjects(id,name,curso) VALUES (114,'Circuitos Electronicos Digitales', 1);
INSERT INTO subjects(id,name,curso) VALUES (115,'Sistemas Operativos', 2);
INSERT INTO subjects(id,name,curso) VALUES (116,'Logica Informatica', 2);
INSERT INTO subjects(id,name,curso) VALUES (117,'Redes de Computadores', 2);
INSERT INTO subjects(id,name,curso) VALUES (118,'Derecho de la Informatica', 4);
