insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('kang종합통장','입출금통장',
'가입대상에 제한이 없고 소비자금 또는 유휴자금의 일시적 보관을 목적으로 개설하는 입출금이 자유로운 예금의 대표적인 상품입니다.',0);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('일반정기적금','적립식예금',
'최저 1만원이상 원단위로 최고예치한도는 없습니다. 정액적립식으로 확정금리 상품입니다.',10000);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('사업자우대적금','적립식예금',
'저탄소 녹색성장 관련 기업, 종업원 급여이체 기업 등에게 다양한 우대이율로 목돈마련을 지원하고, 대출금 상환이나
사업장 구입(임차)를 위해 중도해지하는 경우 특별이율을 적용하는 기업 전용 적립식 예금',10000);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('kang수퍼정기예금','거치식예금',
'목돈을 일정기간동안 예치하여 안정적인 수익을 목적으로 하는 다양한 맞춤식 정기예금',1000000);
insert into kangbank_account_type(accountName, accountType, accountExplanation, minMoney)
values('일반정기예금','거치식예금',
'목돈을 일정기간동안 예치하여 안정적인 수익을 목적으로 하는 일시예치식 예금입니다.',100000);