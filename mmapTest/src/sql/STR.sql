create table STR
(
  STR_no number primary key,
  OKS_email varchar2(50) references OKS(email),
  name varchar2(30) not null,-- 제목
  ----------------seokgi
  keyword varchar2(30) not null,--키워드
  category varchar2(30) not null,
------------
  enter_code varchar2(200) not null,--비밀번호
  regdate date default sysdate--만든날
);
drop table test;
delete from IT;
create sequence STR_SEQ;
select * from it
select count(*) from it　where name = '아이폰';
delete from it where name = '아이폰'
insert into it (name) values('아이폰')