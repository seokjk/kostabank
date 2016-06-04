package org.kosta.kostabank.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kosta.kostabank.model.service.AccountService;
import org.kosta.kostabank.model.service.TransferService;
import org.kosta.kostabank.model.vo.TransferVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class TransferController {
	@Resource
	private TransferService transferService;
	@Resource
	private AccountService accountService;
	
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
	

	


	

}
