package com.produsol.cadastro.grupo.fase.empresa;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.repositories.FaseProcessoRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.vo.FaseProcessoVO;

public class SegundoSocio implements ActionHandler {

	private static final long serialVersionUID = 1L;

	FaseProcessoRepository fase = new FaseProcessoRepository();
	ConversorDataHora dataHora = new ConversorDataHora();
	Authentication auth = getAuthentication();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception {
		
		FaseProcessoVO faseVo = new FaseProcessoVO();
		faseVo.setCodigo(8L);
		faseVo.setIdProcesso(executionContext.getProcessInstance().getId());
		faseVo.setDataInicio(dataHora.getDataActual());
		faseVo.setDataConclusao(dataHora.getDataActual());
		faseVo.setDepartamento("COMERCIAL");
		faseVo.setStatus("CONCLUIDO");
		faseVo.setUsuario(auth.getName());
		fase.updatePhase(faseVo);
	}

	public static Authentication getAuthentication() {
		if (SecurityHolder.get() != null) {
			return SecurityHolder.get();
		} else {
			return SecurityContextHolder.getContext().getAuthentication();
		}
	}
}