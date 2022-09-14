package MyServlet;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RealPreview
 */
@WebServlet("/real-preview")
@MultipartConfig
public class RealPreview extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream fi = request.getPart("photo").getInputStream();
		byte[] b = new byte[fi.available()];

		fi.read(b);
		
		FileOutputStream o = new FileOutputStream("H:\\eclipse-workspace\\ECommerce\\WebContent\\sem.jpg");
		o.write(b);

		o.flush();
		o.close();
		
		String h_align = request.getParameter("h_align");
		String text_align = "";
		if(h_align.equals("center"))
		{
			text_align = "text-center";
		}else if(h_align.equals("flex-start"))
		{
			text_align = "text-left";
		}else if(h_align.equals("flex-end"))
		{
			text_align = "text-right";
		}
		
		request.setAttribute("html",request.getParameter("html"));
	//	request.setAttribute("photo","data:image/jpg;base64,"+Base64.getEncoder().encode(b));
		
		request.setAttribute("v_align",request.getParameter("v_align"));
		request.setAttribute("h_align",request.getParameter("h_align"));
		request.setAttribute("text_align",text_align );
		RequestDispatcher dispatcher = request.getRequestDispatcher("real-preview.jsp");
		dispatcher.forward(request, response);
		
	}

}
