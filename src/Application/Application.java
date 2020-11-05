package Application;
import Entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.*;

public class Application {
	
	public static void main(String[] args) throws SQLException {
		EmployeesDAO e = new EmployeesDAO();
		RatingsDAO r = new RatingsDAO();
		Rating testR = r.displayRatingbyShowNumber(1);
		Employee test = e.getEmployeeByID(1);
		System.out.println(test.getFirst_name() + " " + test.getLast_name());
		System.out.println("Viewers of show 1: " + testR.getViewer_count());
		
		List<Employee> employees = new ArrayList<Employee>();
		employees = e.getAllEmployees();
		for (Employee cur : employees) {
			System.out.println(cur.getFirst_name() + " " + cur.getLast_name());
		}
		
		//System.out.println(e.insertEmployee(4, "test", "insert", "ttt"));
		System.out.println(e.deleteEmployee(4));
		
	}

}
