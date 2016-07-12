package org.kosta.kostabank.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.AccountVO;
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
   public CustomerVO findId(AccountVO vo) {
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
   
   @Override
   public AccountVO updatePassCheck(AccountVO accountVO){
      return template.selectOne("customer.updatePassCheck",accountVO);
   }
   @Override
   public void updatePass(CustomerVO customerVO){
      template.update("customer.updatePass", customerVO);
   }
   //계좌가 존재하는지 검사
   public boolean accountNoCheck(String email, String accountNo) {
      AccountVO vo = new AccountVO(new CustomerVO(email),accountNo);
      String check = template.selectOne("customer.accountNoCheck",vo);
      if(check==null){
         return false;
      }else{
         return true;
      }
   }
   //계좌비밀번호가 맞는지 검사
   public boolean accountPassCheck(String accountNo, String accountPass) {
      AccountVO vo = new AccountVO(accountNo, Integer.parseInt(accountPass));
      String check = template.selectOne("customer.accountPassCheck",vo);
      if(check==null){
         return false;
      }else{
         return true;
      }
   }
   @Override
   public List<CustomerVO> searchCustomer() {
      return template.selectList("customer.searchCustomer");
   }
}
