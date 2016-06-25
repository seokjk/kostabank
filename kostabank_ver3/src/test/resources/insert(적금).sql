
select otherAccountNo, name as otherAccountName    
			from	(select otherAccountNo, name, C.accountType    
					from	(select	A.otherAccountNo, C.name, A.dealDate
							from	kangbank_deal_detail A,
									kangbank_account B,
									kangbank_customer C
							where C.email = B.email
									AND B.accountNo = A.accountNo
									AND A.accountNo='219-5472-119-26'
									and a.dealType='withdraw'
							ORDER by A.dealDate desc) A,
							kangbank_account B,
							kangbank_account_type C
					where 	B.accountNo = A.otherAccountNo
							AND C.accountName = B.accountName
					GROUP by otherAccountNo, name, C.accountType)
			where 	rownum<=10
					AND accountType = '입출금'














insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('정기적금','적금',
'계약기간동안 일정한 날에 일정 금액을 정기적으로 납입하고
원리금을 받는 기본적인 적금 상품입니다',1000);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('정기적금 고급형','적금',
'계약기간동안 일정한 날에 일정 금액을 정기적으로 납입하고
원리금을 받는 고소득자용 적금 상품입니다',10000);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('알뜰 정기적금','적금',
'계약기간동안 일정한 날에 일정 금액을 정기적으로 납입하고
원리금을 받는 저소득자도 부담 없이 사용할 수 있는 적금 상품입니다',500);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('신혼부부파뿌리적금','적금',
'신혼부터 황혼까지, 멋진 결혼 생활을 위하여 일정 금액을 정기적으로 납입하고 원리금을 받는 부부용 상품입니다.',2000);

---------------------------------------------------------------------------------
select * from kangbank_account
insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 1.5, 6, '정기적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 2, 12, '정기적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 2.5, 18, '정기적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 3, 24, '정기적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 2, 6, '정기적금 고급형',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 2.5, 12, '정기적금 고급형',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 3, 18, '정기적금 고급형',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 3.5, 24, '정기적금 고급형',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 1.2, 6, '알뜰 정기적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 1.5, 12, '알뜰 정기적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 1.8, 18, '알뜰 정기적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 2.2, 24, '알뜰 정기적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 2, 6, '신혼부부파뿌리적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 2.5, 12, '신혼부부파뿌리적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 3, 18, '신혼부부파뿌리적금',0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 3.5, 24, '신혼부부파뿌리적금',0);