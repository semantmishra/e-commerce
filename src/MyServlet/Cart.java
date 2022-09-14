package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CartDao;


@WebServlet("/cart")
public class Cart extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String session = (String)request.getSession().getAttribute("user");
		if(session!=null)
		{
			
			request.setAttribute("carts", new CartDao().get(session));
			
			RequestDispatcher rd = request.getRequestDispatcher("jsppage/cart.jsp");
			rd.forward(request, response);	
		}else {
			response.sendRedirect("signin.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String option = request.getParameter("opt");
		String session = (String)request.getSession().getAttribute("user");
		if(session!=null)
		{
			if(option.equals("insert"))
			{
				int productId =Integer.parseInt(request.getParameter("id"));
				String userName = (String) request.getSession().getAttribute("user");
				String msg =new CartDao().save(productId,userName);
				out.println(msg);
			}
			
			if(option.equals("delete"))
			{
				int id =Integer.parseInt(request.getParameter("id"));
				String msg =new CartDao().delete("DELETE FROM cart WHERE id = "+id);
				out.println(msg);
			}
		}
	}

}
