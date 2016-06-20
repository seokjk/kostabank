package org.kosta.kostabank.model.dao;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.kosta.kostabank.model.vo.LoanVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoanDAOImpl implements LoanDAO{
	@Resource
	private SqlSessionTemplate template;

	//대출 금액 체크
	@Override
	public int checkLoan(int maxMoney){
		return template.selectOne("loan.checkLoan",maxMoney);
	}
	@Override
	public LoanAccountVO loanData(String name) {
		return template.selectOne("loan.loanData", name);
	}
	@Override
	public LoanAccountVO checkPeriod(String period){
		return template.selectOne("loan.checkPeriod", period);
	}
	@Override
	public void createAccount(AccountVO accountVO) {
		template.insert("loan.createAccount",accountVO);
	}
	@Override
	public void createLoan(LoanVO vo) {
		template.insert("loan.createLoan",vo);
	}
	@Override
	public LoanVO selectLoan(String accountNo) {
		return template.selectOne("loan.selectLoan",accountNo);
	}
	
	@Override
	public void withdraw() {
		template.update("loan.withdraw");
	}
	@Override
	public void nowBalance() {
		template.update("loan.nowBalance");
	}
	//balanceSum Update 구문
	@Override
	public int balanceSumUpdate() {
		return template.update("loan.balanceSumUpdate");
	}
}
