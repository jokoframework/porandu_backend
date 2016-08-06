insert into PORANDU.EVENTS(CODE, DESCRIPTION, IMAGE_URL, INSERTED_AT) VALUES ('VTECH-6', 'Viernes Tecnológico #6', 'https://c1.staticflickr.com/7/6035/6243837427_cb427c2a8a_b.jpg', now());
insert into PORANDU.EVENTS(CODE, DESCRIPTION, IMAGE_URL, INSERTED_AT) VALUES ('VTECH-5', 'Viernes Tecnológico #5', 'https://pixabay.com/static/uploads/photo/2015/05/31/10/51/technology-791029_960_720.jpg', now());
insert into PORANDU.PERSONS(EMAIL, FULL_NAME) VALUES ('javier@dowhile0.org','Javier Martínez Canillas');
insert into PORANDU.PERSONS(EMAIL, FULL_NAME) VALUES ('alefeltes@sodep.com.py','Ale Feltes Quenhan');
insert into PORANDU.PERSONS(EMAIL, FULL_NAME) VALUES ('expo3@sodep.com.py','Asistente Invitado #1');
insert into PORANDU.LECTURES(CODE, DESCRIPTION, IMAGE_URL, EVENT_ID, AUTHOR_ID, INSERTED_AT) VALUES ('JMC-1', 'Survivor''s Guide to Contributing to the Linux Kernel', 'https://c1.staticflickr.com/7/6091/6365692623_8380d6fc4a_z.jpg',1, 1, now());
insert into PORANDU.LECTURES(CODE, DESCRIPTION, IMAGE_URL, EVENT_ID, AUTHOR_ID, INSERTED_AT) VALUES ('JOKO-1', 'Joko: El paso de la potencia al acto', 'https://pixabay.com/static/uploads/photo/2016/06/09/18/04/frog-1446246_960_720.jpg',1, 2, now());
insert into PORANDU.QUESTIONS(AUTHOR_ID, LECTURE_ID, DETAIL, TITLE, INSERTED_AT) VALUES (2,1,null,'¿por qué eligieron Spring Boot?', now());
insert into PORANDU.QUESTIONS(AUTHOR_ID, LECTURE_ID, DETAIL, TITLE, INSERTED_AT) VALUES (2,1,null,'¿Por qué no usaron algún framework de front end como AngularJS?', now());
insert into PORANDU.QUESTIONS(AUTHOR_ID, LECTURE_ID, DETAIL, TITLE, INSERTED_AT) VALUES (2,1,null,'¿Qué licencia tiene Joko?', now());


update PORANDU.LECTURES set AUTHOR_ID = 2 where CODE = 'JOKO-1'

update PORANDU.QUESTIONS set LECTURE_ID = 2 where LECTURE_ID =1