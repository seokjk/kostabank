package org.kosta.kostabank.model.dao;

import java.util.List;

import org.kosta.kostabank.model.vo.TransferVO;

public interface TransferDAO {

	public abstract int deposit(int money);

	public abstract int withdraw(int money);

	public abstract int checkBal(String myaccountNo);

	List<TransferVO> findAccount();

	int checkPw(int accountPass);

	List<TransferVO> recentAccountNo(String accountNo);

}