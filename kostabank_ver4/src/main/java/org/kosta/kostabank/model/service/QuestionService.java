package org.kosta.kostabank.model.service;

import java.util.List;

import org.kosta.kostabank.model.vo.QuestionVO;

public interface QuestionService {
	public List<QuestionVO> questionTable(String section,String page);

	public QuestionVO questionShowContents(String no);

	public QuestionVO questionShowContentsNoHit(String no);

	public List<QuestionVO> questionSearch(String questionsearch, String page);

	public int totalContents(String section);

	public int searchTotalContents(String questionsearch);
	
}
