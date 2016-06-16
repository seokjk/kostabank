package org.kosta.kostabank.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoanDAOImpl implements LoanDAO{
	@Resource
	private SqlSessionTemplate template;
	
	//대출 계좌 이름 리스트
	@Override
	public List<String> loanNameList() {
		return template.selectList("loan.loanNameList");
	}
	//대출 전체 리스트
	@Override
	public LoanAccountVO loanList(String accountName) {
		System.out.println("loanDAO : " + template.selectOne("loan.loanList", accountName));
		return template.selectOne("loan.loanList", accountName);
	}
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
}
