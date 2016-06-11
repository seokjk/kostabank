package org.kosta.kostabank.model.vo;

import java.util.List;

public class DealListVO {
	private List<DealDetailVO> dealList;
	private PagingBean pagingBean;
	public DealListVO(List<DealDetailVO> dealList, PagingBean pagingBean) {
		super();
		this.dealList = dealList;
		this.pagingBean = pagingBean;
	}
	public DealListVO() {
		super();
	}
	public List<DealDetailVO> getdealList() {
		return dealList;
	}
	public void setdealList(List<DealDetailVO> dealList) {
		this.dealList = dealList;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	@Override
	public String toString() {
		return "DealListVO [dealList=" + dealList
				+ ", pagingBean=" + pagingBean + "]";
	}
}
