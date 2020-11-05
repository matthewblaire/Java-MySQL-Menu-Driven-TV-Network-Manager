package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {
	private final static String URL = "jdbc:mysql://localhost:3306/shoes";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "password";
	private static Connection connection;
	private static DBConnection instance;
	private DBConnection(Connection connection) {
		this.connection = connection;
	}
	
	public static Connection getConnection() {
		if (instance == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				instance = new DBConnection(connection);
				System.out.println("Connected Successfully");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return DBConnection.connection;
	}
	
	public static void closeConnection() {
		if (instance != null) {
			instance.closeConnection();
		}
	}
}