package org.kosta.kostabank.model.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.SavingsDAO;
import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.SavingsVO;
import org.springframework.stereotype.Service;

@Service
public class SavingsServiceImpl implements SavingsService {
	@Resource
	private SavingsDAO savingsDAO;
	@Resource
	private AccountService accountService;
	@Override
	public List<AccountTypeVO> savingsProductlist(String accountType) {
		return savingsDAO.savingsProductlist(accountType);
	}
	@Override
	public AccountRatesVO accountNameFindAccountList(String accountName){
		return savingsDAO.accountNameFindAccountList(accountName);
	}
	@Override
	public double getRatesByTerm(int accountSeq){
		return savingsDAO.getRatesByTerm(accountSeq);
	}
	@Override
	public void createSavings(AccountVO accountVO, SavingsVO savingsVO){
		int randomVal=(int)(Math.random()* 8999)+1000;
		int randomValSe=(int)(Math.random() * 899)+100;
		String accountNo = "219-"+randomVal+"-"+randomValSe+"-26";
		accountVO.setAccountNo(accountNo);
		savingsVO.setAccountVO(accountVO);
		Date d = new Date();
	    String issueDate = d.toString();
		accountVO.setIssueDate(issueDate);
		accountService.createAccount(accountVO);
		System.out.println("createAccount 수행");
		System.out.println(savingsVO);
		savingsDAO.createSavings(savingsVO);
	}
	@Override
	public List<AccountRatesVO> accountSeqByName(String accountName){
		return savingsDAO.accountSeqByName(accountName);
	}
	@Override
	public int getTermBySeq(int accountSeq){
		return savingsDAO.getTermBySeq(accountSeq);
	}
}
