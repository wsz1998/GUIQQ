package com.yujie.server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String dburl  = "jdbc:mysql://127.0.0.1/chatsb";
	private static String uname  = "root";
	private static String upwd   = "1998";

	/**
	 * ��ȡ���Ӷ���
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() {
		try {
			// 1. ����JDBC����
			Class.forName(driver);
			// 2. ������
			Connection conn = DriverManager.getConnection(dburl, uname, upwd);
			return conn;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
