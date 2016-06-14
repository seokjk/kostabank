package org.kosta.kostabank.controller;

import java.io.File;
import java.io.IOException;

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
	//업로드.
	@Resource(name="uploadPath")
	private String uploadPath;
	//QNA로 보내주는 기능
	@RequestMapping("QNA.bank")
	public String roadQNA(){
		return "qna_insert";
	}
	@RequestMapping(value="QNAPosting.bank", method=RequestMethod.POST)
	public String qnaPosting(QNAVO vo){
		vo.getCustomerVO().setEmail(vo.getCustomerVO().getEmail().trim());
		vo.setQnaFileAddress("kangbank/upload/"+vo.getUploadFile().getOriginalFilename());
		MultipartFile file = vo.getUploadFile();
		if(!file.isEmpty()){
			qnaService.qnaPosting(vo);
			File uploadFile = new File(uploadPath+file.getOriginalFilename());
			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			qnaService.qnaPosting(vo);
		}
		return "redirect:showContent.bank?qnaNo="+vo.getQnaNo();
	}
	@RequestMapping("qnaListRoad.bank")
	public ModelAndView qnaListRoad(int page,String email){
		QNAListVO qnaListVO = qnaService.qnaList(page,email);
		return new ModelAndView("qna_board","lvo",qnaListVO);
	}
	@RequestMapping("showContent.bank")
	public ModelAndView showContent(int qnaNo){
		QNAVO vo = qnaService.showContent(qnaNo);
		ModelAndView mv = new ModelAndView();
		mv.addObject("qvo", vo);
		mv.setViewName("qna_showContent");
		return mv;
	}
	@RequestMapping("qnaRePostingRoad.bank")
	public ModelAndView qnaRepostingRoad(int qnaNo,String qnaType){
		ModelAndView mv = new ModelAndView();
		mv.addObject("qnaNo", qnaNo);
		mv.addObject("qnaType", qnaType);
		mv.setViewName("qna_BoardRe");
		return mv;
	}
	@RequestMapping(value="rePosting.bank",method=RequestMethod.POST)
	public String rePosting(QNAVO vo){
		qnaService.rePosting(vo);
		return "redirect:showContent.bank?qnaNo="+vo.getQnaNo();
	}
}
