package org.kosta.kostabank.model.service;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.AccountDAO;
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
}
