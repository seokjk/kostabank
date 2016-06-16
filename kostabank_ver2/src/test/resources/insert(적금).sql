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