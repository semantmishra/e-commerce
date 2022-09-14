package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utilites.Mail;
import database.DatabaseConnection;

@WebServlet("/dispatch")
public class Dispatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String option = request.getParameter("opt");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		String title = request.getParameter("title");
		String qnt = request.getParameter("qnt");
		String price = request.getParameter("price");
		String fullname = request.getParameter("fullname");
		String subject = "";
		String html = "";
		java.sql.Date date = new java.sql.Date(new Date().getTime());
		if(option.equals("dispatch"))
		{
			try {
				int i = DatabaseConnection.getConnection().createStatement().executeUpdate("UPDATE `purchase` SET `status`='dispatched', `dispatched_date` = '"+date+"' WHERE `id` ="+id);
				if(i==1)
				{
					html = "Hi ..."
							+"<br>Your order has shipped "
							+"<br> Order details "
							+"<br> <b>"+fullname+"</b>"
							+"<br> Product name "+title
							+"<br> Quntity "+qnt
							+"<br> Amount "+price
							+"<br> Address "+address
							+"<br> Mobile "+mobile
							+"<br> THANKS AND REGARDS "
							+"<br> SEMANT MISHRA";
					
					subject = "SEMANT ORDER";
					String msg = new Mail().send(email, subject, html);
					if(msg=="send")
					{
						out.println("done");
					}else {
						out.println("Unable tosend mail");
					}
					
				}else {
					out.println("Unable to dispatched ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if(option.equals("deliver")) {
			
			try {
				int i = DatabaseConnection.getConnection().createStatement().executeUpdate("UPDATE `purchase` SET `status`='delivered', `deliver_date` = '"+date+"' WHERE `id` ="+id);
				if(i==1)
				{
					html = "Hi ..."
							+"<br>Your order has Delivered "
							+"<br> Order details "
							+"<br> <b>"+fullname+"</b>"
							+"<br> Product name "+title
							+"<br> Quntity "+qnt
							+"<br> Amount "+price
							+"<br> Address "+address
							+"<br> Mobile "+mobile
							+"<br> THANKS AND REGARDS "
							+"<br> SEMANT MISHRA";
					
					subject = "SEMANT ORDER";
					String msg = new Mail().send(email, subject, html);
					if(msg=="send")
					{
						out.println("done");
					}else {
						out.println("Unable tosend mail");
					}
					
				}else {
					out.println("Unable to dispatched ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		
	}

}
