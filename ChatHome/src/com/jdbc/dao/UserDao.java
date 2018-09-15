package com.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.jdbc.bean.User;

public interface UserDao {
	// 添加用户
	boolean insert(Connection conn, String uname, String upwd) throws SQLException;

	// 账户是否存在
	boolean unameExists(Connection conn, String uname) throws SQLException;

	boolean isLog(Connection conn, String uname, String upwd) throws SQLException;
   
	User getUser(Connection conn, String uname, String upwd) throws SQLException;

	User getUser(Connection conn, int id) throws SQLException;

}
