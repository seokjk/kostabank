package org.kosta.kostabank.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.SavingsDAO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.springframework.stereotype.Service;

@Service
public class SavingsServiceImpl implements SavingsService {
@Resource
private SavingsDAO savingsDAO;
/* (non-Javadoc)
 * @see org.kosta.kostabank.model.service.SavingsService#savingsProductlist(java.lang.String)
 */
@Override
public List<AccountTypeVO> savingsProductlist(String accountType){
	return savingsDAO.savingsProductlist(accountType);
}
}
