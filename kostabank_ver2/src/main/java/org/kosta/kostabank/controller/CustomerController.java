package org.kosta.kostabank.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.kostabank.model.service.AccountService;
import org.kosta.kostabank.model.service.CustomerService;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.CustomerVO;
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
	private AccountService accountService;
	@RequestMapping("{viewId}.bank")
	public String showView(@PathVariable String viewId){
		System.out.println("@PathVariable:"+viewId);
		return viewId;
	}
	
	//로그인
	@RequestMapping(value = "customerLogin.bank", method = RequestMethod.POST)
	public ModelAndView customerLogin(String email1,String password1, HttpServletRequest request) {
		CustomerVO vo = new CustomerVO(email1, password1);
		CustomerVO loginInfo = customerService.customerLogin(vo);
		CustomerVO emailFail = customerService.emailFail(vo);
		ModelAndView mv = new ModelAndView();
		int failCount = customerService.failCount(vo);
		if(loginInfo != null && failCount <= 3) {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
			mv.setViewName("redirect:home.bank");
		} else if(emailFail == null){
			mv.setViewName("kangbank/login/email_fail");
		} else {
			mv.setViewName("redirect:loginFailCount.bank?email="+email1);
		}
		return mv;
	}
	//로그인 실패시 count+1
	@RequestMapping(value="loginFailCount.bank")
	public ModelAndView loginFailCount(String email) {
		System.out.println("loginFailCount");
		CustomerVO vo = new CustomerVO(email);
		ModelAndView mv = new ModelAndView();
		customerService.loginFailCount(vo);
		int failCount = customerService.failCount(vo);
		mv.setViewName("kangbank/login/login_fail");
		mv.addObject("failCount", failCount);
		return mv;
	}
	@RequestMapping(value = "customerLogout.bank")
	public String customerLogout(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("loginInfo") != null){
			session.invalidate();
		}
		return "redirect:home.bank";
	}
	@RequestMapping(value = "customerRegister.bank", method = RequestMethod.POST)
	public String customerRegister(CustomerVO vo, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		String url = "redirect:home.bank";
		if(session == null || session.getAttribute("loginIngo") == null){
			url = "kangbank/register/register_ok";
			customerService.customerRegister(vo);
		}
		return url;
	}
	@RequestMapping(value = "checkEmail.bank")
	@ResponseBody
	public boolean checkEmail(String email){
		CustomerVO vo = customerService.checkEmail(email);
		boolean flag = true;
		if(vo == null){
			flag = false;
		}
		return flag;
	}
	@RequestMapping(value = "findId.bank")
	@ResponseBody
	public boolean findId(CustomerVO vo, HttpServletRequest request){
		CustomerVO findVO = customerService.findId(vo);
		boolean flag = true;
		if(findVO == null){
			flag = false;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("email", findVO.getEmail());
		}
		return flag;
	}
	@RequestMapping("passwordCheck.bank")
	public String passwordCheckRedirect(){
		return "account_passCheck";
	}
	@RequestMapping(value="memberCreateAccount.bank")
	public ModelAndView memberCreateAccount(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		CustomerVO cvo = (CustomerVO) session.getAttribute("loginInfo");
		List<AccountTypeVO> typeList= accountService.findAccountByAccountName();
		ModelAndView mv = new ModelAndView();
		mv.addObject("tlist",typeList);
		session.setAttribute("cvo", cvo);
		mv.setViewName("account_create");
		return mv;
		
	}
	@RequestMapping("checkMemberPass.bank")
	@ResponseBody
	public boolean passwordCheck(HttpServletRequest request, String checkPass){
		System.out.println("입력 비밀 번호 : " + checkPass);
		boolean flag = false;
		HttpSession session = request.getSession(false);
		CustomerVO loginInfo = (CustomerVO)session.getAttribute("loginInfo");
		System.out.println("원래 비밀번호 : " + loginInfo.getPassword());
		if(loginInfo.getPassword().equals(checkPass)){
			flag=true;
		}
		return flag;
	}
	
	//비밀번호 체크 페이지
	@RequestMapping("customer_updatePassCheck.bank")
	public String updatePassCheck() {
		return "customer_updatePassCheck";
	}
		
	//비밀번호 업데이트 화면
	@RequestMapping("customer_updateCustomer.bank")
	public String updateCustomer() {
		return "customer_updateCustomer";
	}
		
	//정보수정
	@RequestMapping("customer_updateCustomerResult.bank")
	public String updateCustomerResult(CustomerVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		CustomerVO vo1 = (CustomerVO) session.getAttribute("loginInfo");
		if(vo == null) {
			return "home.bank";
		} else {
			vo.setName(vo1.getName());
			vo.setBirth(vo1.getBirth());
			vo.setTel(vo1.getTel());
			vo.setSecurity_card(vo1.getSecurity_card());
			System.out.println("업데이트");
			customerService.updateCustomerResult(vo);
			session.setAttribute("loginInfo", vo);
			System.out.println(vo);
			return "customer_updateCustomerResult";
		}
	}

}
