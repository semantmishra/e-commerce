package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.BrandsBean;
import Beans.ProductBean;
import Dao.BrandsDao;
import Dao.FilterProducts;

/**
 * Servlet implementation class ShowProducts
 */
@WebServlet("/show-products")
public class ShowProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		String category =  request.getParameter("p");
		request.setAttribute("s",new BrandsDao().select("SELECT `id`, `category_name`, `brnads` FROM `brands` WHERE `category_name` = ?",category));
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("jsppage/show-products.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter(); 
		String option = request.getParameter("opt");
		if(option!=null) 
		{
			if(option.equals("search"))
			{
				String sql = "";
				String c_name = request.getParameter("cat_name");
				String brand = request.getParameter("brand_name");
				if(brand.equals("all"))
				{
					sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `category_name`='"+c_name+"'";
					
				}
				else {
					sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `brands` = '"+ brand+"' AND `category_name`='"+c_name+"'";
				}
				ArrayList<ProductBean> al = new ArrayList<ProductBean>();
				al = new FilterProducts().filter(sql);
				out.println(new Gson().toJson(al));	
			}
			
			if(option.equals("price-filter"))
			{
				String c_name = request.getParameter("cat_name");
				float min= Float.parseFloat(request.getParameter("min"));
				float max= Float.parseFloat(request.getParameter("max"));
				String sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `category_name`='"+c_name+"' AND price BETWEEN "+min+" AND "+max;
				
				ArrayList<ProductBean> al = new ArrayList<ProductBean>();
				al = new FilterProducts().filter(sql);
				out.println(new Gson().toJson(al));
				
			}
			
			if(option.equals("sort-product"))
			{
				String sql = "";
				String cat_name = request.getParameter("cat_name");
				String brand_name = request.getParameter("brand_name");
				String sort_by = request.getParameter("sort_by");
				
				if(brand_name.equals("all"))
				{
					switch (sort_by) {
					case "high":
						sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `category_name`='"+cat_name+"' ORDER BY price DESC";		
						break;
					case "low":
						sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `category_name`='"+cat_name+"' ORDER BY price ";		
						break;
					case "new":
						sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `category_name`='"+cat_name+"' ORDER BY entry_date DESC ";
						break;
					case "recomended":
						sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `category_name`='"+cat_name+"'";
						break;
					}
					ArrayList<ProductBean> al = new FilterProducts().filter(sql);
					out.println(new Gson().toJson(al));
					
				}else {

					switch (sort_by) {
					case "high":
						sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `category_name`='"+cat_name+"' AND brands = '"+brand_name+"' ORDER BY price DESC";		
						break;
					case "low":
						sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `category_name`='"+cat_name+"' AND brands = '"+brand_name+"' ORDER BY price ";		
						break;
					case "new":
						sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `category_name`='"+cat_name+"' AND brands = '"+brand_name+"' ORDER BY entry_date DESC ";
						break;
					case "recomended":
						sql = "SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `thumb_pic` FROM `products` WHERE `category_name`='"+cat_name+"' AND brands = '"+brand_name+"'";
						break;
					}
					if(!sql.equals(""))
					{
						ArrayList<ProductBean> al = new FilterProducts().filter(sql);
						out.println(new Gson().toJson(al));	
					}					
				}
			}
		}
	}

}
