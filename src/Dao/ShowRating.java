package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DatabaseConnection;

public class ShowRating {
	
	private  int max = 0;
	
	private  int[] n = new int[5]; 
	private  int index;
	
	public  int show(int id) {
		String q = "SELECT COUNT(rating) as 'row' FROM `purchase` WHERE `product_id` = "+id+" AND rating = 1 ";
		String q1 = "SELECT COUNT(rating) as 'row' FROM `purchase` WHERE `product_id` = "+id+" AND rating = 2 ";
		String q2 = "SELECT COUNT(rating) as 'row' FROM `purchase` WHERE `product_id` = "+id+" AND rating = 3 ";
		String q3 = "SELECT COUNT(rating) as 'row' FROM `purchase` WHERE `product_id` = "+id+" AND rating = 4 ";
		String q4 = "SELECT COUNT(rating) as 'row' FROM `purchase` WHERE `product_id` = "+id+" AND rating = 5 ";
		n[0] = ShowRating.get(q);
		n[1] = ShowRating.get(q1);
		n[2] = ShowRating.get(q2);
		n[3] = ShowRating.get(q3);
		n[4] = ShowRating.get(q4);
		for (int i = 0; i < 5; i++) {
			if(max < n[i] )
			{
				max = n[i];
				index = i+1;
			}
		}
		
		
		return index;
	}
	
	private static int get(String sql) {
		
		try {
			
		Connection conn = DatabaseConnection.getConnection();
		Statement statement = conn.createStatement();
		ResultSet set = statement.executeQuery(sql);
		if(set.next())
		{
			int i = set.getInt("row");
			return i;
		}else {
			return 0;
		}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;	
	}

}
