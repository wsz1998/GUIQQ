package com.yujie.bean;

import java.io.Serializable;

/** ���������صĶ������� */
public class Response implements Serializable {

	private static final long serialVersionUID = -2913033246091103160L;
	public static final int TYPE_REGIST = 1; // ע��
	public static final int TYPE_LOGIN = 2; // ��¼
	public static final int TYPE_SENDMSG = 3; // ������Ϣ
	public static final int TYPE_CREATROOM = 4; // ��������
	public static final int TYPE_JOINROOM = 5; // ���뷿��
	public static final int TYPE_EXITROOM = 6; // �˳�����
	public static final int TYPE_ALLMSG = 8; // Ⱥ��
	public static final int TYPE_SINGLESENDMSG = 9; // ������Ϣ
	public static final int TYPE_SINGLELINK = 10; // ������Ϣ
	String nameList;

	public String getNameList() {
		return nameList;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	// ���������
	private int type;
	// ���������
	private String uname;
	private String upwd;
	private String roomname;
	private String chatmsg;
	private String content;
	private int nums;
	private String otherName;
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

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public Response() {
		super();
	}

	public Response(int type) {
		super();
		this.type = type;
	}

	public int getType() {
		return type;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
