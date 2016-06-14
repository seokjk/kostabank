package org.kosta.kostabank.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.QuestionDAO;
import org.kosta.kostabank.model.vo.QuestionVO;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Resource
	private QuestionDAO dao;
	
	public List<QuestionVO> questionTable(String section,String page){
		return dao.questionTable(section,page);
	}

	@Override
	public QuestionVO questionShowContents(String no) {
		dao.updatehits(no);
		return dao.questionShowContents(no);
	}
	
	public QuestionVO questionShowContentsNoHit(String no){
		return dao.questionShowContents(no);
	}

	@Override
	public List<QuestionVO> questionSearch(String questionsearch,String page) {
		return dao.questionSearch(questionsearch,page);
	}

	@Override
	public int totalContents(String section) {
		return dao.totalContents(section);
	}

	@Override
	public int searchTotalContents(String questionsearch) {
		return dao.searchTotalContents(questionsearch);
	}
}
