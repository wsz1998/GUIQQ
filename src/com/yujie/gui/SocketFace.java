package com.yujie.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.yujie.client.ClientReadThread;


public class SocketFace implements ActionListener {

	JFrame frame;
	JLabel labId, labPort, labTitle;
	JTextField txtIP, txtPort;
	JButton linkButton;
	Container con;
	
	public static ObjectOutputStream oos;
	public static JFrame mainJFrame;
	public SocketFace() {
		mainJFrame = new JFrame("连接界面");
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		mainJFrame.setLocation(w / 3, h / 3);

		con = mainJFrame.getContentPane();
		con.setLayout(new FlowLayout());
		labTitle = new JLabel("欢迎使用多人聊天器系统，请先连接服务器");

		labId = new JLabel("IP地址:");
		txtIP = new JTextField();
		txtIP.setText("127.0.0.1");
		txtIP.setColumns(20);

		labPort = new JLabel("端口号    :");
		txtPort = new JTextField();
		txtPort.setText("55555");
		txtPort.setColumns(20);

		linkButton = new JButton("连接");
		linkButton.addActionListener(this);

		con.add(labTitle);
		con.add(Box.createHorizontalStrut(500));// 用来换行

		con.add(labId);
		con.add(txtIP);
		con.add(Box.createHorizontalStrut(500));

		con.add(labPort);
		con.add(txtPort);
		con.add(Box.createHorizontalStrut(500));

		con.add(linkButton);

		mainJFrame.setSize(350, 350);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == linkButton) {// 连接
			String ip = txtIP.getText();
			int port = Integer.parseInt(txtPort.getText());
			connect(ip, port);

			mainJFrame.setVisible(false);
			LoginFace lf = new LoginFace();
			lf.mainJFrame.setVisible(true);
		}
	}

	/**
	 * 连接服务器端
	 */
	public void connect(String ip, int port) {
		try {
			Socket socket = new Socket(ip, port);
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			// 接受服务器消息的线程
			new ClientReadThread(socket).start();
			
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "发生错误！！！！！");
		}

	}

}
