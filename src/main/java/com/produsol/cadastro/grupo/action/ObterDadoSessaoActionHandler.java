package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.services.CooperadoService;

public class ObterDadoSessaoActionHandler implements ActionHandler {

	private static  final long serialVersionUID = 1L;
	String message;
	
@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{
		Authentication auth = getAuthentication();
		CooperadoService servico = new CooperadoService();
		String id= "";
		
		System.out.println("User Email: " + servico.GetUserEmail(auth.getName()));
		
		String resultado = servico.userOfficeId(servico.GetUserEmail(auth.getName())) ;
		if(resultado== null){id = "1";}	
		else{id = resultado;}
		
		Input officeId = new Input();
		officeId.setName("officeId");
		officeId.setLabel("officeId");
		officeId.setValue(id);
		executionContext.getContextInstance().setVariable("officeId", officeId);
		
		System.out.println("******************* LOGS PARA RASTREAMENTO *******************");
		System.out.println("USER AUTHENTICATED : "+  auth.getName());
		System.out.println("USER AUTHENTICATED EMAIL : "+ servico.GetUserEmail(auth.getName()));
		System.out.println("ID DO OFFICE: "+ id);
		System.out.println("******************* FIM *******************");
	}
	
	
public static Authentication getAuthentication() {
	if (SecurityHolder.get() != null) {
		return SecurityHolder.get();
	} else {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}

}
