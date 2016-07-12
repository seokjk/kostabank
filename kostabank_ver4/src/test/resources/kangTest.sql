select no,qna_no,qna_refNo,qna_replyNo,qna_level,qna_title,qna_type,email,tel,name,qna_time,page
from      (select no,qna_no,qna_refNo,qna_replyNo,qna_level,qna_title,qna_type,email,tel,name,qna_time,ceil(rownum/10) as page
         from      (select no, q.qna_no,qna_refNo,qna_replyNo,qna_level,q.qna_title,c.email,c.tel,c.name,q.qna_type,to_char(qna_time,'YYYY.MM.DD') as qna_time
                  from  (select rownum as no, qna_no,qna_refNo,qna_replyNo,qna_level,qna_title,qna_type,email,tel,qna_time
                        from kangbank_qna order by qna_no asc)q, kangbank_customer c
                  where q.email=c.email 
                        start with qna_refno=0
                        connect BY PRIOR qna_no=qna_refno
                        order siblings by qna_refno asc, qna_no desc)
         )
where qna_refNo =0
select qna_refNo as qna_no from kangbank_qna where qna_refNo != 0
where page=#{page} and email=#{email}



select A.qna_no,A.qna_refNo,A.qna_replyNo,A.qna_level,A.qna_title,A.qna_type,A.email,A.tel,A.qna_time
from kangbank_qna A,
	 kangbank_qna B
where A.qna_refNo = 0 AND B.qna_refNo != 0
group by A.qna_no,A.qna_refNo,A.qna_replyNo,A.qna_level,A.qna_title,A.qna_type,A.email,A.tel,A.qna_time
order by A.qna_refno asc, A.qna_no desc

select kangbank_qna_seq.nextval from dual

