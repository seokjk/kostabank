-------------계정 테이블 관련-------------
--kangbank_customer
--email, password, name, birth, tel, address, security_card
drop table kangbank_customer;
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




-------------계좌 테이블 관련-------------
drop table kangbank_account;

--kangbank_account
--accountNo, issueDate, accountPass, balance, email, tel, accountName
create table kangbank_account(
   accountNo varchar2(100) primary key,
   issueDate date not null,
   accountPass number not null,
   balance number not null,
   email varchar2(100) not null,
   tel varchar2(100) not null,
   accountName varchar2(100) not null,
   constraint fk_accountName foreign key (accountName) references kangbank_account_Type
)

ALTER TABLE kangbank_account ADD
CONSTRAINT FK_kangbank_customer FOREIGN KEY
( email, tel
) REFERENCES kangbank_customer (email,tel)


insert into KANGBANK_ACCOUNT(accountNo,issueDate,accountPass,balance,email,tel,accountName)
values('12345',sysdate, 1234, 1000,'leekorea2k@hanmail.net','1111','Kosta종합통장');

insert into KANGBANK_ACCOUNT(accountNo,issueDate,accountPass,balance,email,tel,accountName)
values('12346',sysdate, 1234, 1000,'leekorea2k@hanmail.net','1111','Kosta종합통장');




-------------계좌 종류 테이블 관련-------------
drop table kangbank_account_type;
alter table kangbank_account_type rename column Name to accountName;
select * from kangbank_account_type;

--kangbank_account_type
--name, accountType, accountExplanation, minMoney
CREATE TABLE kangbank_account_type(
accountName varchar2(100) PRIMARY KEY,
accountType varchar2(100) not null,
accountExplanation varchar2(3000) not null,
minMoney number default 0
)


insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('Kosta종합통장','입출금이자유로운예금',
'가입대상에 제한이 없고 소비자금 또는 유휴자금의 일시적 보관을 목 목적으로 개설하는 입출금이 자유로운 예금의 대표적인 상품입니다.',0);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('일반정기적금','적립식예금',
'최저 1만원이상 원단위로 최고예치한도는 없습니다. 정액적립식으로 확정금리 상품입니다.',10000);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('사업자우대적금','적립식예금',
'저탄소 녹색성장 관련 기업, 종업원 급여이체 기업 등에게 다양한 우대이율로 목돈마련을 지원하고, 대출금 상환이나
사업장 구입(임차)를 위해 중도해지하는 경우 특별이율을 적용하는 기업 전용 적립식 예금',10000);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('Kosta수퍼정기예금','거치식예금',
'목돈을 일정기간동안 예치하여 안정적인 수익을 목적으로 하는 다양한 맞춤식 정기예금',1000000);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('일반정기예금','거치식예금',
'목돈을 일정기간동안 예치하여 안정적인 수익을 목적으로 하는 일시예치식 예금입니다.',100000);

select * from kangbank_account_type;
select distinct(accountType) from kangbank_account_type;
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
accountName varchar2(100) not null,
constraint fk_ratesNo foreign key(accountName) references kangbank_account_type(accountName)
)

select * from kangbank_account_rates;

insert into kangbank_account_rates(accountSeq, rates, term, accountName)
values(kangbank_rates_seq.nextval, 0, 0, 'Kosta종합통장');
insert into kangbank_account_rates(accountSeq, rates, term, accountName)
values(kangbank_rates_seq.nextval, 0, 0, 'Kosta종합통장');

insert into kangbank_account_rates(accountSeq, rates, term, accountName)
values(kangbank_rates_seq.nextval, 0.6, 6, '일반정기적금');
insert into kangbank_account_rates(accountSeq, rates, term, accountName)
values(kangbank_rates_seq.nextval, 1.3, 12, '일반정기적금');

insert into kangbank_account_rates(accountSeq, rates, term, accountName)
values(kangbank_rates_seq.nextval, 0.8, 6, '사업자우대적금');
insert into kangbank_account_rates(accountSeq, rates, term, accountName)
values(kangbank_rates_seq.nextval, 1.7, 12, '사업자우대적금');
insert into kangbank_account_rates(accountSeq, rates, term, accountName)
values(kangbank_rates_seq.nextval, 2.5, 18, '사업자우대적금');

insert into kangbank_account_rates(accountSeq, rates, term, accountName)
values(kangbank_rates_seq.nextval, 1.2, 12, 'Kosta수퍼정기예금');
insert into kangbank_account_rates(accountSeq, rates, term, accountName)
values(kangbank_rates_seq.nextval, 2.5, 24, 'Kosta수퍼정기예금');

select t.accountName,t.accountType,t.accountExplanation,t.minMoney,r.rates,r.term,r.accountSeq from(
select accountName,accountType,accountExplanation,minMoney,rates,term,ceil(rownum/5) as page from
KANGBANK_ACCOUNT_TYPE
)
t, KANGBANK_ACCOUNT_RATES r where t.accountName=r.accountName
and page=1
select b.no,b.title,b.time_posted,b.hits,b.id,m.name from (
			select no,title,time_posted,hits,ceil(rownum/5) as page,id from (
					select no,title,to_char(time_posted,'YYYY.MM.DD') as time_posted,
					hits,id from spring_board_inst  order by no desc
		     )
       ) b,spring_member m where b.id=m.id and page=#{pageNo}
       
       
------------------보안카드--------------------
create table secure_card(
   no number primary key,
   one varchar2(4) not null,
   two varchar2(4) not null,
   three varchar2(4) not null,
   four varchar2(4) not null,
   five varchar2(4) not null,
   six varchar2(4) not null,
   seven varchar2(4) not null,
   eight varchar2(4) not null,
   nine varchar2(4) not null,
   ten varchar2(4) not null,
   eleven varchar2(4) not null,
   twelve varchar2(4) not null,
   thirteen varchar2(4) not null,
   fourteen varchar2(4) not null,
   fifteen varchar2(4) not null,
   sixteen varchar2(4) not null,
   seventeen varchar2(4) not null,
   eighteen varchar2(4) not null,
   nineteen varchar2(4) not null,
   twenty varchar2(4) not null,
   twenty_one varchar2(4) not null,
   twenty_two varchar2(4) not null,
   twenty_three varchar2(4) not null,
   twenty_four varchar2(4) not null,
   twenty_five varchar2(4) not null,
   twenty_six varchar2(4) not null,
   twenty_seven varchar2(4) not null,
   twenty_eight varchar2(4) not null,
   twenty_nine varchar2(4) not null,
   thirty varchar2(4) not null
)
drop table secure_card;
create sequence secure_card_seq;

-------------------거래내역----------------------
drop table kangbank_deal_detail
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

delete from kangbank_deal_detail
delete from kangbank_customer
delete from kangbank_account


insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','deposit',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','deposit',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','deposit',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','deposit',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','deposit',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','deposit',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','deposit',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','withdraw',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','withdraw',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','withdraw',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','withdraw',1000,sysdate);
insert into kangbank_deal_detail(dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate)
values(dealNo_seq.nextval, '12345','12346','withdraw',1000,sysdate);



select dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate from (
select dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate,ceil(rownum/5) as page from(
select dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate from kangbank_deal_detail order by dealNo asc))
where page='1' and
	dealDate between '2016-06-05' and '2016-06-08' and accountNo='12345'

	select dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate
	from (
	select
	dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate,ceil(rownum/10)
	as page from(
	select dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate from
	kangbank_deal_detail 
	where dealDate between '2016-06-05' and '2016-06-14' and accountNo='12345'
	order by dealNo asc))
	where page='1';

select count(*)
 		from kangbank_deal_detail
 		where dealDate between '2016-06-05' and '2016-06-08' and accountNo='12345'
 
 
 		select
		dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate
		from (
		select
		dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate,ceil(rownum/10)
		as page from(
		select
		dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate from
		kangbank_deal_detail
		where dealDate between '2016-06-05' and '2016-06-12' and accountNo='12345'
		order by dealNo asc))
		where page='3'
 		
		select ka.email, ka.tel, ka.accountNo 
		from
 		kangbank_account ka,
    	kangbank_deal_detail kd
 		where ka.accountNo=kd.otherAccountNo and kd.dealDate='2016-06-11'
 		
 		select *  from kangbank_deal_detail where to_char(dealDate,'yyyymmdd hh24:mi:ss')='20160611 09:53:21.0'
 		
 		select to_char(dealDate,'yyyymmdd hh24:mi:ss') from kangbank_deal_detail
 		
 	
 		
 		--이름뽑기 쿼리--
 		select name
 		from
 		(select ka.accountNo, ka.issueDate, ka.accountPass, ka.balance, ka.email, ka.tel, ka.accountName, 
 				   kd.dealNo, kd.otheraccountNo
		from
 		kangbank_account ka,
    	kangbank_deal_detail kd
 		where ka.accountNo=kd.otherAccountNo) A,
 		(select kc.email, kc.password, kc.name, kc.birth, kc.tel, kc.address, kc.security_card,
 				  ka.accountNo, ka.issueDate, ka.accountPass, ka.balance, ka.accountName
 		from kangbank_customer kc,
 				kangbank_account ka
 		where kc.email=ka.email and kc.tel=ka.tel
 		)B
 		where B.email=A.email and A.tel=B.tel and A.accountNo=B.accountNo
 		
 	
 		
 		select ka.accountNo
 		from
 		(select ka.accountNo, ka.issueDate, ka.accountPass, ka.balance, ka.email, ka.tel, ka.accountName, 
 				   kd.dealNo, kd.accountNo, kd.otheraccountNo
		from
 		kangbank_account ka,
    	kangbank_deal_detail kd
 		where ka.accountNo=kd.otherAccountNo) A,
 		(
 		from kangbank_customer kc,
 				kangbank_account ka
 		where kc.email=ka.email and kc.tel=ka.tel
 		)B
 		where A.email=B.email

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
 		
 		
 	   where f.email='leekorea2k@hanmail.net'
 		
 	create table kangbank_account(
   accountNo varchar2(100) primary key,
   issueDate date not null,
   accountPass number not null,
   balance number not null,
   email varchar2(100) not null,
   tel varchar2(100) not null,
   accountName varchar2(100) not null,
   constraint fk_accountName foreign key (accountName) references kangbank_account_Type
)
 		
 		
