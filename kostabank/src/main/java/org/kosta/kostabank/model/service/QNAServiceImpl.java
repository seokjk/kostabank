package org.kosta.kostabank.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.QNADAO;
import org.kosta.kostabank.model.vo.ListVO;
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
public QNAListVO qnaList(int page){
	HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
	paramMap.put("page", page);
	paramMap.put("numberOfContent", pagingConfig.get("numberOfContent"));
	List<QNAVO> list = qnaDAO.qnaList(paramMap);
	System.out.println(list);
	int total=qnaDAO.totalContent();
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
	System.out.println(vo);
	qnaDAO.qnaPosting(vo);
}
}
