package org.kosta.kostabank.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.kostabank.model.service.CustomerService;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class CustomerController {
	@Resource
	private CustomerService customerService;
	@RequestMapping("{viewId}.bank")
	public String showView(@PathVariable String viewId){
		System.out.println("@PathVariable:"+viewId);
		return viewId;
	}
	
	@RequestMapping(value = "customerLogin.bank", method = RequestMethod.POST)
	public String customerLogin(String email1,String password1, HttpServletRequest request){
		CustomerVO vo = new CustomerVO(email1,password1);		
		CustomerVO loginInfo = customerService.customerLogin(vo);
		String url = "kangbank/login/login_fail";
		if(loginInfo != null){
			url = "redirect:home.bank";
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
		}
		return url;
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

}
