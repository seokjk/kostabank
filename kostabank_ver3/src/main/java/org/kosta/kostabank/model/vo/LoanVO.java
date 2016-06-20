package org.kosta.kostabank.model.vo;

public class LoanVO {
	private String loanAccountNo;
	private String inAccountNo;
	private String outAccountNo;
	private int repayTerm;
	private int stayTerm;
	private int overdue;
	private int loanRateSeq;
	private int balance;
	private String accountName;
	
	public LoanVO() {
		super();
	}
	
	public LoanVO(String loanAccountNo, String inAccountNo,
			String outAccountNo, int repayTerm, int stayTerm, int overdue,
			int loanRateSeq) {
		super();
		this.loanAccountNo = loanAccountNo;
		this.inAccountNo = inAccountNo;
		this.outAccountNo = outAccountNo;
		this.repayTerm = repayTerm;
		this.stayTerm = stayTerm;
		this.overdue = overdue;
		this.loanRateSeq = loanRateSeq;
	}
	public LoanVO(String loanAccountNo, String inAccountNo,
			String outAccountNo, int repayTerm, int stayTerm, int overdue,
			int loanRateSeq, int balance, String accountName) {
		super();
		this.loanAccountNo = loanAccountNo;
		this.inAccountNo = inAccountNo;
		this.outAccountNo = outAccountNo;
		this.repayTerm = repayTerm;
		this.stayTerm = stayTerm;
		this.overdue = overdue;
		this.loanRateSeq = loanRateSeq;
		this.balance = balance;
		this.accountName = accountName;
	}
	public LoanVO(String loanAccountNo, String outAccountNo) {
		this.loanAccountNo=loanAccountNo;
		this.outAccountNo=outAccountNo;
	}
	
	public String getLoanAccountNo() {
		return loanAccountNo;
	}

	public void setLoanAccountNo(String loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}

	public String getInAccountNo() {
		return inAccountNo;
	}

	public void setInAccountNo(String inAccountNo) {
		this.inAccountNo = inAccountNo;
	}

	public String getOutAccountNo() {
		return outAccountNo;
	}

	public void setOutAccountNo(String outAccountNo) {
		this.outAccountNo = outAccountNo;
	}

	public int getRepayTerm() {
		return repayTerm;
	}

	public void setRepayTerm(int repayTerm) {
		this.repayTerm = repayTerm;
	}

	public int getStayTerm() {
		return stayTerm;
	}

	public void setStayTerm(int stayTerm) {
		this.stayTerm = stayTerm;
	}

	public int getOverdue() {
		return overdue;
	}

	public void setOverdue(int overdue) {
		this.overdue = overdue;
	}

	public int getLoanRateSeq() {
		return loanRateSeq;
	}

	public void setLoanRateSeq(int loanRateSeq) {
		this.loanRateSeq = loanRateSeq;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public String toString() {
		return "LoanVO [loanAccountNo=" + loanAccountNo + ", inAccountNo="
				+ inAccountNo + ", outAccountNo=" + outAccountNo
				+ ", repayTerm=" + repayTerm + ", stayTerm=" + stayTerm
				+ ", overdue=" + overdue + ", loanRateSeq=" + loanRateSeq + "]";
	}
	
	
}
