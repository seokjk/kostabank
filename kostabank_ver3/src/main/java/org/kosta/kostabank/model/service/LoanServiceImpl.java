package org.kosta.kostabank.model.service;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.AccountDAO;
import org.kosta.kostabank.model.dao.DealDetailDAO;
import org.kosta.kostabank.model.dao.LoanDAO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.DealDetailVO;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.kosta.kostabank.model.vo.LoanVO;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {
	@Resource
	private LoanDAO loanDAO;
	@Resource
	private AccountDAO accountDAO;
	@Resource
	private DealDetailDAO dealDetailDAO;
	@Override
	public int checkLoan(int maxMoney) {
		return loanDAO.checkLoan(maxMoney);
	}

	@Override
	public LoanAccountVO loanData(String name) {
		return loanDAO.loanData(name);
	}

	@Override
	public LoanAccountVO checkPeriod(String period) {
		return loanDAO.checkPeriod(period);
	}

	// 대출눌렀을때, 대출계좌, 대출 테이블에 insert
	@Override
	public LoanVO loanSuccess(CustomerVO cvo, LoanVO vo, AccountTypeVO avo) {
		int randomVal = (int) (Math.random() * 8999) + 1000;
		int randomValSe = (int) (Math.random() * 899) + 100;
		String accountNo = "219-" + randomVal + "-" + randomValSe + "-28";
		AccountVO accountVO = new AccountVO(accountNo, avo, vo, cvo);
		loanDAO.createAccount(accountVO);
		vo.setLoanAccountNo(accountVO.getAccountNo());
		vo.setAccountName(avo.getAccountName());
		loanDAO.createLoan(vo);
		return vo;
	}

	@Override
	public LoanVO selectLoan(String accountNo) {
		return loanDAO.selectLoan(accountNo);
	}

	@Override
	public void loanDepositDealDetail(LoanVO lvo) {
		DealDetailVO dvo = new DealDetailVO("219-1111-111-25",
				lvo.getInAccountNo(), "withdraw", lvo.getBalance());
		accountDAO.withdraw(dvo);
		dealDetailDAO.insertTransDetail(dvo);
		dvo = new DealDetailVO(lvo.getInAccountNo(), "219-1111-111-25",
				"deposit", lvo.getBalance());
		accountDAO.deposit(dvo);
		dealDetailDAO.insertTransDetail(dvo);
	}
}
