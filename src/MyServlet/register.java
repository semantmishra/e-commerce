package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.RegisterUserBean;
import Dao.RegisterUserDao;
import Utilites.Mail;
import Utilites.RendomNun;
import Utilites.Sendsms;

@WebServlet("/register")
@MultipartConfig
public class register extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i = 999999;
		response.getWriter().println(new RendomNun().get(100000, 999999)+ " "+i);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String option = request.getParameter("opt");
	
		
		if(option.equals("insert"))
		{
						
			
			RegisterUserBean bean = new RegisterUserBean();
			bean.setFirstname(request.getParameter("firstname"));
			bean.setLastname(request.getParameter("lastname"));
			bean.setEmail(request.getParameter("email"));
			bean.setMobile(request.getParameter("mobile"));
			bean.setPassword(request.getParameter("password"));

			bean.setAddress(request.getParameter("address"));
			bean.setState(request.getParameter("state"));
			bean.setCountry(request.getParameter("country"));
			bean.setPincode(Integer.parseInt(request.getParameter("pincode")));
			
			String sql = "SELECT`email`, `mobile` FROM `users` WHERE `email` = '"+bean.getEmail()+"'";
			RegisterUserDao dao = new RegisterUserDao();
			if(!dao.exist(sql)) 
			{
				sql = "SELECT`email`, `mobile` FROM `users` WHERE `mobile` = '"+bean.getMobile()+"'";
				if(!dao.exist(sql)) 
				{
					String msg = dao.save(bean);
				
					if(msg=="done")
					{
						String otp = Integer.toString(new RendomNun().get(100000, 999999));
						HttpSession session =request.getSession(false);
						session.setAttribute("otp",otp);
						
						String s = new Mail().send(otp,bean.getEmail());
						if(s=="send");
						{
							out.println("done");
						}
						
						
					}
					else {
						out.println(msg);
					}
				}
				else {
					out.println("Mobile number allready exist");
				}
			}
			else {
				out.println("Email allready exist");
			}
		}

		if(option.equals("otp-verify"))
		{
			HttpSession session =request.getSession();
			String otp = session.getAttribute("otp").toString();
			String user_otp = request.getParameter("otp");
			String user_email = request.getParameter("email");
			if(user_otp.equals(otp))
			{
				session.invalidate();
				String msg = new RegisterUserDao().otpVerify(user_email);
				
				if(msg=="done")
				{
					out.println("done");	
				}
			}
			else {
				out.println("Wrong Otp");
			}
			
			
		}
		
		if(option.equals("resendOtp"))
		{
			String mobile = request.getParameter("mobile");
			String email = request.getParameter("email");
			String otp = Integer.toString(new RendomNun().get(100000, 999999));
			HttpSession session =request.getSession(false);
			session.setAttribute("otp",otp);
				
			String s = new Mail().send(otp,email);
			if(s=="send")
			{
				out.println("done");	
			}
			else {
				out.println("faild");
			}
		}
		
		if(option.equals("uniq"))
		{
			
		}
		
	}

}
