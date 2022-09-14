package MyServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.BrandingBean;
import Dao.BrandingDao;
import database.DatabaseConnection;

/**
 * Servlet implementation class Branding
 */
@WebServlet("/branding")
@MultipartConfig
public class Branding extends HttpServlet {
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =  response.getWriter();
		String option = request.getParameter("opt");
		
		
		if(option.equals("selete"))
		{
			BrandingBean b = new BrandingDao().get();
			out.println(new Gson().toJson(b,BrandingBean.class));
		
			//out.println("dvd");
		}
		
		if(option.equals("insert"))
		{
			BrandingBean b  = new BrandingBean();
			b.setBrand_name(request.getParameter("brands_name"));
			b.setInputStream(request.getPart("brands_logo").getInputStream());
			b.setBrand_logo(request.getPart("brands_logo").getSubmittedFileName());
			b.setDomain_name(request.getParameter("domain_name"));
			b.setEmail(request.getParameter("email"));
			b.setFacebook_url(request.getParameter("facebook_url"));
			b.setTwitter_url(request.getParameter("twitter_url"));
			b.setAddress(request.getParameter("address"));
			b.setPhone(request.getParameter("Phone"));
			b.setAbout_us(request.getParameter("about"));
			b.setPrivacy_policy(request.getParameter("privacy_policy"));
			
			b.setCookies_policy(request.getParameter("cookies_policy"));
			b.setTerms_policy(request.getParameter("terms_conditions"));
			
			if(b.getBrand_logo().isEmpty())
			{
				String msg = new BrandingDao().save(b);
				out.println(msg);
				
			}
			else {
					
				String msg = new BrandingDao().saveWithImage(b);
				out.println(msg);
				
			}
		}
		
		if(option.equals("update"))
		{
			BrandingBean b  = new BrandingBean();
			b.setId(Integer.parseInt(request.getParameter("id")));
			b.setBrand_name(request.getParameter("brands_name"));
			b.setInputStream(request.getPart("brands_logo").getInputStream());
			b.setBrand_logo(request.getPart("brands_logo").getSubmittedFileName());
			b.setDomain_name(request.getParameter("domain_name"));
			b.setEmail(request.getParameter("email"));
			b.setFacebook_url(request.getParameter("facebook_url"));
			b.setTwitter_url(request.getParameter("twitter_url"));
			b.setAddress(request.getParameter("address"));
			b.setPhone(request.getParameter("Phone"));
			b.setAbout_us(request.getParameter("about"));
			b.setPrivacy_policy(request.getParameter("privacy_policy"));
			
			b.setCookies_policy(request.getParameter("cookies_policy"));
			b.setTerms_policy(request.getParameter("terms_conditions"));
			
			if(b.getBrand_logo().isEmpty())
			{
				String msg = new BrandingDao().update(b);
				out.println(msg);
			}
			else {
					
				String msg = new BrandingDao().updateWithImage(b);
				out.println(msg);	
			}
		}
	}

}
