package org.kosta.kostabank.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.kostabank.model.service.AccountService;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.ListVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
   @Resource(name = "accountServiceImpl")
   private AccountService accountService;

   ///////////////////////////////////////////////////////////////
   ///////   title : memberCreateAccount                   ///////
   /////// dec : 계좌생성하기 페이지                                 ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping(value = "memberCreateAccount.bank")
   public ModelAndView memberCreateAccount(HttpServletRequest request) {
      HttpSession session = request.getSession(false);
      if (session == null || session.getAttribute("loginInfo") == null) {
         return new ModelAndView("redirect:kangbank/templates/needLogin.jsp");
      }
      List<AccountTypeVO> typeList = accountService
            .findAccountByAccountName();
      return new ModelAndView("account_create", "tlist", typeList);
   }
   
	///////////////////////////////////////////////////////////////
	///////   title : adminCreateAccount                   ///////
	/////// dec : 계좌생성하기 페이지                                 ///////
	///////////////////////////////////////////////////////////////
	@RequestMapping(value = "adminCreateAccount.bank")
	public ModelAndView adminCreateAccount(HttpServletRequest request, String email) {
		List<AccountTypeVO> typeList = accountService
		.findAccountByAccountName();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("kangbank_admin/popup/admin_account_create");
		mv.addObject("tlist", typeList);
		mv.addObject("email", email);
		return mv;
	}
   
   ///////////////////////////////////////////////////////////////
   ///////   title : findAccountByAccountNum               ///////
   /////// dec : 생성된 계좌 정보 보여주기                           ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("findAccountByAccountNum.bank")
   public ModelAndView findAccountByAccountNum(String accountNo) {
      AccountVO avo = accountService.findAccountByAccountNum(accountNo);
      return new ModelAndView("account_create_result", "result", avo);
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : findMinMoney                          ///////
   /////// dec : 최저 가입금액 보여주기                              ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("minMoneyShow.bank")
   @ResponseBody
   public int findMinMoney(String accountName) {
	   System.out.println("asd");
      int minMoney = accountService.findMinMoney(accountName);
      return minMoney;
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : accountTypeList                       ///////
   /////// dec : 상품정보리스트                                      ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("accountTypeList.bank")
   public ModelAndView accountTypeList(int page) {
      ListVO lvo = accountService.findAccountByAccountNamePaging(page);
      return new ModelAndView("account_Type", "lvo", lvo);
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : createAccount                         ///////
   /////// dec : 계좌생성하기                                        ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("createAccount.bank")
   public String createAccount(AccountVO vo, String accountName, String email) {
      vo.setAccountName(new AccountTypeVO(accountName));
      vo.setCustomerVO(new CustomerVO(email));
      accountService.createAccount(vo);
      return "redirect:findAccountByAccountNum.bank?accountNo="
            + vo.getAccountNo();
   }
   
   ///////////////////////////////////////////////////////////////
   ///////   title : adminCreateAccount2                        ///////
   /////// dec : 계좌생성하기                                        ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("adminCreateAccount2.bank")
   public ModelAndView adminCreateAccount2(AccountVO vo, String accountName, String email) {
      vo.setAccountName(new AccountTypeVO(accountName));
      vo.setCustomerVO(new CustomerVO(email));
      accountService.createAccount(vo);
      return new ModelAndView("kangbank_admin/popup/account_ok", "accountNo", vo.getAccountNo());
   }
   
   ///////////////////////////////////////////////////////////////
   ///////   title : accountTotalList                      ///////
   /////// dec : 전체 계좌 조회                                      ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("accountTotalList.bank")
   public ModelAndView accountTotalList(String email,
         HttpServletRequest request) {
      HttpSession session = request.getSession(false);
      if (session == null || session.getAttribute("loginInfo") == null) {
         return new ModelAndView("redirect:kangbank/templates/needLogin.jsp");
      }
      List<AccountVO> list = null;
      list = accountService.accountTotalList(((CustomerVO) session.getAttribute("loginInfo")).getEmail());
      return new ModelAndView("account_total_list", "accountTotalList", list);
   }
   
   ///////////////////////////////////////////////////////////////
   ///////   title : adminAccountTotalList                 ///////
   /////// dec : admin계정 전체 계좌 조회                           ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("adminAccountTotalList.bank")
   public ModelAndView adminAccountTotalList(String email) {
      List<AccountVO> list = null;
      list = accountService.accountTotalList(email);
      return new ModelAndView("kangbank_admin/popup/account_total_list", "accountTotalList", list);
   }
   
   ///////////////////////////////////////////////////////////////
   ///////   title : adminAccountDeal	                   ///////
   /////// dec : admin계정 전체 계좌 조회                           ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("adminAccountDeal.bank")
   public ModelAndView adminAccountDeal(String accountNo) {
      return new ModelAndView("kangbank_admin/popup/dealDetailByDate");
   }
}