package it.Carloan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLConnection {
	
	
	//CONNESSIONE AL DATABASE
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/carloan";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root"; 
//FINE CONNESSIONE AL DATABASE
	
	
	/*public static Connection connetti() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prova", "root", "root");
			return conn;
		} catch (Exception e) {
			return null;
		}
		
	}*/
	public Connection getDBConnection() {	
		System.out.println("------------ Connessione MySQL JDBC ------------");
		Connection dbConnection = null;	 

		try {	 
			Class.forName(DB_DRIVER);	 
		} catch (ClassNotFoundException e) {	 
			System.out.println(e.getMessage());	
			System.out.println("ERROR: MySQL JDBC Driver non trovato!!");
		}

		try {	 
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			System.out.println("Connessione al DB Carloan eseguita correttamente!");
			return dbConnection;	 
		} catch (SQLException e) {	 
			System.out.println(e.getMessage());	 
			System.out.println("Connessione al DB Carloan non eseguita!");
		}	 
		return dbConnection;	 
	}

	
}
