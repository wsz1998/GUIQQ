package com.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.bean.Record;
import com.jdbc.dao.RecordDao;

public class RecordDaoImpl implements RecordDao {

	@Override
	public boolean insertRecord(Connection conn, int userid, String ip, String note) throws SQLException {
		String sql = "INSERT INTO chatnotes (userid, ip ,chatTime, note) VALUES (?, ?,NOW(), ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userid);
		ps.setString(2, ip);
		ps.setString(3, note);
		int count = ps.executeUpdate();
		return count > 0;
	}
	@Override
	public boolean insertRecordFail(Connection conn, String ip, String note) throws SQLException {
		String sql = "INSERT INTO chatnotes (ip ,chatTime, note) VALUES (?,NOW(), ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, ip);
		ps.setString(2, note);
		int count = ps.executeUpdate();
		return count > 0;
	}

}
