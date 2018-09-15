package com.yujie.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public static void main(String[] args) {

		try {
			ServerSocket socket = new ServerSocket(55555);
			System.out.println("服务器端已启动！！！");
			while (true) {
				Socket accept = socket.accept();
				System.out.println("有客户端介入了");
				// 开启一个子线程
				new SingleServerThread(accept).start();
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
