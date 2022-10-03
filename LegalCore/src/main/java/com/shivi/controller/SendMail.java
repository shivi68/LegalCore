package com.shivi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.shivi.model.DAO;


@WebServlet("/SendMail")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String type=request.getParameter("type");
			String email=request.getParameter("email"); //email= reciever email
			DAO d=new DAO();
			String password=d.getPassword(email,type);
			

			//send mail code:
			try {
				String senderEmail="Your Email Id";
				String senderPassword="Your Password";
				String sub="Password for LegalCore App.";
				String body="Your Email ID: "+email+" . Your Password: "+password;
				
				Properties prop = new Properties();  
				prop.put("mail.smtp.host", "smtp.gmail.com");
				prop.put("mail.smtp.port", "587");
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true");
				
				Session ses = Session.getInstance(prop,  
			    new javax.mail.Authenticator() {  
			      protected PasswordAuthentication getPasswordAuthentication() {  
			    return new PasswordAuthentication(senderEmail,senderPassword);  
			      }  
			    });
				Message message=new MimeMessage(ses);
	            message.setFrom(new InternetAddress(senderEmail));
	            message.setRecipients(Message.RecipientType.TO, 
	            		InternetAddress.parse(email));
	            message.setSubject(sub);
	            message.setContent(body,"text/html" );
	            
	            Transport.send(message);
			}catch (Exception e) {
				e.printStackTrace();
			}
			//send mail code: Ends
			
			HttpSession session=request.getSession();
			session.setAttribute("msg","Password Sent Successfully");
			if(type.equalsIgnoreCase("user")) {
				response.sendRedirect("User.jsp");
			}
			else {
				response.sendRedirect("Advocate.jsp");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}