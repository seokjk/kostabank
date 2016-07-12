package org.kosta.kostabank.model.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.kosta.kostabank.model.dao.CustomerDAO;
import org.kosta.kostabank.model.vo.AccountVO;
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
   public CustomerVO customerInfo(String email) {
      CustomerVO vo = customerDAO.checkEmail(email);
      return vo;
   }
    @Override
      public JSONArray findId(AccountVO vo, String birth) {
         JSONArray json = new JSONArray();
         CustomerVO cvo = null;
         cvo = customerDAO.findId(vo);
         boolean flag = true;
         if(cvo == null || !cvo.getBirth().equals(birth)){
            flag = false;
            json.add(flag);
         } else {
            String email = cvo.getEmail();
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
   @Override
   public int updatePass(String email, String password){
      CustomerVO customerVO = new CustomerVO(email,password);
      customerDAO.updatePass(customerVO);
      return 1;
         
    }
    //email로 회원정보받아오기
   @Override
   public CustomerVO infoByEmail(String email) {
      return customerDAO.infoByEmail(email);
   }
   //출금계좌가 존재하는지 확인
   @Override
   public boolean accountNoCheck(String email, String accountNo) {
      return customerDAO.accountNoCheck(email, accountNo);
   }
   @Override
   public boolean accountPassCheck(String accountNo, String accountPass) {
      return customerDAO.accountPassCheck(accountNo, accountPass);
   }
   @Override
   public List<CustomerVO> searchCustomer() {
      return customerDAO.searchCustomer();
   }

}