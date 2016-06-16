package org.kosta.kostabank.controller;

import java.io.File;

import javax.annotation.Resource;

import org.kosta.kostabank.model.service.QNAService;
import org.kosta.kostabank.model.vo.QNAListVO;
import org.kosta.kostabank.model.vo.QNAVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QNAController {
	@Resource
	private QNAService qnaService;

	///////////////////////////////////////////////////////////////
	///////	title : roadQNA									///////
	/////// dec : QNA 글쓰기 페이지							///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("QNA.bank")
	public String roadQNA() {
		return "qna_insert";
	}

	///////////////////////////////////////////////////////////////
	///////	title : qnaPosting								///////
	/////// dec : QNA 글쓰기									///////
	///////////////////////////////////////////////////////////////
	@RequestMapping(value = "QNAPosting.bank", method = RequestMethod.POST)
	public String qnaPosting(QNAVO vo) {
		qnaService.qnaPosting(vo);
		return "redirect:showContent.bank?qnaNo=" + vo.getQnaNo();
	}

	///////////////////////////////////////////////////////////////
	///////	title : qnaListRoad								///////
	/////// dec : QNA 게시물 목록 페이지						///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("qnaListRoad.bank")
	public ModelAndView qnaListRoad(int page, String email) {
		QNAListVO qnaListVO = qnaService.qnaList(page, email);
		return new ModelAndView("qna_board", "lvo", qnaListVO);
	}

	///////////////////////////////////////////////////////////////
	///////	title : showContent								///////
	/////// dec : QNA 게시물 보기								///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("showContent.bank")
	public ModelAndView showContent(int qnaNo) {
		QNAVO vo = qnaService.showContent(qnaNo);
		return new ModelAndView("qna_showContent", "qvo", vo);
	}

	///////////////////////////////////////////////////////////////
	///////	title : qnaRepostingRoad						///////
	/////// dec : QNA 답글 달기 페이지로 이동					///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("qnaRePostingRoad.bank")
	public ModelAndView qnaRepostingRoad(int qnaNo, String qnaType) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("qnaNo", qnaNo);
		mv.addObject("qnaType", qnaType);
		mv.setViewName("qna_BoardRe");
		return mv;
	}

	///////////////////////////////////////////////////////////////
	///////	title : rePosting								///////
	/////// dec : QNA 답글 달기								///////
	///////////////////////////////////////////////////////////////
	@RequestMapping(value = "rePosting.bank", method = RequestMethod.POST)
	public String rePosting(QNAVO vo) {
		qnaService.rePosting(vo);
		return "redirect:showContent.bank?qnaNo=" + vo.getQnaNo();
	}

	///////////////////////////////////////////////////////////////
	///////	title : deleteRe								///////
	/////// dec : QNA 답글 삭제								///////
	///////////////////////////////////////////////////////////////	
	@RequestMapping("deleteRe.bank")
	public String deleteRe(int qnaNo, String email) {
		qnaService.deleteRe(qnaNo);
		return "redirect:qnaListRoad.bank?page=1&email=" + email;
	}
}
