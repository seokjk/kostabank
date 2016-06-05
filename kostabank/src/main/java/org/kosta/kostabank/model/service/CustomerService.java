package org.kosta.kostabank.model.service;

import org.kosta.kostabank.model.vo.CustomerVO;

public interface CustomerService {

	CustomerVO customerLogin(CustomerVO vo);

	void customerRegister(CustomerVO vo);

	CustomerVO checkEmail(String email);

	CustomerVO findId(CustomerVO vo);

	int tempPassword(CustomerVO vo);
	//회원정보수정결과
	int updateCustomerResult(CustomerVO vo);

}
