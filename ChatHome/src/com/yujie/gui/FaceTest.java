package com.yujie.gui;

public class FaceTest {
	public static void main(String[] args) {
		SocketFace socketFace = new SocketFace();
		RegistFace registFace = new RegistFace();
		LoginFace loginFace = new LoginFace();
		AllChatFace chatFace = new AllChatFace("系统");
		SingleChatFace sc = new SingleChatFace("系统", "其他人");
	}
}
