package org.kosta.kostabank.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.LoanTypeDAO;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.springframework.stereotype.Service;
@Service
public class LoanTypeServiceImpl implements LoanTypeService{
	@Resource
	private LoanTypeDAO loanDAO;
	
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
}
