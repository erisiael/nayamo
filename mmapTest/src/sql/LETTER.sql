create table letter(
  no number(5) not null, 
  title varchar2(50) not null,
  contents varchar2(1000) not null,
  form_oks_no references oks(oks_no), --보낸사람
  nick references oks(nick), --받는사람
  indate date default sysdate, --보낸시간
  read varchar2(2) default 'N' --받는사람이 읽었는지 안 읽었는지 체크
);

create sequence letter_seq;