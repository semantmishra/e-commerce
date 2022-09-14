package database;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class CreateTable {
	static String query;
	CreateTable(String query){
		this.query = query;
	}
	
	public static void Create(String query)

	{
		Connection conn=null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",DBInfo.DATABASE_USER,DBInfo.DATABASE_PASSWORD);
			//conn = DatabaseConnection.getConnection();
			Statement s = conn.createStatement();
			s.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
