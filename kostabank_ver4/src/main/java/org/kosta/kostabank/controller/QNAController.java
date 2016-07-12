package org.kosta.kostabank.controller;

import javax.annotation.Resource;

import org.kosta.kostabank.model.service.QNAService;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.QNAListVO;
import org.kosta.kostabank.model.vo.QNAVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QNAController {
   @Resource
   private QNAService qnaService;

   ///////////////////////////////////////////////////////////////
   ///////   title : roadQNA                           ///////
   /////// dec : QNA 글쓰기 페이지                     ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("QNA.bank")
   public String roadQNA() {
      return "qna_insert";
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : qnaPosting                        ///////
   /////// dec : QNA 글쓰기                           ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping(value = "QNAPosting.bank", method = RequestMethod.POST)
   public String qnaPosting(QNAVO vo) {
      qnaService.qnaPosting(vo);
      return "redirect:showContent.bank?qnaNo=" + vo.getQnaNo();
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : qnaListRoad                        ///////
   /////// dec : QNA 게시물 목록 페이지                  ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("qnaListRoad.bank")
   public ModelAndView qnaListRoad(int page, String email) {
      QNAListVO qnaListVO = qnaService.qnaList(page, email);
      return new ModelAndView("qna_board", "lvo", qnaListVO);
   }
   
	///////////////////////////////////////////////////////////////
	///////   title : adminQnaListRoad                      ///////
	/////// dec : QNA 게시물 목록 페이지            		        ///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("adminQnaListRoad.bank")
		public ModelAndView adminQnaListRoad(int page, String email) {
		QNAListVO qnaListVO = qnaService.qnaList(page, email);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("kangbank_admin/popup/qna_board");
		mv.addObject("lvo", qnaListVO);
		mv.addObject("email", email);
		return mv;
	}
	
	///////////////////////////////////////////////////////////////
	///////   title : adminQnaNoListRoad                      ///////
	/////// dec : QNA 게시물 목록 페이지            		        ///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("adminQnaNoListRoad.bank")
		public ModelAndView adminQnaNoListRoad(int page, String email) {
		QNAListVO qnaListVO = qnaService.qnaNoList(page);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("kangbank_admin/popup/qna_board");
		mv.addObject("lvo", qnaListVO);
		mv.addObject("email", email);
		return mv;
	}

   ///////////////////////////////////////////////////////////////
   ///////   title : showContent                        ///////
   /////// dec : QNA 게시물 보기                        ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("showContent.bank")
   public ModelAndView showContent(int qnaNo) {
	  
      QNAVO vo = qnaService.showContent(qnaNo);
      return new ModelAndView("qna_showContent", "qvo", vo);
   }
   
   ///////////////////////////////////////////////////////////////
   ///////   title : adminShowContent                        ///////
   /////// dec : QNA 게시물 보기                        ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("adminShowContent.bank")
   public ModelAndView adminShowContent(int qnaNo, String email) {
      QNAVO vo = qnaService.showContent(qnaNo);
      ModelAndView mv = new ModelAndView();
      mv.setViewName("kangbank_admin/popup/qna_showContent");
      mv.addObject("qvo", vo);
      mv.addObject("email", email);
      return mv;
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : qnaRePostingRoad                  ///////
   /////// dec : QNA 답글 달기 페이지로 이동               ///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping("qnaRePostingRoad.bank")
   public ModelAndView qnaRePostingRoad(int qnaNo, String qnaType) {
      ModelAndView mv = new ModelAndView();
      QNAVO vo = qnaService.showContent(qnaNo);
      mv.addObject("qvo", vo);
      mv.addObject("qnaType", qnaType);
      mv.setViewName("kangbank_admin/popup/qna_BoardRe");
      return mv;
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : rePosting                       	 ///////
   /////// dec : QNA 답글 달기                        				///////
   ///////////////////////////////////////////////////////////////
   @RequestMapping(value = "rePosting.bank", method = RequestMethod.POST)
   @ResponseBody
   public int rePosting(QNAVO vo, String email, String tel) {
	  vo.setCustomerVO(new CustomerVO(email, null, null, null, tel, null, null));
      qnaService.rePosting(vo);
      return vo.getQnaNo();
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : deleteRe                        ///////
   /////// dec : QNA 답글 삭제                        ///////
   ///////////////////////////////////////////////////////////////   
   @RequestMapping("deleteRe.bank")
   public String deleteRe(int qnaNo, String email) {
      qnaService.deleteRe(qnaNo);
      return "redirect:qnaListRoad.bank?page=1&email=" + email;
   }

   ///////////////////////////////////////////////////////////////
   ///////   title : adminDeleteRe                       	 ///////
   /////// dec : QNA 답글 삭제                        				///////
   ///////////////////////////////////////////////////////////////   
   @RequestMapping("adminDeleteRe.bank")
   @ResponseBody
   public String adminDeleteRe(int qnaNo, String email) {
	   System.out.println("123");
      qnaService.deleteRe(qnaNo);
      return "kangbank_admin/popup/qna_showContent";
   }
}