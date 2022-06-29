package com.produsol.cadastro.grupo.action;

import java.util.List;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.repositories.CooperadoRepository;
import com.produsol.cadastro.grupo.repositories.DecisionRepository;
import com.produsol.cadastro.grupo.services.HostService;

public class SaveDecisionActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	private DecisionRepository repository = new DecisionRepository();
	Authentication auth = getAuthentication();
	HostService host = new HostService();
	Input idCooperado = null;
	private String transicao = "";
	CooperadoRepository cooperadoRepository = new CooperadoRepository();
	
	public void execute(ExecutionContext executionContext) throws Exception 
	{
		String transitionName = executionContext.getTransition().getName();
		Input tipoCooperado = (Input) executionContext.getContextInstance().getVariable("tipoCooperado");
		String type = tipoCooperado.getValue().equals("SINGULAR") ? "1" : "2";
		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");

		if(transitionName.equals("Aprovado(a)"))
		{
			this.transicao ="Aprovado(a)";
		}
		if(transitionName.equals("erroEmpresa") || transitionName.equals("paginaErro"))
		{
			this.transicao ="Rejeitado(a)";
		}
			
		String actorId = auth.getName();
		long processId = executionContext.getProcessInstance().getId();
		//this.idCooperado = (Input) executionContext.getContextInstance().getVariable("codigoCliente");

		List<String>  lista = type.equals("1") ? cooperadoRepository.findByNif(nif.getValue()) :cooperadoRepository.findEmpresaByNif(nif.getValue());
		
		repository.saveDecision(lista.get(0).toString(),this.transicao, actorId, processId,this.host.getIpAddress(),this.host.getHostName());
	}
	
	public static Authentication getAuthentication() {
		if (SecurityHolder.get() != null) {
			return SecurityHolder.get();
		} else {
			return SecurityContextHolder.getContext().getAuthentication();
		}
	}

}