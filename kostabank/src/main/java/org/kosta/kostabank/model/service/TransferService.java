package org.kosta.kostabank.model.service;

import org.kosta.kostabank.model.vo.TransferVO;

public interface TransferService {

	public abstract int transfer(TransferVO tvo);

	public abstract int checkBal(TransferVO tvo);

	public abstract int deposit(TransferVO tvo);

	public abstract int withdraw(TransferVO tvo);

}