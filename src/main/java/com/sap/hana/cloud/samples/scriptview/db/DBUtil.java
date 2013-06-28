package com.sap.hana.cloud.samples.scriptview.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DBUtil {
	
	private DataSource dataSource;

	public DBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("Failed to create connection", e);
		}
		return connection;
	}

	public void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String mergeToString(List<String> lines) {
		StringBuilder result = new StringBuilder();
		
		for(String line : lines) {
			result.append(line);
			result.append(" ");
		}
		
		return result.toString();
	}

	public void executeBatch(String sql) throws SQLException {
		List<String> sqls = new ArrayList<String>();
		sqls.add(sql);
		
		executeBatch(sqls);
	}
	
	public void executeBatch(List<String> sqls) throws SQLException {
		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			for (String sql : sqls) {
				statement.addBatch(sql);
			}
			statement.executeBatch();
			statement.close();
		} finally {
			closeConnection(connection);
		}

	}
}
