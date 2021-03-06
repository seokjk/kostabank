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
	//로그인 실패 횟수 업데이트
	int loginFailCount(CustomerVO vo);
	//로그인 실패 횟수
	int failCount(CustomerVO vo);
	//로그인 실패 횟수 0으로 업데이트
	int failCountUpdate(CustomerVO vo);
	//로그인시 이메일 없을 경우
	CustomerVO emailFail(CustomerVO vo);
}
