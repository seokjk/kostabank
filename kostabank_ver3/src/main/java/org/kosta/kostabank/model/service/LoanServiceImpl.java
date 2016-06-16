package org.kosta.kostabank.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.LoanDAO;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.springframework.stereotype.Service;
@Service
public class LoanServiceImpl implements LoanService{
	@Resource
	private LoanDAO loanDAO;
	
	//대출 이름 리스트
	@Override
	public List<String> loanNameList() {
		return loanDAO.loanNameList();
	}
	//대출 전체 리스트
	@Override
	public LoanAccountVO loanList(String accountName) {
		return loanDAO.loanList(accountName);
	}
	@Override
	public int checkLoan(int maxMoney){
		return loanDAO.checkLoan(maxMoney);
	}
	@Override
	public LoanAccountVO loanData(String name) {
		return loanDAO.loanData(name);
	}
	@Override
	public LoanAccountVO checkPeriod(String period){
		return loanDAO.checkPeriod(period);
	}
}
