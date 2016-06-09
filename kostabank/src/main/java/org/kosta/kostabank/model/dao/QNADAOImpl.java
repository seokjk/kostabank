package org.kosta.kostabank.model.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.QNAVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QNADAOImpl implements QNADAO {
@Resource
private SqlSessionTemplate template;

	@Override
	//페이징이 구현된 리스트를 위한 DB와 연동을 위한 기능
	public List<QNAVO> qnaList(Map<String,Integer> pagingConfig){
		System.out.println(pagingConfig);
		return template.selectList("qna.qnaList",pagingConfig);
	}

	@Override
	// QNA를 포스팅하는 기능.
	public void qnaPosting(QNAVO vo){
		template.insert("qna.qnaPosting",vo);
	}
	public int totalContent(){
		return template.selectOne("qna.totalContent");
	}
	public QNAVO showContent(int qnaNo){
		return template.selectOne("qna.showContent",qnaNo);
	}
}
