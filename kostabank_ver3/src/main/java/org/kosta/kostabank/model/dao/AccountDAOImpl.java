package org.kosta.kostabank.model.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.DealDetailVO;
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

	// 페이징 리스트
	public List<AccountTypeVO> findAccountByAccountNamePaging(
			Map<String, Integer> pagingConfig) {
		return template.selectList("account.findAccountByAccountNamePaging",
				pagingConfig);
	}

	public int totalContent() {
		return template.selectOne("account.totalContent");
	}

	public int findMinMoney(String accountName) {
		return template.selectOne("account.findMinMoney", accountName);
	}

	// 전체계좌조회
	@Override
	public List<AccountVO> accountTotalList(String email) {
		return template.selectList("account.accountTotalList", email);
	}

	// 전체계좌중에 이체가능계좌조회
	@Override
	public List<AccountVO> accountList(String email) {
		return template.selectList("account.accountList", email);
	}

	// 계좌번호로 계좌정보 가져오기
	@Override
	public AccountVO accountAll(String accountNo) {
		return template.selectOne("account.accountAll", accountNo);
	}
	
	@Override
	public AccountVO accountBAndN(String accountNo) {
		return template.selectOne("account.accountBAndN", accountNo);
	}

	// 계좌조회
	@Override
	public AccountVO checkAccount(AccountVO avo) {
		return template.selectOne("account.checkAccount", avo);
	}

	@Override
	public AccountVO checkOtherAccount(String accountNo) {
		return template.selectOne("checkOtherAccount", accountNo);
	}

	// 출금
	@Override
	public int withdraw(DealDetailVO dvo) {
		return template.update("account.withdraw", dvo);
	}

	// 입금
	@Override
	public int deposit(DealDetailVO dvo) {
		return template.update("account.deposit", dvo);
	}
	
	//계좌 일수, 잔액 합계 
	@Override
	public int scheduled() {
		return template.update("account.scheduled");
	}
	
	//금리달 금리계산
	@Override
	public int ratesMonth(int month) {
		return template.update("account.ratesMonth", month);
	}

}
