package com.yujie.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public static void main(String[] args) {

		try {
			ServerSocket socket = new ServerSocket(55555);
			System.out.println("��������������������");
			while (true) {
				Socket accept = socket.accept();
				System.out.println("�пͻ��˽�����");
				// ����һ�����߳�
				new SingleServerThread(accept).start();
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
