----------테이블 삭제 ----------
-- 계좌 금리 --
drop table kangbank_rates_info;
-- 금리 --
drop sequence kangbank_rates_seq;
drop table kangbank_account_rates;
-- 계좌 --
drop table kangbank_account;
-- 계정 --
drop table kangbank_customer;
-- 계좌 종류 -- 
drop table kangbank_account_type;
-- 보안 카드 --
drop sequence secure_card_seq;
drop table secure_card;
-- QnA --
drop sequence kangbank_qna_seq;
drop table kangbank_notice;
-- 공지사항 --
drop sequence kangbank_notice_seq;
drop table kangbank_qna;
-- 자주묻는질문 --
drop sequence question_seq;
drop table question;
-- 거래내역 --
drop sequence dealNo_seq;
drop table kangbank_deal_detail;
---------- 테이블 생성 ----------
-- 계정 --
create table kangbank_customer(
   email varchar2(100) not null,
   password varchar2(100) not null,
   name varchar2(100) not null,
   birth varchar2(100) not null,
   tel varchar2(100) not null,
   address varchar2(100) not null,
   security_card varchar2(100) default 0,
   loginFailCount number default 0,
   constraint pk_customer primary key(email,tel)
);

-- 계좌 종류 --
create table kangbank_account_type(
accountName varchar2(100) PRIMARY KEY,
accountType varchar2(100) not null,
accountExplanation varchar2(3000) not null,
minMoney number default 0
);

-- 계좌 --
create table kangbank_account(
   accountNo varchar2(100) primary key,
   issueDate date not null,
   accountPass number not null,
   balance number not null,
   email varchar2(100) not null,
   tel varchar2(100) not null,
   accountName varchar2(100) not null,
   dateCount number default 0,
   balanceSum number default 0,
   constraint fk_accountName foreign key (accountName) references kangbank_account_Type
);

ALTER TABLE kangbank_account ADD
CONSTRAINT FK_kangbank_customer FOREIGN KEY
( email, tel
) REFERENCES kangbank_customer (email,tel)

-- 금리 --
create sequence kangbank_rates_seq;

create table kangbank_account_rates(
accountSeq number PRIMARY KEY,
rates number default 0,
term number default 0,
accountName varchar2(100) not null,
ratesMonth number default 0,
constraint fk_ratesNo foreign key(accountName) references kangbank_account_type(accountName)
);

-- 계좌 금리 --
create table kangbank_rates_info(
	accountNo varchar2(100) primary key,
	accountSeq number not null,
	constraint fk_ratesInfoNo foreign key(accountNo) references kangbank_account(accountNo),
	constraint fk_ratesInfoSeq foreign key(accountSeq) references kangbank_account_rates(accountSeq)
);

-- 보안 카드 --
create sequence secure_card_seq;
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
);

-- 공지사항 --
create sequence kangbank_notice_seq;
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

-- QnA --
create sequence kangbank_qna_seq;
create table kangbank_qna(
   qna_no number primary key,
   qna_refNo number default 0,
   qna_replyNo number default 0,
   qna_level number default 0,
   qna_title varchar2(100) not null,
   qna_type varchar2(100) not null,
   email varchar2(100) not null,
   tel varchar2(100) not null,
   qna_content clob not null,
   qna_fileAddress varchar2(100),
   qna_time date not null
);

ALTER TABLE kangbank_qna ADD
CONSTRAINT kangbank_customer_fk FOREIGN KEY
( email, tel
) REFERENCES kangbank_customer (email,tel)

--자주묻는질문--
create sequence question_seq;
create table question(
	no number primary key,
	title varchar2(200) not null,
	contents clob not null,
	hits number default 0,
	section varchar2(100) not null
)

-- 거래내역 --
create sequence dealNo_seq;
create table kangbank_deal_detail(
   dealNo number primary key,
   accountNo varchar2(100) not null,
   otherAccountNo varchar2(100) not null,
   dealType varchar2(100) not null,
   amountOfMoney number not null,
   dealDate date not null,
   constraint fk_kangbank_account foreign key (accountNo) references kangbank_account(accountNo)
)
