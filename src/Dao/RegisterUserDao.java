package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Beans.RegisterUserBean;
import database.DatabaseConnection;

public class RegisterUserDao {
	
	public String save(RegisterUserBean bean)
	{
		Connection conn ;
		try {
			
			conn = DatabaseConnection.getConnection();																													
			PreparedStatement statement = conn.prepareStatement("INSERT INTO `users`(`firstname`, `lastname`, `email`, `mobile`, `password`,`address`,`state`,`pincode`,`country`) VALUES (?,?,?,?,?,?,?,?,?)");
			statement.setString(1,bean.getFirstname());
			statement.setString(2,bean.getLastname());
			statement.setString(3,bean.getEmail());
			statement.setString(4,bean.getMobile());
			statement.setString(5,bean.getPassword());
			statement.setString(6,bean.getAddress());
			statement.setString(7,bean.getState());
			statement.setInt(8,bean.getPincode());
			statement.setString(9,bean.getCountry());
			int i = statement.executeUpdate();
			if(i==1)
			{
				return "done";
			}
			else {
				return "faild";
			}
			
					
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "exception";
		
	}

	public String otpVerify(String user_email) {
		
		Connection conn ;
		try {
			
			conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("UPDATE `users` SET `status`=? WHERE `email` = ?");
			
			statement.setString(1,"active");
			statement.setString(2,user_email);
			int i= statement.executeUpdate();
			if(i==1)
			{
				return "done";
			}
		
		}
		catch(Exception e) {
			
		}
		return "faild";
	}

	public RegisterUserBean get(String username) {
		
		Connection conn;
		try {
			
			RegisterUserBean bean = new RegisterUserBean();
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT `id`, `firstname`, `lastname`, `email`, `mobile`, `address`, `state`, `country`,`pincode` FROM `users` WHERE `email` = '"+username+"'");
			if(resultSet.next())
			{
				bean.setId(resultSet.getInt("id"));
				bean.setFirstname(resultSet.getString("firstname"));
				bean.setLastname(resultSet.getString("lastname"));
				bean.setEmail(resultSet.getString("email"));
				bean.setMobile(resultSet.getString("mobile"));
				bean.setAddress(resultSet.getString("address"));
				bean.setState(resultSet.getString("state"));
				bean.setCountry(resultSet.getString("country"));
				bean.setPincode(resultSet.getInt("pincode"));
								
				return bean;
				
			}else {
				return null;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
		
	}

	public String update(RegisterUserBean UserBean) {
		
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("UPDATE users SET "
					+ "`firstname` =?, `lastname` =?,`mobile`=?, `address`=?, `state`=?,"
					+ " `country`=?,`pincode`=? WHERE email = ?");
			
			statement.setString(1,UserBean.getFirstname() );
			statement.setString(2,UserBean.getLastname() );
			statement.setString(3,UserBean.getMobile() );
			statement.setString(4,UserBean.getAddress() );
			statement.setString(5,UserBean.getState() );
			statement.setString(6,UserBean.getCountry() );
			statement.setInt(7,UserBean.getPincode() );
			statement.setString(8,UserBean.getEmail() );
			int i=statement.executeUpdate();
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

	public String changePassword(String username ,String oldpassword,String newpassword) {
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `users` SET `password`=? WHERE `email` = ? AND `password` = ?");
			preparedStatement.setString(1,newpassword.trim());
			preparedStatement.setString(2,username.trim());
			preparedStatement.setString(3,oldpassword.trim());
			int i = preparedStatement.executeUpdate();
			if(i==1)
			{
				return "done";
			}else {
				return "invalid";
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return "fail";
	}

	public boolean exist(String sql) {
		
		Connection conn ;
		try {
			
			Statement statement = DatabaseConnection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next())
			{
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
		
		}
		return true;
	}

}
