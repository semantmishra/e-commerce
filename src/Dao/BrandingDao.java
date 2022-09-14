package Dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import Beans.BrandingBean;
import database.DatabaseConnection;

public class BrandingDao {
	
	public BrandingBean get()
	{
		BrandingBean b = new BrandingBean();
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT `id`, `brand_logo`, `brand_name`, `domain_name`, `email`, `facebook_url`, `twitter_url`, `address`, `phone`, `about_us`, `privacy_policy`, `cookies_policy`, `terms_policy` FROM `branding`");
	
			if(resultSet.next())
			{
			b.setId(resultSet.getInt("id"));
			b.setBrand_name(resultSet.getString("brand_name"));
				if(resultSet.getBlob("brand_logo")!=null)
				{
				Blob blob = resultSet.getBlob("brand_logo");
				byte[] byt = blob.getBytes(1,(int)blob.length());
				b.setBrand_logo("data:image/jpg;base64,"+Base64.getEncoder().encodeToString(byt));
				}
			
			b.setDomain_name(resultSet.getString("domain_name"));
			b.setEmail(resultSet.getString("email"));
			b.setFacebook_url(resultSet.getString("facebook_url"));
			b.setTwitter_url(resultSet.getString("twitter_url"));
			b.setAddress(resultSet.getString("address"));
			b.setPhone(resultSet.getString("phone"));
			b.setAbout_us(resultSet.getString("about_us"));
			b.setPrivacy_policy(resultSet.getString("privacy_policy"));
			b.setCookies_policy(resultSet.getString("cookies_policy"));
			b.setTerms_policy(resultSet.getString("terms_policy"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	

		return b;
		
	}

	public String save(BrandingBean b)
	{
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
		PreparedStatement statement = conn.prepareStatement("INSERT INTO `branding`(`brand_name`,`domain_name`, `email`, `facebook_url`, `twitter_url`, `address`, `phone`, `about_us`, `privacy_policy`, `cookies_policy`, `terms_policy`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		//(`brand_name`,`domain_name`, `email`, `facebook_url`, `twitter_url`, `address`, `phone`, `about_us`, `privacy_policy`, `cookies_policy`, `terms_policy`
		statement.setString(1, b.getBrand_name());
		statement.setString(2, b.getDomain_name());
		statement.setString(3, b.getEmail());
		statement.setString(4, b.getFacebook_url());
		statement.setString(5, b.getTwitter_url());
		statement.setString(6, b.getAddress());
		statement.setString(7, b.getPhone());
		statement.setString(8, b.getAbout_us());
		statement.setString(9, b.getPrivacy_policy());
		statement.setString(10, b.getCookies_policy());
		statement.setString(11, b.getTerms_policy());
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

	
	public String updateWithImage(BrandingBean b) {
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
		PreparedStatement statement = conn.prepareStatement("UPDATE `branding` SET `brand_name` = ?,`brand_logo` = ?,`domain_name` = ?, `email` = ?, `facebook_url` = ?, `twitter_url` =?, `address` = ?, `phone` = ?, `about_us` = ?, `privacy_policy` = ?, `cookies_policy` = ?, `terms_policy` =? where id =?");

		statement.setString(1, b.getBrand_name());
		
		statement.setBinaryStream(2,b.getInputStream(),b.getInputStream().available());
		statement.setString(3, b.getDomain_name());
		statement.setString(4, b.getEmail());
		statement.setString(5, b.getFacebook_url());
		statement.setString(6, b.getTwitter_url());
		statement.setString(7, b.getAddress());
		statement.setString(8, b.getPhone());
		statement.setString(9, b.getAbout_us());
		statement.setString(10, b.getPrivacy_policy());
		statement.setString(11, b.getCookies_policy());
		statement.setString(12, b.getTerms_policy());
		statement.setInt(13, b.getId());
		int i = statement.executeUpdate();
		if(i==1)
		{
			return "update";
		}
		else {
			return "faild";
		}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "faild";
	}

	public String update(BrandingBean b) {
		
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
		PreparedStatement statement = conn.prepareStatement("UPDATE `branding` SET `brand_name`=?,`domain_name`=?, `email`=?, `facebook_url`=?, `twitter_url`=?, `address`=?, `phone`=?, `about_us`=?, `privacy_policy`=?, `cookies_policy`=?, `terms_policy`=? WHERE id = ?");
		statement.setString(1, b.getBrand_name());
		statement.setString(2, b.getDomain_name());
		statement.setString(3, b.getEmail());
		statement.setString(4, b.getFacebook_url());
		statement.setString(5, b.getTwitter_url());
		statement.setString(6, b.getAddress());
		statement.setString(7, b.getPhone());
		statement.setString(8, b.getAbout_us());
		statement.setString(9, b.getPrivacy_policy());
		statement.setString(10, b.getCookies_policy());
		statement.setString(11, b.getTerms_policy());
		statement.setInt(12, b.getId());
		int i = statement.executeUpdate();
		if(i==1)
		{
			return "update";
		}
		else {
			return "faild";
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "faild";
	}

	public String saveWithImage(BrandingBean b) {
		
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
		PreparedStatement statement = conn.prepareStatement("INSERT INTO `branding`(`brand_logo`,`brand_name`,`domain_name`, `email`, `facebook_url`, `twitter_url`, `address`, `phone`, `about_us`, `privacy_policy`, `cookies_policy`, `terms_policy`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
		//(`brand_name`,`domain_name`, `email`, `facebook_url`, `twitter_url`, `address`, `phone`, `about_us`, `privacy_policy`, `cookies_policy`, `terms_policy`
		statement.setBinaryStream(1, b.getInputStream(),b.getInputStream().available());
		statement.setString(2, b.getBrand_name());
		statement.setString(3, b.getDomain_name());
		statement.setString(4, b.getEmail());
		statement.setString(5, b.getFacebook_url());
		statement.setString(6, b.getTwitter_url());
		statement.setString(7, b.getAddress());
		statement.setString(8, b.getPhone());
		statement.setString(9, b.getAbout_us());
		statement.setString(10, b.getPrivacy_policy());
		statement.setString(11, b.getCookies_policy());
		statement.setString(12, b.getTerms_policy());
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
