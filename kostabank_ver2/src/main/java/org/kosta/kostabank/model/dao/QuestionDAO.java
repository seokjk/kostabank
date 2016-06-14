package org.kosta.kostabank.model.dao;

import java.util.List;

import org.kosta.kostabank.model.vo.QuestionVO;

public interface QuestionDAO {
	public List<QuestionVO> questionTable(String section, String page);

	public QuestionVO questionShowContents(String no);

	public void updatehits(String no);

	public List<QuestionVO> questionSearch(String questionsearch, String page);

	public int totalContents(String section);

	public int searchTotalContents(String questionsearch);
}
