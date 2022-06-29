package com.produsol.cadastro.grupo.action;

import java.util.ArrayList;
import java.util.List;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import com.produsol.cadastro.grupo.repositories.MetricaRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.vo.MetricaVo;

public class FinalizarMetricaActionHandler implements ActionHandler {
	private static final long serialVersionUID = 1L;
	MetricaRepository metricaRepository = new MetricaRepository();
	ConversorDataHora dataHora = new ConversorDataHora();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception 
	{
		List<MetricaVo> lista = new ArrayList<MetricaVo>();
		MetricaVo metrica = new MetricaVo();
		MetricaVo metrica2 = new MetricaVo();
		metrica.setDescricao("PROCESSOS CONCLUÍDOS");
		metrica2.setDescricao("PROCESSOS CONCLUÍDOS POR DEPARTAMENTO");
		lista.add(metrica);lista.add(metrica2);
		metricaRepository.update(lista);
		
	}

}