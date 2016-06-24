package org.kosta.kostabank.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.kosta.kostabank.model.service.CustomerService;
import org.kosta.kostabank.model.vo.AccountVO;
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
	///////	title : loginFailCount							///////
	/////// dec : 로그인 실패시 카운트 증가						///////
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
	///////	title : customerLogout							///////
	/////// dec : 로그아웃									///////
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
	///////	title : customerRegister						///////
	/////// dec : 회원가입 성공								///////
	///////////////////////////////////////////////////////////////
	@RequestMapping(value = "customerRegister.bank", method = RequestMethod.POST)
	public String customerRegister(CustomerVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String url = "redirect:home.bank";
		if (session == null || session.getAttribute("loginIngo") == null) {
			url = "kangbank/register/register_ok";
			customerService.customerRegister(vo);
		}
		return url;
	}

	///////////////////////////////////////////////////////////////
	///////	title : checkEmail								///////
	/////// dec : 아이디 중복 여부 확인							///////
	///////////////////////////////////////////////////////////////
	@RequestMapping(value = "checkEmail.bank")
	@ResponseBody
	public boolean checkEmail(String email) {
		return customerService.checkEmail(email);
	}
	
   ///////////////////////////////////////////////////////////////
   ///////   title : findId                           ///////
   /////// dec : 아이디 찾기 결과                        ///////
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
	///////	title : passwordCheck							///////
	/////// dec : 비밀번호 일치 확인							///////
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
	///////	title : updateCustomerResult					///////
	/////// dec : 계정정보 수정 후 세션 업데이트					///////
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
}
