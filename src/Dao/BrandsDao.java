package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import Beans.BrandsBean;
import Beans.BrandsName;
import database.DatabaseConnection;

public class BrandsDao {
	ArrayList<BrandsBean> al = new ArrayList<BrandsBean>();

	public String Save(String category,String[] brands) {
		try {
			
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement =  conn.prepareStatement("INSERT INTO `brands`(`category_name`, `brnads`) VALUES (?,?)");

			for (String brand : brands) {

				preparedStatement.setString(1, category);
				preparedStatement.setString(2, brand);
				preparedStatement.addBatch();
			}
			
			int i = preparedStatement.executeBatch().length;
			if(i==brands.length)
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

	public ArrayList<BrandsBean> select(String string,String category) {

		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(string);
			statement.setString(1,category);
			java.sql.ResultSet resultSet =  statement.executeQuery();
			
				while(resultSet.next())
				{
					BrandsBean b = new BrandsBean();				
					b.setId(resultSet.getInt("id"));	
					b.setC_name(resultSet.getString("category_name"));
					b.setBrands(resultSet.getString("brnads"));
					al.add(b);
				}
				return al;	
			
			
			
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	public String delete(int id) {
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("DELETE FROM `brands` WHERE `id` = ?");
			statement.setInt(1, id);
			int i = statement.executeUpdate();
			if(i==1)
			{
				return "done";
			}
			else{
				return "faild";
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "faild";
	}

	public String update(int id, String c_name, String b_name) {
		
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("UPDATE `brands` SET `category_name`=?,`brnads`=? WHERE `id`=?");
			statement.setString(1, c_name);
			statement.setString(2, b_name);
			statement.setInt(3, id);
			int i = statement.executeUpdate();
			if(i==1)
			{
				return "done";
			}
			else{
				return "faild";
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "faild";
	}

	public ArrayList<BrandsBean> getBrends() {
		ArrayList<BrandsBean> al = new ArrayList<BrandsBean>();
		Connection conn ;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			java.sql.ResultSet rs = statement.executeQuery("SELECT `category_name`, `brnads` FROM `brands`");
			while(rs.next())
			{				
				BrandsBean b = new BrandsBean();				
					
				b.setC_name(rs.getString("category_name"));
				b.setBrands(rs.getString("brnads"));
				al.add(b);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return al;
	}

}
