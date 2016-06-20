package org.kosta.kostabank.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.kosta.kostabank.model.service.AccountService;
import org.kosta.kostabank.model.service.AccountTypeService;
import org.kosta.kostabank.model.service.LoanService;
import org.kosta.kostabank.model.service.LoanTypeService;
import org.kosta.kostabank.model.service.SecureCardService;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.kosta.kostabank.model.vo.LoanVO;
import org.kosta.kostabank.model.vo.SecureCardVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoanController {
	@Resource
	private LoanService loanService;
	@Resource
	private LoanTypeService loanTypeService;
	@Resource
	private AccountService accountService;
	@Resource
	private AccountTypeService accountTypeService;
	@Resource
	private SecureCardService securecardService;

	@RequestMapping("loan_viewpre.bank")
	public ModelAndView loanView(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelAndView mv = new ModelAndView();
		CustomerVO vo = (CustomerVO) session.getAttribute("loginInfo");
		List<AccountVO> alist = accountService.accountList(vo.getEmail());
		List<AccountTypeVO> atlist = accountService.findAccountByAccountName();
		List<AccountTypeVO> atlist2 = accountTypeService.selectLoan();
		String accountName = request.getParameter("temp");
		LoanAccountVO lvo = loanTypeService.loanList(accountName);
		mv.addObject("lvo", lvo);
		mv.addObject("alist", alist);
		mv.addObject("atlist", atlist);
		mv.setViewName("loan_view");
		return mv;
	}

	@RequestMapping("checkLoan.bank")
	@ResponseBody
	public JSONArray checkLoan(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		JSONArray obj = new JSONArray();
		String name = request.getParameter("goods");
		System.out.println("name=" + name);
		LoanAccountVO select = loanService.loanData(name);
		System.out.println("s=" + select);
		long overdue = select.getMaximumMoney();
		int repay = select.getTerm();
		int stay = repay / 2;

		obj.add(0, overdue);
		obj.add(1, repay);
		obj.add(2, stay);

		System.out.println("overdue=" + overdue);
		System.out.println("period=" + repay);
		System.out.println("stay=" + stay);
		return obj;
	}

	@RequestMapping("checkPeriod.bank")
	@ResponseBody
	public int checkPeriod(HttpServletRequest request) {
		String period = request.getParameter("repayTerm");
		System.out.println("period=" + period);
		LoanAccountVO select = loanService.loanData(period);
		int p = select.getTerm();
		System.out.println(p);
		return p;
	}

	@RequestMapping(value = "loanSecureCardCheck.bank", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject loanSecureCardCheck(String f, String s, String dlf,
			String dl, String tka, String tk, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession(false);
		CustomerVO cvo = (CustomerVO) session.getAttribute("loginInfo");
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

	@RequestMapping("loansuccess.bank")
	public String loanSuccess(HttpServletRequest request, LoanVO vo) {
		HttpSession session = request.getSession(false);
		CustomerVO cvo = (CustomerVO) session.getAttribute("loginInfo");
		AccountTypeVO avo = new AccountTypeVO();
		avo.setAccountName(vo.getAccountName());
		LoanVO lvo = loanService.loanSuccess(cvo, vo, avo);// 대출계좌 생성되면서 잔액에 원금이
															// 들어감
		accountService.deposit(new AccountVO(lvo.getInAccountNo(), lvo
				.getBalance()));// 입금계좌에 대출금액들어가규
		// scheduledService.inputValue(vo.getLoanAccountNo());
		// scheduledService.withdraw(new
		// LoanVO(vo.getLoanAccountNo(),vo.getOutAccountNo()));
		return ("redirect:loanredirect.bank?accountNo=" + lvo
				.getLoanAccountNo());
	}

	@RequestMapping("loanredirect.bank")
	public ModelAndView loanResult(String accountNo) {
		LoanVO lvo = loanService.selectLoan(accountNo);
		return new ModelAndView("loan_result", "lvo", lvo);
	}
	
}
