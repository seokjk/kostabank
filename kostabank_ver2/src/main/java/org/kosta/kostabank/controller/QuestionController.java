package org.kosta.kostabank.controller;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.service.QuestionService;
import org.kosta.kostabank.model.vo.PagingBean;
import org.kosta.kostabank.model.vo.QuestionListVO;
import org.kosta.kostabank.model.vo.QuestionVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
	@Resource
	private QuestionService service;
	
	@RequestMapping("questiontable.bank")
	public ModelAndView questionTable(String section, String page){
		int total=service.totalContents(section);
		PagingBean pagingBean = new PagingBean(total,Integer.parseInt(page));
		List<QuestionVO> list = service.questionTable(section,page);
		QuestionListVO listVO = new QuestionListVO(list, pagingBean);
		return new ModelAndView("question_list","listVO",listVO);
	}
	
	@RequestMapping("questionshowcontents.bank")
	public String  questionShowContents(String no){
		QuestionVO vo = service.questionShowContents(no);
		return ("redirect:questionShowContentsNoHit.bank?no="+no);
	}
	@RequestMapping("questionShowContentsNoHit.bank")
	public ModelAndView questionShowContentsNoHit(String no){
		QuestionVO vo = service.questionShowContentsNoHit(no);
		return new ModelAndView("question_showcontents","answerVO",vo);
	}
	
	@RequestMapping("questionsearch.bank")
	public ModelAndView questionSearch(String questionsearch,String page){
		int total=service.searchTotalContents(questionsearch);
		PagingBean pagingBean = new PagingBean(total,Integer.parseInt(page));
		List<QuestionVO> list = service.questionSearch(questionsearch,page);
		QuestionListVO listVO = new QuestionListVO(list, pagingBean);
		return new ModelAndView("question_searchlist","listVO",listVO);
	}
}
