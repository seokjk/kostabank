package org.kosta.kostabank.model.vo;

public class AccountTypeVO {
	//name, accountType, accountExplanation, minMoney
	private String accountName;
	private String accountType;
	private String accountExplanation;
	private int minMoney;
	public AccountTypeVO() {
		super();
	}
	public AccountTypeVO(String accountName, String accountType,
			String accountExplanation, int minMoney) {
		super();
		this.accountName = accountName;
		this.accountType = accountType;
		this.accountExplanation = accountExplanation;
		this.minMoney = minMoney;
	}
	public AccountTypeVO(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountExplanation() {
		return accountExplanation;
	}
	public void setAccountExplanation(String accountExplanation) {
		this.accountExplanation = accountExplanation;
	}
	public int getMinMoney() {
		return minMoney;
	}
	public void setMinMoney(int minMoney) {
		this.minMoney = minMoney;
	}
	@Override
	public String toString() {
		return "AccountTypeVO [accountName=" + accountName + ", accountType=" + accountType
				+ ", accountExplanation=" + accountExplanation + ", minMoney="
				+ minMoney + "]";
	}
	
}
