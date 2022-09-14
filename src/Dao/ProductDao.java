package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import Beans.ProductBean;
import database.DatabaseConnection;

public class ProductDao {
	
	public String save(ProductBean productBean) {
	
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("INSERT INTO `products`(`category_name`, `brands`, `title`, `description`, `price`, `quantity`, `thumb_pic`, `front_pic`, `top_pic`, `bottom_pic`, `left_pic`, `right_pic`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString(1,productBean.getCategory_name());
			statement.setString(2,productBean.getBrands());
			statement.setString(3,productBean.getTitle());
			statement.setString(4,productBean.getDescription());
			statement.setFloat(5,productBean.getPrice());
			statement.setString(6,productBean.getQuantity());
			statement.setString(7,productBean.getThumb_pic());
			statement.setString(8,productBean.getFront_pic());
			statement.setString(9,productBean.getTop_pic());
			statement.setString(10,productBean.getBottom_pic());
			statement.setString(11,productBean.getLeft_pic());
			statement.setString(12,productBean.getRight_pic());
		
			int i = statement.executeUpdate();
			if(i==1)
			{
				return "done";
			}
			else {
				return "faild";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "excepton";
	}

	public String getCategoryName(String brands) {
		String c_name="";
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT category_name from brands WHERE brnads = ?");
			statement.setString(1,brands);
			
			java.sql.ResultSet re =  statement.executeQuery();
			if(re.next()) {
				c_name = re.getString("category_name");	
			}
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return c_name;
	}

	public ArrayList<ProductBean> get(){
		
		Connection conn;
		ArrayList<ProductBean> al = new  ArrayList<ProductBean>();
		try {
			conn = DatabaseConnection.getConnection();
			Statement statement =conn.createStatement();
			java.sql.ResultSet rs =statement.executeQuery("SELECT `id`, `title`, `price`, `thumb_pic` FROM `products` ORDER BY RAND() LIMIT 12");
			
			while(rs.next())
			{
				ProductBean bean = new ProductBean();
				bean.setId(rs.getInt("id"));
				bean.setRating(new ShowRating().show(bean.getId()));

				bean.setTitle(rs.getString("title"));
				bean.setPrice(rs.getFloat("price"));
				bean.setThumb_pic(rs.getString("thumb_pic"));
				al.add(bean);
			}
			
			return al;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	// get produt by id
	public ProductBean getLetestProduct(){
		
		Connection conn;
		ArrayList<ProductBean> al = new  ArrayList<ProductBean>();
		try {
			conn = DatabaseConnection.getConnection();
			Statement statement =conn.createStatement();
			java.sql.ResultSet rs =statement.executeQuery("SELECT `id`, `title`, `description`, `price`,`brands` ,`front_pic` FROM `products` ORDER BY `id` DESC LIMIT 1");
			
			if(rs.next())
			{
				ProductBean bean = new ProductBean();
				bean.setId(rs.getInt("id"));
				bean.setTitle(rs.getString("title"));
				bean.setDescription(rs.getString("description"));
				bean.setPrice(rs.getFloat("price"));
				bean.setBrands(rs.getString("brands"));
				bean.setThumb_pic(rs.getString("front_pic"));
				
				return bean;
			}

			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	
	public ArrayList<String> get(String brands){
		
		Connection conn;
		ArrayList<String> al = new  ArrayList<String>();
		try {
			conn = DatabaseConnection.getConnection();
			Statement statement =conn.createStatement();
			java.sql.ResultSet rs =statement.executeQuery("SELECT `title` FROM `products` WHERE `brands` = '"+brands+"'");
			
			while(rs.next())
			{
				String z = rs.getString(1);
				
				al.add(z);
			}
			
			return  al;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	public String update(String sql) {
		
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

	public int getStock(int productId) {
		
		Connection conn ;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			java.sql.ResultSet rs = statement.executeQuery("SELECT `quantity` FROM `products` WHERE `id`="+productId);
			if(rs.next())
			{
				return rs.getInt("quantity");
			}
			else {
				return 0;		
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}
	
	
}
