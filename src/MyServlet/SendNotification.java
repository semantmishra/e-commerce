package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.ProductBean;
import Dao.ProductDao;
import Utilites.Mail;
import database.DatabaseConnection;

/**
 * Servlet implementation class SendNotification
 */
@WebServlet("/send-notification")
public class SendNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductBean product = new ProductDao().getLetestProduct();
		
		String html = "";

		html = "Hi ..."
				+"<br>Upload new Produts "
				+"<br> <b>"+product.getTitle()+"</b>"
				+"<br> "+product.getBrands()
				+"<br> Price "+product.getPrice()
				+"<br> Description "+product.getDescription()
				//+product.getThumb_pic()
				+"<br> THANKS AND REGARDS "
				+"<br> SEMANT MISHRA";
		
		ArrayList<String> emailList = getSubscriber();
		String msg1 = "";
		String subject = "New Product";
		Mail mail = new Mail();
		for (String email : emailList) {
			
			String msg = mail.send(email, subject, html);
			if(msg=="send")
			{
				msg1 = "done";
			}else {
				msg1 = "fail";
			}	
		}
		PrintWriter out =  response.getWriter();
		out.println(msg1);
		
		
		
		
	}
	
private ArrayList<String>	getSubscriber(){
	ArrayList<String> al = new ArrayList<String>();
	try {
		ResultSet resultSet = DatabaseConnection.getConnection().createStatement().executeQuery("SELECT `email` FROM `subscriber`");
		while(resultSet.next()) {
			String email = resultSet.getString("email");
			al.add(email);
		}

		return al;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return al;
	}

}
