package DAO;
import Entity.EmployeeProject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class EmployeeProjectsDAO {
   private static Connection connection;
   private static final String GET_EMPLOYEE_PROJECTS_QUERY = "SELECT * FROM employee_projects";
   private static final String GET_EMPLOYEE_PROJECTS_BY_EMP_NO_QUERY = "SELECT * FROM employee_projects where emp_no = ?";
   private final static String UPDATE_EMPLOYEE_PROJECT_QUERY = "UPDATE employee_projects SET emp_no  = ?, show_no = ? where emp_no = ?";
   private static final String INSERT_EMPLOYEE_PROJECT_QUERY = "INSERT into employee_projects values(?, ?)";
   private static final String DELETE_EMPLOYEE_BY_EMP_NO_QUERY = "DELETE FROM employee_projects where emp_no = ?";
   private static final String DELETE_SHOW_BY_SHOW_NO = "DELETE FROM employee_projects WHERE show_no = ?";
   private static final String REMOVE_EMPLOYEE_FROM_PROJECT_QUERY = "DELETE FROM employee_projects WHERE emp_no = ? AND show_no = ?";
   public EmployeeProjectsDAO() {
      connection = DBConnection.getConnection();
   }
   
   public List<EmployeeProject> EmployeeProjects() throws SQLException {
      ResultSet rs = connection.prepareStatement(GET_EMPLOYEE_PROJECTS_QUERY).executeQuery();
      List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();
      while (rs.next()) {
         employeeProjects.add(populateEmployeeProjects(rs.getInt(1), rs.getInt(2)));
      }
      return employeeProjects;
   }
   
   public List<EmployeeProject> EmployeeProjectsByID(int emp_no) throws SQLException {
	      PreparedStatement ps = connection.prepareStatement(GET_EMPLOYEE_PROJECTS_BY_EMP_NO_QUERY);
	      ps.setInt(1, emp_no);
	      ResultSet rs = ps.executeQuery();
	      List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();
	      while (rs.next()) {
	         employeeProjects.add(populateEmployeeProjects(rs.getInt(1), rs.getInt(2)));
	      }
	      return employeeProjects;
   }
   
   //update
   public void updateEmployeeProject(int search_emp_no, int employeeId, int show_no) throws SQLException {
      PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_PROJECT_QUERY);
      ps.setInt(1, employeeId);
      ps.setInt(2, show_no);
      ps.setInt(3, search_emp_no);
      ps.executeUpdate();
   }
   
   //create
   public void insertEmployeeProject(int employeeId, int show_no) throws SQLException {
      PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_PROJECT_QUERY);
      ps.setInt(1, employeeId);
      ps.setInt(2, show_no);
      ps.executeUpdate();
   }
   
   //delete
   public void deleteEmployeeByEmpNo(int emp_no) throws SQLException {
      PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_BY_EMP_NO_QUERY);
      ps.setInt(1, emp_no);
      ps.executeUpdate();
   }
   
   public void deleteProjectByShowNumber(int show_no) throws SQLException {
	  PreparedStatement ps = connection.prepareStatement(DELETE_SHOW_BY_SHOW_NO);
	  ps.setInt(1, show_no);
	  ps.executeUpdate();
   }
   
   public void removeEmployeeFromProject(int emp_no, int show_no) throws SQLException {
	   PreparedStatement ps = connection.prepareStatement(REMOVE_EMPLOYEE_FROM_PROJECT_QUERY);
	   ps.setInt(1, emp_no);
	   ps.setInt(2, show_no);
	   ps.executeUpdate();
   }
   
   //Method
   private EmployeeProject populateEmployeeProjects(int emp_no, int show_no) {
      return new EmployeeProject(emp_no, show_no);
   }
}