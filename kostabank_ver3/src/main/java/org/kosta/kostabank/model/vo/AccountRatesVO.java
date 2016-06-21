package org.kosta.kostabank.model.vo;

public class AccountRatesVO {
	//accountSeq, rates, term, name
	private int accountSeq;
	private double rates;
	private int term;
	private AccountTypeVO accountTypeVO;
	public AccountRatesVO() {
		super();
	}
	public AccountRatesVO(int accountSeq, double rates, int term, AccountTypeVO accountTypeVO) {
		super();
		this.accountSeq = accountSeq;
		this.rates = rates;
		this.term = term;
		this.accountTypeVO = accountTypeVO;
	}
	public int getAccountSeq() {
		return accountSeq;
	}
	public void setAccountSeq(int accountSeq) {
		this.accountSeq = accountSeq;
	}
	public double getRates() {
		return rates;
	}
	public void setRates(double d) {
		this.rates = d;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public AccountTypeVO getAccountTypeVO() {
		return accountTypeVO;
	}
	public void setAccountTypeVO(AccountTypeVO accountTypeVO) {
		this.accountTypeVO = accountTypeVO;
	}
	@Override
	public String toString() {
		return "AccountRatesVO [accountSeq=" + accountSeq + ", rates=" + rates
				+ ", term=" + term + ", accountTypeVO=" + accountTypeVO + "]";
	}
	
}
