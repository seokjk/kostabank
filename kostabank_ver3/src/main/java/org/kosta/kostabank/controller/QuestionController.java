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
	
	///////////////////////////////////////////////////////////////
	///////	title : questionTable							///////
	/////// dec : 자주 묻는 게시판 보기							///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("questiontable.bank")
	public ModelAndView questionTable(String section, String page){
		int total=service.totalContents(section);
		PagingBean pagingBean = new PagingBean(total,Integer.parseInt(page));
		List<QuestionVO> list = service.questionTable(section,page);
		QuestionListVO listVO = new QuestionListVO(list, pagingBean);
		return new ModelAndView("question_list","listVO",listVO);
	}
	
	///////////////////////////////////////////////////////////////
	///////	title : questionShowContents					///////
	/////// dec : 자주 묻는 게시판 글 보기(조회수 증가o)			///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("questionshowcontents.bank")
	public String  questionShowContents(String no){
		service.questionShowContents(no);
		return ("redirect:questionShowContentsNoHit.bank?no="+no);
	}
	
	///////////////////////////////////////////////////////////////
	///////	title : questionShowContentsNoHit				///////
	/////// dec : 자주 묻는 게시판 글 보기(조회수 증가x)			///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("questionShowContentsNoHit.bank")
	public ModelAndView questionShowContentsNoHit(String no){
		QuestionVO vo = service.questionShowContentsNoHit(no);
		return new ModelAndView("question_showcontents","answerVO",vo);
	}
	
	///////////////////////////////////////////////////////////////
	///////	title : questionTable							///////
	/////// dec : 자주 묻는 게시판 보기(검색)					///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("questionsearch.bank")
	public ModelAndView questionSearch(String questionsearch,String page){
		int total=service.searchTotalContents(questionsearch);
		PagingBean pagingBean = new PagingBean(total,Integer.parseInt(page));
		List<QuestionVO> list = service.questionSearch(questionsearch,page);
		QuestionListVO listVO = new QuestionListVO(list, pagingBean);
		return new ModelAndView("question_searchlist","listVO",listVO);
	}
}
