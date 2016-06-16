package org.kosta.kostabank.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.kosta.kostabank.model.service.AccountService;
import org.kosta.kostabank.model.service.AccountTypeService;
import org.kosta.kostabank.model.service.LoanService;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoanController {
	@Resource
	private LoanService loanService;
	@Resource
	private AccountService accountService;
	@Resource
	private AccountTypeService accountTypeService;
	
	@RequestMapping("loan_view.bank")
	public ModelAndView loanView(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		ModelAndView mv = new ModelAndView();
		CustomerVO vo = (CustomerVO) session.getAttribute("loginInfo");
		List<AccountVO> alist = accountService.accountList(vo.getEmail());
		List<AccountTypeVO> atlist = accountService.findAccountByAccountName();
		List<AccountTypeVO> atlist2 = accountTypeService.selectLoan();
		mv.addObject("alist",alist);
		mv.addObject("atlist",atlist);
		mv.addObject("atlist2",atlist2);
		mv.setViewName("loan_view");
		return mv;
	}
	
	@RequestMapping("checkLoan.bank")
	@ResponseBody
	public JSONArray checkLoan(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		JSONArray obj = new JSONArray();
		String name=request.getParameter("goods");
		System.out.println("name="+name);
		LoanAccountVO select = loanService.loanData(name);
		System.out.println("s="+select);
		long overdue = select.getMaximumMoney();
		int repay = select.getTerm();
		int stay = repay/2;

		obj.add(0,overdue);
		obj.add(1,repay);
		obj.add(2,stay);
		
		System.out.println("overdue="+overdue);
		System.out.println("period="+repay);
		System.out.println("stay="+stay);
		return obj;
	}
	
	@RequestMapping("checkPeriod.bank")
	@ResponseBody
	public int checkPeriod(HttpServletRequest request) {
		String period=request.getParameter("repayTerm");
		System.out.println("period="+period);
		LoanAccountVO select = loanService.loanData(period);
		int p = select.getTerm();
		System.out.println(p);
		return p;
	}
}
