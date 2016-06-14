package org.kosta.kostabank.model.vo;

import org.springframework.web.multipart.MultipartFile;

public class QNAVO {
	private int qnaNo;
	private String qnaTitle;
	private String qnaType;
	private CustomerVO customerVO;
	private String qnaContent;
	private String qnaFileAddress;
	private MultipartFile uploadFile;
	private String qnaTime;
	
	public QNAVO() {
		super();
	}

	public String getQnaFileAddress() {
		return qnaFileAddress;
	}

	public void setQnaFileAddress(String qnaFileAddress) {
		this.qnaFileAddress = qnaFileAddress;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public QNAVO(String qnaTitle, String qnaType, CustomerVO customerVO,
			String qnaContent, String qnaFileAddress, MultipartFile uploadFile,
			String qnaTime) {
		super();
		this.qnaTitle = qnaTitle;
		this.qnaType = qnaType;
		this.customerVO = customerVO;
		this.qnaContent = qnaContent;
		this.qnaFileAddress = qnaFileAddress;
		this.uploadFile = uploadFile;
		this.qnaTime = qnaTime;
	}

	public QNAVO(int qnaNo, String qnaTitle, String qnaType,
			CustomerVO customerVO, String qnaContent, String qnaFileAddress,
			MultipartFile uploadFile, String qnaTime) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaType = qnaType;
		this.customerVO = customerVO;
		this.qnaContent = qnaContent;
		this.qnaFileAddress = qnaFileAddress;
		this.uploadFile = uploadFile;
		this.qnaTime = qnaTime;
	}

	public String getQnaTime() {
		return qnaTime;
	}
	public void setQnaTime(String qnaTime) {
		this.qnaTime = qnaTime;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaType() {
		return qnaType;
	}
	public void setQnaType(String qnaType) {
		this.qnaType = qnaType;
	}
	public CustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	@Override
	public String toString() {
		return "QNAVO [qnaNo=" + qnaNo + ", qnaTitle=" + qnaTitle
				+ ", qnaType=" + qnaType + ", customerVO=" + customerVO
				+ ", qnaContent=" + qnaContent + ", qnaFileAddress="
				+ qnaFileAddress + ", uploadFile=" + uploadFile + ", qnaTime="
				+ qnaTime + "]";
	}
}