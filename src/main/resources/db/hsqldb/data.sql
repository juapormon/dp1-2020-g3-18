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


INSERT INTO students(id,first_name,last_name,username) VALUES (1, 'Tomás Francisco', 'Rodríguez Rodríguez', 'tomas');
INSERT INTO students(id,first_name,last_name,username) VALUES (2, 'Francisco', 'Fernández', 'fran');

INSERT INTO teachers(id,first_name,last_name,username) VALUES (1, 'Julián', 'Locuelo García', 'juloga1');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (2, 'María', 'Mazorca Pontevedra', 'mamapon1');

INSERT INTO scores(id,value,comment,student_id,teacher_id) VALUES (1, 1, 'Comentario del score 1',1,1);

INSERT INTO deans(id,first_name,last_name,username) VALUES (1, 'Pedro', 'Javieles', 'admin1');

INSERT INTO colleges(id,name, city) VALUES (1,'etsii', 'Sevilla');


--INSERT INTO teacher_scores VALUES (2, 1);
--INSERT INTO teacher_scores VALUES (2, 2);

-- INSERT INTO teachers(id,first_name,last_name,username) VALUES (1, 'Julián', 'Locuelo García', 'juloga1');
-- INSERT INTO teachers(id,first_name,last_name,username) VALUES (2, 'María', 'Mazorca Pontevedra', 'mamapon1');
-- 
-- INSERT INTO teacher_scores VALUES (2, 1);
-- INSERT INTO teacher_scores VALUES (2, 2);


INSERT INTO professionalexperiences(id,university,comment) VALUES (1, 'Universidad de Sevilla', 'Comentario de prueba 1.');

INSERT INTO researchexperiences(id,research) VALUES (1, 'Investigación sobre como sobre como cocinar arroz sin que se te pegue con un boli y un transportador');

INSERT INTO teachingexperiences(id,titulation,comment) VALUES (1, 'Graduado en ciencias sociales y económicas del gramo y medio', 'Comentario de prueba a ver que sale...');

INSERT INTO externalevaluations(id,note,comment) VALUES (1, 8, 'Otro comentario de prueba a ver que sale...');

INSERT INTO personalexperiences(id,res_experience,tea_experience,pro_experience, ext_evaluation) VALUES (1,1,1,1,1);

INSERT INTO reports(id,reason) VALUES (1,'Comportamiento abusivo');

--Si quieres una collection de algo tienes que hacer lo siguient
--Lo voy haciendo mejor

--INSERT INTO teachers(id,first_name,last_name,username) VALUES (98, 'test', 'test', 'test');
--Las demas cosas me dan igual porque no tienen ahora mismo NotNull o NotBlank
--INSERT INTO scores(id,point,comment) VALUES (1, 1, 'Comentario del score 1');
--INSERT INTO scores(id,point,comment,teacher_id) VALUES (8, 5, 'test',98);
--INSERT INTO scores(id,point,comment,teacher_id) VALUES (54, 5, 'test',98);
--El id de teacher es el 98 que hemos creado, entonces
--INSERT INTO scores(id,point,comment,teacher_id) VALUES (2,5, 'test',98) --Todas estas scores se le estan añadiendo tb a su collection
--INSERT INTO scores(id,point,comment,teacher_id) VALUES (3,5, 'test',98) --de scores, entiendes? aaaaaa esto es lo que nosabia coño, va va
--INSERT INTO scores(id,point,comment,teacher_id) VALUES (3,5, 'test',98) -- entonces creo que puedo continuar con esto, y te pregunto a la 
--tarde si me quedo pillado otra vez, vava perfe, me dices lo que sea, de todos modos prueba que no pete este data xd




