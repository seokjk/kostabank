package org.kosta.kostabank.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
	@Resource
	private SqlSessionTemplate template;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kosta.kostabank.model.dao.AccountDAO#createAccount(org.kosta.
	 * kostabank.model.vo.AccountVO)
	 */
	@Override
	public void createAccount(AccountVO vo) {
		template.insert("account.createAccount", vo);
	}

	public AccountVO findAccountByAccountNum(String accountNo) {
		System.out.println("들어가냐?" + accountNo);
		return template.selectOne("account.findAccountByAccountNum", accountNo);
	}

	public List<AccountTypeVO> findAccountByAccountName() {
		return template.selectList("account.findAccountByAccountName");
	}

	public int findMinMoney(String accountName) {
		return template.selectOne("account.findMinMoney", accountName);
	}

	// 전체계좌조회
	@Override
	public List<AccountVO> accountTotalList(String email) {
		return template.selectList("account.accountTotalList", email);
	}
}
