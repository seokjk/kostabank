create sequence kangbank_qna_seq;
drop sequence kangbank_qna_seq;

drop table kangbank_qna
create table kangbank_qna(
	qna_no number primary key,
	qna_title varchar2(100) not null,
	qna_type varchar2(100) not null,
	email varchar2(100) not null,
	tel varchar2(100) not null,
	qna_content clob not null,
	qna_fileAddress varchar2(100)	
	
)

ALTER TABLE kangbank_qna ADD
CONSTRAINT kangbank_customer_fk FOREIGN KEY
( email, tel
) REFERENCES kangbank_customer (email,tel)

select*from kangbank_qna

insert into kangbank_qna(qna_no,qna_title,qna_type,email,tel,qna_content,qna_fileAddress)
values(kangbank_qna_seq.nextval,'잉여 하네요 ㅡㅡ','계좌이체오류','sungyounet@naver.com','01041982978','장난하나요','c\\java')