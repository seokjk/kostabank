package org.kosta.kostabank.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kosta.kostabank.model.service.DealDetailService;
import org.kosta.kostabank.model.vo.DealDetailVO;
import org.kosta.kostabank.model.vo.DealListVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
/*	@RequestMapping(value="dealDetailByDate_result.bank", method = RequestMethod.POST)
	@ResponseBody
		public Object dealDetailByDate_result(DealDetailVO dealDetailVO){
    List<DealDetailVO> list = dealDetailService.getDetail(dealDetailVO);
		System.out.println(list);
		return dealDetailService.getDetail(dealDetailVO);
	}
	@RequestMapping(value="dealDetailByDate_result2.bank", method = RequestMethod.POST)
	@ResponseBody
	public Object dealDetailByDate_result2(String gapChecked, DealDetailVO dealDetailVO){
		System.out.println(1);
		List<DealDetailVO> list = dealDetailService.getDetailByGap(gapChecked);
		DealDetailVO dvo = new DealDetailVO();
		return dealDetailService.getDetailByGap(gapChecked, dealDetailVO);
	}*/
/*	@RequestMapping(value="dealDetailByDate_result.bank", method = RequestMethod.POST)
	@ResponseBody
		public Object dealDetailByDate_result(DealDetailVO dealDetailVO){
    List<DealDetailVO> list = dealDetailService.getDetail(dealDetailVO);
		System.out.println(list);
		System.out.println(211111);
		int page = 1;
		//System.out.println("page" + page);
		System.out.println("detailVO"+dealDetailVO);
		DealListVO dvo = dealDetailService.getDetail(dealDetailVO,page);
		System.out.println(dvo);
		return dealDetailService.getDetail(dealDetailVO,page);
	}*/
/*	@RequestMapping(value="dealDetailByDate_result2.bank", method = RequestMethod.POST)
	@ResponseBody
	public Object dealDetailByDate_result2(String gapChecked, DealDetailVO dealDetailVO){
		System.out.println(1);
		List<DealDetailVO> list = dealDetailService.getDetailByGap(gapChecked);
		DealDetailVO dvo = new DealDetailVO();
		return dealDetailService.getDetailByGap(gapChecked, dealDetailVO);
	}*/
/*	@RequestMapping("dealDetailByDate_result.bank")
	public ModelAndView dealDetailByDate_result(String accountNo,String startDay, String endDay, String dealType){
		 ModelAndView mav = new ModelAndView();
		 DealDetailVO dealDetailVO = new DealDetailVO();
		 dealDetailVO.setAccountNo(accountNo);
		 dealDetailVO.setDealType(dealType);
		 dealDetailVO.setStartDay(startDay);
		 dealDetailVO.setEndDay(endDay);
		 System.out.println(11);
		 List<DealDetailVO> list = dealDetailService.getDetail(dealDetailVO);
		 System.out.println(list);
		 System.out.println("66");
		 mav.addObject("list", list);
		 mav.setViewName("deal_dealDetailByDate_result");
		return mav;
	}*/
	@RequestMapping("dealDetailByDate_result.bank")
	public ModelAndView dealDetailByDate_result(String accountNo,String startDay, String endDay, String dealType){
		 ModelAndView mav = new ModelAndView();
		 DealDetailVO dealDetailVO = new DealDetailVO();
		 dealDetailVO.setAccountNo(accountNo);
		 dealDetailVO.setDealType(dealType);
		 dealDetailVO.setStartDay(startDay);
		 dealDetailVO.setEndDay(endDay);
		 int page = 1;
		 System.out.println(11);
		 DealListVO dvo = dealDetailService.getDetailPaging(dealDetailVO, page);
		 System.out.println("66");
		 mav.addObject("dvo", dvo);
		 mav.setViewName("deal_dealDetailByDate_result");
		return mav;
	}
	@RequestMapping("dealDetailByDate_result2.bank")
	public ModelAndView dealDetailByDate_result2(String gapChecked, String dealType, String accountNo){
		ModelAndView mav = new ModelAndView();
		DealDetailVO dealDetailVO = new DealDetailVO();
		dealDetailVO.setAccountNo(accountNo);
		dealDetailVO.setDealType(dealType);
		int page=1;
		DealListVO dvo = dealDetailService.getDetailByGapPaging(gapChecked, dealDetailVO, page);
		 System.out.println("66");
		 mav.addObject("dvo", dvo);
		 mav.setViewName("deal_dealDetailByDate_result2");
		return mav;
	}
}
