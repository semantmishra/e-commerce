package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.RegisterUserBean;
import Dao.RegisterUserDao;


@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = (String)request.getSession().getAttribute("user");
		if(username==null)
		{
			response.sendRedirect("signin.jsp");
		}else {
			request.setAttribute("user", new RegisterUserDao().get(username));
			RequestDispatcher rd = request.getRequestDispatcher("jsppage/Profile.jsp");
			rd.forward(request, response);	
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("opt");
		PrintWriter out = response.getWriter();
		String username = (String)request.getSession().getAttribute("user");
		if(username==null)
		{
			out.println("userinvalid");
		}
		else {
			if(option!=null)
			{
				if(option.equals("update"))
				{
					RegisterUserBean UserBean = new RegisterUserBean();
					
					UserBean.setFirstname(request.getParameter("firstname"));
					UserBean.setLastname(request.getParameter("lastname"));
					UserBean.setMobile(request.getParameter("mobile"));
					UserBean.setAddress(request.getParameter("address"));
					UserBean.setState(request.getParameter("state"));
					UserBean.setCountry(request.getParameter("country"));
					UserBean.setPincode(Integer.parseInt(request.getParameter("pincode")));
					UserBean.setEmail(request.getParameter("email"));
					
					String msg = new RegisterUserDao().update(UserBean);
					out.println(msg);
					
					
				}
				
				if(option.equals("cp"))
				{
					String newpassword = request.getParameter("newpassword");
					String oldpassword = request.getParameter("oldpassword");
					
					out.println(new RegisterUserDao().changePassword(username, oldpassword, newpassword));
				}
			}
		}
	}

}
