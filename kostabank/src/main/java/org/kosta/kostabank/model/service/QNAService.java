package org.kosta.kostabank.model.service;

import org.kosta.kostabank.model.vo.QNAListVO;
import org.kosta.kostabank.model.vo.QNAVO;

public interface QNAService {

	//목록 등록 (이메일로 자신것만 찾기위함)
	public abstract QNAListVO qnaList(int page);

	//질문 등록
	public abstract void qnaPosting(QNAVO vo);

}