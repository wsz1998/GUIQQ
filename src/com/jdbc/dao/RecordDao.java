package com.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.bean.Record;

public interface RecordDao {
	// 写入日志
	boolean insertRecord(Connection conn, int userid, String ip, String note) throws SQLException;
	
	boolean insertRecordFail(Connection conn, String ip, String note) throws SQLException;

	// 获取日志
//	List<Record> queryRecordByUserid(Connection conn, int userid) throws SQLException;

}
