package org.kosta.kostabank.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoanTypeDAOImpl implements LoanTypeDAO{
	@Resource
	private SqlSessionTemplate template;
	
	//대출 계좌 이름 리스트
	@Override
	public List<String> loanNameList() {
		return template.selectList("loan.loanNameList");
	}
	//대출 전체 리스트
	@Override
	public LoanAccountVO loanList(String accountName) {
		System.out.println("loanDAO : " + template.selectOne("loan.loanList", accountName));
		return template.selectOne("loan.loanList", accountName);
	}
}
