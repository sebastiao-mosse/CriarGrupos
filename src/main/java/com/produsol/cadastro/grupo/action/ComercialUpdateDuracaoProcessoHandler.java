package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.repositories.DepartamentoRepository;
import com.produsol.cadastro.grupo.repositories.DuracaoProcessoRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.util.Refactoring;
import com.produsol.cadastro.grupo.vo.DuracaoProcessoVo;

public class ComercialUpdateDuracaoProcessoHandler implements ActionHandler {
	private static final long serialVersionUID = 1L;
	DuracaoProcessoRepository repository = new DuracaoProcessoRepository();
	ConversorDataHora dataHora = new ConversorDataHora();
	
	Refactoring util = new Refactoring();
	DepartamentoRepository departamento = new DepartamentoRepository();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception 
	{
		long idProcesso = executionContext.getProcessInstance().getId();
		Input dataInicio = (Input) executionContext.getContextInstance().getVariable("dataInicio");
		DuracaoProcessoVo objecto = new DuracaoProcessoVo();
		objecto.setDataTermino(dataHora.getDataActual());
		objecto.setIdProcesso(String.valueOf(idProcesso));
		objecto.setIdOperacao("1");
		objecto.setHora(util.getHours(dataHora.getDataActual(), dataInicio.getValue().toString()));
		objecto.setMinuto(util.getMinutes(dataHora.getDataActual(), dataInicio.getValue().toString()));	
		objecto.setIdDepartamento(departamento.getIdByName("COMERCIAL"));
		repository.AddTime(objecto);
		
		Input complianceDataInicio = new Input();
		complianceDataInicio.setName("complianceDataInicio");
		complianceDataInicio.setLabel("complianceDataInicio");
		complianceDataInicio.setValue(String.valueOf(dataHora.getDataActual()));
		executionContext.getContextInstance().setVariable("complianceDataInicio", complianceDataInicio);
		
	}
	


}