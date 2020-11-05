package DAO;

import java.sql.Connection;

import Entity.Employee;

public class EmployeesDAO {
	private Connection connection;
	
	public EmployeesDAO() {
		connection = DBConnection.getConnection();
	}
	
	public Employee getEmployeeByID(int id) {
		Employee employee = null;
		return employee;
	}
	
	
}
