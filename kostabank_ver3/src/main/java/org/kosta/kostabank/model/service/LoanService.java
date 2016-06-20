package org.kosta.kostabank.model.service;

import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.kosta.kostabank.model.vo.LoanVO;

public interface LoanService {
	
	int checkLoan(int maxMoney);

	LoanAccountVO loanData(String name);

	LoanAccountVO checkPeriod(String period);

	LoanVO loanSuccess(CustomerVO cvo, LoanVO vo, AccountTypeVO avo);

	LoanVO selectLoan(String accountNo);
}
