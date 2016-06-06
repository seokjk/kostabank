package org.kosta.kostabank.model.dao;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;

public interface AccountDAO {

	public abstract void createAccount(AccountVO vo);
	public AccountVO findAccountByAccountNum(String accountNo);
	public List<AccountTypeVO> findAccountByAccountName();
	public int findMinMoney(String accountName);
	List<AccountVO> accountTotalList(String email);

}