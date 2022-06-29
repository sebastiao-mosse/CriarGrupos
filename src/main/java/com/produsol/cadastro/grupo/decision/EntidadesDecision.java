package com.produsol.cadastro.grupo.decision;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;
import com.openkm.bean.form.Select;

public class EntidadesDecision implements DecisionHandler
{
	private static final long serialVersionUID = 1L;

	@Override
	public String decide(ExecutionContext executionContext) throws Exception 
	{
		String retorno ="";
		Select empresa = (Select) executionContext.getContextInstance().getVariable("empresa");
		
		if(getSelectedValue(empresa).equals("Outros"))
		{
			retorno = "inserir_empresa";
		}
		else{
				retorno =  "load_docs";
			}
		return retorno;    	
	}

	public String getSelectedValue(Select selectedValue)
	{
		String retorno ="";
		for(int i=0; i<selectedValue.getOptions().size();i++)
		{
			if(selectedValue.getOptions().get(i).isSelected())
			{
				retorno = selectedValue.getOptions().get(i).getLabel();
			}
		}
		return retorno;
	}	

}