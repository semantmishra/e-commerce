package MyServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.RatingBean;
import Dao.RegisterUserDao;
import Utilites.Images;
import database.DatabaseConnection;

/**
 * Servlet implementation class Rating
 */
@WebServlet("/rating")
@MultipartConfig
public class Rating extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		String username = (String)request.getSession().getAttribute("user");
		if(username==null)
		{
			response.sendRedirect("signin.jsp");
		}
		else{
			int rating =Integer.parseInt( request.getParameter("rating"));
			int id = Integer.parseInt(request.getParameter("id"));
			String comment =request.getParameter("comment");
			InputStream in =  request.getPart("picture").getInputStream();
			System.out.println(rating+" "+id+" "+comment);
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","");
				
				PreparedStatement statement  = conn.prepareStatement("UPDATE `purchase` SET `rating`=?,`comment`=?,`picture`=? WHERE  `username` = ? AND `product_id`=?");
				statement.setInt(1,rating);
				statement.setString(2,comment);
				statement.setBinaryStream(3, in,in.available() );
				
				statement.setString(4,username);
				statement.setInt(5,id);
				int i = statement.executeUpdate();
				if(i==1)
				{
					out.println("done");
				}else {
					out.println("Fail");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}


	public ArrayList<RatingBean> getRating(int id) {
		Connection conn;
		ArrayList<RatingBean> al = new ArrayList<RatingBean>();
		try {
			
			conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(" SELECT CONCAT(users.firstname,' ',users.lastname) as 'fullname', purchase.rating as 'rating', purchase.comment as 'commnet', purchase.picture as 'image'  FROM `purchase`\r\n" + 
					"LEFT JOIN users ON purchase.username = users.email WHERE purchase.product_id = "+id+" AND purchase.rating <> 0 ");
			
			while(resultSet.next())
			{
				RatingBean a = new RatingBean();
				a.setFullname(resultSet.getString("fullname"));
				a.setComment(resultSet.getString("commnet"));
				a.setPicture(Images.get(resultSet.getBlob("image")));
				a.setRating(resultSet.getInt("rating"));
				al.add(a);
								
			}
			
			return al;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return al;
	}

	
}
