package org.kosta.kostabank.controller;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.service.LoanService;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoanController {
	@Resource
	private LoanService loanSerive;
	
	//대출 계좌 이름 리스트
	@RequestMapping("laonNameList.bank")
	public ModelAndView loanNameList() {
		List<String> list = loanSerive.loanNameList();
		return new ModelAndView("loan_typeShow", "nameList", list);
	}
	//대출 계좌 전체 리스트
	@RequestMapping("loanList.bank")
	@ResponseBody
	public LoanAccountVO loanList(String accountName) {
		LoanAccountVO vo = loanSerive.loanList(accountName);
		return vo;
	}
}
