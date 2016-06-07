package org.kosta.kostabank.model.vo;

public class DealDetailVO {
	private int dealNo;
	private String accountNo;
	private String otherAccountNo;
	private String dealType;
	private int amountOfMoney;
	private String dealDate;
	private String startDay;
	private String endDay;
	
	
	public DealDetailVO() {
		super();
	}
	public DealDetailVO(String startDay, String endDay) {
		super();
		this.startDay = startDay;
		this.endDay = endDay;
	}
	public DealDetailVO(String accountNo, String startDay, String endDay) {
		super();
		this.accountNo = accountNo;
		this.startDay = startDay;
		this.endDay = endDay;
	}
	public DealDetailVO(int dealNo, String accountNo, String otherAccountNo,
			String dealType, int amountOfMoney, String dealDate) {
		super();
		this.dealNo = dealNo;
		this.accountNo = accountNo;
		this.otherAccountNo = otherAccountNo;
		this.dealType = dealType;
		this.amountOfMoney = amountOfMoney;
		this.dealDate = dealDate;
	}
	public DealDetailVO(int dealNo, String accountNo, String otherAccountNo,
			String dealType, int amountOfMoney, String dealDate,
			String startDay, String endDay) {
		super();
		this.dealNo = dealNo;
		this.accountNo = accountNo;
		this.otherAccountNo = otherAccountNo;
		this.dealType = dealType;
		this.amountOfMoney = amountOfMoney;
		this.dealDate = dealDate;
		this.startDay = startDay;
		this.endDay = endDay;
	}

	

	


	public int getDealNo() {
		return dealNo;
	}


	public void setDealNo(int dealNo) {
		this.dealNo = dealNo;
	}


	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	public String getOtherAccountNo() {
		return otherAccountNo;
	}


	public void setOtherAccountNo(String otherAccountNo) {
		this.otherAccountNo = otherAccountNo;
	}


	public String getDealType() {
		return dealType;
	}


	public void setDealType(String dealType) {
		this.dealType = dealType;
	}


	public int getAmountOfMoney() {
		return amountOfMoney;
	}


	public void setAmountOfMoney(int amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}


	public String getDealDate() {
		return dealDate;
	}


	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}


	public String getStartDay() {
		return startDay;
	}


	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}


	@Override
	public String toString() {
		return "DealDetailVO [dealNo=" + dealNo + ", accountNo=" + accountNo
				+ ", otherAccountNo=" + otherAccountNo + ", dealType="
				+ dealType + ", amountOfMoney=" + amountOfMoney + ", dealDate="
				+ dealDate + ", startDay=" + startDay + ", endDay=" + endDay
				+ "]";
	}
	
}
