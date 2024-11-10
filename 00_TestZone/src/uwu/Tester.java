package uwu;

import java.sql.*;

public class Tester {
	
	public static void main(String[] args) {
		
		
		
	}
	
	public static Connection getConexion() throws SQLException {
		
		String urlDDBB = "jdbc:mysql://localhost:3306/biblioteca";
		String user = "root";
		String contrasenia = "root";
		return DriverManager.getConnection(urlDDBB, user, contrasenia);
		
	}
}
