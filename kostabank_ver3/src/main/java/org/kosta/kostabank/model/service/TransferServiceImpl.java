package org.kosta.kostabank.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.TransferDAO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.TransferVO;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {
	@Resource
	private TransferDAO transferDAO;
	
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.service.TransferService#transfer(org.kosta.kostabank.model.vo.AccountVO, org.kosta.kostabank.model.vo.CustomerVO, int)
	 */
	@Override
	public void transfer(AccountVO avo, CustomerVO cvo, int money){
		//int balance = transferDAO.checkBal(money);
		//System.out.println(balance);
		transferDAO.deposit(money);
		transferDAO.withdraw(money);
		
	}

	@Override
	public List<TransferVO> findAccount(){
		return transferDAO.findAccount();
	}
	@Override
	public int checkPw(int accountPass){
		return transferDAO.checkPw(accountPass);
	}
	@Override
	public int checkBal(String myaccountNo){
		return transferDAO.checkBal(myaccountNo);
	}

	

}
