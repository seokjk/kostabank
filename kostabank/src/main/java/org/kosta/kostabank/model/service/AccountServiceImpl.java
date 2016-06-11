package org.kosta.kostabank.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.AccountDAO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.ListVO;
import org.kosta.kostabank.model.vo.PagingBean;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	@Resource(name="accountDAOImpl")
	private AccountDAO accountDAO;
	@Resource(name="pagingConfig")
	private Map<String,Integer> pagingConfig;
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
	//페이징
	public ListVO findAccountByAccountNamePaging(int page){
		HashMap<String,Integer> paramMap=new HashMap<String,Integer>();
		paramMap.put("page", page);
		paramMap.put("numberOfContent", pagingConfig.get("numberOfContent"));
		List<AccountTypeVO> list=accountDAO.findAccountByAccountNamePaging(paramMap);
		int total=accountDAO.totalContent();
		PagingBean paging=new PagingBean(total,page,pagingConfig);
		ListVO lvo=new ListVO(list,paging);
		return lvo;
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
	//전체계좌중 이체가능계좌만 조회
	@Override
	public List<AccountVO> accountList(String email) {
		return accountDAO.accountList(email);
	}
	@Override
	public AccountVO accountAll(String accountNo){
		return accountDAO.accountAll(accountNo);
	}
	@Override
	public AccountVO checkAccount(AccountVO avo){
		return accountDAO.checkAccount(avo);
	}
	@Override
	public AccountVO checkOtherAccount(String accountNo){
		return accountDAO.checkOtherAccount(accountNo);
	}
	@Override
	public int withdraw(AccountVO avo){
		return accountDAO.withdraw(avo);
	}
	@Override
	public int deposit(AccountVO avo){
		return accountDAO.deposit(avo);
	}
	
}
