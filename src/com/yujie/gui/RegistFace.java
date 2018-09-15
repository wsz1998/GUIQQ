package com.yujie.gui;

import javax.swing.*;

import com.yujie.bean.Request;
import com.yujie.bean.Response;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class RegistFace implements ActionListener {

	public static JFrame mainJFrame;
	Container con;
	JLabel labName, labPass, labRPass, labTitle, labEmpty1, labEmpty, labSex, labAge, labClass;
	JTextField txtName, txtID, txtAge;
	JPasswordField txtPass, txtRPass;
	JButton resetButton, regBtn, cancel;
	JRadioButton mRadio, fRadio;
	ButtonGroup sexBtnGroup;

	public RegistFace() {
		mainJFrame = new JFrame("ע�����");
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;

		mainJFrame.setLocation(w / 3, h / 3);
		con = mainJFrame.getContentPane();
		con.setLayout(new FlowLayout());

		labTitle = new JLabel("�û�ע��");
		con.add(labTitle);
		con.add(Box.createHorizontalStrut(1000));// ��������

		labName = new JLabel("�û���:    ");
		txtName = new JTextField();
		txtName.setColumns(20);
		con.add(labName);
		con.add(txtName);
		con.add(Box.createHorizontalStrut(1000));// ��������

		labPass = new JLabel("����:        ");
		txtPass = new JPasswordField();
		txtPass.setColumns(20);
		con.add(labPass);
		con.add(txtPass);
		con.add(Box.createHorizontalStrut(1000));// ��������

		labRPass = new JLabel("ȷ������:");
		txtRPass = new JPasswordField();
		txtRPass.setColumns(20);
		con.add(labRPass);
		con.add(txtRPass);
		con.add(Box.createHorizontalStrut(1000));// ��������

		regBtn = new JButton("ע��");
		regBtn.addActionListener(this);
		con.add(regBtn);

		resetButton = new JButton("����");
		resetButton.addActionListener(this);
		con.add(resetButton);

		cancel = new JButton("ȡ��");
		cancel.addActionListener(this);
		con.add(cancel);

		mainJFrame.setSize(400, 400);
		mainJFrame.setVisible(true);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == regBtn) {// ע��
			String uname = txtName.getText();
			String upwd = txtPass.getText();
			String upwdR = txtRPass.getText();
			if (upwd.equals(upwdR)) {
				// ��װ�ɶ���
				Request request = new Request(Request.TYPE_REGIST);
				request.setUname(uname);
				request.setUpwd(upwd);
				// ���͸�������

				try {
					SocketFace.oos.writeObject(request);
					SocketFace.oos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				JFrame frame = null;
				JOptionPane.showMessageDialog(frame, "��������������벻ƥ�䣡������");
			}

		}
		if (e.getSource() == resetButton) {// ����
			txtName.setText(null);
			txtPass.setText(null);
			txtRPass.setText(null);
		}
		if (e.getSource() == cancel) {// ȡ��
			mainJFrame.setVisible(false);
			LoginFace login = new LoginFace();
			login.mainJFrame.setVisible(true);
		}
	}

	// ע��
	public static void registResult(Response response) {
		if (response.isSuccess()) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "ע��ɹ�");
			mainJFrame.setVisible(false);
			LoginFace lf = new LoginFace();
			lf.mainJFrame.setVisible(true);
		} else {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "�û��Ѵ��ڣ�������û�����");
		}
	}
}
