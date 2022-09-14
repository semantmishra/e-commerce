package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Dao.KeywordDao;
import database.DatabaseConnection;

/**
 * Servlet implementation class KeyWord
 */
@WebServlet("/keyword")
public class KeyWord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String option = request.getParameter("opt");
		if(option.equals("inORup"))
		{
			String p_key = request.getParameter("p-keyword");
			String s_key = request.getParameter("s-keyword");
			String msg = new KeywordDao().save(p_key,s_key);
			if(msg!=null)
			{
				out.println(msg);
			}	
		}
		
		if(option.equals("select"))
		{
			String p_key = request.getParameter("p_key");
			
			String msg = new KeywordDao().select(p_key);
			if(msg!=null)
			{
				out.println(msg);
			}	
		}
		
		if(option.equals("getfaildkey"))
		{
			ArrayList<String> al = new ArrayList<String>();
			Connection conn ;
			try {
				conn = DatabaseConnection.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT `keyword` FROM `failds_keywords`");
				while(rs.next())
				{
					String key = rs.getString(1);
					al.add(key);
				}
				out.println(new Gson().toJson(al));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		if(option.equals("dele"))
		{
			String del = request.getParameter("tag");

			String data =del.replace('[', ' ') ;
			data =data.replace(']', ' ') ;
			data =data.replace('"', ' ') ;
			
			String[] findata = data.trim().split(",");
			String msg = new KeywordDao().delete(findata);
			out.println(msg);
			
			
			
		}
		
		
	}
}
