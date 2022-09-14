package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Beans.CartBean;
import database.DatabaseConnection;

public class CartDao {

	
	
	public String save(int productId, String userName) {
		// TODO Auto-generated method stub
		Connection conn;
		if(productNotExist(userName,productId))
		{
			
		
			try {
				
				conn = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `cart`(`product_id`, `username`) VALUES (?,?)");
				preparedStatement.setInt(1, productId);
				preparedStatement.setString(2, userName);
				int i = preparedStatement.executeUpdate();
				if(i==1) {
					return "done";
				}
				else {
					return "faild";
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			return "exist";
		}
		return "faild";
	}

	public ArrayList<CartBean> get(String user) {
		Connection conn;
		ArrayList<CartBean> al = new ArrayList<CartBean>(); 
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT cart.id as id,products.id as productid,products.brands as brands , products.title as title, products.price as price, products.thumb_pic as thumb FROM `cart` LEFT JOIN products ON cart.product_id = products.id WHERE cart.username= '"+user+"'");
			while(resultSet.next())
			{
				CartBean bean = new CartBean();
				bean.setId(resultSet.getInt("id"));
				bean.setProductId(resultSet.getInt("productid"));
				bean.setProduct_bands(resultSet.getString("brands"));
				bean.setTitle(resultSet.getString("title"));
				bean.setPrice(resultSet.getFloat("price"));
				bean.setThumb(resultSet.getString("thumb"));
				al.add(bean);
			}
			return al;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return al;
	}

	public int count(String user) {
		Connection conn ;
		int total;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) as total FROM cart WHERE username = '"+user+"'");
			if(resultSet.next())
			{
				 total = resultSet.getInt("total");
			}
			else {
				 total = 0;
			}
			return total;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}

	public String delete(String sql) {
		
		Connection conn ;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql);
			if(i==1)
			{
				return "done";
			}else {
				return "fail";
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "fail";
	}

	public boolean productNotExist(String username,int id)
	{
		Connection conn;
		boolean value;
		try {
			
			conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT `username` FROM `cart` WHERE `product_id`=? AND `username` = ?");
			ps.setInt(1,id);
			ps.setString(2 ,username);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				value = false;
				
			}
			else {
				value = true;
			}
		return value;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
