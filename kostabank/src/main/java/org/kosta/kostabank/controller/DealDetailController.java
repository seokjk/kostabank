package org.kosta.kostabank.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kosta.kostabank.model.service.DealDetailService;
import org.kosta.kostabank.model.vo.DealDetailVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DealDetailController {
	@Resource
	private DealDetailService dealDetailService;
/*@RequestMapping("checking_dealDetailByAccountNo_result.bank")
	public ModelAndView dealDetailByDate(String accountNo){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("checking_dealDetailByAccountNo_result");
		mav.addObject("accountNo",accountNo);
		return mav;
	}*/
	//
	@RequestMapping(value="dealDetailByDate.bank", method = RequestMethod.POST)
	public ModelAndView dealDetailByDate(HttpServletRequest request){
		String accountNo = request.getParameter("accountNo");
		System.out.println(1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("accountNo", accountNo);
		mav.setViewName("deal_dealDetailByDate.bank");
		return mav;
	}
	@RequestMapping(value="dealDetailByDate_result.bank", method = RequestMethod.POST)
	@ResponseBody
	public Object dealDetailByDate_result(String startDay, String endDay,String accountNo){
		DealDetailVO dvo = new DealDetailVO();
		dvo.setStartDay(startDay);
		dvo.setEndDay(endDay);
		dvo.setAccountNo(accountNo);
		System.out.println(startDay);
		List<DealDetailVO> list = dealDetailService.getDetail(dvo);
		System.out.println(endDay);
		/*ModelAndView mav = new ModelAndView();
		mav.addObject("list",list);
		mav.setViewName("deal_dealDetailByDate_result");*/
		System.out.println(list);
		return dealDetailService.getDetail(dvo);
	}
	@RequestMapping(value="dealDetailByDate_result2.bank", method = RequestMethod.POST)
	@ResponseBody
	public Object dealDetailByDate_result2(String gapChecked, String accountNo){
		System.out.println(1);
		/*List<DealDetailVO> list = dealDetailService.getDetailByGap(gapChecked);
		DealDetailVO dvo = new DealDetailVO();*/
		return dealDetailService.getDetailByGap(gapChecked, accountNo);
	}
}
