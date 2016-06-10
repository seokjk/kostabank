package org.kosta.kostabank.model.vo;

public class AccountVO {
	private String accountNo;
	private AccountTypeVO accountTypeVO;
	private String issueDate;
	private int accountPass;
	private int balance;
	private CustomerVO customerVO;
	public AccountVO(String accountNo, AccountTypeVO accountTypeVO, String issueDate,
			int accountPass, int balance, CustomerVO customerVO) {
		super();
		this.accountNo = accountNo;
		this.accountTypeVO = accountTypeVO;
		this.issueDate = issueDate;
		this.accountPass = accountPass;
		this.balance = balance;
		this.customerVO = customerVO;
	}
	public AccountVO() {
		super();
	}
	
	public AccountVO(String accountNo, int balance) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public AccountTypeVO getAccountTypeVO() {
		return accountTypeVO;
	}
	public void setAccountName(AccountTypeVO accountTypeVO) {
		this.accountTypeVO = accountTypeVO;
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
		return "AccountVO [accountNo=" + accountNo + ", accountName="
				+ accountTypeVO + ", issueDate=" + issueDate + ", account_pass="
				+ accountPass + ", balance=" + balance + ", customerVO="
				+ customerVO + "]";
	}
	
	
}
