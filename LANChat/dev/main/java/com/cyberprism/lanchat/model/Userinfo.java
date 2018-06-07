package com.cyberprism.lanchat.model;

public class Userinfo {
	private long id;
	private long userid;
	private long photoid;
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String state;
	private String country;
	private String phone;
	private String pincode;

	public long getid() {
		return this.id;
	}

	public void setid(long id) {
		this.id=id;
	}

	public long getuserid() {
		return this.userid;
	}

	public void setuserid(long userid) {
		this.userid=userid;
	}

	public long getphotoid() {
		return this.photoid;
	}

	public void setphotoid(long photoid) {
		this.photoid=photoid;
	}

	public String getfirstname() {
		return this.firstname;
	}

	public void setfirstname(String firstname) {
		this.firstname=firstname;
	}

	public String getlastname() {
		return this.lastname;
	}

	public void setlastname(String lastname) {
		this.lastname=lastname;
	}

	public String getaddress() {
		return this.address;
	}

	public void setaddress(String address) {
		this.address=address;
	}

	public String getcity() {
		return this.city;
	}

	public void setcity(String city) {
		this.city=city;
	}

	public String getstate() {
		return this.state;
	}

	public void setstate(String state) {
		this.state=state;
	}

	public String getcountry() {
		return this.country;
	}

	public void setcountry(String country) {
		this.country=country;
	}

	public String getphone() {
		return this.phone;
	}

	public void setphone(String phone) {
		this.phone=phone;
	}

	public String getpincode() {
		return this.pincode;
	}

	public void setpincode(String pincode) {
		this.pincode=pincode;
	}

}

