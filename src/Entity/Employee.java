package Entity;

public class Employee {
	
	private int emp_no;
	private String first_name = null;
	private String last_name = null;
	private String title = null;
	
	public Employee(int emp_no, String first_name, String last_name, String title) {
		this.setEmp_no(emp_no);
		this.setFirst_name(first_name);
		this.setLast_name(last_name);
		this.setTitle(title);
	}
	
	
	public int getEmp_no() {
		return emp_no;
	}
	
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
