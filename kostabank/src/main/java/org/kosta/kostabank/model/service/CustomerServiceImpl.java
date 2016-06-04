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

}
