package com.produsol.cadastro.grupo.fase.singular;

import java.util.List;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.repositories.CooperadoRepository;
import com.produsol.cadastro.grupo.repositories.FaseProcessoRepository;
import com.produsol.cadastro.grupo.repositories.ProcessoRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.vo.FaseProcessoVO;

public class InserirProcessoActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;

	FaseProcessoRepository fase = new FaseProcessoRepository();
	ConversorDataHora dataHora = new ConversorDataHora();
	ProcessoRepository processRepository = new ProcessoRepository();
	CooperadoRepository cooperadoRepository = new CooperadoRepository();
	Authentication auth = getAuthentication();
	

	@Override
	public void execute(ExecutionContext executionContext) throws Exception {
		
		System.out.println("Insirindo o processo");
		long taskManagementInstance = executionContext.getTaskMgmtInstance().getId();
		long swimlaneInstance       = executionContext.getTaskMgmtInstance().getSwimlaneInstance("initiator").getId();	
		String actorId              = auth.getName();
		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		
		List<String> lista = cooperadoRepository.findByNif(nif.getValue());
		
		processRepository.save(executionContext.getProcessInstance().getId(),this.dataHora.getDataActual()
				,Propriedades.getProp().getString("produto.codigo.operacao.cadastro.cooperados"),
				taskManagementInstance, swimlaneInstance, actorId,lista.get(0), Propriedades.getProp().getString("produto.status.execucao"),
				executionContext.getProcessInstance().getId(),Propriedades.getProp().getString("process.area.comercial"));
		
		Input codigoCliente = new Input();
		codigoCliente.setName("codigoCliente");
		codigoCliente.setLabel("codigoCliente");
		codigoCliente.setValue(String.valueOf(lista.get(0)));
		executionContext.getContextInstance().setVariable("codigoCliente", codigoCliente);
	}

	public static Authentication getAuthentication() {
		if (SecurityHolder.get() != null) {
			return SecurityHolder.get();
		} else {
			return SecurityContextHolder.getContext().getAuthentication();
		}
	}

}