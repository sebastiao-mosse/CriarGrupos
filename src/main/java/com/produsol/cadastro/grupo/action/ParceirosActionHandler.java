package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import com.produsol.cadastro.grupo.repositories.ParceirosRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;

public class ParceirosActionHandler implements ActionHandler {
	private static final long serialVersionUID = 1L;
	ParceirosRepository parceiroRepository = new ParceirosRepository();
	ConversorDataHora dataHora = new ConversorDataHora();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception 
	{
		parceiroRepository.savePartner();
	}

}