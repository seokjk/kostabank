package org.kosta.kostabank.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.kosta.kostabank.model.vo.LoanVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoanDAOImpl implements LoanDAO {
	@Resource
	private SqlSessionTemplate template;

	// 대출 금액 체크
	@Override
	public int checkLoan(int maxMoney) {
		return template.selectOne("loan.checkLoan", maxMoney);
	}

	@Override
	public LoanAccountVO loanData(String name) {
		return template.selectOne("loan.loanData", name);
	}

	@Override
	public LoanAccountVO checkPeriod(String period) {
		return template.selectOne("loan.checkPeriod", period);
	}

	@Override
	public void createAccount(AccountVO accountVO) {
		System.out.println(accountVO);
		template.insert("loan.createAccount", accountVO);
	}

	@Override
	public void createLoan(LoanVO vo) {
		template.insert("loan.createLoan", vo);
	}

	@Override
	public LoanVO selectLoan(String accountNo) {
		return template.selectOne("loan.selectLoan", accountNo);
	}

	@Override
	public void withdraw() {
		template.update("loan.withdraw");
	}

	@Override
	public void nowBalance() {
		template.update("loan.nowBalance");
	}

	@Override
	public List<LoanVO> selectDealDetail() {
		return template.selectList("loan.selectDealDetail");
	}

	@Override
	public void transfer(List<LoanVO> list) {
		for (int i = 0; i < list.size(); i++) {
			template.insert("loan.transferWithdraw", list.get(i));
			template.insert("loan.transferDeposit", list.get(i));
		}
	}

	@Override
	public List<LoanVO> dailyCheckBalance() {
		return template.selectList("loan.dailyCheckBalance");
	}

	@Override
	public void dailyCheckWithdraw(List<LoanVO> list) {
		for (int i = 0; i < list.size(); i++) {
			template.update("loan.dailyCheckWithdraw", list.get(i));
		}
	}

	@Override
	public void dailyCheckUpdate(List<LoanVO> list) {
		for (int i = 0; i < list.size(); i++) {
			template.update("loan.dailyCheckUpdate", list.get(i));
		}
	}
	
	@Override
	public int outAccountNoCount() {
		return template.selectOne("loan.outAccountNoCount");
	}

}