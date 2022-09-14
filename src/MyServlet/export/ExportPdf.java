package MyServlet.export;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import database.DatabaseConnection;

/**
 * Servlet implementation class ExportPdf
 */
@WebServlet("/export-to-pdf")
public class ExportPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Content-Disposition","attachment;filename=purchase_entry.pdf");
		response.setContentType("aplication/octet-stream");
		
		PrintWriter out = response.getWriter();
		String html = "";
		Connection conn;
		String sql = "SELECT purchase.id as 'id',purchase.product_id as 'p_id', products.title as 'title', " + 
				"purchase.qnt as 'qnt', (products.price * purchase.qnt) as 'price',users.address as 'Address'," + 
				"users.state as 'state', users.country as 'country',  users.pincode as 'pincode'," + 
				"purchase.purchase_date as 'p_date', CONCAT(users.firstname,' ',users.lastname) as 'name'," + 
				"username,users.mobile as 'mobile',purchase.status as 'status',purchase.dispatched_date as 'date' FROM `purchase` " + 
				"LEFT JOIN users ON users.email = purchase.username " + 
				"LEFT JOIN products ON products.id = purchase.product_id";
		
		Document document = new Document();
		
		try {
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("purchase_entry.pdf"));
			document.open();
			document.add(new Paragraph("SALES REPORT"));
			
			PdfPTable table = new PdfPTable(14);
			table.addCell("");
			
			conn = DatabaseConnection.getConnection();
			Statement s = conn.createStatement();
			ResultSet rs  = s.executeQuery(sql);
			table.addCell("S/NO");
			table.addCell("PRODUCT");
			table.addCell("TITLE");
			table.addCell("QUANTITY");
			table.addCell("PRICE");
			table.addCell("ADDRESS");
			table.addCell("STATE");
			table.addCell("COUNTRY");
			table.addCell("PINCODE");
			table.addCell("PURCHASE DATE");
			table.addCell("CUSTOMER NAME");
			table.addCell("USERNAME");
			table.addCell("MOBILE");
			table.addCell("STATUS");
			
			while(rs.next())
			{
				table.addCell(rs.getString("id"));
				table.addCell(rs.getString("p_id"));
				table.addCell(rs.getString("title"));
				table.addCell(rs.getString("qnt"));
				table.addCell(rs.getString("price"));
				table.addCell(rs.getString("address"));
				table.addCell(rs.getString("state"));
				table.addCell(rs.getString("country"));
				table.addCell(rs.getString("pincode"));
				table.addCell(rs.getString("p_date"));
				table.addCell(rs.getString("name"));
				table.addCell(rs.getString("username"));
				table.addCell(rs.getString("mobile"));
				table.addCell(rs.getString("status"));				
			}
			
			document.add(table);
			
			document.close();
			
			FileInputStream in = new FileInputStream("purchase_entry.pdf");
			OutputStream o = response.getOutputStream();
						
			byte[] b =new byte[in.available()];
			
			in.read(b);
			o.write(b);
			o.flush();
			o.close();
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
