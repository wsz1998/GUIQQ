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
		mainJFrame = new JFrame("注册界面");
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;

		mainJFrame.setLocation(w / 3, h / 3);
		con = mainJFrame.getContentPane();
		con.setLayout(new FlowLayout());

		labTitle = new JLabel("用户注册");
		con.add(labTitle);
		con.add(Box.createHorizontalStrut(1000));// 用来换行

		labName = new JLabel("用户名:    ");
		txtName = new JTextField();
		txtName.setColumns(20);
		con.add(labName);
		con.add(txtName);
		con.add(Box.createHorizontalStrut(1000));// 用来换行

		labPass = new JLabel("密码:        ");
		txtPass = new JPasswordField();
		txtPass.setColumns(20);
		con.add(labPass);
		con.add(txtPass);
		con.add(Box.createHorizontalStrut(1000));// 用来换行

		labRPass = new JLabel("确认密码:");
		txtRPass = new JPasswordField();
		txtRPass.setColumns(20);
		con.add(labRPass);
		con.add(txtRPass);
		con.add(Box.createHorizontalStrut(1000));// 用来换行

		regBtn = new JButton("注册");
		regBtn.addActionListener(this);
		con.add(regBtn);

		resetButton = new JButton("重置");
		resetButton.addActionListener(this);
		con.add(resetButton);

		cancel = new JButton("取消");
		cancel.addActionListener(this);
		con.add(cancel);

		mainJFrame.setSize(400, 400);
		mainJFrame.setVisible(true);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == regBtn) {// 注册
			String uname = txtName.getText();
			String upwd = txtPass.getText();
			String upwdR = txtRPass.getText();
			if (upwd.equals(upwdR)) {
				// 包装成对象
				Request request = new Request(Request.TYPE_REGIST);
				request.setUname(uname);
				request.setUpwd(upwd);
				// 发送给服务器

				try {
					SocketFace.oos.writeObject(request);
					SocketFace.oos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				JFrame frame = null;
				JOptionPane.showMessageDialog(frame, "您输入的两次密码不匹配！！！！");
			}

		}
		if (e.getSource() == resetButton) {// 重置
			txtName.setText(null);
			txtPass.setText(null);
			txtRPass.setText(null);
		}
		if (e.getSource() == cancel) {// 取消
			mainJFrame.setVisible(false);
			LoginFace login = new LoginFace();
			login.mainJFrame.setVisible(true);
		}
	}

	// 注册
	public static void registResult(Response response) {
		if (response.isSuccess()) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "注册成功");
			mainJFrame.setVisible(false);
			LoginFace lf = new LoginFace();
			lf.mainJFrame.setVisible(true);
		} else {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "用户已存在，请更换用户名！");
		}
	}
}
