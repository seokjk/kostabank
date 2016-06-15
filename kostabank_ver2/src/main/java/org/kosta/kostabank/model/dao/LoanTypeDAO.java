package org.kosta.kostabank.model.dao;

import java.util.List;

import org.kosta.kostabank.model.vo.LoanAccountVO;

public interface LoanTypeDAO {
	
	List<String> loanNameList();

	LoanAccountVO loanList(String accountName);

}
