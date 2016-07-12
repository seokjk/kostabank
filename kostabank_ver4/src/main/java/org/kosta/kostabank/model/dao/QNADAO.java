package org.kosta.kostabank.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kosta.kostabank.model.vo.QNAVO;

public interface QNADAO {

	public List<QNAVO> qnaList(Map<String,String> pagingConfig);

	public abstract void qnaPosting(QNAVO vo);
	
	public int totalContent(String email);
	
	public QNAVO showContent(int qnaNo);
	
	public void rePosting(QNAVO vo);
	
	public void deleteRe(int qnaNo);

	public List<QNAVO> qnaNoList(HashMap<String, String> paramMap);

	public int NototalContent();

	List<QNAVO> tempList(HashMap<String, String> pagingConfig);

}