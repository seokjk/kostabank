package org.kosta.kostabank.model.dao;

import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;

public interface CustomerDAO {

	CustomerVO customerLogin(CustomerVO vo);

	void customerRegister(CustomerVO vo);

	CustomerVO checkEmail(String email);

	CustomerVO findId(AccountVO vo);

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
	//비밀번호 변경전 확인
    AccountVO updatePassCheck(AccountVO accountVO);
    //비밀번호 변경
    void updatePass(CustomerVO customerVO);
    
    //계좌가 존재하는지 검사
    boolean accountNoCheck(String email, String accountNo);
    
    //계좌의 비밀번호가 맞는지 검사
    boolean accountPassCheck(String accountNo, String accountPass);
	
}
