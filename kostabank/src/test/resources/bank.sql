drop table kangbank_customer;
drop table kangbank_account;
drop table kangbank_deal_detail;
drop table kangbank_account_type;


create table kangbank_customer(
   email varchar2(100) not null,
   password varchar2(100) not null,
   name varchar2(100) not null,
   birth varchar2(100) not null,
   tel varchar2(100) not null,
   address varchar2(100) not null,
   security_card varchar2(100) default 0,
   constraint pk_customer primary key(email,tel)
);
update KANGBANK_CUSTOMER set password='1234' where email='leekorea2k@hanmail.net';
insert into KANGBANK_CUSTOMER(email, password, name, birth, tel, address)
values('leekorea2k@hanmail.net','1234','유소현','921128','01071504106','의왕');
insert into KANGBANK_CUSTOMER(email, password, name, birth, tel, address)
values('dkdkdk@nate.net','1234','아아아','921128','01071504107','의왕');

--kangbank_account_type
--name, accountType, accountExplanation, minMoney
CREATE TABLE kangbank_account_type(
name varchar2(100) PRIMARY KEY,
accountType varchar2(100) not null,
accountExplanation varchar2(3000) not null,
minMoney number default 0
)


insert into kangbank_account_type(name, accountType, accountExplanation, minMoney)
values('Kosta종합통장','입출금이자유로운예금',
'가입대상에 제한이 없고 소비자금 또는 유휴자금의 일시적 보관을 목 목적으로 개설하는 입출금이 자유로운 예금의 대표적인 상품입니다.',0);
insert into kangbank_account_type(name, accountType, accountExplanation, minMoney)
values('일반정기적금','적립식예금',
'최저 1만원이상 원단위로 최고예치한도는 없습니다. 정액적립식으로 확정금리 상품입니다.',10000);
insert into kangbank_account_type(name, accountType, accountExplanation, minMoney)
values('사업자우대적금','적립식예금',
'저탄소 녹색성장 관련 기업, 종업원 급여이체 기업 등에게 다양한 우대이율로 목돈마련을 지원하고, 대출금 상환이나
사업장 구입(임차)를 위해 중도해지하는 경우 특별이율을 적용하는 기업 전용 적립식 예금',10000);
insert into kangbank_account_type(name, accountType, accountExplanation, minMoney)
values('Kosta수퍼정기예금','거치식예금',
'목돈을 일정기간동안 예치하여 안정적인 수익을 목적으로 하는 다양한 맞춤식 정기예금',1000000);
insert into kangbank_account_type(name, accountType, accountExplanation, minMoney)
values('일반정기예금','거치식예금',
'목돈을 일정기간동안 예치하여 안정적인 수익을 목적으로 하는 일시예치식 예금입니다.',100000);

select * from kangbank_account_type;

-------------금리 테이블 관련-------------
drop table kangbank_account_rates;

create sequence kangbank_rates_seq;
drop sequence kangbank_rates_seq;

--kangbank_account_rates
--accountSeq, rates, term, name
CREATE TABLE kangbank_account_rates(
accountSeq number PRIMARY KEY,
rates number default 0,
term number default 0,
name varchar2(100) not null,
constraint fk_ratesNo foreign key(name) references kangbank_account_type(name)
)

select * from kangbank_account_rates;

insert into kangbank_account_rates(accountSeq, rates, term, name)
values(kangbank_rates_seq.nextval, 0, 0, 'Kosta종합통장');
insert into kangbank_account_rates(accountSeq, rates, term, name)
values(kangbank_rates_seq.nextval, 0, 0, 'Kosta종합통장');

insert into kangbank_account_rates(accountSeq, rates, term, name)
values(kangbank_rates_seq.nextval, 0.6, 6, '일반정기적금');
insert into kangbank_account_rates(accountSeq, rates, term, name)
values(kangbank_rates_seq.nextval, 1.3, 12, '일반정기적금');

insert into kangbank_account_rates(accountSeq, rates, term, name)
values(kangbank_rates_seq.nextval, 0.8, 6, '사업자우대적금');
insert into kangbank_account_rates(accountSeq, rates, term, name)
values(kangbank_rates_seq.nextval, 1.7, 12, '사업자우대적금');
insert into kangbank_account_rates(accountSeq, rates, term, name)
values(kangbank_rates_seq.nextval, 2.5, 18, '사업자우대적금');

insert into kangbank_account_rates(accountSeq, rates, term, name)
values(kangbank_rates_seq.nextval, 1.2, 12, 'Kosta수퍼정기예금');
insert into kangbank_account_rates(accountSeq, rates, term, name)
values(kangbank_rates_seq.nextval, 2.5, 24, 'Kosta수퍼정기예금');



create table kangbank_account(
   accountNo varchar2(100) primary key,
   issueDate date not null,
   accountPass number not null,
   balance number not null,
   email varchar2(100) not null,
   tel varchar2(100) not null,
   accountName varchar2(100) not null,
   constraint fk_accountName foreign key (accountName) references kangbank_account_type
)
   ALTER TABLE kangbank_account ADD
CONSTRAINT FK_kangbank_customer FOREIGN KEY
( email, tel
) REFERENCES kangbank_customer (email,tel)

select * from KANGBANK_ACCOUNT;
select * from KANGBANK_CUSTOMER;
select * from KANGBANK_DEAL_DETAIL;

insert into KANGBANK_ACCOUNT(accountNo,issueDate,accountPass,balance,email,tel,accountName)
values('12345',sysdate, 1234, 1000,'leekorea2k@hanmail.net','01071504106','Kosta종합통장');

create table kangbank_deal_detail(
   dealNo number primary key,
   accountNo varchar2(100) not null,
   otherAccountNo varchar2(100) not null,
   dealType varchar2(100) not null,
   amountOfMoney number not null,
   dealDate date not null,
   constraint fk_kangbank_account foreign key (accountNo) references kangbank_account(accountNo)
)

create sequence dealNo_seq;
drop sequence dealNo_seq;

insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','deposit',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','deposit',1000,sysdate);



select a.accountNo, a.otherAccountNo, a.dealDate
from (select accountNo, otherAccountNo, dealDate from kangbank_deal_detail 
) a
right outer join
(select sysdate+1 tomorrow, sysdate-1 yesterday, sysdate to_day from dual) b	
on a.dealDate = b.to_day;

select a.accountNo, a.otherAccountNo, a.dealDate
from (select accountNo,otherAccountNo, dealDate from kangbank_deal_detail 
where accountNo='12345') a
right outer join
(select sysdate yesterday from dual) b	
on a.dealDate >b.yesterday;

select a.accountNo, a.otherAccountNo, a.dealDate
from (select accountNo,otherAccountNo, dealDate from kangbank_deal_detail 
) a
right outer join
(select sysdate+1 tomorrow,sysdate-1 yesterday from dual) b	
on a.dealDate > b.yesterday


select a.accountNo, a.otherAccountNo, to_char(a.dealDate,'yyyy/mm/dd hh24:mi:ss')
from (select accountNo,otherAccountNo, dealDate from kangbank_deal_detail where accountNo='12345'
) a
right outer join
(select sysdate+1 tomorrow,sysdate-1 yesterday,sysdate to_day,add_months(sysdate,-12) lastyear from dual) b	
on a.dealDate between yesterday and to_day

--to_char비교
select a.accountNo, a.otherAccountNo, to_char(a.dealDate,'yyyy/mm/dd hh24:mi:ss')
from (select accountNo,otherAccountNo, to_char(dealDate,'yyyy/mm/dd hh24:mi:ss') as dealDate  from kangbank_deal_detail where accountNo='12345'
) a,
 (select accountNo,otherAccountNo, to_char(dealDate,'yyyy/mm/dd hh24:mi:ss') as dealDate from kangbank_deal_detail where accountNo='12345'
) b	
where a.dealDate = b.dealDate;

select * 
from KANGBANK_DEAL_DETAIL
where dealDate between '2016-06-03' and '2016-06-05' and accountNo='12345';

select accountNo, otherAccountNo from kangbank_deal_detail 
select sysdate from dual;

select to_char(dealDate-3, 'MM-DD-YYYY HH24:MI:SS') from kangbank_deal_detail
select add_months(dealDate, -12) from kangbank_deal_detail;

--계좌주 출력
select 













