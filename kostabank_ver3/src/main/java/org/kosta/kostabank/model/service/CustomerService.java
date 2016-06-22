package org.kosta.kostabank.model.service;

import net.sf.json.JSONArray;

import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;

public interface CustomerService {

	CustomerVO customerLogin(CustomerVO vo);

	void customerRegister(CustomerVO vo);

	boolean checkEmail(String email);

	JSONArray findId(AccountVO vo, String birth);

	int tempPassword(CustomerVO vo);
	//회원정보수정결과
	CustomerVO updateCustomerResult(CustomerVO vo);
	//로그인 실패 횟수 업데이트
	int loginFailCount(CustomerVO vo);
	//로그인 실패 횟수
	int failCount(CustomerVO vo);
	//로그인 실패 횟수 0으로 업데이트
	int failCountUpdate(CustomerVO vo);
	//로그인시 이메일 없을 경우
	CustomerVO emailFail(CustomerVO vo);
}
