package Entity;

public class Show {
	private int show_no;
	private String show_title = null;
	private String time_slot = null;
	
	public Show (int show_no, String show_title, String time_slot) {
		this.setShow_no(show_no);
		this.setShow_title(show_title);
		this.setTime_slot(time_slot);
	}
	
	public int getShow_no() {
		return show_no;
	}
	
	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}

	public String getShow_title() {
		return show_title;
	}

	public void setShow_title(String show_title) {
		this.show_title = show_title;
	}

	public String getTime_slot() {
		return time_slot;
	}

	public void setTime_slot(String time_slot) {
		this.time_slot = time_slot;
	}
	
}
