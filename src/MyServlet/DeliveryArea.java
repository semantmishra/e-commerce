package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.DefaultBean;
import Beans.DeliveryAreaBean;
import Dao.DeliveryAreaDao;

/**
 * Servlet implementation class DeliveryArea
 */
@WebServlet("/delivery-area")
public class DeliveryArea extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();		
		String option = request.getParameter("opt");
		if(option.equals("state"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<DefaultBean> arrayList = new DeliveryAreaDao().getState("SELECT `id`, `name` FROM `states` WHERE  `country_id` ="+id);
			out.println(new Gson().toJson(arrayList));
		}
		
		if(option.equals("city"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<DefaultBean> arrayList = new DeliveryAreaDao().getState("SELECT `id`, `name`FROM `cities` WHERE `state_id`="+id);
			out.println(new Gson().toJson(arrayList));
		}
		
		if(option.equals("insert"))
		{
			DeliveryAreaBean areaBean = new DeliveryAreaBean();
			areaBean.setCoutry( request.getParameter("country") );
			areaBean.setState( request.getParameter("state") );
			areaBean.setCity( request.getParameter("city") );
			areaBean.setPincode(Integer.parseInt(request.getParameter("pincode") ));
			areaBean.setDays( request.getParameter("days") );
			areaBean.setPayment_mode( request.getParameter("payment-mode") );
			
			String msg = new DeliveryAreaDao().save(areaBean);
			out.println(msg);
		}
		
		if(option.equals("checkPin"))
		{
			String pin = request.getParameter("pincode");
			DeliveryAreaBean msg = new DeliveryAreaDao().checkDelivery(pin);
			if(msg!=null)
				{
				out.println(msg.getDays());
				}
			else {
				out.println("Whoops ! delivery not avilable ");
			}
			
		}
	}

}
