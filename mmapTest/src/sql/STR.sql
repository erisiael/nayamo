create table STR
(
  STR_no number primary key,
  OKS_email varchar2(50) references OKS(email),
  name varchar2(30) not null,
  savepath varchar2(50) not null,
  enter_code varchar2(20) not null,
  regdate date default sysdate
);

create sequence STR_SEQ;