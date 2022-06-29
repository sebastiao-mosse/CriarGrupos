package com.produsol.cadastro.grupo.action;

import java.util.ArrayList;
import java.util.List;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.repositories.DepartamentoRepository;
import com.produsol.cadastro.grupo.repositories.DuracaoProcessoRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.util.Refactoring;
import com.produsol.cadastro.grupo.vo.DuracaoProcessoVo;

public class SaveDuracaoProcessoHandler implements ActionHandler {
	private static final long serialVersionUID = 1L;
	DuracaoProcessoRepository repository = new DuracaoProcessoRepository();
	ConversorDataHora dataHora = new ConversorDataHora();
	Refactoring util = new Refactoring();
	DepartamentoRepository departamento = new DepartamentoRepository();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception 
	{
		
		long idProcesso = executionContext.getProcessInstance().getId();
		DuracaoProcessoVo objecto = new DuracaoProcessoVo();
		objecto.setDataInicio(dataHora.getDataActual());
		objecto.setIdProcesso(String.valueOf(idProcesso));
		objecto.setIdDepartamento(this.getUserDepartamentId());
		objecto.setIdOperacao("1");
		repository.save(objecto);
		
		Input dataInicio = new Input();
		dataInicio.setName("dataInicio");
		dataInicio.setLabel("dataInicio");
		dataInicio.setValue(String.valueOf(dataHora.getDataActual()));
		executionContext.getContextInstance().setVariable("dataInicio", dataInicio);
		
		System.out.println("Data e Hora actual: "+ dataHora.getDataActual());
	}
	
	public String getUserDepartamentId() throws Exception
	{	
		String departament = "";		
		List<String> lista = new ArrayList<String>();
		lista = this.util.getRolesByUser(util.getAuthentication().getName());
		for(int i=0; i<lista.size(); i++)
		{
			if(lista.get(i).equals("ROLE_ASSISTENTE_COOPERADO") || lista.get(i).equals("ROLE_DIRECAO_COMERCIAL"))
			{
				departament ="COMERCIAL";
			}
		}
		return  this.departamento.getIdByName(departament);
	}
	

}