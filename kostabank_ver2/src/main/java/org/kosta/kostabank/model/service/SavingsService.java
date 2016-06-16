package org.kosta.kostabank.model.service;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;

public interface SavingsService {

	public abstract List<AccountTypeVO> savingsProductlist(String accountType);
	public AccountRatesVO accountNameFindAccountList(String accountName);
}