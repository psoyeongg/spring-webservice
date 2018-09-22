/** post test data **/

insert into posts (title, author, content, code, status, created_date, modified_date) values ('테스트1', 'test1@gmail.com', '테스트1의 본문', '마크다운1', 'Y', now(), now());
insert into posts (title, author, content, code, status, created_date, modified_date) values ('테스트2', 'test2@gmail.com', '테스트2의 본문', '마크다운2', 'Y', now(), now());
insert into posts (title, author, content, code, status, created_date, modified_date) values ('테스트3', 'test3@gmail.com', '테스트3의 본문', '마크다운3', 'Y', now(), now());
insert into posts (title, author, content, code, status, created_date, modified_date) values ('테스트4', 'test4@gmail.com', '테스트4의 본문', '마크다운4', 'Y', now(), now());
insert into posts (title, author, content, code, status, created_date, modified_date) values ('테스트5', 'test5@gmail.com', '테스트5의 본문', '마크다운5', 'Y', now(), now());

/*insert into posts (title, author, content, created_date, modified_date) values ('테스트1', 'test1@gmail.com', '테스트1의 본문', now(), now());
insert into posts (title, author, content, created_date, modified_date) values ('테스트2', 'test2@gmail.com', '테스트2의 본문', now(), now());*/

/** category test data **/
insert into category (name, created_date, modified_date) values ('category test1', now(), now());
insert into category (name, created_date, modified_date) values ('category test2', now(), now());
insert into category (name, created_date, modified_date) values ('category test3', now(), now());