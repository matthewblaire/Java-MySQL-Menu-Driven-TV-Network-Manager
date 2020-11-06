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
   private final static String UPDATE_EMPLOYEE_PROJECT_QUERY = "UPDATE employee_projects SET emp_no  = ?, show_no = ? where emp_no = ?";
   private static final String INSERT_EMPLOYEE_PROJECT_QUERY = "INSERT into employee_projects values(?, ?)";
   private static final String DELETE_EMPLOYEE_BY_SHOW_ID_QUERY = "DELETE FROM employee_projects where emp_no = ?";
   
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
   
   //update
   public static void updateEmployeeProject(int search_emp_no, int employeeId, int show_no) throws SQLException {
      PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_PROJECT_QUERY);
      ps.setInt(1, employeeId);
      ps.setInt(2, show_no);
      ps.setInt(3, search_emp_no);
      ps.executeUpdate();
   }
   
   //create
   public static void insertEmployeeProject(int employeeId, int show_no) throws SQLException {
      PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_PROJECT_QUERY);
      ps.setInt(1, employeeId);
      ps.setInt(2, show_no);
      ps.executeUpdate();
   }
   
   //delete
   public void deleteEmployeeByEmpNo(int emp_no) throws SQLException {
      PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_BY_SHOW_ID_QUERY);
      ps.setInt(1, emp_no);
      ps.executeUpdate();
   }
   
   //Method
   private EmployeeProject populateEmployeeProjects(int emp_no, int show_no) {
      return new EmployeeProject(emp_no, show_no);
   }
}