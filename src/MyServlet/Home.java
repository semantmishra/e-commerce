package MyServlet;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import Beans.HeaderShowcaseBean;
import Beans.ProductBean;
import Dao.CategoryShowcaseDao;
import Dao.HeaderShowcaseDao;
import Dao.ProductDao;
import Dao.ShowRating;
import database.CreateTable;


/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 	request.setAttribute("header_showcasees",new HeaderShowcaseDao().get());
		 	
		 	request.setAttribute("products",new ProductDao().get());
		 		 	
		 	request.setAttribute("category_showcase", new CategoryShowcaseDao().get());
		 
		 	
		 	RequestDispatcher dispatcher =  request.getRequestDispatcher("home.jsp");	 
			dispatcher.forward(request, response);	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
		String option = request.getParameter("opt");	
		
		
		if(option.equals("getid"))
		{
			ArrayList<HeaderShowcaseBean> hsb = new HeaderShowcaseDao().get();
			 out.println( new Gson().toJson(hsb));	
		}
		if(option.equals("getByid"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<HeaderShowcaseBean> hsb = new HeaderShowcaseDao().get(id);
			 out.println( new Gson().toJson(hsb));	
		}
		if(option.equals("delete"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String msg = new HeaderShowcaseDao().delete(id);
			out.println(msg);	
		}
				 
	}

	protected void init (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ygfd");
		try {
			String sql = "";
			String s = null;
			DataInputStream o = new DataInputStream(new FileInputStream("H:\\eclipse-workspace\\ECommerce\\WebContent\\sql.txt")) ;
			while(true) {
				s = o.readLine();	
				if(s!=null)
				{
					sql +=s;
				}else {
					break;
				}
			}
			String [] sqll = sql.split(";");
			for (String string : sqll) {
				CreateTable.Create(string);	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
	


}
