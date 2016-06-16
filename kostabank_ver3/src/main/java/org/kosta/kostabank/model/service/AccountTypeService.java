package org.kosta.kostabank.model.service;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;

public interface AccountTypeService {

	public abstract void createAccountType(AccountTypeVO accountTypeVO,
			AccountRatesVO accountRatesVO);
	
	List<AccountTypeVO> selectLoan();

}