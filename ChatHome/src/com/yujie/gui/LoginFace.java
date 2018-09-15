package com.yujie.gui;

import javax.swing.*;

import com.yujie.bean.Request;
import com.yujie.bean.Response;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginFace implements ActionListener {

	public static JFrame mainJFrame;
	Container con;
	JLabel labName, labPass, labTitle, labEmpty;
	JTextField txtName, txtID;
	JPasswordField txtPass;
	JButton login1, register;
	public static String localName;
	public static int flage = 1;

	public LoginFace() {

		mainJFrame = new JFrame("��¼����");
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		mainJFrame.setLocation(w / 3, h / 3);
		con = mainJFrame.getContentPane();
		con.setLayout(new FlowLayout());
		labTitle = new JLabel("��ӭʹ�ö���������ϵͳ");

		labName = new JLabel("�û���:");
		txtName = new JTextField();
		txtName.setColumns(20);

		labPass = new JLabel("����    :");
		txtPass = new JPasswordField();
		txtPass.setColumns(20);

		login1 = new JButton("��¼");
		login1.addActionListener(this);

		register = new JButton("ע��");
		register.addActionListener(this);

		con.add(labTitle);
		con.add(Box.createHorizontalStrut(500));// ��������

		con.add(labName);
		con.add(txtName);
		con.add(Box.createHorizontalStrut(500));

		con.add(labPass);
		con.add(txtPass);
		con.add(Box.createHorizontalStrut(500));

		con.add(login1);
		con.add(register);

		mainJFrame.setSize(350, 350);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login1) {
			String uname = txtName.getText();
			String upwd = txtPass.getText();
			localName = uname;
			// ��װ�ɶ���
			Request request = new Request(Request.TYPE_LOGIN);
			request.setUname(uname);
			request.setUpwd(upwd);
			// ���͸�������

			try {
				SocketFace.oos.writeObject(request);
				SocketFace.oos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		if (e.getSource() == register) {
			mainJFrame.setVisible(false);
			RegistFace rf = new RegistFace();
			rf.mainJFrame.setVisible(true);

		}
	}

	// ��¼���
	public static void loginResult(Response response) {

		if (response.isSuccess()) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "��¼�ɹ�");
			mainJFrame.setVisible(false);
			AllChatFace reg = new AllChatFace(response.getUname());
			reg.mainJFrame.setVisible(true);
			flage = 1;
		} else {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "����ȷ�ϸ��˻������ߣ�����������˻������룬������ " + (3 - flage) + " �λ��ᣡ");
			flage++;
			if (flage == 4) {
				JFrame frame1 = new JFrame();
				JOptionPane.showMessageDialog(frame1, "�Բ��������������Σ������˳���\n ");
			}
		}
	}
}
