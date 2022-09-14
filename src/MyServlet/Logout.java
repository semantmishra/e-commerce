package MyServlet;

import java.io.IOException;
import java.net.CookieStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		Cookie cookie =new Cookie("_au_","");
		response.addCookie(cookie);
		response.sendRedirect("Home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		Cookie cookie =new Cookie("_au_","");
		response.addCookie(cookie);
		
		
		response.sendRedirect("Home");
	}

}
