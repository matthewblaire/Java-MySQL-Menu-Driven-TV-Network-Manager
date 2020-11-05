package DAO;

import java.sql.Connection;

public class RatingsDAO {
	private Connection connection;
	
	public RatingsDAO() {
		connection = DBConnection.getConnection();
	}
}
