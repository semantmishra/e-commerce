package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.HeaderShowcaseBean;
import Dao.HeaderShowcaseDao;

/**
 * Servlet implementation class HeaderShowcase
 */
@WebServlet("/header-showcase")
@MultipartConfig
public class HeaderShowcase extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String option = request.getParameter("opt");	
		HeaderShowcaseBean showcaseBean = new HeaderShowcaseBean();
		if(option.equals("insert"))
		{
			showcaseBean.setTitle_size(request.getParameter("title_size"));
			showcaseBean.setTitle_color(request.getParameter("title_color"));
			showcaseBean.setSubtitle_size(request.getParameter("subtitle_size"));
			showcaseBean.setSubtitle_color(request.getParameter("subtitle_color"));
			showcaseBean.setV_align(request.getParameter("v_align"));
			showcaseBean.setH_align(request.getParameter("h_align"));
			showcaseBean.setTitle_text(request.getParameter("title_text"));
			showcaseBean.setSubtitle_text(request.getParameter("subtitle_text"));
			showcaseBean.setButtons(request.getParameter("buttons"));
			showcaseBean.setFile_data(request.getPart("file_data").getSubmittedFileName());
			showcaseBean.setInputStream(request.getPart("file_data").getInputStream());
			
			HeaderShowcaseDao showcaseDao = new HeaderShowcaseDao();
			
			if(!showcaseDao.limitFull())
			{
				String msg = showcaseDao.save(showcaseBean);
				out.println(msg);	
			}
			else{
				out.println("limit-full");
			}		

		}
		
		
		if(option.equals("delete"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String msg = new HeaderShowcaseDao().delete(id);
			out.println(msg);	
		}
		
		
		if(option.equals("update"))
		{
			
			showcaseBean.setTitle_size(request.getParameter("title_size"));
			showcaseBean.setTitle_color(request.getParameter("title_color"));
			showcaseBean.setSubtitle_size(request.getParameter("subtitle_size"));
			showcaseBean.setSubtitle_color(request.getParameter("subtitle_color"));
			showcaseBean.setV_align(request.getParameter("v_align"));
			showcaseBean.setH_align(request.getParameter("h_align"));
			showcaseBean.setTitle_text(request.getParameter("title_text"));
			showcaseBean.setSubtitle_text(request.getParameter("subtitle_text"));
			showcaseBean.setButtons(request.getParameter("buttons"));

			showcaseBean.setFile_data(request.getPart("file_data").getSubmittedFileName());
			showcaseBean.setInputStream(request.getPart("file_data").getInputStream());
			
			
			
			
			showcaseBean.setId(Integer.parseInt(request.getParameter("id")));
			String msg = new HeaderShowcaseDao().update(showcaseBean);
			out.println(msg);	
			
		}
				
	}

}
