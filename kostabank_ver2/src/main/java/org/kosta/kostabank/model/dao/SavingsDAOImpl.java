package org.kosta.kostabank.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SavingsDAOImpl implements SavingsDAO {
@Resource
private SqlSessionTemplate template;

	@Override
	public List<AccountTypeVO> savingsProductlist(String accountType){
		System.out.println(accountType);
		return template.selectList("savings.savingsProductlist",accountType);
	}
	public AccountRatesVO accountNameFindAccountList(String accountName){
		return template.selectOne("savings.accountNameFindAccountList",accountName);
	}
}
