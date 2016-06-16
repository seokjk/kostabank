package org.kosta.kostabank.model.dao;

import org.kosta.kostabank.model.vo.CustomerVO;

public interface CustomerDAO {

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
	int failConut(CustomerVO vo);
	//로그인 실패 횟수 0으로 업데이트
	int failCountUpdate(CustomerVO vo);
	//로그인시 이메일 없을 떄
	CustomerVO emailFail(CustomerVO vo);

	CustomerVO infoByEmail(String email);
}
