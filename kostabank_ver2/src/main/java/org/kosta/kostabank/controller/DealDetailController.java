package org.kosta.kostabank.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kosta.kostabank.model.service.DealDetailService;
import org.kosta.kostabank.model.vo.DealDetailVO;
import org.kosta.kostabank.model.vo.DealListVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DealDetailController {
	@Resource
	private DealDetailService dealDetailService;
	@RequestMapping(value="dealDetailByDate.bank", method = RequestMethod.POST)
	public ModelAndView dealDetailByDate(HttpServletRequest request){
		String accountNo = request.getParameter("accountNo");
		System.out.println(1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("accountNo", accountNo);
		mav.setViewName("deal_dealDetailByDate.bank");
		return mav;
	}

	@RequestMapping("dealDetailByDate_result.bank")
	public ModelAndView dealDetailByDate_result(String accountNo,String startDay, String endDay, String dealType,String page){
		 ModelAndView mav = new ModelAndView();
		 DealDetailVO dealDetailVO = new DealDetailVO();
		 dealDetailVO.setAccountNo(accountNo);
		 dealDetailVO.setDealType(dealType);
		 dealDetailVO.setStartDay(startDay);
		 dealDetailVO.setEndDay(endDay);
		 //int page = 1;
		 System.out.println(11);
		 
		 DealListVO dvo = dealDetailService.getDetailPaging(dealDetailVO, Integer.parseInt(page));
		 System.out.println("66");
		 mav.addObject("dvo", dvo);
		 mav.setViewName("deal_dealDetailByDate_result");
		return mav;
	}
	@RequestMapping("dealDetailByDate_result2.bank")
	public ModelAndView dealDetailByDate_result2(String gapChecked, String dealType, String accountNo,String page){
		ModelAndView mav = new ModelAndView();
		DealDetailVO dealDetailVO = new DealDetailVO();
		dealDetailVO.setAccountNo(accountNo);
		dealDetailVO.setDealType(dealType);
		System.out.println("되라아아아아아");
		DealListVO dvo = dealDetailService.getDetailByGapPaging(gapChecked, dealDetailVO, Integer.parseInt(page));
		 System.out.println("66");
		 System.out.println("dkdkdkdkdk");
		 mav.addObject("dvo", dvo);
		 mav.setViewName("deal_dealDetailByDate_result2");
		return mav;
	}
}
