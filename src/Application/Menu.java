// By Matthew Blaire

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
		
		
		
		do {
			printMenu();
			
			options(scanInt());
			
			
		} while (run);
		
	}
	
	private void printMenu() {
		System.out.println("\nMenu");
		System.out.println("Enter option 1-");
		System.out.println("1. Show all Employees");
		System.out.println("2. Show all projects with employee number");
		System.out.println("3. Display shows with ratings");
		System.out.println("4. Display show by show number");
		System.out.println("5. Create new show");
		System.out.println("6. Cancel show");
		System.out.println("7. Update show viewer count with show number");
		System.out.println("8. Add employee to project");
		System.out.println("9. Remove employee from project");
		System.out.println("10. Update show time slot with show number");
		System.out.println("11. Hire employee");
		System.out.println("12. Fire employee");
		System.out.println("\n13. Exit");
		// create/delete show
				// create/delete employee
				// add/delete project for employee
				// update viewer count
				// update show time slot
	}
	
	// this method handles the menu logic
	private void options(int choice) throws SQLException {
		if (choice == 1) {
			
			showAllEmployees();
			
		} else if (choice == 2) {
			
			System.out.println("Enter employee number:");
			showEmployeeProjectsWithID(scanInt());
			
		} else if (choice == 3) {
			
			displayAllShowsWithRatings();
			
		} else if (choice == 4) {
			
			System.out.println("Enter show number:");
			displayShowByShowNumber(scanInt());
			
		} else if (choice == 5) {
			
			System.out.println("Create show");
			System.out.println("Enter new show number: ");
			int show_no = scanInt();
			System.out.println("Enter new show title: ");
			String show_title = scanString();
			System.out.println("Enter new time slot (24hr format, HH:MM");
			String time_slot = scanString();
			createShow(show_no, show_title, time_slot);
			System.out.println("Show created!");
			
		} else if (choice == 6) {
			
			System.out.println("Cancel show");
			displayAllShowsWithRatings();
			System.out.println("Enter show number:");
			cancelShow(scanInt());
			
		} else if (choice == 7) {
			
			System.out.println("Update ratings");
			System.out.println("Enter show number:");
			int show_no = scanInt();
			System.out.println("Enter new viewer count:");
			int viewer_count = scanInt();
			updateViewersByShowNumber(show_no, viewer_count);
			System.out.println("Ratings updated");
			
		} else if (choice == 8) {
			
			System.out.println("Add employee to project");
			showAllEmployees();
			System.out.println("Enter employee number:");
			int emp_no = scanInt();
			displayAllShowsWithRatings();
			System.out.println("Enter show number:");
			int show_no = scanInt();
			addEmployeeToProject(emp_no, show_no);
			System.out.println("Employee added to show number " + show_no + ".");
			
		} else if (choice == 9) {
			
			System.out.println("Remove employee from project");
			showAllEmployees();
			System.out.println("Enter employee number:");
			int emp_no = scanInt();
			displayAllShowsWithRatings();
			System.out.println("Enter show number:");
			int show_no = scanInt();
			removeEmployeeFromProject(emp_no, show_no);
			
		} else if (choice == 10) {
			
			System.out.println("Update time slot with show number:");
			System.out.println("Enter show number:");
			int show_no = scanInt();
			System.out.println("Enter new time slot (24hr format, HH:MM");
			String time_slot = scanString();
			updateTimeSlotByShowNumber(show_no, time_slot);
			
		} else if (choice == 11) {
			
			System.out.println("Hire employee");
			System.out.println("Enter new employee number:");
			int emp_no = scanInt();
			System.out.println("Enter first name:");
			String first_name = scanString();
			System.out.println("Enter last name:");
			String last_name = scanString();
			System.out.println("Enter job title:");
			String title = scanString();
			
			hireEmployee(emp_no, first_name, last_name, title);
			
		} else if (choice == 12) {
			
			System.out.println("Fire employee");
			System.out.println("Enter employee number:");
			int emp_no = scanInt();
			fireEmployee(emp_no);
			
		} else if (choice == 13) {
			
			run = false;
			
		}
	}
	
	// this method returns a scanned int from the user
	private int scanInt() {
		
		Scanner s = new Scanner(System.in);
		int userInput = s.nextInt();
		return userInput;
		
	}
	
	// this method returns a scanned string from the user
	private String scanString() {
		
		Scanner s = new Scanner(System.in);
		String userInput = s.nextLine();
		return userInput;
		
	}
	
	// this method prints a list of all employees to console, from employees table
	private void showAllEmployees() throws SQLException {
		
		List<Employee> employeeList= eDAO.getAllEmployees();
		for (Employee e : employeeList) {
			System.out.println(e.getEmp_no() + " | " + e.getFirst_name() + " | " + e.getLast_name() + " | " + e.getTitle());
		}
		
	}
	
	// Display all of an employee's projects
	private void showEmployeeProjectsWithID(int emp_no) throws SQLException {
		
		List<EmployeeProject> EPList = epDAO.EmployeeProjectsByID(emp_no); // used to find emp_no and show_no
		Employee employee = eDAO.getEmployeeByID(emp_no); // used to find employee name
		
		if (EPList.isEmpty()) {
			
			System.out.println("This employee has no projects.");
			
		}
		
		for (EmployeeProject e : EPList) {
			
			Show show = sDAO.getShowByID(e.getShow_no());
			System.out.println(
					"Name: " + employee.getFirst_name() + " " + employee.getLast_name() + " | "
					+ "Project Title: " +show.getShow_title() + " | " + "Job: " + employee.getTitle());
		
		}
	}
	
	// this method displays all shows formatted with show_no, title, time_slot, and viewer count
	private void displayAllShowsWithRatings() throws SQLException {
		
		List<Show> shows = sDAO.getAllShows();
		for (Show s : shows) {
			Rating r = rDAO.displayRatingbyShowNumber(s.getShow_no());
			System.out.println(s.getShow_no() + " | Title: " + s.getShow_title() + " | Time slot: " + s.getTime_slot() + " | Viewers: " + r.getViewer_count());
		}
	}
	
	// this method displays a single show formatted with show_no, title, time_slot, and viewer count
	private void displayShowByShowNumber(int show_no) throws SQLException {
		
		Show show = sDAO.getShowByID(show_no);
		Rating r = rDAO.displayRatingbyShowNumber(show.getShow_no());
		System.out.println(show.getShow_no() + " | Title: " + show.getShow_title() + " | Time slot: " + show.getTime_slot() + " | Viewers: " + r.getViewer_count());
	
	}
	
	// this method inserts a show to the shows table
	private void createShow(int show_no, String show_title, String time_slot) throws SQLException {
		
		sDAO.insertShow(show_no, show_title, time_slot);
		rDAO.createNewRating(show_no, 0);
		
	}
	
	// this method removes a show 
	private void cancelShow(int show_no) throws SQLException {
		
		rDAO.deleteRatingByShowNumber(show_no);
		epDAO.deleteProjectByShowNumber(show_no);
		sDAO.deleteShow(show_no);
		
	}
	
	// this method updates the viewer count of a show in the ratings table
	private void updateViewersByShowNumber(int show_no, int viewer_count) throws SQLException{
		
		rDAO.updateRatingByShowNumber(show_no, viewer_count);
		
	}
	
	// this method adds an employee to a project in the employee_projects table
	private void addEmployeeToProject(int emp_no, int show_no) throws SQLException {
		
		epDAO.insertEmployeeProject(emp_no, show_no);
	}
	
	//this method removes an employee from a project in the employee_projects table
	private void removeEmployeeFromProject(int emp_no, int show_no) throws SQLException {
		
		epDAO.removeEmployeeFromProject(emp_no, show_no);
	}
	
	// this method updates the time slot of a show in the shows table
	private void updateTimeSlotByShowNumber(int show_no, String time_slot) throws SQLException {
		
		sDAO.updateTimeSlot(show_no, time_slot);
		
	}
	
	// this method inserts an employee to the employees table
	private void hireEmployee(int emp_no, String first_name, String last_name, String title) throws SQLException {
		
		eDAO.insertEmployee(emp_no, first_name, last_name, title);
	
	}
	
	// this method deletes an employee from the employees table
	private void fireEmployee(int emp_no) throws SQLException {
		
		epDAO.deleteEmployeeByEmpNo(emp_no);
		eDAO.deleteEmployee(emp_no);
		
	}
	
}
