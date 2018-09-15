package com.yujie.server;

import java.io.ObjectOutputStream;
import java.sql.SQLException;

import com.jdbc.bean.User;
import com.jdbc.dao.RecordDao;
import com.jdbc.dao.UserDao;
import com.jdbc.impl.RecordDaoImpl;
import com.jdbc.impl.UserDaoImpl;
import com.yujie.bean.UserTemp;

public class ServerFunction {

	private static UserDao userDao = new UserDaoImpl();
	private static RecordDao recordDao = new RecordDaoImpl();
    //ע��
	public boolean doRegist(String uname, String upwd) throws ClassNotFoundException, SQLException {

		if (userDao.unameExists(ConnectionFactory.getConnection(), uname)) {
			System.out.println("�û����Ѵ��ڣ�");
			return false;
		}
		return userDao.insert(ConnectionFactory.getConnection(), uname, upwd);

	}
  //��¼
	public boolean doLogin(String uname, String upwd, ObjectOutputStream oos) throws SQLException {

		if ((userDao.isLog(ConnectionFactory.getConnection(), uname, upwd)) && inOline(uname)) {
			System.out.println("��½�ɹ�");
			UserTemp ut = new UserTemp(uname, upwd);
			ut.setOos(oos);
			SingleServerThread.userList.add(ut);
			return true;
		}
		return false;

	}
     //�ж��Ƿ�����
	public boolean inOline(String uname) {
		for (UserTemp t : SingleServerThread.userList) {
			if (uname.equals(t.getUname())) {
				return false;
			}
		}
		return true;
	}

	public static void Notes(String uname, String upwd, String ip, String note) {
		try {
			if (note.equals("��½�ɹ�")||note.equals("ע��ɹ�")) {
				User user = userDao.getUser(ConnectionFactory.getConnection(), uname, upwd);
				recordDao.insertRecord(ConnectionFactory.getConnection(), user.getId(), ip, uname+note);
			} else {
				recordDao.insertRecordFail(ConnectionFactory.getConnection(), ip,note);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
