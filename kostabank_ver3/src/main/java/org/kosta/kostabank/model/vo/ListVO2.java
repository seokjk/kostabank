package org.kosta.kostabank.model.vo;

import java.util.List;

public class ListVO2 {
	//공지사항
	private List<NoticeVO> noticeList;
	private PagingBean pagingBean;
	public ListVO2(List<NoticeVO> noticeList, PagingBean pagingBean) {
		super();
		this.noticeList = noticeList;
		this.pagingBean = pagingBean;
	}
	public ListVO2() {
		super();
	}
	public List<NoticeVO> getNoticeList() {
		return noticeList;
	}
	public void setNoticeList(List<NoticeVO> noticeList) {
		this.noticeList = noticeList;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	@Override
	public String toString() {
		return "ListVO2 [noticeList=" + noticeList + ", pagingBean="
				+ pagingBean + "]";
	}
	
		

}
