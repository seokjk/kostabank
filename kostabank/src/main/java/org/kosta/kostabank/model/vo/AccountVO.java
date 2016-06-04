package org.kosta.kostabank.model.vo;

public class AccountVO {
	private String accountNo;
	private String accountType;
	private String issueDate;
	private int accountPass;
	private int balance;
	private CustomerVO customerVO;
	public AccountVO(String accountNo, String accountType, String issueDate,
			int accountPass, int balance, CustomerVO customerVO) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.issueDate = issueDate;
		this.accountPass = accountPass;
		this.balance = balance;
		this.customerVO = customerVO;
	}
	public AccountVO() {
		super();
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public int getAccountPass() {
		return accountPass;
	}
	public void setAccountPass(int accountPass) {
		this.accountPass = accountPass;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public CustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	@Override
	public String toString() {
		return "AccountVO [accountNo=" + accountNo + ", accountType="
				+ accountType + ", issueDate=" + issueDate + ", account_pass="
				+ accountPass + ", balance=" + balance + ", customerVO="
				+ customerVO + "]";
	}
	
	
}
