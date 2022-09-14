package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.DisplayPurchaseItemBean;
import Beans.ProductBean;
import database.DatabaseConnection;

/**
 * Servlet implementation class SortBy
 */
@WebServlet("/sort-by")
public class SortBy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.setContentType("text/json");
		PrintWriter out =response.getWriter(); 
		String option = request.getParameter("option");
		String sql = "";
		
		if(option!=null) 
		{
			java.sql.Date date = new Date(new java.util.Date().getTime());
			
			switch (option) {
			case "todays-sales":
				
				sql = "SELECT purchase.id as 'id',purchase.product_id as 'p_id', products.title as 'title'," 
						+"purchase.qnt as 'qnt', (products.price * purchase.qnt) as 'price',users.address as 'Address',"
						+"users.state as 'state', users.country as 'country',  users.pincode as 'pincode'," 
						+"purchase.purchase_date as 'p_date', CONCAT(users.firstname,' ',users.lastname) as 'name',"
						+" username,users.mobile as 'mobile',purchase.status as 'status',purchase.dispatched_date as 'date',purchase.deliver_date as 'd_date' FROM `purchase`"
						+" LEFT JOIN users ON users.email = purchase.username"
						+" LEFT JOIN products ON products.id = purchase.product_id WHERE purchase.purchase_date='"+date+"'";

				break;
			case "new-sales":
				sql = "SELECT purchase.id as 'id',purchase.product_id as 'p_id', products.title as 'title'," 
						+"purchase.qnt as 'qnt', (products.price * purchase.qnt) as 'price',users.address as 'Address',"
						+"users.state as 'state', users.country as 'country',  users.pincode as 'pincode'," 
						+"purchase.purchase_date as 'p_date', CONCAT(users.firstname,' ',users.lastname) as 'name',"
						+" username,users.mobile as 'mobile',purchase.status as 'status',purchase.dispatched_date as 'date',purchase.deliver_date as 'd_date' FROM `purchase`"
						+" LEFT JOIN users ON users.email = purchase.username"
						+" LEFT JOIN products ON products.id = purchase.product_id ORDER BY purchase.purchase_date DESC ";
				break;
			case "processing":
				sql = "SELECT purchase.id as 'id',purchase.product_id as 'p_id', products.title as 'title'," 
						+"purchase.qnt as 'qnt', (products.price * purchase.qnt) as 'price',users.address as 'Address',"
						+"users.state as 'state', users.country as 'country',  users.pincode as 'pincode'," 
						+"purchase.purchase_date as 'p_date', CONCAT(users.firstname,' ',users.lastname) as 'name',"
						+" username,users.mobile as 'mobile',purchase.status as 'status',purchase.dispatched_date as 'date',purchase.deliver_date as 'd_date' FROM `purchase`"
						+" LEFT JOIN users ON users.email = purchase.username"
						+" LEFT JOIN products ON products.id = purchase.product_id WHERE purchase.status = 'processing' ";
				break;
			case "dispatched":
				sql = "SELECT purchase.id as 'id',purchase.product_id as 'p_id', products.title as 'title'," 
						+"purchase.qnt as 'qnt', (products.price * purchase.qnt) as 'price',users.address as 'Address',"
						+"users.state as 'state', users.country as 'country',  users.pincode as 'pincode'," 
						+"purchase.purchase_date as 'p_date', CONCAT(users.firstname,' ',users.lastname) as 'name',"
						+" username,users.mobile as 'mobile',purchase.status as 'status',purchase.dispatched_date as 'date',purchase.deliver_date as 'd_date' FROM `purchase`"
						+" LEFT JOIN users ON users.email = purchase.username"
						+" LEFT JOIN products ON products.id = purchase.product_id WHERE purchase.status = 'dispatched' ";
				break;
			case "returned":
				sql = "SELECT purchase.id as 'id',purchase.product_id as 'p_id', products.title as 'title'," 
						+"purchase.qnt as 'qnt', (products.price * purchase.qnt) as 'price',users.address as 'Address',"
						+"users.state as 'state', users.country as 'country',  users.pincode as 'pincode'," 
						+"purchase.purchase_date as 'p_date', CONCAT(users.firstname,' ',users.lastname) as 'name',"
						+" username,users.mobile as 'mobile',purchase.status as 'status',purchase.dispatched_date as 'date',purchase.deliver_date as 'd_date' FROM `purchase`"
						+" LEFT JOIN users ON users.email = purchase.username"
						+" LEFT JOIN products ON products.id = purchase.product_id WHERE purchase.status = 'returned' ";
				break;
			}
			
			ArrayList<DisplayPurchaseItemBean> list  = get(sql);
			out.println(new Gson().toJson(list));
		}
		
		
	}
	
	private ArrayList<DisplayPurchaseItemBean> get(String sql) {
	
		Connection conn ;
		ArrayList<DisplayPurchaseItemBean> list = new ArrayList<DisplayPurchaseItemBean>();
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				DisplayPurchaseItemBean d = new DisplayPurchaseItemBean();
						d.setId(rs.getInt("id"));
						d.setP_id(rs.getInt("p_id"));
						d.setTitle(rs.getString("title"));
						d.setQnt(rs.getString("qnt"));
						d.setPrice(rs.getString("price"));
						d.setAddress(rs.getString("Address"));
						d.setState(rs.getString("state"));
						d.setCountry(rs.getString("country"));
						d.setPincode(rs.getString("pincode"));
						d.setP_date(rs.getString("p_date"));
						d.setD_date(rs.getString("date"));
						d.setName(rs.getString("name"));
						d.setUsername(rs.getString("username"));
						d.setMobile(rs.getString("mobile"));
						d.setStatus(rs.getString("status"));

				list.add(d);
			}
			return list;
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	return list;
	
	}
	


}
