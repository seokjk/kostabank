package org.kosta.kostabank.model.dao;

import java.util.List;
import java.util.Map;

import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.DealDetailVO;

public interface AccountDAO {

	public abstract void createAccount(AccountVO vo);
	public AccountVO findAccountByAccountNum(String accountNo);
	public List<AccountTypeVO> findAccountByAccountNamePaging(Map<String,Integer> pagingConfig);
	public List<AccountTypeVO> findAccountByAccountName();
	public int totalContent();
	public int findMinMoney(String accountName);
	List<AccountVO> accountTotalList(String email);
	List<AccountVO> accountList(String email);
	AccountVO accountAll(String accountNo);
	AccountVO checkAccount(AccountVO avo);
	AccountVO checkOtherAccount(String accountNo);
	int scheduled();
	int ratesMonth(int month);
	AccountVO accountBAndN(String accountNo);
	int deposit(DealDetailVO dvo);
	int deposit(AccountVO avo);
	int withdraw(DealDetailVO dvo);
	int withdraw(AccountVO avo);
	List<AccountVO> accountList2(String email);
}