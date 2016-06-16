package org.kosta.kostabank.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.kosta.kostabank.model.service.AccountService;
import org.kosta.kostabank.model.service.DealDetailService;
import org.kosta.kostabank.model.service.SecureCardService;
import org.kosta.kostabank.model.service.TransferService;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.DealDetailVO;
import org.kosta.kostabank.model.vo.SecureCardVO;
import org.kosta.kostabank.model.vo.TransferVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class TransferController {
	@Resource
	private TransferService transferService;
	@Resource
	private AccountService accountService;
	@Resource
	private SecureCardService securecardService;
	@Resource
	private DealDetailService dealDetailService;
	
	@RequestMapping("transfer_view.bank")
	public ModelAndView transferview(String email, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		CustomerVO vo = (CustomerVO) session.getAttribute("loginInfo");
		List<AccountVO> list = accountService.accountList(vo.getEmail());
		return new ModelAndView("transfer_view", "accountList", list);
	}

	@RequestMapping("transfer_transfer.bank")
	public ModelAndView transfer_transfer(HttpServletRequest request,TransferVO tvo) {
		HttpSession session = request.getSession(false);
		ModelAndView mv = new ModelAndView();
		session.setAttribute("tvo", tvo);
		System.out.println(tvo);
		String no = tvo.getOtheraccountNo();
		System.out.println("no="+no);
		AccountVO a = accountService.checkOtherAccount(no);

		System.out.println("다른계좌TR=" + a);
		String name=a.getCustomerVO().getName();
		System.out.println("받는사람: "+name);
		mv.addObject("name", name);
		mv.addObject("tvo",tvo);
		mv.setViewName("redirect:transfer_secure.bank");

		return mv;
	}
	//이체결과를 보여주는 곳(실제 수행)
	@RequestMapping("transfer_ing.bank")
	public ModelAndView transfer_result(HttpServletRequest request,TransferVO tvo, AccountVO avo) {
		HttpSession session = request.getSession(false);
		ModelAndView mv = new ModelAndView();
		
		CustomerVO vo = (CustomerVO) session.getAttribute("loginInfo");
		
		TransferVO tvo2 = (TransferVO) session.getAttribute("tvo");
		AccountVO dv = accountService.accountAll(tvo2.getaccount());
		System.out.println("출금계좌정보="+dv);
		long m = dv.getBalance();
		System.out.println("출금계좌의 잔액="+m); //여기까지나옴
		// 임시 출금쪽 temp 를 만들어줘서 수행
		AccountVO temp = new AccountVO(tvo2.getaccount(),tvo2.getMoney());
		accountService.withdraw(temp);
		AccountVO myavo = accountService.checkOtherAccount(tvo2.getaccount());
		System.out.println("출금계좌에서 이체후 잔액="+myavo.getBalance());
		
		DealDetailVO ddvo = new DealDetailVO();
		ddvo.setAccountNo(tvo2.getaccount()); //내계좌
		ddvo.setOtherAccountNo(tvo2.getOtheraccountNo());//상대계좌
		ddvo.setDealType("withdraw");//입금인지 출금인지
		ddvo.setAmountOfMoney(tvo2.getMoney());//이체금액
		System.out.println("ddvo="+ddvo);
		dealDetailService.insertTransDetail(ddvo);
		
		
		//임시 입금쪽 temp
		AccountVO temp2 = new AccountVO(tvo2.getOtheraccountNo(),tvo2.getMoney());
		accountService.deposit(temp2);
		AccountVO youavo = accountService.checkOtherAccount(tvo2.getOtheraccountNo());
		System.out.println("입금계좌에서 이체후 잔액="+youavo.getBalance());
		
		DealDetailVO ddvo2 = new DealDetailVO();
		
		ddvo2.setAccountNo(tvo2.getOtheraccountNo()); //내계좌
		ddvo2.setOtherAccountNo(tvo2.getaccount());//상대계좌
		ddvo2.setDealType("deposit");//입금인지 출금인지
		ddvo2.setAmountOfMoney(tvo2.getMoney());//이체금액
		System.out.println("ddvo2="+ddvo2);
		dealDetailService.insertTransDetail(ddvo2);
		
		mv.addObject("afterMoney",myavo.getBalance());
		mv.addObject("youName",youavo.getCustomerVO().getName());
		mv.setViewName("redirect:transfer_result.bank");
		return mv;
	}
 
	@RequestMapping("checkBalance.bank")
	@ResponseBody
	public long checkBal(HttpServletRequest request, String myaccountNo) {
		HttpSession session = request.getSession(false);
		CustomerVO vo = (CustomerVO) session.getAttribute("loginInfo");
		List<AccountVO> list = accountService.accountList(vo.getEmail());
		myaccountNo = request.getParameter("account"); // 선택한 계좌
		System.out.println("선택한계좌=" + myaccountNo);
		AccountVO s = accountService.accountAll(myaccountNo);
		System.out.println("내 계좌=" + s);
		long balance = s.getBalance();
		System.out.println("잔액=" + balance);
		return balance;
	}

	@RequestMapping("checkOtherAccount.bank")
	@ResponseBody
	public String checkOtherAccount(HttpServletRequest request, AccountVO avo) {
		HttpSession session = request.getSession(false);

		AccountVO a = accountService.checkOtherAccount(request.getParameter("otheraccountNo"));
		System.out.println("다른계좌=" + a);
		String name=null;
		if (a != null &&a.getCustomerVO().getName() != null) {
			name = a.getCustomerVO().getName();
		}
		return name;
	}

	
	@RequestMapping("checkPassword.bank")
	@ResponseBody
	public boolean checkPassword(AccountVO avo) {
		System.out.println("ck");
		AccountVO vo = accountService.checkAccount(avo);
		boolean flag = true;
		if (vo == null) {
			flag = false;
		}
		return flag;
	}

	@RequestMapping(value = "transferSecureCardCheck.bank", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject transferSecureCardCheck(String f, String s, String dlf,
			String dl, String tka, String tk, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession(false);
		CustomerVO cvo = (CustomerVO) session.getAttribute("loginInfo");
		JSONObject obj = new JSONObject();
		if (cvo.getSecurity_card().equals("0")) {
			obj.put("address", "noexistsecurecard");
		} else {
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
				obj.put("address", "transfer_ok");
			}else if(scvo.getSecure_card_fail()==4){
				obj.put("address", "transfernum_fail");
			}
			else {
				securecardService.secureCardFail(cvo.getSecurity_card());
				SecureCardVO scvo2 = securecardService.selectSecureCard(cvo.getSecurity_card());
				obj.put("cnt", scvo2.getSecure_card_fail());
				obj.put("address", "transfer_fail");
			}
		}
		return obj;
	}
	


}