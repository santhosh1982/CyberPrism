package com.cyberprism.lanchat.model;

public class User {
	private long id;
	private String username;
	private String password;
	private String usertype;
	private String emailid;
	private String status;

	public long getid() {
		return this.id;
	}

	public void setid(long id) {
		this.id=id;
	}

	public String getusername() {
		return this.username;
	}

	public void setusername(String username) {
		this.username=username;
	}

	public String getpassword() {
		return this.password;
	}

	public void setpassword(String password) {
		this.password=password;
	}

	public String getusertype() {
		return this.usertype;
	}

	public void setusertype(String usertype) {
		this.usertype=usertype;
	}

	public String getemailid() {
		return this.emailid;
	}

	public void setemailid(String emailid) {
		this.emailid=emailid;
	}

	public String getstatus() {
		return this.status;
	}

	public void setstatus(String status) {
		this.status=status;
	}

}

