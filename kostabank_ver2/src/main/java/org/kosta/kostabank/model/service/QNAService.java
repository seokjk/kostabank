package org.kosta.kostabank.model.service;

import org.kosta.kostabank.model.vo.QNAListVO;
import org.kosta.kostabank.model.vo.QNAVO;

public interface QNAService {

	//목록 등록 (이메일로 자신것만 찾기위함)
	public QNAListVO qnaList(int page,String email);

	//질문 등록
	public abstract void qnaPosting(QNAVO vo);
	
	public QNAVO showContent(int qnaNo);
	
	public void rePosting(QNAVO vo);
	
	public void deleteRe(int qnaNo);

}