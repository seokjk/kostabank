package org.kosta.kostabank.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.kosta.kostabank.model.service.QNAService;
import org.kosta.kostabank.model.vo.ListVO;
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
		return "qna_create";
	}
	@RequestMapping(value="QNAPosting.bank", method=RequestMethod.POST)
	public String qnaPosting(QNAVO vo){
		System.out.println(vo.getUploadFile().getOriginalFilename());
		vo.setQnaFileAddress("kangbank/upload/"+vo.getUploadFile().getOriginalFilename());
		MultipartFile file = vo.getUploadFile();
		if(file.isEmpty()==false){
			System.out.println(vo);
			qnaService.qnaPosting(vo);
			File uploadFile = new File(uploadPath+file.getOriginalFilename());
			System.out.println("경로: "+uploadPath);
			System.out.println(uploadFile);
			try {
				file.transferTo(uploadFile);
				System.out.println(uploadPath+"에 파일 업로드");
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "redirect:showContent.bank?qnaFileAddress="+file.getOriginalFilename()+"&qnaNo="+vo.getQnaNo();
	}
	@RequestMapping("qnaListRoad.bank")
	public ModelAndView qnaListRoad(int page){
		QNAListVO qnaListVO = qnaService.qnaList(page);
		return new ModelAndView("qna_list","lvo",qnaListVO);
	}
	@RequestMapping("showContent.bank")
	public ModelAndView showContent(int qnaNo,String qnaFileAddress){
		System.out.println(qnaNo);
		QNAVO vo = qnaService.showContent(qnaNo);
		System.out.println(vo.getQnaFileAddress());
		ModelAndView mv = new ModelAndView();
		mv.addObject("qvo", vo);
		mv.addObject("fileName",qnaFileAddress);
		System.out.println(qnaFileAddress);
		mv.setViewName("qna_showContent");
		return mv;
	}
}
