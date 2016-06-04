package org.kosta.kostabank.model.vo;

public class TransferVO {
	private String myaccountno;
	private int money;
	private AccountVO accountVO;
	public TransferVO(String myaccountno, int money, AccountVO accountVO) {
		super();
		this.myaccountno = myaccountno;
		this.money = money;
		this.accountVO = accountVO;
	}
	public TransferVO() {
		super();
	}
	public String getMyaccountno() {
		return myaccountno;
	}
	public void setMyaccountno(String myaccountno) {
		this.myaccountno = myaccountno;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public AccountVO getAccountVO() {
		return accountVO;
	}
	public void setAccountVO(AccountVO accountVO) {
		this.accountVO = accountVO;
	}
	@Override
	public String toString() {
		return "TransferVO [myaccountno=" + myaccountno + ", money=" + money
				+ ", accountVO=" + accountVO + "]";
	}
	
	

}
