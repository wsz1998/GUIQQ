package com.yujie.bean;

import java.io.Serializable;

/** �ͻ����������� */
public class Request implements Serializable {

	private static final long serialVersionUID = 3158860821176938128L;

	public static final int TYPE_REGIST = 1; // ע��
	public static final int TYPE_LOGIN = 2; // ��¼
	public static final int TYPE_SENDMSG = 3; // ������Ϣ
	public static final int TYPE_CREATROOM = 4; // ��������
	public static final int TYPE_JOINROOM = 5; // ���뷿��
	public static final int TYPE_EXITROOM = 6; // �˳�����
	public static final int TYPE_ALLMSG = 8; // Ⱥ��
	public static final int TYPE_SINGLESENDMSG = 9; // ������Ϣ
	public static final int TYPE_SINGLELINK = 10; // ����

	// ���������
	private int type;
	private Boolean alive = false;
	// ���������
	private String uname;
	private String otherName;
	private String upwd;
	private String roomname;
	private String chatmsg;
	private String clientName;

	boolean isSuccess;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public Request(int type) {
		super();
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getChatmsg() {
		return chatmsg;
	}

	public void setChatmsg(String chatmsg) {
		this.chatmsg = chatmsg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
