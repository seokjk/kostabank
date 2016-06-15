package org.kosta.kostabank.model.vo;
public class SavingsVO {  
	private AccountVO accountVO;
	private String automaticNo;
	private AccountRatesVO accountRatesVO;
	private int monthlyPayment;
	private int transferBoolean;
	private String paybackNo;
	public SavingsVO(AccountVO accountVO, String automaticNo,
			AccountRatesVO accountRatesVO, int monthlyPayment,
			int transferBoolean, String paybackNo) {
		super();
		this.accountVO = accountVO;
		this.automaticNo = automaticNo;
		this.accountRatesVO = accountRatesVO;
		this.monthlyPayment = monthlyPayment;
		this.transferBoolean = transferBoolean;
		this.paybackNo = paybackNo;
	}
	public SavingsVO() {
		super();
	}
	public AccountVO getAccountVO() {
		return accountVO;
	}
	public void setAccountVO(AccountVO accountVO) {
		this.accountVO = accountVO;
	}
	public String getAutomaticNo() {
		return automaticNo;
	}
	public void setAutomaticNo(String automaticNo) {
		this.automaticNo = automaticNo;
	}
	public AccountRatesVO getAccountRatesVO() {
		return accountRatesVO;
	}
	public void setAccountRatesVO(AccountRatesVO accountRatesVO) {
		this.accountRatesVO = accountRatesVO;
	}
	public int getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(int monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public int getTransferBoolean() {
		return transferBoolean;
	}
	public void setTransferBoolean(int transferBoolean) {
		this.transferBoolean = transferBoolean;
	}
	public String getPaybackNo() {
		return paybackNo;
	}
	public void setPaybackNo(String paybackNo) {
		this.paybackNo = paybackNo;
	}
	@Override
	public String toString() {
		return "SavingsVO [accountVO=" + accountVO + ", automaticNo="
				+ automaticNo + ", accountRatesVO=" + accountRatesVO
				+ ", monthlyPayment=" + monthlyPayment + ", transferBoolean="
				+ transferBoolean + ", paybackNo=" + paybackNo + "]";
	}
}
