package com.cyberprism.lanchat.model;

public class Photo {
	private long id;
	private long mediaid;
	private String imagetype;
	private String status;

	public long getid() {
		return this.id;
	}

	public void setid(long id) {
		this.id=id;
	}

	public long getmediaid() {
		return this.mediaid;
	}

	public void setmediaid(long mediaid) {
		this.mediaid=mediaid;
	}

	public String getimagetype() {
		return this.imagetype;
	}

	public void setimagetype(String imagetype) {
		this.imagetype=imagetype;
	}

	public String getstatus() {
		return this.status;
	}

	public void setstatus(String status) {
		this.status=status;
	}

}

