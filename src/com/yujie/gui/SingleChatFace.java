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

public class SingleChatFace implements ActionListener {

	public static JFrame mainJFrame;
	JTextField text_field;
	JTextField name_textfiled;
	JScrollPane textScrollPane;
	static JLabel users_label;
	static JTextPane msgArea;
	static JScrollBar vertical;

	JList<String> leftList;
	static JList<String> userlist;
	DefaultListModel<String> rooms_model;
	static DefaultListModel<String> users_model;
	static String meuname;
	static String otherName;

	public SingleChatFace(String uname, String orName) {
		meuname = uname;
		otherName = orName;
		mainJFrame = new JFrame("用户： " + uname + "对方用户： " + orName);
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;

		mainJFrame.setLocationRelativeTo(AllChatFace.mainJFrame);
		JPanel panel = new JPanel();
		JPanel mainpanel = new JPanel(); // 主

		BorderLayout layout = new BorderLayout();// 边框布局
		GridBagLayout gridBagLayout = new GridBagLayout();
		// 设置初始窗口的一些性质
		mainJFrame.setBounds(100, 100, 600, 400);
		mainJFrame.setContentPane(panel);
		mainJFrame.setLayout(layout);

		// 设置各个部分的panel的布局和大小
		mainpanel.setLayout(gridBagLayout);

		// footpanel组件
		JButton foot_send = new JButton("发送");
		text_field = new JTextField();
		mainpanel.add(text_field, new GridBagConstraints(0, 0, 1, 1, 100, 100, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainpanel.add(foot_send, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
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
		panel.add(textScrollPane, "Center");

		foot_send.addActionListener(this);

		mainJFrame.setVisible(true);

	}

	/**
	 * 事件监听处理
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		switch (event) {
		case "发送": // 点击发送消息的按钮
			String msg = meuname + "：\n" + text_field.getText();
			text_field.setText("");
			// 包装成对象
			Request request = new Request(Request.TYPE_SINGLESENDMSG);
			request.setUname(meuname);
			request.setOtherName(otherName);
			request.setChatmsg(msg);
			// 发送给服务器
			try {
				SocketFace.oos.writeObject(request);
				SocketFace.oos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			break;
		default:
			break;
		}

	}

	public static void insertMessage(Response response) {
		System.out.println("单独*************************");
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

}
