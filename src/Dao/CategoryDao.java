package Dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Beans.CategoryBean;
import database.DatabaseConnection;

public class CategoryDao {
	ArrayList<CategoryBean> al = new ArrayList<CategoryBean>();
	String[] mobiles = {};
	
public CategoryDao() {}

public CategoryDao(String[] string ) {
	this.mobiles = string;
}

public String Save() {
	try {
		
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement =  conn.prepareStatement("INSERT INTO `category`(`category_name`) VALUES (?)");

		for (String mobile : this.mobiles) {

			preparedStatement.setString(1, mobile);
			preparedStatement.addBatch();
		}
		
		int i = preparedStatement.executeBatch().length;
		if(i==this.mobiles.length)
		{
			return "done";
		}
		else {
			return "fail";
			}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "fail";
}

public ArrayList<CategoryBean> select(String query) {
	Connection conn =null;

	try {
			conn = DatabaseConnection.getConnection();
			
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				CategoryBean cb = new CategoryBean();
				cb.setId(resultSet.getInt("id"));
				cb.setCategoryName(resultSet.getString("category_name"));				
				 al.add(cb);
			}		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return al;
	
}

public String update(int id, String name) {
	
	Connection conn ;
	try {
		conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `category` SET `category_name`=? WHERE `id` =?");
		preparedStatement.setString(1,name);
		preparedStatement.setInt(2,id );
		
		int i = preparedStatement.executeUpdate();
		if(i==1)
		{
			return "done";
		}else {
			return "faild";
		}
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	return "faild";
}

public String delete(int id) {
	
	Connection conn ;
	try {
		conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement("DELETE from `category` WHERE `id` = ?");
		preparedStatement.setInt(1,id );
		
		int i = preparedStatement.executeUpdate();
		if(i==1)
		{
			return "done";
		}else {
			return "faild";
		}
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "faild";
}
	
}
