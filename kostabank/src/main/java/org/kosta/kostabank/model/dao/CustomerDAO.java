package org.kosta.kostabank.model.dao;

import org.kosta.kostabank.model.vo.CustomerVO;

public interface CustomerDAO {

	CustomerVO customerLogin(CustomerVO vo);

	void customerRegister(CustomerVO vo);

	CustomerVO checkEmail(String email);

	CustomerVO findId(CustomerVO vo);

	int tempPassword(CustomerVO vo);

	int updateCustomerResult(CustomerVO vo);

}
