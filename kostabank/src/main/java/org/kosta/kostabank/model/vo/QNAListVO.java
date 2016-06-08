package org.kosta.kostabank.model.vo;

import java.util.List;

public class QNAListVO {
	private List<QNAVO> qnaList;
	private PagingBean pagingBean;
	public QNAListVO(List<QNAVO> qnaList, PagingBean pagingBean) {
		super();
		this.qnaList = qnaList;
		this.pagingBean = pagingBean;
	}
	public QNAListVO() {
		super();
	}
	public List<QNAVO> getQnaList() {
		return qnaList;
	}
	public void setQnaList(List<QNAVO> qnaList) {
		this.qnaList = qnaList;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	@Override
	public String toString() {
		return "QNAListVO [qnaList=" + qnaList + ", pagingBean=" + pagingBean
				+ "]";
	}
	
}
