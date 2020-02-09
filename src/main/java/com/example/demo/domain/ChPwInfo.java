package com.example.demo.domain;

public class ChPwInfo {
	public ChPwInfo() {};
	public ChPwInfo(String mail, String curPw, String newPw) {
		this.mail = mail;
		this.pw1 = pw1;
		this.pw2 = pw2;
	}
	private String mail;
	private String pw1;
	private String pw2;
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPw1() {
		return pw1;
	}
	public void setPw1(String pw1) {
		this.pw1 = pw1;
	}
	public String getPw2() {
		return pw2;
	}
	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}
}
