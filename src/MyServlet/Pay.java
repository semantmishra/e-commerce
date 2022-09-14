package MyServlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CartDao;
import Dao.PayDao;

/**
 * Servlet implementation class Pay
 */
@WebServlet("/pay")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String session = (String) request.getSession().getAttribute("user");
		if(session==null)
		{
			response.sendRedirect("signin.jsp");	
		}else {
			
			request.setAttribute("sem", LocalDateTime.now());
			
			String username = (String)session;
			int product_id = Integer.parseInt(request.getParameter("p_id"));
			int qnt = Integer.parseInt(request.getParameter("qnt"));
			String paymode = request.getParameter("paymode");
			
			String msg = new PayDao().save(username,product_id,qnt,paymode);
			
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("jsppage/purchase_entry.jsp");
			rd.forward(request, response);	
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
