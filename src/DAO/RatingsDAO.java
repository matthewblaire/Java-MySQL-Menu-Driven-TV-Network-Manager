package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Rating;

public class RatingsDAO {
	private Connection connection;

	private final String CREATE_NEW_RATING_QUERY = "INSERT INTO ratings values (?, ?)";
	private final String DELETE_RATING_BY_SHOW_NO = "DELETE FROM ratings WHERE show_number = ? ";
	private final String DISPLAY_RATING_BY_SHOWNO = " SELECT * FROM ratings WHERE show_no = ?";
	private final String UPDATE_RATING_BY_SHOWNO = "UPDATE ratings SET viewer_count = ? WHERE show_id = ?";

	public RatingsDAO() {
		connection = DBConnection.getConnection();

	}

	public void createNewRating(int show_no, int viewer_count) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_RATING_QUERY);
		ps.setInt(1, show_no);
		ps.setInt(2, viewer_count);
		ps.executeUpdate();
	}

	public void deleteRatingByShowNumber(int show_no) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_RATING_BY_SHOW_NO);
		ps.setInt(1, show_no);
		ps.executeUpdate();
	}

	public Rating displayRatingbyShowNumber(int show_no) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DISPLAY_RATING_BY_SHOWNO);
		ps.setInt(1, show_no);
		ps.executeQuery();
		ResultSet rs = ps.executeQuery();
		Rating rating = null;
		while (rs.next()) {
			rating = new Rating(rs.getInt("show_no"), rs.getInt("viewer_count"));

		}
		return rating;

	}

	public void updateRatingByShowNumber(int show_no, int viewer_count) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_RATING_BY_SHOWNO);
		ps.setInt(1, show_no);
		ps.setInt(2, viewer_count);
		ps.executeUpdate();

	}

}
