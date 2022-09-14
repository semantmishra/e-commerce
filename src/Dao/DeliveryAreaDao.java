package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.taglibs.standard.extra.spath.Step;

import Beans.DefaultBean;
import Beans.DeliveryAreaBean;
import database.DatabaseConnection;

public class DeliveryAreaDao {
	
	public ArrayList<DefaultBean> getState(String sql) {
		
		Connection conn;
		ArrayList<DefaultBean> al = new ArrayList<DefaultBean>();
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery(sql);
			while(res.next())
			{
				DefaultBean bean =  new DefaultBean();
				bean.id = res.getInt(1);
				bean.name = res.getString(2);
				al.add(bean);
			}
			return al;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	public String save(DeliveryAreaBean areaBean) {
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			PreparedStatement  preparedStatement =conn.prepareStatement("INSERT INTO `delivery_area`(`country`, `state`, `city`, `pincode`, `days`, `payment_mode`) VALUES (?,?,?,?,?,?)");
			preparedStatement.setString(1, areaBean.getCoutry());
			preparedStatement.setString(2, areaBean.getState());
			preparedStatement.setString(3, areaBean.getCity());
			preparedStatement.setInt(4, areaBean.getPincode());
			preparedStatement.setString(5, areaBean.getDays());
			preparedStatement.setString(6, areaBean.getPayment_mode());
			int i = preparedStatement.executeUpdate();
			if(i==1)
			{
				return "done";
			}
			else {
				return "fails";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "exception";
	}

	public DeliveryAreaBean checkDelivery(String pin) {
		int pincode = Integer.parseInt(pin);
		
		DeliveryAreaBean areaBean = new DeliveryAreaBean();
		
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			Statement statement =conn.createStatement();
			ResultSet re = statement.executeQuery("SELECT  `pincode`, `days`, `payment_mode` FROM `delivery_area` WHERE `pincode` ="+pincode);
				if(re.next())
				{
					areaBean.setPincode(re.getInt("pincode"));
					areaBean.setPayment_mode(re.getString("payment_mode"));
					areaBean.setDays(re.getString("days"));
					return areaBean;
				}else {
					return null;
				}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
		
	}

}
