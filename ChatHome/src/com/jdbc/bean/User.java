package com.jdbc.bean;
/*
 * �û���
 */
public class User {
    //����ID���û���������
	private int id;
	private String uname;
	private String upwd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", upwd=" + upwd + "]";
	}

}
