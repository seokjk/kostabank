package org.kosta.kostabank.model.dao;

import org.kosta.kostabank.model.vo.AccountVO;

public interface AccountDAO {

	public abstract void createAccount(AccountVO vo);

}