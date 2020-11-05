package DAO;

import java.sql.Connection;

public class ShowsDAO {
private Connection connection;
	
	public ShowsDAO() {
		connection = DBConnection.getConnection();
	}
}
