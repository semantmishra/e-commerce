package MyServlet.search;

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
 * Servlet implementation class AjaxLiveSearch
 */
@WebServlet("/ajax-live-search")
public class AjaxLiveSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection conn;
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			
			ResultSet set = statement.executeQuery("SELECT `id`, `title` FROM `products` WHERE `title` LIKE '%"+ keyword +"%'  LIMIT 10");
			while(set.next())
			{
				out.println("<p class='mx-4 search-tag p-2' id="+set.getInt("id") +">"+set.getString("title") +"</p>");
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
