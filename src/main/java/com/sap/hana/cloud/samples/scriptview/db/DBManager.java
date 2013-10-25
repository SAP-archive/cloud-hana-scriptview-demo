package com.sap.hana.cloud.samples.scriptview.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBManager {

	private static final String SELECT_ALL_MANAGERS_SCRIPTED_VIEW = "SELECT * FROM \"NEO_<YOUR_SCHEMA_NAME>\".\"manager/SCRIPTVIEW\"";  
	 
	private DBUtil dbAccess;

	public DBManager(DBUtil dbAccess) {
		this.dbAccess = dbAccess;
	}

	public void initDB() throws IOException {
		dropTables();
		createTables();
		insertTables();
		createProcedure();
		createView();
	}

	public List<ManagerEntity> getManagers() throws IOException {
		Connection connection = dbAccess.getConnection();
		List<ManagerEntity> managerList = new ArrayList<ManagerEntity>();
		try {
			ResultSet result = connection.prepareStatement(SELECT_ALL_MANAGERS_SCRIPTED_VIEW).executeQuery();
			while (result.next()) {
				ManagerEntity manager = new ManagerEntity();
				manager.setId(result.getInt("ID"));
				manager.setName(result.getString("NAME"));
				manager.setRegion(result.getString("REGION"));
				manager.setBudget(result.getDouble("BUDGET"));
				manager.setBudgetPercentageShare(result.getDouble("BUDGET_PERCENTAGE_SHARE"));
				managerList.add(manager);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error while reading customer data", e);
		} finally {
			dbAccess.closeConnection(connection);
		}
		return managerList;
	}

	private void createProcedure() throws IOException {
		try {
			dbAccess.executeBatch(dbAccess.mergeToString(loadResource("create_procedure.sql")));
		} catch (SQLException e) {
			throw new RuntimeException("Can not create procedure:\n", e);
		}
	}

	private void insertTables() throws IOException {
		try {
			dbAccess.executeBatch(loadResource("insert_tables.sql"));
		} catch (SQLException e) {
			throw new RuntimeException("Can not insert tables:\n", e);
		}
	}

	private void createView() throws IOException {
		try {
			dbAccess.executeBatch(loadResource("create_view.sql"));
		} catch (SQLException e) {
			throw new RuntimeException("Can not create view:\n", e);
		}
	}

	private void createTables() throws IOException {
		try {
			dbAccess.executeBatch(loadResource("create_tables.sql"));
		} catch (SQLException e) {
			throw new RuntimeException("Can not create tables:\n", e);
		}
	}

	private void dropTables() throws IOException {
		try {
			dbAccess.executeBatch(loadResource("drop_tables.sql"));
		} catch (SQLException e) {
		}
	}

	private List<String> loadResource(String name) throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream("/sql/" + name);
		BufferedReader resourceReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
		List<String> result = new ArrayList<String>();
		String line;

		while ((line = resourceReader.readLine()) != null) {
			if (!line.trim().isEmpty()) {
				result.add(line);
			}
		}
		return result;
	}
}
