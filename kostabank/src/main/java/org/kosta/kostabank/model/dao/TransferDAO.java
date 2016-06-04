package org.kosta.kostabank.model.dao;

import org.kosta.kostabank.model.vo.TransferVO;

public interface TransferDAO {

	/**
	 * 입금
	 * @param tvo
	 * @return
	 */
	public abstract int deposit(TransferVO tvo);

	/**
	 * 출금
	 * @param tvo
	 * @return
	 */
	public abstract int withdraw(TransferVO tvo);

	/**
	 * 잔액조회
	 * @param tvo
	 * @return
	 */
	public abstract int checkBal(TransferVO tvo);

	/**
	 * 계좌이체
	 * @param tvo
	 * @return
	 */
	public abstract int transfer(TransferVO tvo);

}