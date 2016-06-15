---------- 대출 ----------
-- accountName, accountType, accountExplanation, minMoney
-- minMoney는 대출최고한도로 정한다.
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('kang새내기직장인대출','대출','당행이 정하는 우량업체에 3년 미만 재직중인 사회초년생 고객님께 드리는 인터넷 전용 신용대출', 30000000);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('kang우대드림대출', '대출', '당행 거래실적에 따라 대출 한도가 산정되는 고객님꼐 드리는 인터넷 전용 대출', 60000000);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('kang직장인행복대출', '대출', '중소법인기업 재직중인 고객님 이제 편하게 대출받으세요~ 영업점 방분?NO!서류준비 NO! 무서류, 무방문, 인터넷 간편 대출', 30000000);

---------- 금리 ----------
-- kangbank_account_rates
-- accountSeq, rates, term, accountName, ratesMonth
-- term은 최대로 빌릴 수 있는 기간으로 하고, ratesMonth은 0으로 default 한다.
select * from kangbank_account_rates;

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 4.05, 12*3, 'kang새내기직장인대출', 0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 4.6, 12*5, 'kang우대드림대출', 0);

insert into kangbank_account_rates(accountSeq, rates, term, accountName, ratesMonth)
values(kangbank_rates_seq.nextval, 4.13, 12*5, 'kang직장인행복대출', 0);

select at.accountName, at.accountExplanation, at.minMoney, ar.rates, ar.term, lr.additionalRates
from kangbank_account_type at, kangbank_account_rates ar, kangbank_loan_rates lr
where at.accountName=ar.accountName and ar.accountSeq=lr.loanRateSeq

---------- 대출 금리 ----------
-- kangbank_loan_rates
-- loanRateSeq, additionalRates, accountSeq
select * from kangbank_loan_rates;

insert into kangbank_loan_rates(loanRateSeq, additionalRates, accountSeq)
values(loanRateSeq.nextval,'0.7', '9');
insert into kangbank_loan_rates(loanRateSeq, additionalRates, accountSeq)
values(loanRateSeq.nextval,'0.7', '10');
insert into kangbank_loan_rates(loanRateSeq, additionalRates, accountSeq)
values(loanRateSeq.nextval,'0.7', '11');



----------------------select--------------------
select C.AccountName, C.accountExplanation, C.maximumMoney, C.rates, C.term, D.additionalRates
from (select A.accountName, A.accountExplanation, A.maximumMoney, B.rates, B.term, B.accountSeq
	from (select accountName, accountExplanation, minMoney as maximumMoney 
		from kangbank_account_type 
		where accountType='대출') A, kangbank_account_rates B
	where A.accountName = B.accountName) C, kangbank_loan_rates D
where C.accountSeq = D.accountSeq and C.accountName='kang우대드림대출';

select accountName from kangbank_account_type where accountType='대출';
