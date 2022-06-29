package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.repositories.CooperadoRepository;
import com.produsol.cadastro.grupo.vo.Cooperado;

public class UpdateCooperadoActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	CooperadoRepository cooperadoRepository = new CooperadoRepository();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception {

		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		Input idCooperado = (Input) executionContext.getContextInstance().getVariable("idCooperado");
		Cooperado cooperado = new Cooperado();
		
		cooperado.setNif(nif.getValue().toString());
		cooperado.setNumeroMatricula(idCooperado.getValue());	
		this.cooperadoRepository.updateCooperado(cooperado);
		
		
		
	}

}