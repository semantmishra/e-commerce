package MyServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import Beans.BrandsBean;
import Beans.ProductBean;
import Dao.BrandsDao;
import Dao.ProductDao;



@WebServlet("/products")
@MultipartConfig
public class Products extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response .getWriter();	
		String path = "H:\\eclipse-workspace\\ECommerce\\WebContent\\stocks";
		
		 String option = request.getParameter("opt");
	//	 out.println(option);
//		 out.println("dvdxv");
		 ProductBean pb = new ProductBean();
		 if(option.equals("insert"))
		 {
			 
			 pb.setCategory_name(request.getParameter("category-name"));
			 pb.setBrands(request.getParameter("brands"));
			 pb.setTitle(request.getParameter("title"));
			 pb.setDescription(request.getParameter("description"));
			 pb.setPrice(Float.parseFloat(request.getParameter("price")));
			 pb.setQuantity(request.getParameter("quantity"));
			 
			 Part[] part = {
					request.getPart("thumb_pic"),
					request.getPart("front_pic"),
					request.getPart("top_pic"),
					request.getPart("bottom_pic"),
					request.getPart("left_pic"),
					request.getPart("right_pic")
					};
			 
			 String categoryName = new ProductDao().getCategoryName(pb.getBrands());

			 String finalPathSave = "stocks"+File.separator+categoryName+File.separator+pb.getBrands()+File.separator+pb.getTitle();

				pb.setThumb_pic(finalPathSave+File.separator+request.getPart("thumb_pic").getSubmittedFileName());
				pb.setFront_pic(finalPathSave+File.separator+request.getPart("front_pic").getSubmittedFileName());
				pb.setTop_pic(finalPathSave+File.separator+request.getPart("top_pic").getSubmittedFileName());
				pb.setBottom_pic(finalPathSave+File.separator+request.getPart("bottom_pic").getSubmittedFileName());
				pb.setLeft_pic(finalPathSave+File.separator+request.getPart("left_pic").getSubmittedFileName());
				pb.setRight_pic(finalPathSave+File.separator+request.getPart("right_pic").getSubmittedFileName());
			 
			 //String dbPah = 
			 
			 String msg = new ProductDao().save(pb);
			 
			 
			 if(msg=="done") 
			 {
				 if(categoryName!="")
				 {
					 String finalPath = path+File.separator+categoryName+File.separator+ pb.getBrands()+File.separator+pb.getTitle();
					 File f = new File(finalPath);
					 if(!f.exists())
					 {
						 if(f.mkdir())
						 {
							 // upload image 
								 for (Part part2 : part) {
									 if(!part2.getSubmittedFileName().equals("")) {
										
										 InputStream in =part2.getInputStream();
										 byte[] data = new byte[in.available()];
										 in.read(data);
										 FileOutputStream fos = new FileOutputStream(finalPath+File.separator+part2.getSubmittedFileName());
										 fos.write(data);
										 fos.close();	 
									 }
									
								}
							out.println("done");
						 }
						 else{
							 out.println("filds");
						 }
					 }
					 else{
						 out.println("Products allrady exist"); 	 
					 }
				 }
				 else {
					out.println("Category not found"); 
				 }
			 }
			 else {
					out.println("Product save faild"); 
				 }		 
		}
		 
		 
		 
		 if(option.equals("getproduts"))
		 {
			 String brands = request.getParameter("brands");
			 ArrayList<String> string =  new  ProductDao().get(brands);
			 out.println(new Gson().toJson(string));
			 
		 }
		
	}

}
