package org.kosta.kostabank.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.kosta.kostabank.model.service.AccountService;
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
	
	@RequestMapping("transfer_view.bank")
	public ModelAndView transferview(String email, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		CustomerVO vo = (CustomerVO) session.getAttribute("loginInfo");
		List<AccountVO> list = accountService.accountList(vo.getEmail());
		return new ModelAndView("transfer_view","accountList",list);
	}
	
	@RequestMapping("transfer_transfer.bank")
	public ModelAndView transfer_transfer(HttpServletRequest request,TransferVO tvo){
		HttpSession session = request.getSession(false);
		session.setAttribute("tvo", tvo);
		System.out.println(tvo);
		AccountVO  s = accountService.accountAll(tvo.getOtheraccountNo());
		System.out.println("다른계좌="+s);
		CustomerVO c = s.getCustomerVO();
		System.out.println("인간="+c);
		
		
		
		return new ModelAndView("transfer_secure","tvo",tvo);
	}
	@RequestMapping("transfer_secure.bank")
	public String transfer_secure(){
		
		return "transfer_result";
	}
		
	@RequestMapping("checkBalance.bank")
	@ResponseBody
	public int checkBal(HttpServletRequest request, String myaccountNo){
		HttpSession session = request.getSession(false);
		CustomerVO vo = (CustomerVO) session.getAttribute("loginInfo");
		List<AccountVO> list = accountService.accountList(vo.getEmail());
		myaccountNo = request.getParameter("account"); // 선택한 계좌
		System.out.println("선택한계좌="+myaccountNo);
		AccountVO  s = accountService.accountAll(myaccountNo);
		System.out.println("계좌="+s);
		int balance = s.getBalance();
		System.out.println("잔액="+balance);
		return balance; 
	}
	
	@RequestMapping("checkPassword.bank")
	@ResponseBody
	public boolean checkPassword(AccountVO avo){
		AccountVO vo = accountService.checkAccount(avo);
		boolean flag=true;
		if(vo==null){
			flag=false;
		}
		return flag;
	}
	
	int cnt=1;
	@RequestMapping(value="transferSecureCardCheck.bank",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject transferSecureCardCheck(String f, String s, String dlf, String dl, String tka, String tk, HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession(false);
		System.out.println(" zz");
		CustomerVO cvo = (CustomerVO) session.getAttribute("loginInfo");
		JSONObject obj=new JSONObject();
		System.out.println("");
		if(cvo.getSecurity_card().equals("0")){
			obj.put("address","noexistsecurecard");
		}else{
			SecureCardVO scvo = securecardService.selectSecureCard(cvo.getSecurity_card());
			String[] array ={scvo.getOne(),scvo.getTwo(),scvo.getThree(),scvo.getFour(),scvo.getFive(),scvo.getSix(),scvo.getSeven(),scvo.getEight(),scvo.getNine(),scvo.getTen(),scvo.getEleven(),scvo.getTwelve(),scvo.getThirteen(),scvo.getFourteen(),scvo.getFifteen(),scvo.getSixteen(),scvo.getSeventeen(),scvo.getEighteen(),scvo.getNineteen(),scvo.getTwenty(),scvo.getTwenty_one(),scvo.getTwenty_three(),scvo.getTwenty_three(),scvo.getTwenty_four(),scvo.getTwenty_five(),scvo.getTwenty_six(),scvo.getTwenty_seven(),scvo.getTwenty_eight(),scvo.getTwenty_nine(),scvo.getThirty()};
			String first_check = array[Integer.parseInt(f)-1];
			String second_check = array[Integer.parseInt(s)-1];
			if(first_check.substring(0,2).equals(dlf+dl) && second_check.substring(2,4).equals(tka+tk)){
				obj.put("address","transfer_ok");
			}else if(cnt==5){
				obj.put("address","transfernum_fail");
				cnt=1;
			}else{
				obj.put("cnt", cnt++);
				obj.put("address", "transfer_fail");
			}
		}
		return obj;
	}


	

}
