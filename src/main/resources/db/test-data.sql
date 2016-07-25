insert into PORANDU.EVENTS(CODE, DESCRIPTION) VALUES ('VTECH-6', 'Viernes Tecnológico #6');
insert into PORANDU.PERSONS(EMAIL, FULL_NAME) VALUES ('test@sodep.com.py','Expositor Sodep #1');
insert into PORANDU.PERSONS(EMAIL, FULL_NAME) VALUES ('test@sodep.com.py','Asistente Invitado #1');
insert into PORANDU.LECTURES(CODE, DESCRIPTION, EVENT_ID, AUTHOR_ID) VALUES ('JOKO-1', 'Joko Framework', 1, 1);
insert into PORANDU.QUESTIONS(AUTHOR_ID, LECTURE_ID, DETAIL, TITLE) VALUES (2,1,null,'¿por qué eligieron Spring Boot?');
insert into PORANDU.QUESTIONS(AUTHOR_ID, LECTURE_ID, DETAIL, TITLE) VALUES (2,1,null,'¿Por qué no usaron algún framework de front end como AngularJS?');
