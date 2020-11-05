package Entity;

public class Rating {
	private int show_no;
	private int viewer_count;
	
	public Rating (int show_no, int viewer_count) {
		this.setShow_no(show_no);
		this.setViewer_count(viewer_count);
	}
	
	public int getShow_no() {
		return show_no;
	}
	
	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}
	
	public int getViewer_count() {
		return viewer_count;
	}
	
	public void setViewer_count(int viewer_count) {
		this.viewer_count = viewer_count;
	}
	
}
