package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import Beans.CategoryShowcase;
import Beans.CategoryShowcaseBean;
import database.DatabaseConnection;

public class CategoryShowcaseDao {

	public String saveOrUpdate(CategoryShowcaseBean showcaseBean) {
		
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT `id` FROM `category_showcase` WHERE `direction` = '"+showcaseBean.getDirection()+"'");
			if(resultSet.next())
			{
				PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `category_showcase` SET`image`=?,`lable`=? WHERE `direction` = ?");
				preparedStatement.setBinaryStream(1,showcaseBean.getInputStream(),showcaseBean.getInputStream().available());
				preparedStatement.setString(2,showcaseBean.getLable());
				preparedStatement.setString(3,showcaseBean.getDirection());
				int i = preparedStatement.executeUpdate();
				if(i==1)
				{
					return "update";
				}else {
					return "updatefaild";
				}
				
				
			}
			else{
				PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `category_showcase`(`image`, `lable`, `direction`) VALUES (?,?,?)");
				preparedStatement.setBinaryStream(1,showcaseBean.getInputStream(),showcaseBean.getInputStream().available());
				preparedStatement.setString(2,showcaseBean.getLable());
				preparedStatement.setString(3,showcaseBean.getDirection());
				int i = preparedStatement.executeUpdate();
				if(i==1)
				{
					return "done";
				}else {
					return "faild";
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "Error";
	}

	public CategoryShowcase get() {
		
		String[] dir = {"top-left","bottom-left","center","top-right","bottom-right"};
		
		Connection conn ;
		CategoryShowcase categoryShowcase = new CategoryShowcase();
		
		try {
			conn = DatabaseConnection.getConnection();
			for (String string : dir) {
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT `image`, `lable` FROM `category_showcase` WHERE `direction`= '"+string+"'");
				if(resultSet.next())
				{
					if(string =="top-left")
					{
						byte[] bty = resultSet.getBlob("image").getBytes(1,(int)resultSet.getBlob("image").length());
						
						categoryShowcase.setTopLeftImg( "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bty));
						
						categoryShowcase.setTopLeftLbl(resultSet.getString("lable"));
						
					}
					if(string =="bottom-left")
					{
						byte[] bty = resultSet.getBlob("image").getBytes(1,(int)resultSet.getBlob("image").length());
						categoryShowcase.setBottomLeftImg( "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bty));
						categoryShowcase.setBottomLeftLbl(resultSet.getString("lable"));
					}
					if(string =="center")
					{
						byte[] bty = resultSet.getBlob("image").getBytes(1,(int)resultSet.getBlob("image").length());
						categoryShowcase.setCenterImg( "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bty));
						categoryShowcase.setCenterLbl(resultSet.getString("lable"));
					}
					if(string =="top-right")
					{
						byte[] bty = resultSet.getBlob("image").getBytes(1,(int)resultSet.getBlob("image").length());
						categoryShowcase.setTopRightImg( "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bty));
						categoryShowcase.setTopRightLbl(resultSet.getString("lable"));
					}
					if(string =="bottom-right")
					{
						byte[] bty = resultSet.getBlob("image").getBytes(1,(int)resultSet.getBlob("image").length());
						categoryShowcase.setBottomRightImg( "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bty));
						categoryShowcase.setBottomRightLbl(resultSet.getString("lable"));
					}
					
				}
			}
			
			return categoryShowcase;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

public CategoryShowcase getDefault() {
	String sPath = "/ECommerce/employee_panel/images/small.jpg";
	String lPath = "/ECommerce/employee_panel/images/large.jpg";
		
		String[] dir = {"top-left","bottom-left","center","top-right","bottom-right"};
		
		Connection conn ;
		CategoryShowcase categoryShowcase = new CategoryShowcase();
		
		try {
			conn = DatabaseConnection.getConnection();
			for (String string : dir) {
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT `image`, `lable` FROM `category_showcase` WHERE `direction`= '"+string+"'");
				if(string =="top-left")
				{
					if(resultSet.next())
					{
						byte[] bty = resultSet.getBlob("image").getBytes(1,(int)resultSet.getBlob("image").length());
						
						categoryShowcase.setTopLeftImg( "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bty));
						
						categoryShowcase.setTopLeftLbl(resultSet.getString("lable"));
						
					}else {
						categoryShowcase.setTopLeftImg(sPath);
					}
				}
					if(string =="bottom-left")
					{
						if(resultSet.next())
						{
						byte[] bty = resultSet.getBlob("image").getBytes(1,(int)resultSet.getBlob("image").length());
						categoryShowcase.setBottomLeftImg( "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bty));
						categoryShowcase.setBottomLeftLbl(resultSet.getString("lable"));
						}
						else {
							categoryShowcase.setBottomLeftImg(sPath);
						}
					}
					
					if(string =="center")
					{
						if(resultSet.next())
						{
						byte[] bty = resultSet.getBlob("image").getBytes(1,(int)resultSet.getBlob("image").length());
						categoryShowcase.setCenterImg( "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bty));
						categoryShowcase.setCenterLbl(resultSet.getString("lable"));
						}
						else {
							categoryShowcase.setCenterImg(lPath);
						}
					}
					if(string =="top-right")
					{
						if(resultSet.next())
						{
						byte[] bty = resultSet.getBlob("image").getBytes(1,(int)resultSet.getBlob("image").length());
						categoryShowcase.setTopRightImg( "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bty));
						categoryShowcase.setTopRightLbl(resultSet.getString("lable"));
						}
						else {
							categoryShowcase.setTopRightImg(sPath);
						}
					}
					if(string =="bottom-right")
					{
						if(resultSet.next())
						{
						byte[] bty = resultSet.getBlob("image").getBytes(1,(int)resultSet.getBlob("image").length());
						categoryShowcase.setBottomRightImg( "data:image/jpg;base64,"+ Base64.getEncoder().encodeToString(bty));
						categoryShowcase.setBottomRightLbl(resultSet.getString("lable"));
						}
						else {
							categoryShowcase.setBottomRightImg(sPath);
						}
					}
					
				}
			
			return categoryShowcase;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
