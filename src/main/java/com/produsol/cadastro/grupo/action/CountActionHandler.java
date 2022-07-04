package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;

public class CountActionHandler implements ActionHandler 
{
		private static final long serialVersionUID = 1L;
		@Override
		public void execute(ExecutionContext executionContext) throws Exception 
		{	
			Select qtIntegrantes = (Select) executionContext.getContextInstance().getVariable("qtIntegrantes");
			if(Integer.parseInt(qtIntegrantes.getValue().toString())<=3)
			{
				Hide(executionContext);
			}
		}
		
		public void Hide(ExecutionContext executionContext)
		{
			Input quartoCodigo = new Input();
			quartoCodigo.setName("quartoCodigo");
			quartoCodigo.setLabel("quartoCodigo");
			quartoCodigo.setReadonly(true);
			//quartoCodigo.setValue(String.valueOf("1"));
			executionContext.getContextInstance().setVariable("quartoCodigo", quartoCodigo);
			
			Input quintoCodigo = new Input();
			quintoCodigo.setName("quintoCodigo");
			quintoCodigo.setLabel("quintoCodigo");
			quintoCodigo.setReadonly(true);
			//quintoCodigo.setValue(String.valueOf("1"));
			executionContext.getContextInstance().setVariable("quintoCodigo", quintoCodigo);
			
			Input sextoCodigo = new Input();
			sextoCodigo.setName("sextoCodigo");
			sextoCodigo.setLabel("sextoCodigo");
			sextoCodigo.setReadonly(true);
			//sextoCodigo.setValue(String.valueOf("1"));
			executionContext.getContextInstance().setVariable("sextoCodigo", sextoCodigo);
			
			
			Input setimoCodigo = new Input();
			setimoCodigo.setName("setimoCodigo");
			setimoCodigo.setLabel("setimoCodigo");
			setimoCodigo.setReadonly(true);
			//setimoCodigo.setValue(String.valueOf("1"));
			executionContext.getContextInstance().setVariable("setimoCodigo", setimoCodigo);
		}
	}