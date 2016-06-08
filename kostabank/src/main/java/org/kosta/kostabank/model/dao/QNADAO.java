package org.kosta.kostabank.model.dao;

import java.util.List;
import java.util.Map;

import org.kosta.kostabank.model.vo.QNAVO;

public interface QNADAO {

	public List<QNAVO> qnaList(Map<String,Integer> pagingConfig);

	public abstract void qnaPosting(QNAVO vo);
	
	public int totalContent();

}