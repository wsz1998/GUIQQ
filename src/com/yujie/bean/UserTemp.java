package com.yujie.bean;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserTemp {

	private String uname;
	private String upwd;

	public ObjectInputStream getOis() {
		return ois;
	}

	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}

	ObjectInputStream ois;
	ObjectOutputStream oos;

	public UserTemp() {
		super();
	}

	public UserTemp(String uname, String upwd) {
		super();
		this.uname = uname;
		this.upwd = upwd;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

}
