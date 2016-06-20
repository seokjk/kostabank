package org.kosta.kostabank.model.vo;

public class AccountVO {
	private String accountNo;
	private AccountTypeVO accountTypeVO;
	private String issueDate;
	private int accountPass;
	private long balance;
	private long balanceAvg;
	private CustomerVO customerVO;
	private LoanVO loanVO;
	public AccountVO(long balance, CustomerVO customerVO) {
		super();
		this.balance = balance;
		this.customerVO = customerVO;
	}
	public AccountVO(String accountNo, AccountTypeVO accountTypeVO, String issueDate,
			int accountPass, long balance, CustomerVO customerVO) {
		super();
		this.accountNo = accountNo;
		this.accountTypeVO = accountTypeVO;
		this.issueDate = issueDate;
		this.accountPass = accountPass;
		this.balance = balance;
		this.customerVO = customerVO;
	}
	public AccountVO(String accountNo, AccountTypeVO accountTypeVO,
			String issueDate, int accountPass, long balance, long balanceAvg,
			CustomerVO customerVO) {
		super();
		this.accountNo = accountNo;
		this.accountTypeVO = accountTypeVO;
		this.issueDate = issueDate;
		this.accountPass = accountPass;
		this.balance = balance;
		this.balanceAvg = balanceAvg;
		this.customerVO = customerVO;
	}
	public AccountVO(String accountNo, AccountTypeVO accountTypeVO,
			 LoanVO loanVO, CustomerVO customerVO) {
		super();
		this.accountNo = accountNo;
		this.accountTypeVO = accountTypeVO;
		this.customerVO = customerVO;
		this.loanVO = loanVO;
	}
	public AccountVO() {
		super();
	}
	public AccountVO(String accountNo, long balance) {
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
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public CustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	
	public long getBalanceAvg() {
		return balanceAvg;
	}
	public void setBalanceAvg(long balanceAvg) {
		this.balanceAvg = balanceAvg;
	}
	public void setAccountTypeVO(AccountTypeVO accountTypeVO) {
		this.accountTypeVO = accountTypeVO;
	}
	public LoanVO getLoanVO() {
		return loanVO;
	}
	public void setLoanVO(LoanVO loanVO) {
		this.loanVO = loanVO;
	}
	@Override
	public String toString() {
		return "AccountVO [accountNo=" + accountNo + ", accountName="
				+ accountTypeVO + ", issueDate=" + issueDate + ", account_pass="
				+ accountPass + ", balance=" + balance + ", customerVO="
				+ customerVO + "]";
	}
	
	
}
