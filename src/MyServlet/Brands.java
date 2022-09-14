package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.BrandsBean;
import Beans.BrandsName;
import Beans.CategoryBean;
import Dao.BrandsDao;
import Dao.CategoryDao;

/**
 * Servlet implementation class Brands
 */
@WebServlet("/brands")
public class Brands extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Brands() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response .getWriter();	
		
		 String option = request.getParameter("opt");		 
		 if(option.equals("select"))
		{
			 String category = request.getParameter("category");
			 ArrayList<BrandsBean> s =  new BrandsDao().select("SELECT `id`, `category_name`, `brnads` FROM `brands` WHERE `category_name` = ?",category);
			 String data = new Gson().toJson(s);
			 out.println(data);		
		}
		 if(option.equals("selectBrands"))
		 {
			ArrayList<BrandsBean> al =  new BrandsDao().getBrends();
			out.println(new Gson().toJson(al));
		 }
		 
		if(option.equals("insert"))
		{
		String c = request.getParameter("category");
		String s[] = request.getParameterValues("brand-name");
		
		String msg = new BrandsDao().Save(c, s);
		String msg1 = "";
			if(msg=="done")
			{
				String path = "H:\\eclipse-workspace\\ECommerce\\WebContent\\stocks";
				for (String string : s) {
					File f = new File(path+File.separator+c+File.separator+string);
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
		
		if(option.equals("delete"))
		{
		int id =Integer.parseInt(request.getParameter("id"));		
		String msg = new BrandsDao().delete(id);
		out.println(msg);
		}
		
		if(option.equals("update"))
		{
		int id =Integer.parseInt(request.getParameter("id"));
		String c_name =request.getParameter("c_name");
		String b_name =request.getParameter("b_name");
		String msg = new BrandsDao().update(id,c_name,b_name);
		out.println(msg);
		}
					
	}

}
