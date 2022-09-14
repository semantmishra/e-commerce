package MyServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Beans.CategoryBean;
import Dao.CategoryDao;
import database.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Category
 */
@WebServlet("/category")
public class Category extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("application/json");
		PrintWriter out = response .getWriter();
		ArrayList<CategoryBean> c =  new CategoryDao().select("SELECT `id`, `category_name` FROM `category`");
		Gson g = new Gson();
		String s = g.toJson(c);
		out.println(s);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//response.setContentType("application/json");
		PrintWriter out = response .getWriter();	
		
		 String option = request.getParameter("opt");
		 
		 
		 
		 if(option.equals("select"))
		{
			ArrayList<CategoryBean> c =  new CategoryDao().select("SELECT `id`, `category_name` FROM `category`");
			String s = new Gson().toJson(c);
			out.println(s);
			
		}
		
		
		if(option.equals("insert"))
		{
			
			String s[] = request.getParameterValues("mobile");
			String msg = new CategoryDao(s).Save();
			String msg1="";
			if(msg=="done")
			{
				String path = "H:\\eclipse-workspace\\ECommerce\\WebContent\\stocks";
				for (String string : s) {
					File f = new File(path+File.separator+string);
					
					if(!f.exists())
					{ 
						f.mkdir();
						msg1 = "done";
					}
					else {
						msg1 = "faild";
					}
				}
				out.println(msg1);
			}
			
		}
		
		
		if(option.equals("update"))
		{
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("change_name");
			String msg = new CategoryDao().update(id,name);
			out.println(msg);
		}
		
		if(option.equals("delete"))
		{
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			String msg = new CategoryDao().delete(id);
			out.println(msg);
		}
		
	}

}
