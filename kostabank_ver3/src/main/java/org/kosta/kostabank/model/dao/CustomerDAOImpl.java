package org.kosta.kostabank.model.dao;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.CustomerVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class CustomerDAOImpl implements CustomerDAO {
	@Resource
	private SqlSessionTemplate template;
	@Override
	public CustomerVO customerLogin(CustomerVO vo) {
		return template.selectOne("customer.customerLogin", vo);
	}
	@Override
	public void customerRegister(CustomerVO vo) {
		template.insert("customer.customerRegister", vo);
	}
	@Override
	public CustomerVO checkEmail(String email) {
		return template.selectOne("customer.checkEmail", email);
	}
	@Override
	public CustomerVO findId(CustomerVO vo) {
		return template.selectOne("customer.findId", vo);
	}
	@Override
	public int tempPassword(CustomerVO vo) {
		return template.update("customer.tempPassword", vo);
	}
	//회원정보수정결과
	@Override
	public int updateCustomerResult(CustomerVO vo) {
		return template.update("customer.updateCustomerResult", vo);
	}
	//로그인 실패 횟수 업데이트
	@Override
	public int loginFailCount(CustomerVO vo) {
		return template.update("customer.loginFailCount", vo);
	}
	//로그인 실패 횟수
	@Override
	public int failConut(CustomerVO vo) {
		return template.selectOne("customer.failCountUpdate", vo);
	}
	//로그인 실패 횟수 0으로 업데이트
	@Override
	public int failCountUpdate(CustomerVO vo) {
		return template.update("customer.updateCount", vo);
	}
	//로그인시 이메일 없을 떄
	@Override
	public CustomerVO emailFail(CustomerVO vo) {
		return template.selectOne("customer.emailFail", vo);
	}
	@Override
	public CustomerVO infoByEmail(String email) {
		return template.selectOne("customer.infoByEmail", email);
	}
}
