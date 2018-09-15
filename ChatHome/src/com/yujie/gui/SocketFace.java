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
		mainJFrame = new JFrame("���ӽ���");
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		mainJFrame.setLocation(w / 3, h / 3);

		con = mainJFrame.getContentPane();
		con.setLayout(new FlowLayout());
		labTitle = new JLabel("��ӭʹ�ö���������ϵͳ���������ӷ�����");

		labId = new JLabel("IP��ַ:");
		txtIP = new JTextField();
		txtIP.setText("127.0.0.1");
		txtIP.setColumns(20);

		labPort = new JLabel("�˿ں�    :");
		txtPort = new JTextField();
		txtPort.setText("55555");
		txtPort.setColumns(20);

		linkButton = new JButton("����");
		linkButton.addActionListener(this);

		con.add(labTitle);
		con.add(Box.createHorizontalStrut(500));// ��������

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
		if (e.getSource() == linkButton) {// ����
			String ip = txtIP.getText();
			int port = Integer.parseInt(txtPort.getText());
			connect(ip, port);

			mainJFrame.setVisible(false);
			LoginFace lf = new LoginFace();
			lf.mainJFrame.setVisible(true);
		}
	}

	/**
	 * ���ӷ�������
	 */
	public void connect(String ip, int port) {
		try {
			Socket socket = new Socket(ip, port);
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			// ���ܷ�������Ϣ���߳�
			new ClientReadThread(socket).start();
			
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "�������󣡣�������");
		}

	}

}
