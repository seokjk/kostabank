package org.kosta.kostabank.model.service;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;

public interface AccountService {
	public void createAccount(AccountVO vo);
	public AccountVO findAccountByAccountNum(String accountNo);
	public List<AccountTypeVO> findAccountByAccountName();
	public int findMinMoney(String accountName);
	List<AccountVO> accountTotalList(String email);
	List<AccountVO> accountList(String email);
	AccountVO accountAll(String accountNo);
	AccountVO checkAccount(AccountVO avo);
}