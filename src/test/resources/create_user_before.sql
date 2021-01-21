delete from user_role;
delete from usr;

insert into usr(id, active, password, username) values
(1, true, '$2a$08$WWjyy5kURXPsC/WKvBUl9Ow1xvpbzkiZjjo5oC/sX33Q7zdr2Md/y', 'user'),
(2, true, '$2a$08$UdS5DpiwowQsc.12yqEwBOTfmqVYNaOqHbUdQ.gB2II3Im.1oTSzO', 'test');

insert into user_role values
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER');
