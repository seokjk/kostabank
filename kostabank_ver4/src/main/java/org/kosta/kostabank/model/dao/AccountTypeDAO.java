package org.kosta.kostabank.model.dao;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;

public interface AccountTypeDAO {

	public abstract void createAccountType(AccountTypeVO accountTypeVO);

	public abstract void createAccountRates(AccountRatesVO accountRatesVO);
	
	List<AccountTypeVO> selectLoan();

}