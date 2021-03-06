package org.kosta.kostabank.controller;


import javax.annotation.Resource;

import org.kosta.kostabank.model.service.AccountTypeService;
import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountTypeController {
	@Resource
	private AccountTypeService accountTypeService;
	
	///////////////////////////////////////////////////////////////
	///////	title : createAccountType						///////
	/////// dec : 은행상품 생성								///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("accountType_CreateAccountType.bank")
	public String createAccountType(AccountTypeVO accountTypeVO, AccountRatesVO accountRatesVO, String accountName){
		accountTypeService.createAccountType(accountTypeVO, accountRatesVO);
		return "redirect:accountTypeList.bank?page=1";
	}
	
}
