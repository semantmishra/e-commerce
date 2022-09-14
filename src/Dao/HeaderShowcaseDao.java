package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import Beans.HeaderShowcaseBean;
import database.DatabaseConnection;

public class HeaderShowcaseDao {
	ArrayList<HeaderShowcaseBean>  al = new ArrayList<HeaderShowcaseBean>();
	public String save(HeaderShowcaseBean bean)
	{
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();		
			
			PreparedStatement statement = conn.prepareStatement("INSERT INTO `header_showcase`(`title_image`, `title_text`, `title_size`, `title_color`, `subtitle_text`, `subtitle_size`, `subtitle_color`, `v_align`, `h_align`, `buttons`) VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			statement.setBinaryStream(1,bean.getInputStream(),bean.getInputStream().available());
			
			statement.setString(2, bean.getTitle_text());
			statement.setString(3, bean.getTitle_size());
			statement.setString(4,bean.getTitle_color());
			statement.setString(5,bean.getSubtitle_text());
			statement.setString(6,bean.getSubtitle_size());
			statement.setString(7,bean.getSubtitle_color());
			statement.setString(8,bean.getV_align());
			statement.setString(9,bean.getH_align());
			statement.setString(10,bean.getButtons());
			
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
		}
		
		return "faild";
	}

	
	public boolean limitFull()
	{
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement1 = conn.createStatement();
			ResultSet rs = statement1.executeQuery("SELECT COUNT(`id`) as `row` FROM `header_showcase`");
			if(rs.next())
			{
				int num = rs.getInt("row");
				if(num>=3)
				{
					return true;
				}
				else {
					return false;
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public ArrayList<HeaderShowcaseBean> get()
	{
		
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT `id`, `title_image`, `title_text`, `title_size`, `title_color`, `subtitle_text`, `subtitle_size`, `subtitle_color`, `v_align`, `h_align`, `buttons` FROM `header_showcase`");
			while(resultSet.next())
			{
				HeaderShowcaseBean bean = new HeaderShowcaseBean();
				bean.setId(resultSet.getInt("id"));
				
				bean.setTitle_image("data:image/jpg;base64,"+Base64.getEncoder().encodeToString(resultSet.getBlob("title_image").getBytes(1, (int)resultSet.getBlob("title_image").length())));
				bean.setTitle_text(resultSet.getString("title_text"));
				bean.setTitle_size(resultSet.getString("title_size"));
				bean.setTitle_color(resultSet.getString("title_color"));
				bean.setSubtitle_text(resultSet.getString("subtitle_text"));
				bean.setSubtitle_size(resultSet.getString("subtitle_size"));
				bean.setSubtitle_color(resultSet.getString("subtitle_color"));
				bean.setTitle_text(resultSet.getString("title_text"));
				//`v_align`, `h_align`, `buttons`
				bean.setV_align(resultSet.getString("v_align"));
				bean.setH_align(resultSet.getString("h_align"));
				bean.setButtons(resultSet.getString("buttons"));
				al.add(bean);				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return al;
		
	}

	public ArrayList<HeaderShowcaseBean> get(int id)
{
		
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT `id`, `title_image`,`title_text`, `title_size`, `title_color`, `subtitle_text`, `subtitle_size`, `subtitle_color`, `v_align`, `h_align`, `buttons` FROM `header_showcase` where id ="+id);
			while(resultSet.next())
			{
				HeaderShowcaseBean bean = new HeaderShowcaseBean();
				bean.setId(resultSet.getInt("id"));
				
				bean.setTitle_image("data:image/jpg;base64,"+Base64.getEncoder().encodeToString(resultSet.getBlob("title_image").getBytes(1, (int)resultSet.getBlob("title_image").length())));
				bean.setTitle_text(resultSet.getString("title_text"));
				bean.setTitle_size(resultSet.getString("title_size"));
				bean.setTitle_color(resultSet.getString("title_color"));
				bean.setSubtitle_text(resultSet.getString("subtitle_text"));
				bean.setSubtitle_size(resultSet.getString("subtitle_size"));
				bean.setSubtitle_color(resultSet.getString("subtitle_color"));
				bean.setTitle_text(resultSet.getString("title_text"));
				//`v_align`, `h_align`, `buttons`
				bean.setV_align(resultSet.getString("v_align"));
				bean.setH_align(resultSet.getString("h_align"));
				bean.setButtons(resultSet.getString("buttons"));
				al.add(bean);				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return al;	
	}


	public String delete(int id) {
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement =  conn.createStatement();
			int i = statement.executeUpdate("DELETE FROM `header_showcase` WHERE `id` ="+id);
			if(i==1)
			{
				return "done";
			}
			else {
				return "faild";
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "faild";
	}


	public String update(HeaderShowcaseBean bean) {
		
		Connection conn;
		String sql = "";
		if(bean.getFile_data()!=null)
		{
			sql = "UPDATE `header_showcase` SET `title_image`=?,`title_text`=?,`title_size`=?,`title_color`=?,`subtitle_text`=?,`subtitle_size`=?,`subtitle_color`=?,`v_align`=?,`h_align`=?,`buttons`=? WHERE `id` = ?";
		}
		else {
			sql = "UPDATE `header_showcase` SET `title_text`=?,`title_size`=?,`title_color`=?,`subtitle_text`=?,`subtitle_size`=?,`subtitle_color`=?,`v_align`=?,`h_align`=?,`buttons`=? WHERE `id` = ?";
		}
		try {
			conn = DatabaseConnection.getConnection();		
			PreparedStatement statement = conn.prepareStatement(sql);
			if(bean.getFile_data()!=null)
			{
				statement.setBinaryStream(1,bean.getInputStream(),bean.getInputStream().available());
				statement.setString(2, bean.getTitle_text());
				statement.setString(3, bean.getTitle_size());
				statement.setString(4,bean.getTitle_color());
				statement.setString(5,bean.getSubtitle_text());
				statement.setString(6,bean.getSubtitle_size());
				statement.setString(7,bean.getSubtitle_color());
				statement.setString(8,bean.getV_align());
				statement.setString(9,bean.getH_align());
				statement.setString(10,bean.getButtons());
				statement.setInt(11,bean.getId());
			}
			else {
				
				statement.setString(1, bean.getTitle_text());
				statement.setString(2, bean.getTitle_size());
				statement.setString(3,bean.getTitle_color());
				statement.setString(4,bean.getSubtitle_text());
				statement.setString(5,bean.getSubtitle_size());
				statement.setString(6,bean.getSubtitle_color());
				statement.setString(7,bean.getV_align());
				statement.setString(8,bean.getH_align());
				statement.setString(9,bean.getButtons());
				statement.setInt(10,bean.getId());	
			}
			
			
			
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
		}
		
		return "faild";

	}






}
