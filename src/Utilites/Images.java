package Utilites;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class Images {
	public static String get(Blob blob) {
		
		try {
			
			byte[] byt = blob.getBytes(1,(int)blob.length());
			String img = "data:image/jpg;base64,"+Base64.getEncoder().encodeToString(byt);
			
			return img;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
