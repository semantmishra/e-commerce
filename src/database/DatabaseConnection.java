package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection{
private DatabaseConnection(){}
public static Connection getConnection() {
		Connection conn = null;
		try {
			
			Class.forName(DBInfo.DATABASE_DRIVER);
			conn = DriverManager.getConnection(DBInfo.DABABASE_URL,DBInfo.DATABASE_USER,DBInfo.DATABASE_PASSWORD);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
