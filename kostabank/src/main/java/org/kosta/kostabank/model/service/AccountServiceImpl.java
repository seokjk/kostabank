package org.kosta.kostabank.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.AccountDAO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	@Resource
	private AccountDAO accountDAO;
	public void createAccount(AccountVO vo){
		int randomVal=(int)(Math.random()* 9999)+1000;
		int randomValSe=(int)(Math.random() * 999)+100;
		String accountNo = "219-"+randomVal+"-"+randomValSe+"-26";
		vo.setAccountNo(accountNo);
		System.out.println(vo);
		accountDAO.createAccount(vo);		
	}
	public AccountVO findAccountByAccountNum(String accountNo){
		return accountDAO.findAccountByAccountNum(accountNo);
	}
	public List<AccountTypeVO> findAccountByAccountName(){
		return accountDAO.findAccountByAccountName();
	}
	public int findMinMoney(String accountName){
		return accountDAO.findMinMoney(accountName);
	}
	//전체계좌조회
	@Override
	public List<AccountVO> accountTotalList(String email) {
		return accountDAO.accountTotalList(email);
	}
}
