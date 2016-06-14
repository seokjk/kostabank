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
   security_card number not null,
   constraint pk_customer primary key(email,tel),
   constraint fk_security_card foreign key (security_card) references secure_card(no)
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
       
       
-------------보안카드 테이블 관련-------------
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
insert into secure_card values(0,'0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000','0000');
drop table secure_card;
create sequence secure_card_seq;
select * from kangbank_customer

--------자주묻는질문-------
create table question(
	no number primary key,
	title varchar2(200) not null,
	contents clob not null,
	hits number default 0,
	section varchar2(100) not null
)

create sequence question_seq;

insert into question(no,title,contents,section) values(question_seq.nextval, '다른은행계좌로 자동이체가 가능한가요?','네 고객님 저희 KangBank에서는 자동이체 서비스를 진행하고 있습니다.<br>상단메뉴바에 계좌이체 코너를 이용해주시면 감사하겠습니다.<br>','계좌이체')
insert into question(no,title,contents,section) values(question_seq.nextval, 'KANGBANK 인터넷뱅킹 미가입 고객입니다. 어떻게 해야하나요?','네 고객님 저희 KangBank 왼쪽 메뉴바에서 회원가입을 하시면 사용가능합니다.<br>계좌이체시에는 보안카드가 꼭 필요하므로 보안카드를 발급받아 주시기 바랍니다.<br>','계좌이체')
insert into question(no,title,contents,section) values(question_seq.nextval, '보안카드는 어떻게 발급받나요?','네 고객님 저희 KangBank에서는 보안카드를 인터넷으로 발급 받으실 수 있습니다.<br>상단메뉴바에 보안센터 코너를 이용해주시면 감사하겠습니다.<br>보안카드 발급시 이메일로 인증번호가 전송되고 인증이 성공하면 보안카드가 메일로 발송됩니다','보안카드')
insert into question(no,title,contents,section) values(question_seq.nextval, '보안카드 해지가 가능한가요?','네 고객님 저희 KangBank에서는 인터넷으로 보안카드를 해지하실 수 있습니다.<br>상단메뉴바에 보안센터 코너를 이용해주시면 감사하겠습니다.<br>','보안카드')
insert into question(no,title,contents,section) values(question_seq.nextval, '보안카드 재발급이 가능한가요?','네 고객님 저희 KangBank에서는 인터넷으로 보안카드를 재발급 받으실 수 있습니다.<br>상단메뉴바에 보안센터 코너를 이용해주시면 감사하겠습니다.<br>재발급은 보안카드 발급절차와 동일합니다.','보안카드')
insert into question(no,title,contents,section) values(question_seq.nextval, '보안카드 발급을 신청했습니다. 어디서 볼 수 있나요?','네 고객님 저희 KangBank에서는 고객님의 이메일로 보안카드를 보내드리고 있습니다.<br>이메일에서 확인해 주시면 감사하겠습니다.<br>','보안카드')
insert into question(no,title,contents,section) values(question_seq.nextval, 'KANGBANK에 적금을 하려고 합니다. 이율이 얼마나 되나요?','네 고객님 저희 KangBank에서는 다양한 이율이 적용되고 있는 적금 서비스를 제공하고 있습니다.<br>상단메뉴바에 상품정보 코너를 이용해주시면 감사하겠습니다.<br>','적금')
insert into question(no,title,contents,section) values(question_seq.nextval, 'KANGBANK에서 대출을 하려고 합니다. 대출이자가 얼마나 되나요?','네 고객님 저희 KangBank에서는 다양한 이자율이 적용되고 있는 대출 서비스를 제공하고 있습니다.<br>상단메뉴바에 상품정보 코너를 이용해주시면 감사하겠습니다.<br>','대출')
insert into question(no,title,contents,section) values(question_seq.nextval, '이체시에 보안카드가 필요한가요?','네 고객님 저희 KangBank에서는 이체 시 보안유지를 위하여 보안카드 확인을 하고 있습니다.<br>보안카드가 없으면 온라인 이체가 가능하지 않으니 이점 양해바랍니다.<br>','계좌이체');
insert into question(no,title,contents,section) values(question_seq.nextval, '보안카드 분실시 어떻게하면 되나요?','네 고객님 저희 KangBank에서는 보안카드 분실시 재발급을 받도록 하고 있습니다.<br>보안카드가 없으면 온라인 서비스가 가능하지 않으니 이점 양해바랍니다.<br>','보안카드');
insert into question(no,title,contents,section) values(question_seq.nextval, '다른은행계좌로 계좌이체시 수수료가 얼마인가요?','네 고객님 저희 KangBank에서는 은행마다 수수료를 다르게 책정하고 있습니다.<br>KangBank은행 내에서의 이체는 현재 수수료가 적용되지 않고 있습니다.<br>이체시에 고객님의 확인 부탁드립니다.<br>','계좌이체');
insert into question(no,title,contents,section) values(question_seq.nextval, '보안카드 입력 5회 오류가 났습니다. 어떻게 해야하나요?','네 고객님 저희 KangBank에서는 보안을 위해 보안카드를 확인하고 있습니다.<br>5회 오류시에는 번거로우시더라도 은행을 방문해주시길 바랍니다.<br>양해부탁드립니다.<br>','계좌이체');
insert into question(no,title,contents,section) values(question_seq.nextval, '보안카드 입력 5회 오류가 났습니다. 어떻게 해야하나요?','네 고객님 저희 KangBank에서는 보안을 위해 보안카드를 확인하고 있습니다.<br>5회 오류시에는 번거로우시더라도 은행을 방문해주시길 바랍니다.<br>양해부탁드립니다.<br>','보안카드');

select * from question where section='계좌이체';

update question set hits=hits+1 where no=1
select no, title, contents, hits, page from(
select no,title,contents,hits,ceil(rownum/3) as page,section from (
					select no,title,contents,hits,section from question where section='보안카드')  order by no desc) where page='1'
select count(*) from question where section='보안카드'

select no, title, hits,section from(
 		select no,title,hits,ceil(rownum/3) as page,section from (select no,title,hits,section from question where section='계좌이체')  order by no desc)
 		where page=1
 		
 		
select count(*) from question where title like '%G%'

	select no, title, hits, section from(
 				select no,title,hits,ceil(rownum/3) as page,section from 
 					(select no,title,hits,section from question where title like  '%G%')  order by no desc)
 		where page=2

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
	dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate,ceil(rownum/5)
	as page from(
	select dealNo,accountNo,otherAccountNo,dealType,amountOfMoney,dealDate from
	kangbank_deal_detail 
	where dealDate between '2016-06-05' and '2016-06-08' and accountNo='12345'
	order by dealNo asc))
	where page='1';

select count(*)
 		from kangbank_deal_detail
 		where dealDate between '2016-06-05' and '2016-06-08' and accountNo='12345'
 
 select 
 		
 		
 
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
 		
 		
 		
 		
 		
