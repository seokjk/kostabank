package org.kosta.kostabank.model.dao;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountTypeVO;

public interface SavingsDAO {

	public abstract List<AccountTypeVO> savingsProductlist(String accountType);

}