package com.rollanddice.comunidice.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.rollanddice.comunidice.service.spi.MailService;

public class MailServiceImpl implements MailService{
	
	public MailServiceImpl() {
	}
	
	public boolean emailService(String asunto, String mensaje, String para) throws EmailException {
	
		try {
				
			  HtmlEmail email = new HtmlEmail();
			  email.setHostName("smtp.googlemail.com");
			  email.setSmtpPort(465);
			  email.setAuthenticator(new DefaultAuthenticator ("modinoweb@gmail.com", "A1b2C3d4"));
			  email.setSSLOnConnect(true);
			  email.setFrom("modinoweb@gmail.com");
			  email.setSubject(asunto);
			  email.setHtmlMsg(mensaje);
			  email.addTo(para);
			  email.send();
			  return true;
			  
		}
		
		catch (EmailException e) {
			e.printStackTrace();
			throw new EmailException();
			
			
		}
	}

}
