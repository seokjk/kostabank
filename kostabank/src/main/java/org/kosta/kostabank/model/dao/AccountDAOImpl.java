package org.kosta.kostabank.model.dao;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.AccountVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
@Resource
private SqlSessionTemplate template;
/* (non-Javadoc)
 * @see org.kosta.kostabank.model.dao.AccountDAO#createAccount(org.kosta.kostabank.model.vo.AccountVO)
 */
@Override
public void createAccount(AccountVO vo){
	template.insert("account.createAccount",vo);
}
}
