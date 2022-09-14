package MyServlet.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseConnection;

/**
 * Servlet implementation class SearchResult
 */
@WebServlet("/search-result")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String keyword = request.getParameter("search");
		String html = "";
		
		String sql = "SELECT `id`, `category_name`, `brands`, `title`, `price`,`thumb_pic` FROM `products` WHERE `title` LIKE '%"+ keyword +"%'  LIMIT 10";
		if(keyword!=null)
		{
			html = search(sql);
			
			if(html.equals(""))
			{
				sql = "SELECT `id`, `category_name`, `brands`, `title`, `price`,`thumb_pic` FROM `products` WHERE `category_name` LIKE '%"+ keyword +"%'  LIMIT 10";
				html = search(sql);
				if(html.equals(""))
				{
					sql = "SELECT `id`, `category_name`, `brands`, `title`, `price`,`thumb_pic` FROM `products` WHERE `brands` LIKE '%"+ keyword +"%'  LIMIT 10";
					html = search(sql);
					if(html.equals(""))
					{
						// find from s_key
						sql = "SELECT `p_key` FROM `keywords` WHERE `s_key` LIKE '%"+keyword+"%'";
						String p_key = get_p_key(sql);
						if(!p_key.equals(""))
						{
							
							sql = "SELECT `id`, `category_name`, `brands`, `title`, `price`,`thumb_pic` FROM `products` WHERE `category_name` LIKE '%"+ p_key +"%' OR `brands` LIKE '%"+ p_key +"%'OR `title` LIKE '%"+ p_key +"%'  LIMIT 10";
							html = search(sql);
							if(html.equals(""))
							{
								if(saveFailKeyWord(keyword))
								{
									html = "product not found";
								}else {
									html = "Try again ";		
								}
							}
								
								
						}
						else {
								if(saveFailKeyWord(keyword))
								{
									html = "product not found";
								}else {
									html = "Try again ";		
								}
						
						}
					}
				}
			}
		}
						
					
		
		request.setAttribute("html",html);
		
		RequestDispatcher rd = request.getRequestDispatcher("jsppage/search-result.jsp");
		rd.forward(request, response);
	}

	private String search(String sql) {
		String html = "";
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			
			ResultSet set = statement.executeQuery(sql);
			
			while(set.next())
			{
				html += "<div class='bg-white text-center border shadow-sm p-3 mb-4 mr-3'>";
				html += " <img src='"+set.getString("thumb_pic")+"' width='250' height='316'/>";
				html +="<br> <span class='text-uppercase fony-weight-bold'> "+ set.getString("brands") +" </span> ";
				html +="<br> <span class='text-uppercase'> "+ set.getString("title") +" </span> ";
				html +="<br> <span class='text-uppercase'><i class='fa fa-rupee'></i> "+ set.getString("price") +" </span> ";
				html +="<br><button type='button' product_id = "+set.getString("id")+" class='mr-2 btn btn-danger mt-3 cart-btn'><i class='fa fa-shopping-cart'></i>ADD TO CART</button>"; 
				html +="<button type='button' product_id ="+set.getString("id")+" class='buy-btn btn btn-primary mt-3'>BUY NOW</button> ";	
				html += "</div>";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return html;
	}

	
	private String get_p_key(String sql) {
		String p_key = "";
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			
			ResultSet set = statement.executeQuery(sql);
			
			while(set.next())
			{
				p_key = set.getString("p_key");			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return p_key;
	}
	
	
	private boolean saveFailKeyWord(String keyword) {
		
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate("INSERT INTO `failds_keywords`(`keyword`) VALUES ('"+keyword+"')");
			if(i==1)
			{
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return false;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
	}

}
