package org.kosta.kostabank.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.kosta.kostabank.model.service.AccountService;
import org.kosta.kostabank.model.service.SecureCardService;
import org.kosta.kostabank.model.service.TransferService;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.SecureCardVO;
import org.kosta.kostabank.model.vo.TransferVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class TransferController {
	@Resource
	private TransferService transferService;
	@Resource
	private AccountService accountService;
	@Resource
	private SecureCardService securecardService;
	
	@RequestMapping(value="transfer_empty")
	public String empty(){
		return "transfer_result";
	}
	
	@RequestMapping(value = "transfer_result.bank")
	public String transfer(HttpServletRequest request, TransferVO tvo){
/*		int balance = transferService.checkBal(tvo);
		System.out.println(balance);
		System.out.println(tvo);*/
		return "transfer_empty";
	}
/*	@RequestMapping("transfer_checkbal.bank")
	
	public int checkBal(TransferVO tvo){
		int balance= transferService.checkBal(tvo);
		return "transfer_empty";
	}*/
	
	int cnt=1;
	@RequestMapping(value="transferSecureCardCheck.bank",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject transferSecureCardCheck(String f, String s, String dlf, String dl, String tka, String tk, HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession(false);
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
