package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.repositories.DepartamentoRepository;
import com.produsol.cadastro.grupo.repositories.DuracaoProcessoRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.util.Refactoring;
import com.produsol.cadastro.grupo.vo.DuracaoProcessoVo;

public class ComplianceUpdateDuracaoProcessoHandler implements ActionHandler {
	private static final long serialVersionUID = 1L;
	DuracaoProcessoRepository repository = new DuracaoProcessoRepository();
	ConversorDataHora dataHora = new ConversorDataHora();
	
	Refactoring util = new Refactoring();
	DepartamentoRepository departamento = new DepartamentoRepository();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception 
	{
		long idProcesso = executionContext.getProcessInstance().getId();
		Input complianceDataInicio = (Input) executionContext.getContextInstance().getVariable("complianceDataInicio");
		DuracaoProcessoVo objecto = new DuracaoProcessoVo();
		objecto.setDataInicio(complianceDataInicio.getValue().toString());;
		objecto.setDataTermino(dataHora.getDataActual());
		objecto.setIdProcesso(String.valueOf(idProcesso));
		objecto.setIdOperacao("1");
		objecto.setHora(util.getHours(dataHora.getDataActual(), complianceDataInicio.getValue().toString()));
		objecto.setMinuto(util.getMinutes(dataHora.getDataActual(), complianceDataInicio.getValue().toString()));	
		objecto.setIdDepartamento(departamento.getIdByName("COMPLIANCE"));
		repository.saveComplianceDuracao(objecto);	
	}
	
	

}