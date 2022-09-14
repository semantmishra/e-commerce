package Utilites;
import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.websocket.Session;
import javax.activation.*;
public class Mail {
	
	public String send(String otp,String email){
		
		try {
			
			Properties p = new Properties();
			p.put("mail.smtp.host","smtp.gmail.com");
			p.put("mail.smtp.auth","true");
			p.put("mail.debug","false");
			p.put("mail.smtp.socketFactory.port","465");
			p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback","false");
			
			javax.mail.Session ses  = javax.mail.Session.getDefaultInstance(p,new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("email", "password");
				}
			}); 
			
			ses.setDebug(false);
			Message msg = new MimeMessage(ses);
			InternetAddress addressfrom = new InternetAddress("email");
			msg.setFrom(addressfrom);
			InternetAddress addressTo = new InternetAddress(email);
			msg.addRecipient(Message.RecipientType.TO, addressTo);
			
			msg.setSubject("OTP Veryfication");
			
			msg.setContent("Your OTP Code <b>" +otp+"</b>","text/html");
			Transport.send(msg);
			
			return"send";
			
			
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println(e.toString());
		}
				
		return"nosend";
		
	}

	
public String send(String email,String subject,String html){
		
		try {
			
			Properties p = new Properties();
			p.put("mail.smtp.host","smtp.gmail.com");
			p.put("mail.smtp.auth","true");
			p.put("mail.debug","false");
			p.put("mail.smtp.socketFactory.port","465");
			p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback","false");
			
			javax.mail.Session ses  = javax.mail.Session.getDefaultInstance(p,new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("semantmishra@gmail.com", "hemant#1994");
				}
			}); 
			
			ses.setDebug(false);
			Message msg = new MimeMessage(ses);
			InternetAddress addressfrom = new InternetAddress("semantmishra@gmail.com");
			msg.setFrom(addressfrom);
			InternetAddress addressTo = new InternetAddress(email);
			msg.addRecipient(Message.RecipientType.TO, addressTo);
			
			msg.setSubject(subject);
			
			msg.setContent(html,"text/html");
			Transport.send(msg);
			
			return"send";
			
			
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println(e.toString());
		}
				
		return"nosend";
		
	}
	
	
}
