package com.yujie.gui;

public class FaceTest {
	public static void main(String[] args) {
		SocketFace socketFace = new SocketFace();
		RegistFace registFace = new RegistFace();
		LoginFace loginFace = new LoginFace();
		AllChatFace chatFace = new AllChatFace("ϵͳ");
		SingleChatFace sc = new SingleChatFace("ϵͳ", "������");
	}
}
