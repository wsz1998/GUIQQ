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
		mainJFrame = new JFrame("�û��� " + uname + "�Է��û��� " + orName);
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;

		mainJFrame.setLocationRelativeTo(AllChatFace.mainJFrame);
		JPanel panel = new JPanel();
		JPanel mainpanel = new JPanel(); // ��

		BorderLayout layout = new BorderLayout();// �߿򲼾�
		GridBagLayout gridBagLayout = new GridBagLayout();
		// ���ó�ʼ���ڵ�һЩ����
		mainJFrame.setBounds(100, 100, 600, 400);
		mainJFrame.setContentPane(panel);
		mainJFrame.setLayout(layout);

		// ���ø������ֵ�panel�Ĳ��ֺʹ�С
		mainpanel.setLayout(gridBagLayout);

		// footpanel���
		JButton foot_send = new JButton("����");
		text_field = new JTextField();
		mainpanel.add(text_field, new GridBagConstraints(0, 0, 1, 1, 100, 100, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainpanel.add(foot_send, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
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
		panel.add(textScrollPane, "Center");

		foot_send.addActionListener(this);

		mainJFrame.setVisible(true);

	}

	/**
	 * �¼���������
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		switch (event) {
		case "����": // ���������Ϣ�İ�ť
			String msg = meuname + "��\n" + text_field.getText();
			text_field.setText("");
			// ��װ�ɶ���
			Request request = new Request(Request.TYPE_SINGLESENDMSG);
			request.setUname(meuname);
			request.setOtherName(otherName);
			request.setChatmsg(msg);
			// ���͸�������
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
		System.out.println("����*************************");
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

}
