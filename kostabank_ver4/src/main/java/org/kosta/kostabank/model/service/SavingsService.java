package org.kosta.kostabank.model.service;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.SavingsVO;

public interface SavingsService {

	public abstract List<AccountTypeVO> savingsProductlist(String accountType);
	public AccountRatesVO accountNameFindAccountList(String accountName);
	double getRatesByTerm(int accountSeq);
	void createSavings(AccountVO accountVO, SavingsVO savingsVO);
	List<AccountRatesVO> accountSeqByName(String accountName);
	int getTermBySeq(int accountSeq);
	public abstract int selectRatesBySeq(String seq);
}