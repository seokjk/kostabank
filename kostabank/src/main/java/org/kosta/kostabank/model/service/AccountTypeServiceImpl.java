package org.kosta.kostabank.model.service;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.AccountTypeDAO;
import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.springframework.stereotype.Service;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {
@Resource
private AccountTypeDAO accountTypeDAO;
/* (non-Javadoc)
 * @see org.kosta.kostabank.model.service.AccountTypeService#createAccountType(org.kosta.kostabank.model.vo.AccountTypeVO, org.kosta.kostabank.model.vo.AccountRatesVO)
 */
@Override
public void createAccountType(AccountTypeVO accountTypeVO, AccountRatesVO accountRatesVO){
	accountTypeDAO.createAccountType(accountTypeVO);
	accountTypeDAO.createAccountRates(accountRatesVO);
}
}
