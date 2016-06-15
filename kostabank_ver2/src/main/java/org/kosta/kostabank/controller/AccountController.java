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
	@Resource(name="accountServiceImpl")
	private AccountService accountService;

	// 아아아아아
	@RequestMapping("createAccount.bank")
	public String createAccount(AccountVO vo, String accountName) {
		System.out.println(accountName);
		vo.setAccountName(new AccountTypeVO(accountName));
		System.out.println(vo);
		accountService.createAccount(vo);
		return "redirect:findAccountByAccountNum.bank?accountNo="+vo.getAccountNo();
	}

	@RequestMapping("findAccountByAccountNum.bank")
	public ModelAndView findAccountByAccountNum(String accountNo) {
		System.out.println(accountNo);
		AccountVO avo = accountService.findAccountByAccountNum(accountNo);
		System.out.println(avo);
		return new ModelAndView("account_create_result", "result", avo);
	}

	@RequestMapping("minMoneyShow.bank")
	@ResponseBody
	public int findMinMoney(String accountName) {
		int minMoney = accountService.findMinMoney(accountName);
		if (minMoney == 0) {
			minMoney = 0;
		}
		return minMoney;
	}

	@RequestMapping("accountTypeList.bank")
	public ModelAndView accountTypeList(int page) {
		ListVO lvo = accountService.findAccountByAccountNamePaging(page);
		return new ModelAndView("account_Type", "lvo", lvo);
	}
	
	//계좌 전체 리스트 조회
	@RequestMapping("accountTotalList.bank")
	public ModelAndView accountTotalList(String email, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		CustomerVO vo = (CustomerVO) session.getAttribute("loginInfo");
		email = vo.getEmail();
		List<AccountVO> list = accountService.accountTotalList(email);
		return new ModelAndView("account_total_list","accountTotalList",list);
	}
}