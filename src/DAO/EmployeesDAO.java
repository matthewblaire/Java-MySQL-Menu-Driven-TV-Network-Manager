package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.Employee;

public class EmployeesDAO {
	private static Connection connection;
	private static final String GET_EMPLOYEE_QUERY = "select * from employees where emp_no = ?";
	private static final String GET_ALL_EMPLOYEES_QUERY = "select * from employees";
	private static final String INSERT_EMPLOYEE_QUERY = "insert into employees values(?, ?, ?, ?)";
	private static final String DELETE_EMPLOYEE_QUERY = "delete from employees where emp_no = ?";
	private static final String UPDATE_EMPLOYEE_QUERY = "update foods set emp_no = ?, first_name = ?, last_name = ?, title = ? where emp_no = ?";
	
	// Constructor. Gets instance of DB Connection
	public EmployeesDAO() {
		connection = DBConnection.getConnection();
	}
	
	// READ
	//This method returns an Employee copied from 'employees' table, whose ID matches argument
	public Employee getEmployeeByID(int id) throws SQLException {
		
		PreparedStatement ps = connection.prepareStatement(GET_EMPLOYEE_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Employee employee = null;
		while (rs.next()) {
			employee = new Employee(rs.getInt("emp_no"), 
					rs.getString("first_name"), 
					rs.getString("last_name"),
					rs.getString("title"));
		}
		
		return employee;
	}
	
	// READ
	//This method returns a list of type Employee, containing all employees in the employee table
	public List<Employee> getAllEmployees() throws SQLException {
		List<Employee> listOfEmployees = new ArrayList<Employee>();
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery(GET_ALL_EMPLOYEES_QUERY);
		
		while (rs.next()) {
			Employee current = new Employee(rs.getInt("emp_no"), 
					rs.getString("first_name"), 
					rs.getString("last_name"),
					rs.getString("title"));
			listOfEmployees.add(current);
		}
		
		
		return listOfEmployees;
	}
	
	// CREATE
	// this method inserts an employee to employee table. returns true on success, false on fail
	// Requires 
		// emp_no (inserted emp_no)
		// first_name (inserted first_name)
		// last_name (inserted last_name)
		// title (inserted title)
	public boolean insertEmployee(int emp_no, String first_name, String last_name, String title) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_QUERY);
		ps.setInt(1, emp_no);
		ps.setString(2, first_name);
		ps.setString(3, last_name);
		ps.setString(4, title);
		
		if (ps.executeUpdate() == 1) { 
			return true; 
		} else {
			return false;
		}
		
		
	}
	
	// DELETE
	// This method deletes employee with id argument from employee table
	// requires search_id (emp_no to search for)
	public boolean deleteEmployee(int search_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_QUERY);
		ps.setInt(1, search_id);
		
		if (ps.executeUpdate() == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
	// UPDATE
	// This method updates all fields of an employee based on its emp_no
	// Requires 
		// search_emp_no (emp_no to search for row we're updating)
		// updated_emp_no (updated emp_no)
		// updated_first_name (updated first_name)
		// updated_last_name (updated last_name)
		// updated_title (updated title)
	
	public boolean updateEmployee(int search_emp_no, int updated_emp_no, String updated_first_name, String updated_last_name, String updated_title) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_QUERY);
		ps.setInt(1, updated_emp_no);
		ps.setString(2, updated_first_name);
		ps.setString(3, updated_last_name);
		ps.setString(4, updated_title);
		ps.setInt(5, search_emp_no);
		
		if (ps.executeUpdate() == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
