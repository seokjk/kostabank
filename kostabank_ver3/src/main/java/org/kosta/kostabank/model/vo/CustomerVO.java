package org.kosta.kostabank.model.vo;

public class CustomerVO {
	private String email;
	private String password;
	private String name;
	private String birth;
	private String tel;
	private String address;
	private String security_card;
	private int loginFailCount;
	public CustomerVO() {
		super();
	}
	
	public CustomerVO(String email, String password, String name, String birth,
			String tel, String address, String security_card, int loginFailCount) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.tel = tel;
		this.address = address;
		this.security_card = security_card;
		this.loginFailCount = loginFailCount;
	}
	
	public CustomerVO(String email) {
		super();
		this.email = email;
	}
	
	public CustomerVO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public CustomerVO(String email, String password, String name, String birth,
			String tel, String address, String security_card) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.tel = tel;
		this.address = address;
		this.security_card = security_card;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSecurity_card() {
		return security_card;
	}
	public void setSecurity_card(String security_card) {
		this.security_card = security_card;
	}
	
	public int getLoginFailCount() {
		return loginFailCount;
	}
	
	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}
	
	@Override
	public String toString() {
		return "CustomerVO [email=" + email + ", password=" + password
				+ ", name=" + name + ", birth=" + birth + ", tel=" + tel
				+ ", address=" + address + ", security_card=" + security_card
				+ ", loginFailCount=" + loginFailCount + "]";
	}
}
