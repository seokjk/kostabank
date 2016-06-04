package org.kosta.kostabank.model.dao;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountTypeDAOImpl implements AccountTypeDAO {
	@Resource
	private SqlSessionTemplate template;
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.dao.AccountTypeDAO#createAccountType(org.kosta.kostabank.model.vo.AccountTypeVO)
	 */
	@Override
	public void createAccountType(AccountTypeVO accountTypeVO){
		template.insert("accountType.createAccountType",accountTypeVO);
	}
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.dao.AccountTypeDAO#createAccountRates(org.kosta.kostabank.model.vo.AccountRatesVO)
	 */
	@Override
	public void createAccountRates(AccountRatesVO accountRatesVO){
		template.insert("accountType.createAccountRates",accountRatesVO);
	}
}
