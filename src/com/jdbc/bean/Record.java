package com.jdbc.bean;

import java.util.Date;
/*
 * ��¼��
 */
public class Record {
	//��¼ID���û�����ʱ�䡢ip��ַ������Ϣ
	private int id;
	private int userid;
	private Date time;
	private String ip;
	private String note;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", userid=" + userid + ", time=" + time + ", ip=" + ip + ", note=" + note + "]";
	}
}
