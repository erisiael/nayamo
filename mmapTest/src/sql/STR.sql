create table STR
(
  STR_no number primary key,
  OKS_email varchar2(50) references OKS(email),
  name varchar2(30) not null,-- 제목
  ----------------seokgi
  keyword varchar2(30) not null,--키워드
  category varchar2(30) not null,
  
  ------------
  savepath varchar2(50) not null,--파일이 저장될 경로 (마인드맵의 정보)
  
  enter_code varchar2(20) not null,--비밀번호
  regdate date default sysdate--만든날
);
drop table STR;

create sequence STR_SEQ;