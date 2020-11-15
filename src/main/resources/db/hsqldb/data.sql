-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin','admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (99,'admin','admin');

-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

INSERT INTO users(username,password,enabled) VALUES ('josrojrom1','pepe12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'josrojrom1','owner');

INSERT INTO users(username,password,enabled) VALUES ('luis','luis',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'luis','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO users(username,password,enabled) VALUES ('juapormon','juapormon',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'juapormon','owner');

INSERT INTO scores(id,point,comment) VALUES (1, 1, 'Comentario del score 1');
INSERT INTO scores(id,point,comment) VALUES (2, 3, 'Comentario del score 2');

INSERT INTO deans(id,first_name,last_name,username) VALUES (1, 'Pedro', 'Javieles', 'admin1');

INSERT INTO colleges(id,name,ciudad,dean_id) VALUES (1, 'etsii', 'Sevilla', 1);

-- INSERT INTO teachers(id,first_name,last_name,username) VALUES (1, 'Julian', 'Locuelo', 'profesor1');

