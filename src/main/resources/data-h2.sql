
/** category test data **/
insert into category (id, name, created_date, modified_date) values (1, 'spring', now(), now());
insert into category (id, name, created_date, modified_date) values (2, 'java', now(), now());
insert into category (id ,name, created_date, modified_date) values (3, 'sql', now(), now());

/** post test data **/

insert into posts (title, author, content, code, status, created_date, modified_date, CATEGORY_ID) values ('테스트1', 'test1@gmail.com', '테스트1의 본문', '마크다운1', 'Y', now(), now(), 1);
insert into posts (title, author, content, code, status, created_date, modified_date, CATEGORY_ID) values ('테스트2', 'test2@gmail.com', '테스트2의 본문', '마크다운2', 'Y', now(), now(), 1);
insert into posts (title, author, content, code, status, created_date, modified_date, CATEGORY_ID) values ('테스트3', 'test3@gmail.com', '테스트3의 본문', '마크다운3', 'Y', now(), now(), 2);
insert into posts (title, author, content, code, status, created_date, modified_date, CATEGORY_ID) values ('테스트4', 'test4@gmail.com', '테스트4의 본문', '마크다운4', 'Y', now(), now(), 2);
insert into posts (title, author, content, code, status, created_date, modified_date, CATEGORY_ID) values ('테스트5', 'test5@gmail.com', '테스트5의 본문', '마크다운5', 'Y', now(), now(), 3);

/*insert into posts (title, author, content, created_date, modified_date) values ('테스트1', 'test1@gmail.com', '테스트1의 본문', now(), now());
insert into posts (title, author, content, created_date, modified_date) values ('테스트2', 'test2@gmail.com', '테스트2의 본문', now(), now());*/
