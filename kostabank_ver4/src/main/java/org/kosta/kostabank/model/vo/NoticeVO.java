package org.kosta.kostabank.model.vo;

public class NoticeVO {
	private int no;
	private String title;
	private String content;
	private int hits;
	private String timePosted;
	private CustomerVO customerVO;
	public NoticeVO(int no, String title, String content, int hits,
			String timePosted, CustomerVO customerVO) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hits = hits;
		this.timePosted = timePosted;
		this.customerVO = customerVO;
	}
	public NoticeVO() {
		super();
	}
	public NoticeVO(int no, String title, String content, String timePosted,
			CustomerVO customerVO) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.timePosted = timePosted;
		this.customerVO = customerVO;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getTimePosted() {
		return timePosted;
	}
	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}
	public CustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	@Override
	public String toString() {
		return "NoticeVO [no=" + no + ", title=" + title + ", content="
				+ content + ", hits=" + hits + ", timePosted=" + timePosted
				+ ", customerVO=" + customerVO + "]";
	}
	

}
