package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

import database.DatabaseConnection;

public class PayDao {

	public String save(String username, int product_id, int qnt,String paymode) {
		
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps1 = conn.prepareStatement("INSERT INTO `purchase`(`product_id`, `username`, `qnt`,`payment_mode`, `purchase_date`, `purchase_time`) VALUES (?,?,?,?,?,?)");
			ps1.setInt(1,product_id);
			ps1.setString(2,username);
			ps1.setInt(3,qnt);
			ps1.setString(4,paymode);
			ps1.setDate(5, new java.sql.Date(new Date().getTime()));
			ps1.setTime(6, new java.sql.Time(new Date().getTime()));
			
			int i = ps1.executeUpdate();
			if(i==1)
			{
				String msg = new CartDao().delete("DELETE FROM `cart` WHERE `username` = '"+ username +"' AND `product_id` ="+product_id);
				if(msg=="done")
				{
					PreparedStatement ps2 = conn.prepareStatement("UPDATE `products` SET `quantity`= quantity-?  WHERE `id`=?");
					ps2.setInt(1, qnt);
					ps2.setInt(2, product_id);
					ps2.executeUpdate();
					conn.commit();
					return "SUCCESS";
				}else {
					PreparedStatement ps2 = conn.prepareStatement("UPDATE `products` SET `quantity`= quantity-?  WHERE `id`=?");
					ps2.setInt(1, qnt);
					ps2.setInt(2, product_id);
					ps2.executeUpdate();
					conn.commit();
					return "SUCCESS";
				}
			}
			else {
				return "Faild";
			}
			
			
			
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
		return "Exception try again";
	}

}
