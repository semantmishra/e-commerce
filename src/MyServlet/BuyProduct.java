package MyServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.RatingBean;
import Dao.CartDao;
import Dao.DeliveryAreaDao;
import Dao.ProductDao;

/**
 * Servlet implementation class BuyProduct
 */
@WebServlet("/buy-product")
public class BuyProduct extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String session = (String) request.getSession().getAttribute("user");
		if(session==null)
		{
			response.sendRedirect("signin.jsp");	
		}else {
			int id = Integer.parseInt(request.getParameter("p_id"));
			request.setAttribute("ProductEexist", new CartDao().productNotExist(session, id) );
			
			String pin = new Dao.UserLogin().getFullname(session).split(",")[1];
			
			request.setAttribute("checkDeliveryPaymet", new DeliveryAreaDao().checkDelivery(pin));
			
			request.setAttribute("stock",new ProductDao().getStock(id));
			request.setAttribute("ratings",new Rating().getRating(id));
						
			RequestDispatcher rd = request.getRequestDispatcher("jsppage/buy-product.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
