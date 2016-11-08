create table SKA
(
  SKA_no number primary key,
  OKS_email varchar2(50) references OKS(email),
  name varchar2(30) not null,
  point number not null,
  winner number references OKS(OKS_no),  
  regdate date default sysdate
);