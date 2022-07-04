package com.produsol.cadastro.grupo.decision;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;

public class userDecision implements DecisionHandler
{
	private static final long serialVersionUID = 1L;

	@Override
	public String decide(ExecutionContext executionContext) throws Exception 
	{
		Select qtIntegrantes = (Select) executionContext.getContextInstance().getVariable("qtIntegrantes");
		Input count = (Input) executionContext.getContextInstance().getVariable("count");
		String retorno ="";

		if(Integer.parseInt(count.getValue())>=1 && Integer.parseInt(count.getValue().toString()) <= Integer.parseInt(qtIntegrantes.getValue().toString()))
		{
			retorno = "pesquisar";
		}
		else
		{
			retorno = "listar";
		}
		count.setValue(String.valueOf(Integer.parseInt(count.getValue().toString()) + 1));
		executionContext.getContextInstance().setVariable("count", count);
		
		return retorno;    	
	}

}