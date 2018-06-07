package com.cyberprism.lanchat.model;

public class Media {
	private long id;
	private String media;
	private String file;
	private String mediatype;
	private String status;

	public long getid() {
		return this.id;
	}

	public void setid(long id) {
		this.id=id;
	}

	public String getmedia() {
		return this.media;
	}

	public void setmedia(String media) {
		this.media=media;
	}

	public String getfile() {
		return this.file;
	}

	public void setfile(String file) {
		this.file=file;
	}

	public String getmediatype() {
		return this.mediatype;
	}

	public void setmediatype(String mediatype) {
		this.mediatype=mediatype;
	}

	public String getstatus() {
		return this.status;
	}

	public void setstatus(String status) {
		this.status=status;
	}

}

