package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;

	public class SectorInstitucionalActionHandler implements ActionHandler {

		private static final long serialVersionUID = 1L;

		@Override
		public void execute(ExecutionContext executionContext) throws Exception 
		{
			Select sector = (Select) executionContext.getContextInstance().getVariable("sectorInstitucional");
			
			System.out.println("Valor do Sector INstitucuional: "+ sector);
			System.out.println("Valor do Sector INstitucuional: "+ getSelectedValue(sector));
			Input InputsectorInstitucional = new Input();
			InputsectorInstitucional.setName("InputsectorInstitucional");
			InputsectorInstitucional.setLabel("InputsectorInstitucional");
			InputsectorInstitucional.setValue(String.valueOf(getSelectedValue(sector)));
			executionContext.getContextInstance().setVariable("InputsectorInstitucional", InputsectorInstitucional);	
			
			
			Select province = (Select) executionContext.getContextInstance().getVariable("provincia");
			Select proviRegistro = (Select) executionContext.getContextInstance().getVariable("provinciaRegistro");
			Select banco = (Select) executionContext.getContextInstance().getVariable("bancoDomiciliacao");
			Select pep1 = (Select) executionContext.getContextInstance().getVariable("pep");
			this.setCampos(executionContext, province, proviRegistro, pep1, banco);
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
		

		public void setCampos(ExecutionContext executionContext,Select provincia1, Select provinciaRegistro1, Select pep1, Select bancoDomiciliacao1)
		{
			Input provincia = new Input();
			provincia.setName("provincia");
			provincia.setLabel("provincia");
			provincia.setValue(String.valueOf(getSelectedValue(provincia1)));
			executionContext.getContextInstance().setVariable("provincia", provincia);
			
			Input provinciaRegistro = new Input();
			provinciaRegistro.setName("provinciaRegistro");
			provinciaRegistro.setLabel("provinciaRegistro");
			provinciaRegistro.setValue(String.valueOf(getSelectedValue(provinciaRegistro1)));
			executionContext.getContextInstance().setVariable("provinciaRegistro", provinciaRegistro);
			
			Input pep = new Input();
			pep.setName("pep");
			pep.setLabel("pep");
			pep.setValue(String.valueOf(getSelectedValue(pep1)));
			executionContext.getContextInstance().setVariable("pep", pep);
			
			Input bancoDomiciliacao = new Input();
			bancoDomiciliacao.setName("bancoDomiciliacao");
			bancoDomiciliacao.setLabel("bancoDomiciliacao");
			bancoDomiciliacao.setValue(String.valueOf(getSelectedValue(bancoDomiciliacao1)));
			executionContext.getContextInstance().setVariable("bancoDomiciliacao", bancoDomiciliacao);
			
			System.out.println("Banco Domiciliacao: "+ getSelectedValue(bancoDomiciliacao1));
			System.out.println("Pep: "+ getSelectedValue(pep1));
			System.out.println("Provincia: "+ getSelectedValue(provinciaRegistro1));
			System.out.println("Provincia de Registro1: "+ getSelectedValue(provincia1));
		}
		
	}