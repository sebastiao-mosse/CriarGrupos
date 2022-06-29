package com.produsol.cadastro.grupo.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class SMTPAuthenticator extends Authenticator {
	public String username;
	public String password;
	
	public SMTPAuthenticator(String usuario,String senha)
	{
		this.username= usuario;
		this.password= senha;
	}
	
    public PasswordAuthentication getPasswordAuthentication() {
       return new PasswordAuthentication(this.username, this.password);
    }
}