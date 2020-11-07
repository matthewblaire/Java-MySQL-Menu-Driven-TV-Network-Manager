// By Andrew Liam, Britney Hanna, Matthew Blaire

package Entity;

public class EmployeeProject {
	private int emp_no;
	private int show_no;
	
	public EmployeeProject (int emp_no, int show_no) {
		this.setEmp_no(emp_no);
		this.setShow_no(show_no);
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public int getShow_no() {
		return show_no;
	}

	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}
}
