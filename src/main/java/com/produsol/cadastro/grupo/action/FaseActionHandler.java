package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.repositories.FaseProcessoRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.vo.FaseProcessoVO;

public class FaseActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	Authentication auth = getAuthentication();

	FaseProcessoRepository fase = new FaseProcessoRepository();
	ConversorDataHora dataHora = new ConversorDataHora();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception {
		
		FaseProcessoVO faseVo = new FaseProcessoVO();
		faseVo.setDataInicio(dataHora.getDataActual());
		faseVo.setDataConclusao(dataHora.getDataActual());
		faseVo.setDepartamento("COMERCIAL");
		faseVo.setFaseDescricao("Selecionar Tipo Cooperado");
		faseVo.setIdProcesso(executionContext.getProcessInstance().getId());
		faseVo.setStatus("CONCLUIDO");
		faseVo.setUsuario(auth.getName());
		//fase.savePhase(faseVo);
	}

	public static Authentication getAuthentication() {
		if (SecurityHolder.get() != null) {
			return SecurityHolder.get();
		} else {
			return SecurityContextHolder.getContext().getAuthentication();
		}
	}
}