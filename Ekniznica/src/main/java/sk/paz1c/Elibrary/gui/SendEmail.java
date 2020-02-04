package sk.paz1c.Elibrary.gui;

import com.sun.mail.smtp.SMTPTransport;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  


import java.util.Date;
import java.util.Properties;

public class SendEmail {

	public static void sendMail(String usernameReader,String passwordReader) {
		final String username = "testekniznica@gmail.com";
		final String password = "programko123";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("testekniznica@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dmnk.kolesar@gmail.com"));
			message.setSubject("E-library credentials");
			message.setText("Your username is: " +usernameReader+" your password is:"+passwordReader);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println("something is wrong");
			throw new RuntimeException(e);
		}
	}
}
