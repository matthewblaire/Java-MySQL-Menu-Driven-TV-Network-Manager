package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Entity.Show;

public class ShowsDAO {
	private Connection connection;
	private static final String SHOWS_INSERT_QUERY = "insert into shows values(?, ?, ?)";
	private static final String SELECT_ALL_SHOWS_QUERY = "select * from shows";
	private static final String SELECT_SHOW_QUERY = "select * from shows where show_no = ?";
	private static final String UPDATE_SHOW_QUERY = "update shows set show_no = ?, show_title = ?, time_slot = ?  where show_no = ?";
	private static final String DELETE_SHOW_QUERY = "delete from shows where show_no = ?";
	
	
	public ShowsDAO() {
		connection = DBConnection.getConnection();
	}
	
	// READ
		//This method returns an Employee copied from 'employees' table, whose ID matches argument
	public Show getShowByID(int search_no) throws SQLException {
			
		PreparedStatement ps = connection.prepareStatement(SELECT_SHOW_QUERY);
		ps.setInt(1, search_no);
		ResultSet rs = ps.executeQuery();
		Show show = null;
		while (rs.next()) {
			show = new Show(rs.getInt("show_no"), 
					rs.getString("show_title"), 
					rs.getString("time_slot"));
		}
			
		return show;
	}		
	
	// READ
	//This method returns a list of type Show, containing all shows in the shows table
	public List<Show> getAllEmployees() throws SQLException {
		List<Show> listOfShows = new ArrayList<Show>();
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery(SELECT_ALL_SHOWS_QUERY);
		
		while (rs.next()) {
			Show current = new Show(rs.getInt("show_no"), 
					rs.getString("show_title"), 
					rs.getString("time_slot"));
			listOfShows.add(current);
		}
		
		
		return listOfShows;
	}
	
	// CREATE
	// this method inserts show to shows table. returns true on success, false on fail
	// Requires 
		// emp_no (inserted emp_no)
		// first_name (inserted first_name)
		// last_name (inserted last_name)
		// title (inserted title)
	// returns: true on success, false on fail
	public boolean insertShow(int show_no, String show_title, String time_slot) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(SHOWS_INSERT_QUERY);
		ps.setInt(1, show_no);
		ps.setString(2, show_title);
		ps.setString(3, time_slot);
		
		if (ps.executeUpdate() == 1) { 
			return true; 
		} else {
			return false;
		}
		
		
	}
	
	// DELETE
	// This method deletes a show with id argument from shows table
	// requires search_no (show_no to search for)
	// returns: true on success, false on fail
	public boolean deleteShow(int search_no) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_SHOW_QUERY);
		ps.setInt(1, search_no);
		
		if (ps.executeUpdate() == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
	// UPDATE
	// This method updates all fields of show based on its show_no
	// Requires 
		// search_show_no (show_no to search for row we're updating)
		// updated_show_no (updated show_no)
		// updated_show_title (updated show_title)
		// updated_time_slot (updated time_slot)
	// returns: true on success, false on fail
	
	public boolean updateShow(int search_show_no, int updated_show_no, String updated_show_title, String updated_time_slot) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_SHOW_QUERY);
		ps.setInt(1, updated_show_no);
		ps.setString(2, updated_show_title);
		ps.setString(3, updated_time_slot);
		ps.setInt(4, search_show_no);
		
		if (ps.executeUpdate() == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
