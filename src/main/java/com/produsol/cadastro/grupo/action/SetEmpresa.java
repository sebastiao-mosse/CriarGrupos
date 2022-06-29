package com.produsol.cadastro.grupo.action;

import java.util.Locale;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;

public class SetEmpresa implements ActionHandler {

	private static final long serialVersionUID = 1L;
	public void execute(ExecutionContext executionContext) throws Exception 
	{
		Input nomeEmpresa = (Input) executionContext.getContextInstance().getVariable("nomeEmpresa");
		Select empresa_onde_trabalha = (Select) executionContext.getContextInstance().getVariable("empresa");
		Input empresa = new Input();
		empresa.setName("empresa");
		empresa.setLabel("empresa");
		
		if(getSelectedValue(empresa_onde_trabalha).equals("Outros")){empresa.setValue(String.valueOf(nomeEmpresa.getValue().toString().toUpperCase(Locale.getDefault())));}
		else{empresa.setValue(String.valueOf(getSelectedValue(empresa_onde_trabalha).toUpperCase(Locale.getDefault())));}		
		executionContext.getContextInstance().setVariable("empresa", empresa);		
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
