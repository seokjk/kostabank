package org.kosta.kostabank.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

   ///////////////////////////////////////////////////////////////////////
   ///////   title : dealDetailByDate                              //////
   /////// dec : 선택한 계좌의 상세내역 보기   페이지로 이동                    //////
   /////////////////////////////////////////////////////////////////////
   @RequestMapping(value="dealDetailByDate.bank", method = RequestMethod.POST)
   public ModelAndView dealDetailByDate(String accountNo, HttpServletRequest request){
      HttpSession session = request.getSession(false);
      if (session == null || session.getAttribute("loginInfo") == null) {
         return new ModelAndView("redirect:kangbank/templates/needLogin.jsp");
      }
      return new ModelAndView("deal_dealDetailByDate.bank", "accountNo", accountNo);
   }
   //////////////////////////////////////////////////////////////////////
   ///////   title : adminDealDetailByDate                     ///////
   /////// dec : 선택한 계좌의 상세내역 보기   페이지로 이동      ///////
   //////////////////////////////////////////////////////////////////////
   @RequestMapping("adminDealDetailByDate.bank")
   public ModelAndView adminDealDetailByDate(String accountNo){
      System.out.println("adminDeal");
      return new ModelAndView("kangbank_admin/popup/dealDetailByDate", "accountNo", accountNo);
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : dealDetailByDate_result              ///////
   /////// dec : 선택한 날짜의 상세내역 보기              ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("dealDetailByDate_result.bank")
   public ModelAndView dealDetailByDate_result(DealDetailVO dealDetailVO, String page, HttpServletRequest request){
      HttpSession session = request.getSession(false);
      if (session == null || session.getAttribute("loginInfo") == null) {
         return new ModelAndView("redirect:kangbank/templates/needLogin.jsp");
      }
       DealListVO dvo = dealDetailService.getDetailPaging(dealDetailVO, Integer.parseInt(page));
      return new ModelAndView("deal_dealDetailByDate_result", "dvo", dvo);
   }
   ///////////////////////////////////////////////////////////////
   ///////   title : adminDealDetailByDate_result        ///////
   /////// dec : 선택한 날짜의 상세내역 보기              ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("adminDealDetailByDate_result.bank")
   public ModelAndView adminDealDetailByDate_result(DealDetailVO dealDetailVO, String page){
      DealListVO dvo = dealDetailService.getDetailPaging(dealDetailVO, Integer.parseInt(page));
      return new ModelAndView("kangbank_admin/popup/dealDetailByDate_result", "dvo", dvo);
   }
   ///////////////////////////////////////////////////////////////
   /////// title : dealDetailByDate_result2              ///////
   /////// dec : 선택한 날짜의 상세내역 보기              ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("dealDetailByDate_result2.bank")
   public ModelAndView dealDetailByDate_result2(String gapChecked, DealDetailVO dealDetailVO, String page, HttpServletRequest request){
      HttpSession session = request.getSession(false);
      if (session == null || session.getAttribute("loginInfo") == null) {
         return new ModelAndView("redirect:kangbank/templates/needLogin.jsp");
      }
      DealListVO dvo = dealDetailService.getDetailByGapPaging(gapChecked, dealDetailVO, Integer.parseInt(page));
       return new ModelAndView("deal_dealDetailByDate_result2", "dvo", dvo);
   }
   ///////////////////////////////////////////////////////////////
   /////// title : adminDealDetailByDate_result2        ///////
   /////// dec : 선택한 날짜의 상세내역 보기              ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("adminDealDetailByDate_result2.bank")
   public ModelAndView adminDealDetailByDate_result2(String gapChecked, DealDetailVO dealDetailVO, String page){
      DealListVO dvo = dealDetailService.getDetailByGapPaging(gapChecked, dealDetailVO, Integer.parseInt(page));
      return new ModelAndView("kangbank_admin/popup/dealDetailByDate_result2", "dvo", dvo);
   }
}