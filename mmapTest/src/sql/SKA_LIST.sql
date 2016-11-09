create table SKA_list
(
  SKA_l_no number primary key,
  SKA_no number references SKA(SKA_no),
  OKS_no number references OKS(OKS_no),
  comt varchar2(300),
  regdate date default sysdate
);

drop table ska_list;
create sequence SKA_LIST_SEQUENCE;
select * from ska_list;
commit