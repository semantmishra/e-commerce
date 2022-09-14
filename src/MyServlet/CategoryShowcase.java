package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.CategoryShowcaseBean;
import Dao.CategoryShowcaseDao;

/**
 * Servlet implementation class CategoryShowcase
 */
@WebServlet("/category-showcase")
@MultipartConfig
public class CategoryShowcase extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String option = request.getParameter("opt");
		if(option.equals("IU"))
		{
			CategoryShowcaseBean showcaseBean = new CategoryShowcaseBean();
			
			showcaseBean.setInputStream(request.getPart("photo").getInputStream());
			showcaseBean.setLable(request.getParameter("text"));
			showcaseBean.setDirection(request.getParameter("direction"));
			
			String msg = new CategoryShowcaseDao().saveOrUpdate(showcaseBean);
			out.println(msg);
		}
	}

}
