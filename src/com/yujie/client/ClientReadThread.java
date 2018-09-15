package com.yujie.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.yujie.bean.Response;
import com.yujie.gui.AllChatFace;
import com.yujie.gui.LoginFace;
import com.yujie.gui.RegistFace;
import com.yujie.gui.SingleChatFace;

public class ClientReadThread extends Thread {
	private Socket socket;
	private BufferedReader br;

	public ClientReadThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Response response = null;
			while ((response = (Response) ois.readObject()) != null) {
				parseMessage(response.getType(), response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 处理从服务器收到的消息
	 */
	public void parseMessage(int i, Response response) {

		switch (i) {
		case Response.TYPE_LOGIN:
			LoginFace.loginResult(response);
			break;
		case Response.TYPE_REGIST:
			RegistFace.registResult(response);
			break;
		case Response.TYPE_SENDMSG:
			AllChatFace.insertMessage(response);
			break;
		case Response.TYPE_ALLMSG:
			AllChatFace.addUserView(response);
			System.out.println("读到了列表");
			break;
		case Response.TYPE_SINGLESENDMSG:
			SingleChatFace.insertMessage(response);
			break;
		case Response.TYPE_SINGLELINK:
			AllChatFace.addSingleChat(response);
			break;
		default:
			break;
		}

	}
}
