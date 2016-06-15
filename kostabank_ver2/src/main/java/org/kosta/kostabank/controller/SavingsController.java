package org.kosta.kostabank.controller;


import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.service.SavingsService;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SavingsController {
	@Resource
	private SavingsService savingsService;
	
	@RequestMapping("savings.bank")
	public ModelAndView savings(String accountType){
		System.out.println(accountType);
		List<AccountTypeVO> slist = savingsService.savingsProductlist(accountType); 
		System.out.println(slist);
		return new ModelAndView("savings_savings","slist",slist);
	}
}
