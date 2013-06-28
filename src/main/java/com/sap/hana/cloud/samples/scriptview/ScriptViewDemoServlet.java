package com.sap.hana.cloud.samples.scriptview;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.sap.hana.cloud.samples.scriptview.db.DBManager;
import com.sap.hana.cloud.samples.scriptview.db.DBUtil;
import com.sap.hana.cloud.samples.scriptview.db.ManagerEntity;

public class ScriptViewDemoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DBManager dbManager;

	@Override
	public void init() throws ServletException {
		try {
			DBUtil dbUtil = new DBUtil((DataSource) new InitialContext().lookup("java:comp/env/jdbc/DefaultDB"));
			dbManager = new DBManager(dbUtil);
			dbManager.initDB();

		} catch (NamingException e) {
			throw new RuntimeException("Failed to lookup default datasource", e);
		} catch (IOException ioe) {
			throw new RuntimeException("Failed to load resource", ioe);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.print(getFormattedString(dbManager.getManagers()));
		writer.flush();
	}

	private String getFormattedString(List<ManagerEntity> managerList) {
		StringBuilder result = new StringBuilder();
		for (ManagerEntity manager : managerList) {
			result.append(manager.getName() + "/ " + manager.getRegion() + "/ " + manager.getBudget() + "/ " + manager.getBudgetPercentageShare() + "\n");
		}
		return result.toString();
	}

}
