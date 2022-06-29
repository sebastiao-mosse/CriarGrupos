package com.produsol.cadastro.grupo.fase.empresa;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.repositories.FaseProcessoRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.vo.FaseProcessoVO;

public class FaseCadastroEmpresaActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;

	FaseProcessoRepository fase = new FaseProcessoRepository();
	ConversorDataHora dataHora = new ConversorDataHora();
	Authentication auth = getAuthentication();
	
	@Override
	public void execute(ExecutionContext executionContext) throws Exception {
	
		Input numeroNif    = (Input) executionContext.getContextInstance().getVariable("nif");
		Input tipoCooperado    = (Input) executionContext.getContextInstance().getVariable("tipoCooperado");
		
		System.out.println("Insirindo as fases");
		FaseProcessoVO faseVo = new FaseProcessoVO();
		faseVo.setDataInicio("");
		faseVo.setDataConclusao("");
		faseVo.setDepartamento("COMERCIAL");
		faseVo.setIdProcesso(executionContext.getProcessInstance().getId());
		faseVo.setStatus("");
		faseVo.setUsuario(auth.getName());
		faseVo.setNif(numeroNif.getValue().toString());
		fase.saveListPhase(faseVo, tipoCooperado.getValue().toString());
	}
	
	public static Authentication getAuthentication() {
		if (SecurityHolder.get() != null) {
			return SecurityHolder.get();
		} else {
			return SecurityContextHolder.getContext().getAuthentication();
		}
	}

}