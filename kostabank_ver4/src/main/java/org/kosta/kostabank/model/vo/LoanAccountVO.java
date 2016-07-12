package org.kosta.kostabank.model.vo;

public class LoanAccountVO {
	private String accountName;
	private String accountExplanation;
	private int maximumMoney;
	private int rates;
	private int term;
	private double additionalRates;

	public LoanAccountVO() {
		super();
	}

	public LoanAccountVO(String accountName, String accountExplanation,
			int maximumMoney, int rates, int term, double additionalRates) {
		super();
		this.accountName = accountName;
		this.accountExplanation = accountExplanation;
		this.maximumMoney = maximumMoney;
		this.rates = rates;
		this.term = term;
		this.additionalRates = additionalRates;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountExplanation() {
		return accountExplanation;
	}

	public void setAccountExplanation(String accountExplanation) {
		this.accountExplanation = accountExplanation;
	}

	public int getMaximumMoney() {
		return maximumMoney;
	}

	public void setMaximumMoney(int maximumMoney) {
		this.maximumMoney = maximumMoney;
	}

	public int getRates() {
		return rates;
	}

	public void setRates(int rates) {
		this.rates = rates;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public double getAdditionalRates() {
		return additionalRates;
	}

	public void setAdditionalRates(double additionalRates) {
		this.additionalRates = additionalRates;
	}

	@Override
	public String toString() {
		return "LoanAccountVO [accountName=" + accountName
				+ ", accountExplanation=" + accountExplanation
				+ ", maximumMoney=" + maximumMoney + ", rates=" + rates
				+ ", term=" + term + ", additionalRates=" + additionalRates
				+ "]";
	}
}
