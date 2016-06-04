package org.kosta.kostabank.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.kostabank.model.service.AccountService;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
@Resource
private AccountService accountService;
 //아아아아아
@RequestMapping("createAccount.bank")
public String createAccount(AccountVO vo,String accountName){
	System.out.println(accountName);
	vo.setAccountName(new AccountTypeVO(accountName));
	System.out.println(vo);
	accountService.createAccount(vo);
	return "redirect:home.bank";
}
@RequestMapping("findAccountByAccountNum.bank")
public ModelAndView findAccountByAccountNum(String accountNo){
	System.out.println(accountNo);
	AccountVO avo = accountService.findAccountByAccountNum(accountNo);
	System.out.println(avo);
	return new ModelAndView("account_create_result","result",avo);
}
@RequestMapping("minMoneyShow.bank")
@ResponseBody
public int findMinMoney(String accountName){
	int minMoney = accountService.findMinMoney(accountName);
	if(minMoney==0){
		minMoney=0;
	}
	return minMoney;
}
@RequestMapping("accountTypeList.bank")
public ModelAndView accountTypeList(){
	List<AccountTypeVO> rlist = accountService.findAccountByAccountName();
	return new ModelAndView("account_Type","rlist",rlist);
}
}
