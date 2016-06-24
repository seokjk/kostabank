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
	
	///////////////////////////////////////////////////////////////
	///////	title : transferview						    ///////
	/////// dec : 계좌이체 페이지로 이동							///////
	///////////////////////////////////////////////////////////////	
	@RequestMapping("transfer_view.bank")
	public ModelAndView transferview(HttpServletRequest request,String depositAccount,String withdrawAccount) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginInfo") == null) {
			mav.setViewName("redirect:kangbank/templates/needLogin.jsp");
			//return new ModelAndView("redirect:kangbank/templates/needLogin.jsp");
		}
		List<AccountVO> list = accountService.accountList(((CustomerVO) session.getAttribute("loginInfo")).getEmail());
		mav.setViewName("transfer_view");
		mav.addObject("accountList",list);
		mav.addObject("depositAccount", depositAccount);
		mav.addObject("withdrawAccount",withdrawAccount);
		//return new ModelAndView("transfer_view", "accountList", list);
		return mav;
	}

	///////////////////////////////////////////////////////////////
	///////	title : transfer_transfer						///////
	/////// dec : 계좌이체 확인 페이지로 이동						///////
	///////////////////////////////////////////////////////////////	
	@RequestMapping("transfer_transfer.bank")
	public ModelAndView transfer_transfer(HttpServletRequest request, TransferVO tvo) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginInfo") == null) {
			return new ModelAndView("redirect:kangbank/templates/needLogin.jsp");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", accountService.checkOtherAccountName(tvo.getOtheraccountNo()));
		mv.addObject("tvo",tvo);
		mv.setViewName("transfer_secure");
		return mv;
	}
	
	///////////////////////////////////////////////////////////////
	///////	title : transfer_result							///////
	/////// dec : 이체 수행 후 결과페이지로 이동					///////
	///////////////////////////////////////////////////////////////	
	@RequestMapping("transfer_ing.bank")
	public ModelAndView transfer_result(HttpServletRequest request,TransferVO tvo) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginInfo") == null) {
			return new ModelAndView("redirect:kangbank/templates/needLogin.jsp");
		}
		ModelAndView mv = new ModelAndView();
		AccountVO vo = dealDetailService.insertTrans(tvo);
		mv.addObject("afterMoney",vo.getBalance());
		mv.addObject("youName",vo.getCustomerVO().getName());
		mv.addObject("tvo", tvo);
		mv.setViewName("transfer_result");
		return mv;
	}
 
	///////////////////////////////////////////////////////////////
	///////	title : checkBal								///////
	/////// dec : 선택한 계좌의 잔액 확인						    ///////
	///////////////////////////////////////////////////////////////	
	@RequestMapping("checkBalance.bank")
	@ResponseBody
	public long checkBal(HttpServletRequest request, String account) {
		return accountService.accountAll(account).getBalance();
	}

	///////////////////////////////////////////////////////////////
	///////	title : checkOtherAccount						///////
	/////// dec : 상대 계좌 존재 확인							///////
	///////////////////////////////////////////////////////////////	
	@RequestMapping("checkOtherAccount.bank")
	@ResponseBody
	public String checkOtherAccount(HttpServletRequest request, String otheraccountNo) {
		AccountVO vo = accountService.accountAll(otheraccountNo);
		String name=null;
		if (vo != null) {
			name = vo.getCustomerVO().getName();
		}
		return name;
	}

	///////////////////////////////////////////////////////////////
	///////	title : checkPassword							///////
	/////// dec : 계좌비밀번호 일치 확인							///////
	///////////////////////////////////////////////////////////////	
	@RequestMapping("checkPassword.bank")
	@ResponseBody
	public boolean checkPassword(AccountVO avo) {
		return accountService.checkAccount(avo);
	}
	
	///////////////////////////////////////////////////////////////
	///////	title : recentAccountNo							///////
	/////// dec : 최근입금계좌 리스트 출력						///////
	///////////////////////////////////////////////////////////////	
	@RequestMapping("recentAccountNo.bank")
	public ModelAndView recentAccountNo(String accountNo) {
		List<TransferVO> list = transferService.recentAccountNo(accountNo);
		System.out.println(list);
		return new ModelAndView("kangbank/transfer/transferBestList","blist", list);
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