package edu.unlp.oo2;

import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailHandler extends Handler {
	
	Properties props;
	String from;
	String to;
	String username;
	String password;
	String host;
	
	public MailHandler(String destination) {
		this.to = destination;
		this.createConfiguration();
	}
	
	private void createConfiguration() {
      String host = "smtp.mailtrap.io";
      from = "example@logger.com";
      // use your credentials!
      username = "";
      password = "";

      props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");				 
	}
	
	@Override
	public void publish(LogRecord record) {
		try {
			Session session = Session.getInstance(props,
		         new Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
			});

		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(from, "Java logging mail"));
		    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		    message.setSubject("Logging mail - Level " + record.getLevel().getName());
		    message.setText(
	    		"Record content:" + System.lineSeparator() + System.lineSeparator()
	    		+ record.getMessage()
	    		+ System.lineSeparator() + System.lineSeparator()
	    		+ "Class: " + record.getSourceClassName() + System.lineSeparator()
	    		+ "Method: " + record.getSourceMethodName()
    		);

		    Transport.send(message);
		}
		catch (Exception e) {
	         throw new RuntimeException(e);
	    }
	}

	@Override
	public void flush() {
	}

	@Override
	public void close() throws SecurityException {
	}

}
