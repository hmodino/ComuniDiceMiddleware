package com.rollanddice.comunidice.service.spi;

public interface MailService {

	public boolean emailService(String asunto, String mensaje, String para) 
			throws Exception;
	
}
