package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Utilites.Mail;
import Utilites.RendomNun;
import database.DatabaseConnection;

public class UserLogin {
	private String fullname;
	private String email;
	private String mobile;
	private String status;
	private String pincode;
	
	
	public String login(String email,String pass) {
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT `firstname`, `lastname`, `email`, `mobile`, `password`, `status` FROM `users` WHERE `email`=? AND `password` = ?");
			statement.setString(1, email);
			statement.setString(2, pass);
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next())
			{
				this.email = resultSet.getString("email");
				this.status = resultSet.getString("status");
				if(this.status.equals("pending"))
				{
						return "pending" ;
				}
				else{
					return "done" ;
				}
			}
			else {
				return "invalid";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "faild";
	}

	
	public String getFullname(String email)
	{
		
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT `firstname`, `lastname`,`pincode`  FROM `users` WHERE email = '"+email+"'");
			if(resultSet.next())
			{
				this.fullname = resultSet.getString("firstname")+" "+resultSet.getString("lastname");
				this.pincode = resultSet.getString("pincode");
				
				return this.fullname+","+this.pincode;
				
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "no";
	}
}
