package com.produsol.cadastro.grupo.action;

import java.util.List;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.services.UserAuthenticationServices;
import com.produsol.cadastro.grupo.util.SMS;
import com.produsol.cadastro.grupo.util.SendEmail;

public class NotificarAssistenteComercial  implements ActionHandler {

	private static final long serialVersionUID = 1L;

	SMS sms = new SMS();
	SendEmail enviarEmail = new SendEmail();
	Propriedades api = new  Propriedades();
UserAuthenticationServices authentication = new UserAuthenticationServices();
	
	public void execute(ExecutionContext executionContext) throws Exception 
	{
		this.enviarEmailPersonalizado(authentication.getUserName(authentication.GetUsersByRole("ROLE_ASSISTENTE_COOPERADO")));
	}
	
	public void enviarEmailPersonalizado(List<String> resultado) throws Exception
	{	
		for(int i= 0; i<resultado.size(); i++) 
		{		
			String name = resultado.get(i);
			String email = this.authentication.GetUserEmail(name);	
			enviarEmail.Notificar(name, email);
		}


	}
}
