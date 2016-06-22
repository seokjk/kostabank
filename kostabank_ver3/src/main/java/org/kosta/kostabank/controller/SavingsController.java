package org.kosta.kostabank.controller;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.kosta.kostabank.model.service.AccountService;
import org.kosta.kostabank.model.service.SavingsService;
import org.kosta.kostabank.model.service.SecureCardService;
import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.SavingsVO;
import org.kosta.kostabank.model.vo.SecureCardVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SavingsController {
	@Resource
	private SavingsService savingsService;
	@Resource
	private AccountService accountService;
	@Resource
	private SecureCardService securecardService;
	@RequestMapping("savings.bank")
	public ModelAndView savings(String accountType){
		List<AccountTypeVO> slist = savingsService.savingsProductlist(accountType); 
		return new ModelAndView("savings_savings","slist",slist);
	}
	@RequestMapping(value = "accountNameFindAccountList.bank",method=RequestMethod.POST)
	@ResponseBody
	public AccountRatesVO accountNameFindAccountList(String accountName){
		AccountRatesVO vo = savingsService.accountNameFindAccountList(accountName);
		return vo;
	}
	// 적금 생성 전 패스워드 확인
	@RequestMapping("checkMemberPassSavings.bank")
	@ResponseBody
	public boolean passwordCheck(HttpServletRequest request, String checkPass){
		boolean flag = false;
		HttpSession session = request.getSession(false);
		CustomerVO loginInfo = (CustomerVO)session.getAttribute("loginInfo");
		if(loginInfo.getPassword().equals(checkPass)){
			flag=true;
		}
		return flag;
	}
	// 패스워드 확인 후, 일치하면 적금 생성 페이지로가는 메서드
	@RequestMapping("savingsCreate.bank")
	public ModelAndView savingsCreate(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String accountName = request.getParameter("accountName");
		mav.addObject("accountName", accountName);
		HttpSession session = request.getSession(false);
		CustomerVO loginInfo = (CustomerVO) session.getAttribute("loginInfo");
		String email = loginInfo.getEmail();
		List<AccountVO> list = accountService.accountList(email);
		List<AccountRatesVO> list2 = savingsService.accountSeqByName(accountName);
		mav.addObject("list", list);
		mav.addObject("list2",list2);
		mav.setViewName("savings_create");
		return mav;
	}
	//form에서 기간별로 금리 ajax로 띄우는 메서드
	@RequestMapping(value="getRatesByTerm.bank", method=RequestMethod.POST)
	@ResponseBody
	public double getRatesByTerm(String accountSeq){
		double result= savingsService.getRatesByTerm(Integer.parseInt(accountSeq));
		return result;
	}
	//form 완성 후, 보안카드 확인한 후 적금 생성하는 메서드(미완)
	@RequestMapping(value="creatingSavingsSecureCardCheck.bank", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject creatingSavingsSecureCardCheck(String f, String s, String dlf,
			String dl, String tka, String tk, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession(false);
		CustomerVO cvo = (CustomerVO) session.getAttribute("loginInfo");
		JSONObject obj = new JSONObject();
		if (cvo.getSecurity_card().equals("0")) {
			obj.put("address", "noexistsecurecard");
		} else{
			SecureCardVO scvo = securecardService.selectSecureCard(cvo.getSecurity_card());
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
				obj.put("address", "creating_ok");
			}else if(scvo.getSecure_card_fail()==4){
				obj.put("address", "transfernum_fail");
			}
			else {
				securecardService.secureCardFail(cvo.getSecurity_card());
				SecureCardVO scvo2 = securecardService.selectSecureCard(cvo.getSecurity_card());
				obj.put("cnt", scvo2.getSecure_card_fail());
				obj.put("address", "creating_fail");
			}
		}
		return obj;
	}
	//적금 생성 form 완성 후 적금 생성 메서드
	@RequestMapping("endingSavingsForm.bank")
	public ModelAndView creatingSavings(HttpServletRequest request,
		    String rates, String savingsPass,
			String savingsName,String automaticNo, 
			String monthlyPayment,String paybackNo, String accountSeq){		
		
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		AccountRatesVO accountRatesVO = new AccountRatesVO();
		AccountTypeVO accountTypeVO = new AccountTypeVO();
		AccountVO accountVO = new AccountVO();
		SavingsVO savingsVO = new SavingsVO();
		
		int accountPass = Integer.parseInt(savingsPass);
		accountVO.setAccountPass(accountPass);
		CustomerVO customerVO = (CustomerVO) session.getAttribute("loginInfo");
		accountVO.setCustomerVO(customerVO);
		accountTypeVO.setAccountName(savingsName);
		accountVO.setAccountTypeVO(accountTypeVO);
		 
		accountRatesVO.setAccountSeq(Integer.parseInt(accountSeq));
		accountRatesVO.setRates(Double.parseDouble(rates));
		savingsVO.setAccountRatesVO(accountRatesVO);
		savingsVO.setAccountVO(accountVO);
		savingsVO.setAutomaticNo(automaticNo);
		savingsVO.setMonthlyPayment(Integer.parseInt(monthlyPayment));
		savingsVO.setPaybackNo(paybackNo);
		savingsService.createSavings(accountVO, savingsVO);
		
		mav.setViewName("savings_createResult");
		return mav;
	}
	@RequestMapping("savingsSecure.bank")
	public ModelAndView savingsSecure(String accountSeq){
		ModelAndView mav = new ModelAndView();
		mav.addObject("savingsTerm", savingsService.getTermBySeq(Integer.parseInt(accountSeq)));
		mav.setViewName("savings_secure");
		return mav;
	}
}
