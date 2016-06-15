package org.kosta.kostabank.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SavingsDAOImpl implements SavingsDAO {
@Resource
private SqlSessionTemplate template;

	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.dao.SavingsDAO#savingsProductlist(java.lang.String)
	 */
	@Override
	public List<AccountTypeVO> savingsProductlist(String accountType){
		return template.selectList("savings.savingsProductlist",accountType);
	}
}
