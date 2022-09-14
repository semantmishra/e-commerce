package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import Beans.BrandsBean;
import database.DatabaseConnection;

public class MenuDao {
	
	public ArrayList<String> getMenu() {
		ArrayList<String> list  = new ArrayList<String>();
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			java.sql.ResultSet resultSet =  statement.executeQuery("SELECT `category_name` FROM `category`");
				while(resultSet.next())
				{
					String name = resultSet.getString("category_name");
					list.add(name);
				}

				return list;	

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
