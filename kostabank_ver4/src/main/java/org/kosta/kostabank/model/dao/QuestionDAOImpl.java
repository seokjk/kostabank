package org.kosta.kostabank.model.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.QuestionVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class QuestionDAOImpl implements QuestionDAO{
	
	@Resource
	private SqlSessionTemplate template;
	
	@Override
	public List<QuestionVO> questionTable(String section,String page) {
		HashMap<String, String> map= new HashMap<String,String>();
		map.put("section", section);
		map.put("page", page);
		return template.selectList("question.questionTable",map);
	}

	@Override
	public QuestionVO questionShowContents(String no) {
		return template.selectOne("question.questionShowContents",no);
	}

	@Override
	public void updatehits(String no) {
		template.update("question.updatehits",no);
	}

	@Override
	public List<QuestionVO> questionSearch(String questionsearch,String page) {
		HashMap<String, String> map= new HashMap<String,String>();
		map.put("questionsearch", questionsearch);
		map.put("page", page);
		return template.selectList("question.questionSearch",map);
	}

	@Override
	public int totalContents(String section) {
		return template.selectOne("question.totalContents",section);
	}

	@Override
	public int searchTotalContents(String questionsearch) {
		return template.selectOne("question.searchTotalContents",questionsearch);
	}
	
}
