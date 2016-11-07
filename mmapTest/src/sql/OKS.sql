create table OKS
(
  OKS_no number primary key,
  email varchar2(50) unique not null,
  pass varchar2(30) not null,
  nick varchar2(30),
  point number default 0,
  regdate date default sysdate
);

create sequence OKS_SEQ;
select * from OKS;


create table text(
	keyword varchar2(100)
)
select * from text;
drop table text;
select * from it;


