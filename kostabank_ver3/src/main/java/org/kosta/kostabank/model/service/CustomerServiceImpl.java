package org.kosta.kostabank.model.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

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
	public boolean checkEmail(String email) {
		boolean flag = true;
		if (customerDAO.checkEmail(email) == null) {
			flag = false;
		}
		return flag;
	}
	@Override
	public JSONArray findId(CustomerVO vo) {
		JSONArray json = new JSONArray();
		vo = customerDAO.findId(vo);
		boolean flag = true;
		if(vo == null){
			flag = false;
			json.add(flag);
		} else {
			String email = vo.getEmail();
			json.add(flag);
			json.add(email);
		}
		return json;
	}
	@Override
	public int tempPassword(CustomerVO vo) {
		 return customerDAO.tempPassword(vo);
	}
	//회원정보결과
	@Override
	public CustomerVO updateCustomerResult(CustomerVO vo) {
		customerDAO.updateCustomerResult(vo);
		return customerDAO.infoByEmail(vo.getEmail());
	}
	//로그인 실패 횟수 업데이트
	@Override
	public int loginFailCount(CustomerVO vo) {
		customerDAO.loginFailCount(vo);
		return customerDAO.failConut(vo);
	}
	////////////////////////////////사용안함
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