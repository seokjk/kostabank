package org.kosta.kostabank.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.QNADAO;
import org.kosta.kostabank.model.vo.PagingBean;
import org.kosta.kostabank.model.vo.QNAListVO;
import org.kosta.kostabank.model.vo.QNAVO;
import org.springframework.stereotype.Service;

@Service
public class QNAServiceImpl implements QNAService {
@Resource
private QNADAO qnaDAO;
@Resource(name="pagingConfig")
private Map<String,Integer> pagingConfig;
//목록 등록 (이메일로 자신것만 찾기위함)
/* (non-Javadoc)
 * @see org.kosta.kostabank.model.service.QNAService#qnaList(java.lang.String, int)
 */
@Override
public QNAListVO qnaList(int page,String email){
	HashMap<String, String> paramMap = new HashMap<String, String>();
	String numOfCont = String.valueOf(pagingConfig.get("numberOfContent"));
	System.out.println(numOfCont);
	String pageValue = String.valueOf(page);
	paramMap.put("page", pageValue);
	paramMap.put("numOfCont", numOfCont);
	paramMap.put("email", email);
	List<QNAVO> list = qnaDAO.qnaList(paramMap);
	int total=qnaDAO.totalContent(email);
	PagingBean paging = new PagingBean(total,page,pagingConfig);
	QNAListVO Qlvo = new QNAListVO(list,paging);	
	return Qlvo;
}
//질문 등록
/* (non-Javadoc)
 * @see org.kosta.kostabank.model.service.QNAService#qnaPosting(org.kosta.kostabank.model.vo.QNAVO)
 */
@Override
public void qnaPosting(QNAVO vo){
	System.out.println(vo+"어어어");
	qnaDAO.qnaPosting(vo);
}
public QNAVO showContent(int qnaNo){
	System.out.println("1"+qnaNo);
	return qnaDAO.showContent(qnaNo);
}
}
