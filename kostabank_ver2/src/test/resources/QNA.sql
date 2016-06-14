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
   qna_fileAddress varchar2(100),
   qna_time date not null
)

ALTER TABLE kangbank_qna ADD
CONSTRAINT kangbank_customer_fk FOREIGN KEY
( email, tel
) REFERENCES kangbank_customer (email,tel)

select*from kangbank_qna

select


select q.qna_no,q.qna_title,q.qna_type,c.email,c.tel,c.name,q.qna_content,q.qna_fileAddress,q.qna_time
from kangbank_qna q,kangbank_customer c where q.qna_no=11



select q.qna_no,q.qna_title,q.qna_type,c.email,c.tel,c.name,q.qna_time,q.qna_fileAddress from (
			select qna_no,qna_title,qna_time,qna_type,ceil(rownum/5) as page,email,tel,qna_fileAddress from (
					select qna_no,qna_title,to_char(qna_time,'YYYY.MM.DD') as qna_time,
					qna_type,email,tel,qna_fileAddress from kangbank_qna  order by qna_no desc
		     )
       ) q,kangbank_customer c where page=1 and c.email='seokjk2308@naver.com'







insert into kangbank_qna(qna_no,qna_title,qna_type,email,tel,qna_content,qna_fileAddress,qna_time)
values(kangbank_qna_seq.nextval,'잉여 하네요 ㅡㅡ','계좌이체오류','dbclqydqyd@naver.com','01091775524','장난하나요','c\\java',sysdate)