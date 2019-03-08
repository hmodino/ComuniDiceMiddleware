package com.rollanddice.comunidice.util;

import org.jasypt.util.password.StrongPasswordEncryptor;


 /** @author https://www.linkedin.com/in/joseantoniolopezperez
  @version 0.2
*/
public class PasswordEncryptionUtil {
	
	private static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR = new StrongPasswordEncryptor();	
	
	public static final String encryptPassword(String password) {
		System.out.println();
		return PASSWORD_ENCRYPTOR.encryptPassword(password);
	}

	public static final boolean checkPassword(String plainPassword, String encryptedPassword) {
		if (PASSWORD_ENCRYPTOR.checkPassword(plainPassword, encryptedPassword)) {
			return true;
		} else {
			return false;
		}
	}


}
