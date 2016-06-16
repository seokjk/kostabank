package org.kosta.kostabank.model.vo;

public class TransferVO {
	private String account; //출금계좌번호
	private int myaccountPass; // 계좌비밀번호
	private int money;						//이체금액
	private String bank;					//입금은행a
	private String otheraccountNo; //입금계좌번호
	private String otheraccountName; //받는분

	private AccountVO accountVO;
	private CustomerVO customerVO;
	public TransferVO(String account, int myaccountPass, int money,
			String bank, String otheraccountNo, String otheraccountName,
			AccountVO accountVO, CustomerVO customerVO) {
		super();
		this.account = account;
		this.myaccountPass = myaccountPass;
		this.money = money;
		this.bank = bank;
		this.otheraccountNo = otheraccountNo;
		this.otheraccountName = otheraccountName;
		this.accountVO = accountVO;
		this.customerVO = customerVO;
	}
	public TransferVO() {
		super();
	}
	public TransferVO(AccountVO accountVO) {
		super();
		this.accountVO = accountVO;
	}
	public TransferVO(CustomerVO customerVO) {
		super();
		this.customerVO = customerVO;
	}
	public TransferVO(String account, int myaccountPass, int money,
			String bank, String otheraccountNo, String otheraccountName) {
		super();
		this.account = account;
		this.myaccountPass = myaccountPass;
		this.money = money;
		this.bank = bank;
		this.otheraccountNo = otheraccountNo;
		this.otheraccountName = otheraccountName;
	}
	public TransferVO(String account, int myaccountPass, int money,
			String bank, String otheraccountNo) {
		super();
		this.account = account;
		this.myaccountPass = myaccountPass;
		this.money = money;
		this.bank = bank;
		this.otheraccountNo = otheraccountNo;
	}
	public TransferVO(String account, int money, String bank,
			String otheraccountNo, String otheraccountName) {
		super();
		this.account = account;
		this.money = money;
		this.bank = bank;
		this.otheraccountNo = otheraccountNo;
		this.otheraccountName = otheraccountName;
	}
	public String getaccount() {
		return account;
	}
	public void setaccount(String account) {
		this.account = account;
	}
	public int getMyaccountPass() {
		return myaccountPass;
	}
	public void setMyaccountPass(int myaccountPass) {
		this.myaccountPass = myaccountPass;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getOtheraccountNo() {
		return otheraccountNo;
	}
	public void setOtheraccountNo(String otheraccountNo) {
		this.otheraccountNo = otheraccountNo;
	}
	public String getOtheraccountName() {
		return otheraccountName;
	}
	public void setOtheraccountName(String otheraccountName) {
		this.otheraccountName = otheraccountName;
	}
	public AccountVO getAccountVO() {
		return accountVO;
	}
	public void setAccountVO(AccountVO accountVO) {
		this.accountVO = accountVO;
	}
	public CustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	@Override
	public String toString() {
		return "TransferVO [account=" + account + ", myaccountPass="
				+ myaccountPass + ", money=" + money + ", bank=" + bank
				+ ", otheraccountNo=" + otheraccountNo + ", otheraccountName="
				+ otheraccountName + ", accountVO=" + accountVO
				+ ", customerVO=" + customerVO + "]";
	}
	

}
