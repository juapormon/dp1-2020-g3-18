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

INSERT INTO users(username,password,enabled) VALUES ('t','t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'t','student');

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

INSERT INTO students(id,first_name,last_name,username) VALUES (1, 'Tomás Francisco', 'Rodríguez Rodríguez', 't');
INSERT INTO students(id,first_name,last_name,username) VALUES (66, 'administrador', 'Rodríguez Rodríguez', 'a');
INSERT INTO students(id,first_name,last_name,username) VALUES (2, 'Francisco', 'Fernández', 'fran');
INSERT INTO students(id,first_name,last_name,username) VALUES (3, 'Serperior', 'Surmano', 's');


INSERT INTO teachers(id,first_name,last_name,username) VALUES (1, 'Julian', 'Locuelo García', 'julian');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (2, 'Popench', 'De los bosques Hernández', 'pop');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (3, 'Macarena', 'Arejo Pínchez', 'macarron32');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (4, 'Ambrosio', 'Ansiado Augusto', 'ambrella');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (5, 'Abelle', 'Lambert Ginni', 'abe');



INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (1, 1, 'Las clases de este profesor me parecen un autentico toston, creo que no debería deshaogarse tanto con nosotros',1,1);
INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (2, 5, 'Las clases de este profesor estan mejorando desde el ultimo trimestre, le falta confianza',1,1);
INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (3, 9, 'Me gusta mucho como da las clases y como ha evolucionado desde el inicio, un auténtico profesor',1,1);

INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (4, 3, 'Me gusta mucho como da las clases y como ha evolucionado desde el inicio, un auténtico profesor',1,2);
INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (5, 4, 'Me gusta mucho como da las clases y como ha evolucionado desde el inicio, un auténtico profesor',1,2);
INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (6, 6, 'Me gusta mucho como da las clases y como ha evolucionado desde el inicio, un auténtico profesor',1,3);





INSERT INTO colleges(id,name,city) VALUES (1,'etsii', 'Sevilla');

INSERT INTO deans(id,first_name,last_name,username) VALUES (1, 'Pedro', 'Javieles', 'admin1');
INSERT INTO deans(id,first_name,last_name,username,college_id) VALUES (2, 'Paco', 'Administrador', 'a', 1);



INSERT INTO professionalexperiences(id,university,comment) VALUES (1, 'Universidad de Sevilla', 'Comentario de prueba 1.');

INSERT INTO researchexperiences(id,research) VALUES (1, 'Investigación sobre como sobre como cocinar arroz sin que se te pegue con un boli y un transportador');

INSERT INTO teachingexperiences(id,titulation,comment) VALUES (1, 'Graduado en ciencias sociales y económicas del gramo y medio', 'Comentario de prueba a ver que sale...');

INSERT INTO externalevaluations(id,note,comment) VALUES (1, 8, 'Otro comentario de prueba a ver que sale...');

INSERT INTO personalexperiences(id,res_experience,tea_experience,pro_experience, ext_evaluation) VALUES (1,1,1,1,1);

INSERT INTO reports(id,reason, score_id) VALUES (5,'Comportamiento abusivo', 1);


INSERT INTO subjects(id,name,curso) VALUES (111,'Diseño y pruebas', 3);
INSERT INTO subjects(id,name,curso) VALUES (112,'Fundamentos Fisicos de la Informatica', 1);
INSERT INTO subjects(id,name,curso) VALUES (113,'Matematica Discreta', 2);
INSERT INTO subjects(id,name,curso) VALUES (114,'Circuitos Electronicos Digitales', 1);
INSERT INTO subjects(id,name,curso) VALUES (115,'Sistemas Operativos', 2);
INSERT INTO subjects(id,name,curso) VALUES (116,'Logica Informatica', 2);
INSERT INTO subjects(id,name,curso) VALUES (117,'Redes de Computadores', 2);
INSERT INTO subjects(id,name,curso) VALUES (118,'Derecho de la Informatica', 4);



INSERT INTO departments(id,name) VALUES (1,'Lenguaje y sistemas informaticos');
INSERT INTO departments(id,name) VALUES (2,'Tecnologia electronica');
INSERT INTO departments(id,name) VALUES (3,'Matematicas aplicadas');
INSERT INTO departments(id,name) VALUES (4,'Ingenieria del diseño');
INSERT INTO departments(id,name) VALUES (5,'Electronica y magnetismo');

INSERT INTO subjects(id,name,curso, department_id) VALUES (150,'Analisis y diseño de algoritmos', 2,1);
INSERT INTO subjects(id,name,curso, department_id) VALUES (151,'Fundamentos de programación', 1,1);
INSERT INTO subjects(id,name,curso, department_id) VALUES (152,'Criptografia', 4,3);
INSERT INTO subjects(id,name,curso, department_id) VALUES (153,'Circuitos', 1,2);

INSERT INTO teachers_subjects VALUES (1,111);
INSERT INTO teachers_subjects VALUES (2,112);
INSERT INTO teachers_subjects VALUES (1,113);
INSERT INTO teachers_subjects VALUES (3,114);
INSERT INTO teachers_subjects VALUES (2,118);
INSERT INTO teachers_subjects VALUES (4,115);
INSERT INTO teachers_subjects VALUES (5,117);

INSERT INTO teachers_departments VALUES (1,1);
INSERT INTO teachers_departments VALUES (2,2);
INSERT INTO teachers_departments VALUES (1,3);
INSERT INTO teachers_departments VALUES (3,2);
INSERT INTO teachers_departments VALUES (2,5);
INSERT INTO teachers_departments VALUES (4,4);
INSERT INTO teachers_departments VALUES (5,3);


INSERT INTO students_subjects VALUES (2,111);
INSERT INTO students_subjects VALUES (2,112);
INSERT INTO students_subjects VALUES (2,113);
INSERT INTO students_subjects VALUES (1,114);
INSERT INTO students_subjects VALUES (1,115);
INSERT INTO students_subjects VALUES (3,116);

-- INSERT INTO subjects_departments VALUES(111,1);
-- INSERT INTO subjects_departments VALUES(112,2);
-- INSERT INTO subjects_departments VALUES(113,1);
-- INSERT INTO subjects_departments VALUES(114,2);


