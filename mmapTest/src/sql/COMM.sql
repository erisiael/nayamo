create table COMM
(
  COMM_no number primary key,
  OKS_no number references OKS(OKS_no),
  C_MENT varchar2(500),
  regdate date default sysdate
);

create sequence COMM_SEQ;