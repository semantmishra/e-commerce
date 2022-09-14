package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Beans.ProductBean;
import database.DatabaseConnection;

public class FilterProducts {
	
	public ArrayList<ProductBean> filter(String sql)
	{
		Connection conn ;
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				ProductBean bean = new ProductBean();
				bean.setId(rs.getInt("id"));
				bean.setCategory_name(rs.getString("category_name"));
				bean.setCategory_name(rs.getString("category_name"));
				bean.setBrands(rs.getString("brands"));
				bean.setTitle(rs.getString("title"));
				bean.setDescription(rs.getString("description"));
				bean.setPrice(rs.getFloat("price"));
				bean.setThumb_pic(rs.getString("thumb_pic"));
				list.add(bean);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

}
