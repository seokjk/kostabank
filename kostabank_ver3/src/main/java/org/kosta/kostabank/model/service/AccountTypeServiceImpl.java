package org.kosta.kostabank.model.service;

import java.util.List;

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
	System.out.println(accountTypeVO.getAccountName());
	accountRatesVO.setAccountTypeVO(new AccountTypeVO(accountTypeVO.getAccountName()));
	accountTypeDAO.createAccountRates(accountRatesVO);
}
@Override
public List<AccountTypeVO> selectLoan(){
	return accountTypeDAO.selectLoan();
}
}
