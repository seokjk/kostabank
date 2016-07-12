package org.kosta.kostabank.model.vo;

import java.util.List;

public class ListVO {
	private List<AccountTypeVO> accountTypeList;
	private PagingBean pagingBean;
	public ListVO(List<AccountTypeVO> accountTypeList, PagingBean pagingBean) {
		super();
		this.accountTypeList = accountTypeList;
		this.pagingBean = pagingBean;
	}
	public ListVO() {
		super();
	}
	public List<AccountTypeVO> getAccountTypeList() {
		return accountTypeList;
	}
	public void setAccountTypeList(List<AccountTypeVO> accountTypeList) {
		this.accountTypeList = accountTypeList;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	@Override
	public String toString() {
		return "ListVO [accountTypeList=" + accountTypeList + ", pagingBean="
				+ pagingBean + "]";
	}
	
}
