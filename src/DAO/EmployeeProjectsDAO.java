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
   private final static String UPDATE_EMPLOYEE_PROJECT_QUERY = "UPDATE employees SET employeeID = ?, employeeType = ?";
   private static final String INSERT_EMPLOYEE_PROJECT_QUERY = "INSERT into employees values(?, ?, ?, ?)";
   private static final String DELETE_EMPLOYEE_BY_SHOW_ID_QUERY = "DELETE FROM employees where show_no = ?";
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
   public static void updateEmployeeProject(int employeeId, String employeeType) throws SQLException {
      PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_PROJECT_QUERY);
      ps.setInt(1, employeeId);
      ps.setString(2, employeeType);
      ps.executeUpdate();
   }
   //create
   public static void insertEmployeeProject(int employeeId, String employeeType) throws SQLException {
      PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_PROJECT_QUERY);
      ps.setInt(1, employeeId);
      ps.setString(2, employeeType);
      ps.executeUpdate();
   }
   //delete
   public void deleteEmployeeByShowId(int ShowId) throws SQLException {
      PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_BY_SHOW_ID_QUERY);
      ps.setInt(1, ShowId);
      ps.executeUpdate();
   }
   //Method
   private EmployeeProject populateEmployeeProjects(int emp_no, int show_no) {
      return new EmployeeProject(emp_no, show_no);
   }
}