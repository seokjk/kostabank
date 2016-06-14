package org.kosta.kostabank.model.service;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.CustomerDAO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.springframework.stereotype.Service;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Resource
	private CustomerDAO customerDAO;
	@Override
	public CustomerVO customerLogin(CustomerVO vo) {
		return customerDAO.customerLogin(vo);
	}
	@Override
	public void customerRegister(CustomerVO vo) {
		customerDAO.customerRegister(vo);
	}
	@Override
	public CustomerVO checkEmail(String email) {
		return customerDAO.checkEmail(email);
	}
	@Override
	public CustomerVO findId(CustomerVO vo) {
		return customerDAO.findId(vo);
	}
	@Override
	public int tempPassword(CustomerVO vo) {
		 return customerDAO.tempPassword(vo);
	}
	//회원정보결과
	@Override
	public int updateCustomerResult(CustomerVO vo) {
		return customerDAO.updateCustomerResult(vo);
	}
	//로그인 실패 횟수 업데이트
	@Override
	public int loginFailCount(CustomerVO vo) {
		return customerDAO.loginFailCount(vo);
	}
	//로그인 실패 횟수
	@Override
	public int failCount(CustomerVO vo) {
		return customerDAO.failConut(vo);
	}
	//로그인 실패 횟수 0으로 업데이트
	@Override
	public int failCountUpdate(CustomerVO vo) {
		return customerDAO.failCountUpdate(vo);
	}
	//로그인시 이메일 없을 떄
	@Override
	public CustomerVO emailFail(CustomerVO vo) {
		return customerDAO.emailFail(vo);
	}
}
