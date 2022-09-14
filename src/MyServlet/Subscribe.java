package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Utilites.Mail;
import Utilites.RendomNun;
import database.DatabaseConnection;

/**
 * Servlet implementation class Subscribe
 */
@WebServlet("/subscribe")
public class Subscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subscribe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String option = request.getParameter("opt");
		if(option.equals("varify"))
		{
			String email = request.getParameter("email");
			String otp = Integer.toString(new RendomNun().get(100000, 999999));
			String msg = new Mail().send(otp,email);
			if(msg.equals("send"))
			{
				String s[] = {"done" ,otp };				
				out.println(new Gson().toJson(s));
			}else {
				out.println("fail");
			}
	
		}
		
		if(option.equals("save"))
		{
			String email = request.getParameter("email");
			Connection conn ;
			try {
				
				conn = DatabaseConnection.getConnection();
				Statement statement = conn.createStatement();
				java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
				int i = statement.executeUpdate("INSERT INTO `subscriber`(`email`,`subscriber_date`) VALUES ('"+email+"','"+date+"')");
				if(i==1)
				{
					out.println("Subscribe Success !");
				}else {
					out.println("faild ");
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
		}
		
	}

}
