package org.kosta.kostabank.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.kosta.kostabank.model.service.CustomerService;
import org.kosta.kostabank.model.service.SecureCardService;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.SecureCardVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
   @Resource
   private CustomerService customerService;
   @Resource
   private SecureCardService securecardService;

   @RequestMapping("{viewId}.bank")
   public String showView(@PathVariable String viewId) {
      System.out.println("@PathVariable:" + viewId);
      return viewId;
   }

   // 로그인
   @RequestMapping(value = "customerLogin.bank", method = RequestMethod.POST)
   public ModelAndView customerLogin(String email1, String password1,
         HttpServletRequest request) {
      CustomerVO vo = new CustomerVO(email1, password1);
      CustomerVO loginInfo = customerService.customerLogin(vo);
      CustomerVO emailFail = customerService.emailFail(vo);
      ModelAndView mv = new ModelAndView();
      if (loginInfo != null) {
         int failCount = customerService.failCount(vo);
         if (failCount <= 3) {
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo", loginInfo);
            customerService.failCountUpdate(loginInfo);
            mv.setViewName("redirect:home.bank");
         } else {
            mv.setViewName("redirect:loginFailCount.bank?email=" + email1);
         }
      } else {
         if (emailFail == null) {
            mv.setViewName("kangbank/login/email_fail");
         } else {
            mv.setViewName("redirect:loginFailCount.bank?email=" + email1);
         }
      }
      return mv;
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : loginFailCount                          ///////
   /////// dec : 로그인 실패시 카운트 증가                 ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping(value = "loginFailCount.bank")
   public ModelAndView loginFailCount(String email) {
      ModelAndView mv = new ModelAndView();
      int failCount = customerService.loginFailCount(new CustomerVO(email));
      mv.setViewName("kangbank/login/login_fail");
      mv.addObject("failCount", failCount);
      return mv;
   }

   ///////////////////////////////////////////////////////////////
   /////// title : customerLogout                          ///////
   /////// dec : 로그아웃                                            ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping(value = "customerLogout.bank")
   public String customerLogout(HttpServletRequest request) {
      HttpSession session = request.getSession(false);
      if (session != null && session.getAttribute("loginInfo") != null) {
         session.invalidate();
      }
      return "redirect:home.bank";
   }

   ///////////////////////////////////////////////////////////////
   /////// title : customerRegister                        ///////
   /////// dec : 회원가입 성공                                       ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping(value = "customerRegister.bank", method = RequestMethod.POST)
   public String customerRegister(CustomerVO vo) {
      String url = "redirect:kangbank_admin/popup/closeWindow.jsp";
      customerService.customerRegister(vo);
      return url;
   }
 
   ///////////////////////////////////////////////////////////////
   /////// title : checkEmail                              ///////
   /////// dec : 아이디 중복 여부 확인                               ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping(value = "checkEmail.bank")
   @ResponseBody
   public boolean checkEmail(String email) {
      return customerService.checkEmail(email);
   }
   
   ///////////////////////////////////////////////////////////////
   /////// title : findId                                  ///////
   /////// dec : 아이디 찾기 결과                                    ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping(value = "findId.bank")
   @ResponseBody
   public JSONArray findId(AccountVO vo, String birth, HttpServletRequest request) {
      JSONArray json = customerService.findId(vo, birth);
      return json;
   }
   @RequestMapping("passwordCheck.bank")
   public String passwordCheckRedirect() {
      return "account_passCheck";
   }

   ///////////////////////////////////////////////////////////////
   /////// title : passwordCheck                           ///////
   /////// dec : 비밀번호 일치 확인                                  ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("checkMemberPass.bank")
   @ResponseBody
   public boolean passwordCheck(HttpServletRequest request, String checkPass) {
      boolean flag = false;
      HttpSession session = request.getSession(false);
      CustomerVO loginInfo = (CustomerVO) session.getAttribute("loginInfo");
      if (loginInfo.getPassword().equals(checkPass)) {
         flag = true;
      }
      return flag;
   }

   ///////////////////////////////////////////////////////////////
   /////// title : updateCustomerResult                    ///////
   /////// dec : 계정정보 수정 후 세션 업데이트                       ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("customer_updateCustomerResult.bank")
   public String updateCustomerResult(CustomerVO vo, HttpServletRequest request) {
      HttpSession session = request.getSession(false);
      if (session == null || session.getAttribute("loginInfo") == null) {
         return "redirect:kangbank/templates/needLogin.jsp";
      }
         session.setAttribute("loginInfo",
               customerService.updateCustomerResult(vo));
         return "customer_updateCustomerResult";
   }
      @RequestMapping("passwordChange.bank")
      public ModelAndView passwordChange(String email,  String password){
         ModelAndView mav = new ModelAndView();
         int a = customerService.updatePass(email, password);
         mav.setViewName("find_checkpasswordview_result");   
         if(a==1){ //update가 되었으면
            mav.addObject("a", "1");
            customerService.failCountUpdate(new CustomerVO(email));
            System.out.println("update");
         }else{
            mav.addObject("a","0");
         }
         return mav;
      }
      
      ///////////////////////////////////////////////////////////////
      /////// title : passwordSecureCardCheck                 ///////
      /////// dec : 비밀번호수정관련 보안카드 확인                       ///////
      ///////////////////////////////////////////////////////////////
      @RequestMapping(value="passwordsecurecheck.bank", method = RequestMethod.POST)
      @ResponseBody
      public JSONObject passwordSecureCardCheck(String f, String s, String dlf,
            String dl, String tka, String tk, String email)
            throws IOException {
         CustomerVO cvo = customerService.infoByEmail(email);
         JSONObject obj = new JSONObject();
         if (cvo.getSecurity_card().equals("0")) {
            obj.put("address", "noexistsecurecard");
         } else {
            SecureCardVO scvo = securecardService.selectSecureCard(cvo
                  .getSecurity_card());
            String[] array = { scvo.getOne(), scvo.getTwo(), scvo.getThree(),
                  scvo.getFour(), scvo.getFive(), scvo.getSix(),
                  scvo.getSeven(), scvo.getEight(), scvo.getNine(),
                  scvo.getTen(), scvo.getEleven(), scvo.getTwelve(),
                  scvo.getThirteen(), scvo.getFourteen(), scvo.getFifteen(),
                  scvo.getSixteen(), scvo.getSeventeen(), scvo.getEighteen(),
                  scvo.getNineteen(), scvo.getTwenty(), scvo.getTwenty_one(),
                  scvo.getTwenty_three(), scvo.getTwenty_three(),
                  scvo.getTwenty_four(), scvo.getTwenty_five(),
                  scvo.getTwenty_six(), scvo.getTwenty_seven(),
                  scvo.getTwenty_eight(), scvo.getTwenty_nine(),
                  scvo.getThirty() };
            String first_check = array[Integer.parseInt(f) - 1];
            String second_check = array[Integer.parseInt(s) - 1];
            if (first_check.substring(0, 2).equals(dlf + dl)
                  && second_check.substring(2, 4).equals(tka + tk)) {
               securecardService.secureCardOK(cvo.getSecurity_card());
               obj.put("address", "loan_ok");
            } else if (scvo.getSecure_card_fail() == 4) {
               obj.put("address", "loannum_fail");
            } else {
               securecardService.secureCardFail(cvo.getSecurity_card());
               SecureCardVO scvo2 = securecardService.selectSecureCard(cvo
                     .getSecurity_card());
               obj.put("cnt", scvo2.getSecure_card_fail());
               obj.put("address", "loan_fail");
            }
         }
         return obj;
      }

      ///////////////////////////////////////////////////////////////
      /////// title : passwordSecureCardCheck                 ///////
      /////// dec : 비밀번호수정관련 보안카드 확인                       ///////
      ///////////////////////////////////////////////////////////////
      @RequestMapping("passwordModifyCheck.bank")
      public String passwordModifyCheck(String email, String accountNo, String accountPass, String password){
         boolean accountNoCheck = customerService.accountNoCheck(email,accountNo);
         boolean accountPassCheck = customerService.accountPassCheck(accountNo,accountPass);
         if(accountNoCheck==false){
            return("kangbank/register/noAccountNo");
         }if(accountPassCheck==false){
            return("kangbank/register/wrongAccountPass");
         }else{
            return("find_secure");
         }
      }
      ///////////////////////////////////////////////////////////////
      /////// title : searchCustomer                          ///////
      /////// dec : 전체 회원 정보 검색                                 ///////
      ///////////////////////////////////////////////////////////////
      @RequestMapping("searchCustomer.bank")
      @ResponseBody   
      public List<CustomerVO> searchCustomer(){
            List<CustomerVO> customerList = customerService.searchCustomer();
            return customerList;
      }
      
}