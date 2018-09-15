package com.yujie.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import com.yujie.bean.Request;
import com.yujie.bean.Response;

public class AllChatFace implements ActionListener {

	public static JFrame mainJFrame;
	JTextField host_textfield;
	JTextField port_textfield;
	JTextField text_field;
	JTextField name_textfiled;
	JLabel left_label;
	static JLabel users_label;
	static JTextPane msgArea;
	JScrollPane textScrollPane;
	static JScrollBar vertical;

	JList<String> leftList;
	static JList<String> userlist;
	DefaultListModel<String> rooms_model;
	static DefaultListModel<String> users_model;
	static String uname;
	static String otherName;

	public AllChatFace(String uname) {
		mainJFrame = new JFrame("聊天界面  ———— 用户： " + uname);
		this.uname = uname;
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;

		mainJFrame.setLocation(w, h);
		JPanel panel = new JPanel();
		JPanel mainpanel = new JPanel(); // 主
		JPanel leftpanel = new JPanel(); // 左
		JPanel rightpanel = new JPanel(); // 右

		BorderLayout layout = new BorderLayout();// 边框布局
		GridBagLayout gridBagLayout = new GridBagLayout();
		// 设置初始窗口的一些性质
		mainJFrame.setBounds(100, 100, 600, 400);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.setContentPane(panel);
		mainJFrame.setLayout(layout);

		// 设置各个部分的panel的布局和大小
		mainpanel.setLayout(gridBagLayout);
		leftpanel.setLayout(gridBagLayout);
		rightpanel.setLayout(gridBagLayout);
		leftpanel.setPreferredSize(new Dimension(90, 0));
		rightpanel.setPreferredSize(new Dimension(90, 0));

		// footpanel组件
		JButton foot_send = new JButton("发送");
		text_field = new JTextField();
		mainpanel.add(text_field, new GridBagConstraints(0, 0, 1, 1, 100, 100, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainpanel.add(foot_send, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		// 两边的格子中的组件
		left_label = new JLabel("功能演示列表");
		users_label = new JLabel("在线人数：0");

		
		JButton chat_button = new JButton("创建私聊");

		rooms_model = new DefaultListModel<>();
		users_model = new DefaultListModel<>();

		leftList = new JList<>(rooms_model);
		userlist = new JList<>(users_model);

		JScrollPane ListPane = new JScrollPane(leftList);
		JScrollPane userListPane = new JScrollPane(userlist);

		leftpanel.add(left_label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		leftpanel.add(chat_button, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		leftpanel.add(ListPane, new GridBagConstraints(0, 4, 1, 1, 100, 100, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		rightpanel.add(users_label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		rightpanel.add(userListPane, new GridBagConstraints(0, 1, 1, 1, 100, 100, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		// 中间的文本区组件
		msgArea = new JTextPane();
		msgArea.setEditable(false);
		textScrollPane = new JScrollPane();
		textScrollPane.setViewportView(msgArea);
		vertical = new JScrollBar(JScrollBar.VERTICAL);
		vertical.setAutoscrolls(true);
		textScrollPane.setVerticalScrollBar(vertical);

		// 设置顶层布局
		panel.add(mainpanel, "South");
		panel.add(leftpanel, "West");
		panel.add(rightpanel, "East");
		panel.add(textScrollPane, "Center");

		foot_send.addActionListener(this);

		chat_button.addActionListener(this);

		mainJFrame.setVisible(true);

	}

	/**
	 * 事件监听处理
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * 实现单聊
		 * 指定用户再单击创建私聊即可单聊对话
		 */
		String event = e.getActionCommand();
		switch (event) {
		case "创建私聊": // 选择房间后，点击加入房间按钮
			String orName = userlist.getSelectedValue();
			if (!uname.equals(orName)) {
				System.out.println("你想和 " + orName + "聊天");
				otherName = orName;
				// 包装成对象
				Request request = new Request(Request.TYPE_SINGLELINK);
				request.setUname(uname);
				request.setOtherName(orName);
				// 发送给服务器
				try {
					SocketFace.oos.writeObject(request);
					SocketFace.oos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
			break;
	        case "发送": // 点击发送消息的按钮
			String msg = LoginFace.localName + "：\n" + text_field.getText();
			text_field.setText("");
			// 包装成对象
			Request request = new Request(Request.TYPE_SENDMSG);
			request.setUname(uname);
			request.setChatmsg(msg);
			// 发送给服务器
			try {
				SocketFace.oos.writeObject(request);
				SocketFace.oos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			break;
		// String string = JOptionPane.showInputDialog("请输入你的名称");
		default:
			break;
		}

	}

	public static void insertMessage(Response response) {
		String content = response.getChatmsg();
		StyledDocument document = msgArea.getStyledDocument();
		try {
			document.insertString(document.getLength(), content + "\n", null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		/* 设置滑动条到最后 */
		vertical.setValue(vertical.getMaximum());
	}

	/**
	 * 添加在线用户显示
	 */
	public static void addUserView(Response response) {

		String[] split = response.getNameList().split("split");
		if (split.length == 1) {
			users_model.addElement(split[0]);
		} else {
			users_model.removeAllElements();
			for (int i = 0; i < split.length; i++) {
				users_model.addElement(split[i]);
			}
		}

		users_label.setText("在线人数：" + split.length); /* 更新房间内的人数 */

	}

	public static void addSingleChat(Response response) {
		if (uname.equals(response.getUname())) {
			SingleChatFace sce = new SingleChatFace(uname, response.getOtherName());
			sce.mainJFrame.setVisible(true);
		}

	}

}
