package Application;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.DBConnection;
import DAO.EmployeeProjectsDAO;
import DAO.EmployeesDAO;
import DAO.RatingsDAO;
import DAO.ShowsDAO;
import Entity.Employee;
import Entity.EmployeeProject;
import Entity.Rating;
import Entity.Show;


public class Menu {
	RatingsDAO rDAO = new RatingsDAO();
	EmployeeProjectsDAO epDAO = new EmployeeProjectsDAO();
	EmployeesDAO eDAO = new EmployeesDAO();
	ShowsDAO sDAO = new ShowsDAO();
	boolean run = true;
	public void start() throws SQLException {
		
		Scanner s = new Scanner(System.in);
		
		do {
			printMenu();
			int userInput = s.nextInt();
			options(userInput);
			
			
		} while (run);
		
	}
	
	private void printMenu() {
		System.out.println("\nMenu");
		System.out.println("Enter option 1-");
		System.out.println("1. Show all Employees");
		System.out.println("2. Show all projects with employee number");
		System.out.println("3. Display shows with ratings");
		
	}
	
	private void options(int choice) throws SQLException {
		if (choice == 1) {
			showAllEmployees();
		} else if (choice == 2) {
			
		} else if (choice == 3) {
			run = false;
			
		}
	}
	
	private void showAllEmployees() throws SQLException {
		List<Employee> employeeList= eDAO.getAllEmployees();
		for (Employee e : employeeList) {
			System.out.println(e.getEmp_no() + " | " + e.getFirst_name() + " | " + e.getLast_name() + " | " + e.getTitle());
		}
	}
	

}
