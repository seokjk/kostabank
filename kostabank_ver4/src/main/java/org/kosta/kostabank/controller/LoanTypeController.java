package org.kosta.kostabank.controller;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.service.LoanTypeService;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoanTypeController {
	@Resource
	private LoanTypeService loanTypeSerive;
	
	///////////////////////////////////////////////////////////////
	///////	title : laonNameList     						///////
	/////// dec : 대출 계좌 이름 리스트							///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("laonNameList.bank")
	public ModelAndView loanNameList() {
		List<String> list = loanTypeSerive.loanNameList();
		return new ModelAndView("loan_typeShow", "nameList", list);
	}
	
	///////////////////////////////////////////////////////////////
	///////	title : loanList        						///////
	/////// dec : 대출 계좌 중 해당 대출의 정보     				///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("loanList.bank")
	@ResponseBody
	public LoanAccountVO loanList(String accountName) {
		LoanAccountVO vo = loanTypeSerive.loanList(accountName);
		return vo;
	}
}
