
create sequence kangbank_notice_seq;
select kangbank_notice_seq.nextval from dual;
--캉뱅 공지사항
drop table kangbank_notice;
create table kangbank_notice(
	no number primary key,
	title varchar2(50) not null,
    content clob not null,
	hits number default 0,
	timePosted date not null,
	email varchar2(100) not null,
	tel varchar2(100) not null,
	constraint kangbank_notice_fk foreign key(email,tel) references kangbank_customer(email,tel)
)

------
insert into kangbank_notice(no,title,content,timePosted,email,tel) 
values(kangbank_notice_seq.nextval,'방가1','ㅋㅋ1',sysdate,'andrew2967@naver.com','01093942967');

insert into kangbank_notice(no,title,content,timePosted,email,tel) 
values(kangbank_notice_seq.nextval,'방가2','ㅋㅋ2',sysdate,'andrew2967@naver.com','01093942967');

insert into kangbank_notice(no,title,content,timePosted,email,tel) 
values(kangbank_notice_seq.nextval,'방가3','ㅋㅋ3',sysdate,'andrew2967@naver.com','01093942967');

insert into kangbank_notice(no,title,content,timePosted,email,tel) 
values(kangbank_notice_seq.nextval,'방가4','ㅋㅋ4',sysdate,'andrew2967@naver.com','01093942967');

insert into kangbank_notice(no,title,content,timePosted,email,tel) 
values(kangbank_notice_seq.nextval,'방가5','ㅋㅋ5',sysdate,'andrew2967@naver.com','01093942967');

insert into kangbank_notice(no,title,content,timePosted,email,tel) 
values(kangbank_notice_seq.nextval,'방가6','ㅋㅋ6',sysdate,'andrew2967@naver.com','01093942967');

insert into kangbank_notice(no,title,content,timePosted,email,tel) 
values(kangbank_notice_seq.nextval,'방가7','ㅋㅋ7',sysdate,'andrew2967@naver.com','01093942967');

insert into kangbank_notice(no,title,content,timePosted,email,tel) 
values(kangbank_notice_seq.nextval,'방가12','ㅋㅋ12',sysdate,'andrew2967@naver.com','01093942967');

		select b.no,b.title,b.timePosted,b.hits,b.email,m.name from (
			select no,title,timePosted,hits,ceil(rownum/5) as page,email from (
					select no,title,to_char(timePosted,'YYYY.MM.DD') as timePosted,
					hits,email from kangbank_notice  order by no desc
		     )
       ) b,kangbank_customer m where b.email=m.email and page=1

update from kangbank_notice set hits=hits+1 where no=21