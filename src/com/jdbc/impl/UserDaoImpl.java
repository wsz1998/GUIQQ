package com.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.bean.User;
import com.jdbc.dao.UserDao;

public class UserDaoImpl implements UserDao {

	@Override
	//зЂВс
	public boolean insert(Connection conn, String uname, String upwd) throws SQLException {
		String sql = "INSERT INTO user (uname, upwd) VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, upwd);
		int count = ps.executeUpdate();
		conn.close();
		ps.close();
		return count > 0;
	}

	@Override
	public boolean unameExists(Connection conn, String uname) throws SQLException {
		String sql = "SELECT * FROM user WHERE uname = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, uname);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	@Override
	
	public boolean isLog(Connection conn, String uname, String upwd) throws SQLException {
		String sql = "SELECT * FROM user WHERE uname = ? AND upwd = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, upwd);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	@Override
	public User getUser(Connection conn, String uname, String upwd) throws SQLException {

		String sql = "SELECT * FROM user WHERE uname = ? AND upwd = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, upwd);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUname(rs.getString("uname"));
			user.setUpwd(rs.getString("upwd"));
			return user;
		} else {
			return null;
		}

	}

	@Override
	public User getUser(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM user WHERE id = " + id;
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);

		if (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUname(rs.getString("uname"));
			user.setUpwd(rs.getString("upwd"));
			return user;
		} else {
			return null;
		}
	}

}
