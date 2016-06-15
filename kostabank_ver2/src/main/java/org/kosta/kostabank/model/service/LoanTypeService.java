package org.kosta.kostabank.model.service;

import java.util.List;

import org.kosta.kostabank.model.vo.LoanAccountVO;

public interface LoanTypeService {

	List<String> loanNameList();

	LoanAccountVO loanList(String accountName);

}
