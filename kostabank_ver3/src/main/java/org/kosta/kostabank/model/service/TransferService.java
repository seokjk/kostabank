package org.kosta.kostabank.model.service;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.TransferVO;

public interface TransferService {

	public abstract void transfer(AccountVO avo, CustomerVO cvo, int money);

	List<TransferVO> findAccount();

	int checkPw(int accountPass);

	int checkBal(String myaccountNo);

	List<TransferVO> recentAccountNo(String accountNo);

}