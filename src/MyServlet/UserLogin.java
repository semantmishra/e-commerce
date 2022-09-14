package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Dao.RegisterUserDao;
import Utilites.Mail;
import Utilites.RendomNun;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/user-login")
@MultipartConfig
public class UserLogin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String option = request.getParameter("opt");
		if(option.equals("login"))
		{
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			String d = new Dao.UserLogin().login(email, pass);
			if(d=="pending")
			{
				String otp = Integer.toString(new RendomNun().get(100000, 999999));
				HttpSession session =request.getSession(false);
				session.setAttribute("otp",otp);
				String s = new Mail().send(otp,email);
				out.println(d);
			}
			else{
				if(d=="done")
				{
					HttpSession session = request.getSession(false);
					session.setAttribute("user",email);
					Cookie cookie =new Cookie("_au_", email);
					cookie.setMaxAge(60*60*20*365);
					response.addCookie(cookie);
					out.println("done");
				}
				else {
					out.println(d);
				}
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
				out.println("Wrong OTP");
			}
			
			
		}
		
		
		if(option.equals("resendOtp"))
		{
			//String mobile = request.getParameter("mobile");
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
	}

}
