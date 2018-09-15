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
		mainJFrame = new JFrame("�������  �������� �û��� " + uname);
		this.uname = uname;
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;

		mainJFrame.setLocation(w, h);
		JPanel panel = new JPanel();
		JPanel mainpanel = new JPanel(); // ��
		JPanel leftpanel = new JPanel(); // ��
		JPanel rightpanel = new JPanel(); // ��

		BorderLayout layout = new BorderLayout();// �߿򲼾�
		GridBagLayout gridBagLayout = new GridBagLayout();
		// ���ó�ʼ���ڵ�һЩ����
		mainJFrame.setBounds(100, 100, 600, 400);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.setContentPane(panel);
		mainJFrame.setLayout(layout);

		// ���ø������ֵ�panel�Ĳ��ֺʹ�С
		mainpanel.setLayout(gridBagLayout);
		leftpanel.setLayout(gridBagLayout);
		rightpanel.setLayout(gridBagLayout);
		leftpanel.setPreferredSize(new Dimension(90, 0));
		rightpanel.setPreferredSize(new Dimension(90, 0));

		// footpanel���
		JButton foot_send = new JButton("����");
		text_field = new JTextField();
		mainpanel.add(text_field, new GridBagConstraints(0, 0, 1, 1, 100, 100, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainpanel.add(foot_send, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		// ���ߵĸ����е����
		left_label = new JLabel("������ʾ�б�");
		users_label = new JLabel("����������0");

		
		JButton chat_button = new JButton("����˽��");

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

		// �м���ı������
		msgArea = new JTextPane();
		msgArea.setEditable(false);
		textScrollPane = new JScrollPane();
		textScrollPane.setViewportView(msgArea);
		vertical = new JScrollBar(JScrollBar.VERTICAL);
		vertical.setAutoscrolls(true);
		textScrollPane.setVerticalScrollBar(vertical);

		// ���ö��㲼��
		panel.add(mainpanel, "South");
		panel.add(leftpanel, "West");
		panel.add(rightpanel, "East");
		panel.add(textScrollPane, "Center");

		foot_send.addActionListener(this);

		chat_button.addActionListener(this);

		mainJFrame.setVisible(true);

	}

	/**
	 * �¼���������
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * ʵ�ֵ���
		 * ָ���û��ٵ�������˽�ļ��ɵ��ĶԻ�
		 */
		String event = e.getActionCommand();
		switch (event) {
		case "����˽��": // ѡ�񷿼�󣬵�����뷿�䰴ť
			String orName = userlist.getSelectedValue();
			if (!uname.equals(orName)) {
				System.out.println("����� " + orName + "����");
				otherName = orName;
				// ��װ�ɶ���
				Request request = new Request(Request.TYPE_SINGLELINK);
				request.setUname(uname);
				request.setOtherName(orName);
				// ���͸�������
				try {
					SocketFace.oos.writeObject(request);
					SocketFace.oos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
			break;
	        case "����": // ���������Ϣ�İ�ť
			String msg = LoginFace.localName + "��\n" + text_field.getText();
			text_field.setText("");
			// ��װ�ɶ���
			Request request = new Request(Request.TYPE_SENDMSG);
			request.setUname(uname);
			request.setChatmsg(msg);
			// ���͸�������
			try {
				SocketFace.oos.writeObject(request);
				SocketFace.oos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			break;
		// String string = JOptionPane.showInputDialog("�������������");
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
		/* ���û���������� */
		vertical.setValue(vertical.getMaximum());
	}

	/**
	 * ��������û���ʾ
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

		users_label.setText("����������" + split.length); /* ���·����ڵ����� */

	}

	public static void addSingleChat(Response response) {
		if (uname.equals(response.getUname())) {
			SingleChatFace sce = new SingleChatFace(uname, response.getOtherName());
			sce.mainJFrame.setVisible(true);
		}

	}

}
