package org.kosta.kostabank.model.dao;

import java.util.List;

import org.kosta.kostabank.model.vo.LoanAccountVO;

public interface LoanDAO {
	
	List<String> loanNameList();

	LoanAccountVO loanList(String accountName);
	
	int checkLoan(int maxMoney);

	LoanAccountVO loanData(String name);

	LoanAccountVO checkPeriod(String period);

}
