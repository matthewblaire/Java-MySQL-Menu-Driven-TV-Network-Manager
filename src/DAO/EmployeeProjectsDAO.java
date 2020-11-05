package DAO;

import java.sql.Connection;

public class EmployeeProjectsDAO {
	private Connection connection;
	
	public EmployeeProjectsDAO() {
		connection = DBConnection.getConnection();
	}
	
}
