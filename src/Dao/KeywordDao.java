package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DatabaseConnection;

public class KeywordDao {

	public String save(String p_key,String s_key) {
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet res  = 	statement.executeQuery("SELECT id,s_key FROM `keywords` WHERE `p_key` = '"+p_key+"'");
			if(res.next())
			{
				String data = res.getString("s_key");
				data += ","+s_key;
				int i= statement.executeUpdate("UPDATE `keywords` SET `s_key`='"+data+"' WHERE `p_key` = '"+p_key+"'");
				if(i==1)
				{
					return "update";
				}else {
					return "update fail";
				}
			}else {
				
				int i = statement.executeUpdate("INSERT INTO `keywords`(`p_key`, `s_key`) VALUES ('"+p_key+"','"+s_key+"')");
				if(i==1)
				{
					return "done";
				}else {
					return "fail";
				}
					
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	public String select(String p_key) {
		Connection conn;
		String s_key="";
		try {
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet res  = 	statement.executeQuery("SELECT s_key FROM `keywords` WHERE `p_key` = '"+p_key+"'");
			if(res.next())
			{
				s_key = res.getString("s_key");
			}
			return s_key;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	public String delete(String[] data) {

		Connection conn;
		String msg="";
		try {
			for (String deldata : data) {
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT `id` FROM `keywords` WHERE `s_key` LIKE '%"+deldata.trim()+"%'");
				if(rs.next())
				{
					int i = statement.executeUpdate("DELETE FROM `failds_keywords` WHERE `keyword` = '"+deldata.trim()+"'");
						if(i==1)
						{
							msg = "done";
							
						}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
}
