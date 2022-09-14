package MyServlet.export;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseConnection;

/**
 * Servlet implementation class ExportExcel
 */
@WebServlet("/export-to-excel")
public class ExportExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Content-Disposition","attachment;filename=purchase_entry.xls");
		response.setContentType("Content-Type:application/xls");
		
		PrintWriter out = response.getWriter();
		String html = "";
		Connection conn;
		
		try {
			String sql = "SELECT purchase.id as 'id',purchase.product_id as 'p_id', products.title as 'title', " + 
					"purchase.qnt as 'qnt', (products.price * purchase.qnt) as 'price',users.address as 'Address'," + 
					"users.state as 'state', users.country as 'country',  users.pincode as 'pincode'," + 
					"purchase.purchase_date as 'p_date', CONCAT(users.firstname,' ',users.lastname) as 'name'," + 
					"username,users.mobile as 'mobile',purchase.status as 'status',purchase.dispatched_date as 'date' FROM `purchase` " + 
					"LEFT JOIN users ON users.email = purchase.username " + 
					"LEFT JOIN products ON products.id = purchase.product_id";
			conn = DatabaseConnection.getConnection();
			Statement s = conn.createStatement();
			ResultSet rs  = s.executeQuery(sql);
			html +="<table class='w-100 text-center table table-bordered bg-white'>";
			html +="<tr>"
					+ "<th>S/NO</th>"
					+ "<th>PRODUCT</th>"
					+ "<th>TITLE</th>"
					+ "<th>QUANTITY</th>"
					+ "<th>PRICE</th>"
					+ "<th>ADDRESS</th>"
					+ "<th>STATE</th>"
					+ "<th>COUNTRY</th>"
					+ "<th>PINCODE</th>"
					+ "<th>PURCHASE DATE</th>"
					+ "<th>CUSTOMER NAME</th>"
					+ "<th>USERNAME</th>"
					+ "<th>MOBILE</th>"
					+ "<th>STATUS</th>"
					+ "</tr>";
			
			while(rs.next())
			{
				html +="<tr>"
							+ "<td>"+rs.getInt("id")+"</td>"
							+ "<td>"+rs.getInt("p_id")+"</td>"
							+ "<td>"+rs.getString("title")+"</td>"
							+ "<td>"+rs.getString("qnt")+"</td>"
							+ "<td>"+rs.getString("price")+"</td>"
							+ "<td>"+rs.getString("address")+"</td>"
							+ "<td>"+rs.getString("state")+"</td>"
							+ "<td>"+rs.getString("country")+"</td>"
							+ "<td>"+rs.getString("pincode")+"</td>"
							+ "<td>"+rs.getString("p_date")+"</td>"
							+ "<td>"+rs.getString("name")+"</td>"
							+ "<td>"+rs.getString("username")+"</td>"
							+ "<td>"+rs.getString("mobile")+"</td>"
							+ "<td>"+rs.getString("status")+"</td>"
						+ "</tr>";
				
			}
			html +="</table>";
			out.println(html);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
