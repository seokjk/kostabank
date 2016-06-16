package org.kosta.kostabank.model.service;

import java.util.List;

import org.kosta.kostabank.model.vo.LoanAccountVO;

public interface LoanService {

	List<String> loanNameList();

	LoanAccountVO loanList(String accountName);
	
	int checkLoan(int maxMoney);

	LoanAccountVO loanData(String name);

	LoanAccountVO checkPeriod(String period);

}
