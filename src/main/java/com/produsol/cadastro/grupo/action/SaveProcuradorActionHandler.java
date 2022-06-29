package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.DelegationException;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.services.CompanyService;

public class SaveProcuradorActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	private CompanyService companyService = new CompanyService();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	

		Input nomeProcurador = (Input) executionContext.getContextInstance().getVariable("nomeProcurador");
		Input dataNascProcurador   = (Input) executionContext.getContextInstance().getVariable("dataNascProcurador");
		Select nacionalidadeProcurador   = (Select) executionContext.getContextInstance().getVariable("nacionalidadeProcurador");
		Select estadoCivilProcurador   = (Select) executionContext.getContextInstance().getVariable("estadoCivilProcurador");
		Input numBIProcurador   = (Input) executionContext.getContextInstance().getVariable("numBIProcurador");
		Input dataEmissaoProcurador = (Input) executionContext.getContextInstance().getVariable("dataEmissaoProcurador");
		Input dataExpiracaoProcurador    = (Input) executionContext.getContextInstance().getVariable("dataExpiracaoProcurador");
		Select localEmissaoProcurador    = (Select) executionContext.getContextInstance().getVariable("localEmissaoProcurador");
		Input nif    = (Input) executionContext.getContextInstance().getVariable("nif");
		Input moradaProcurador    = (Input) executionContext.getContextInstance().getVariable("moradaProcurador");
		Select bancoProcurador = (Select) executionContext.getContextInstance().getVariable("bancoProcurador");
		Input balcaoProcurador = (Input) executionContext.getContextInstance().getVariable("balcaoProcurador");	
		//Input numContaProcurador = (Input) executionContext.getContextInstance().getVariable("numContaProcurador");	
		Input emailProcurador = (Input) executionContext.getContextInstance().getVariable("emailProcurador");
		Input telefoneProcurador = (Input) executionContext.getContextInstance().getVariable("telefoneProcurador");
	    Input percentagemProcurador = (Input) executionContext.getContextInstance().getVariable("percentagemProcurador"); 
	    Input rendimentoProcurador = (Input) executionContext.getContextInstance().getVariable("rendimentoProcurador"); 
	    Input cooperadoId = (Input) executionContext.getContextInstance().getVariable("modified"); 
	    Select provinciaProcurador = (Select) executionContext.getContextInstance().getVariable("provinciaProcurador");
	    Input funcaoProcurador = (Input) executionContext.getContextInstance().getVariable("funcaoProcurador");
	    Select pep   = (Select) executionContext.getContextInstance().getVariable("pep");
	    try{				
				
	 this.companyService.SaveProcurador(cooperadoId.getValue().toString(), nomeProcurador.getValue().toString(), dataNascProcurador.getValue().toString(),
			 getSelectedValue(nacionalidadeProcurador), getSelectedValue(estadoCivilProcurador),
			 numBIProcurador.getValue().toString(), getSelectedValue(localEmissaoProcurador), dataEmissaoProcurador.getValue().toString(),
			 dataExpiracaoProcurador.getValue().toString(), moradaProcurador.getValue().toString(),
			 getSelectedValue(provinciaProcurador), emailProcurador.getValue().toString(), telefoneProcurador.getValue().toString(), 
			 nif.getValue().toString(), rendimentoProcurador.getValue().toString(),
			 getSelectedValue(bancoProcurador), balcaoProcurador.getValue().toString(), 
			 percentagemProcurador.getValue().toString(),funcaoProcurador.getValue().toString(),
			 pep.getValue().toString());
	    }
	 
			catch (Exception e) {
				executionContext.getProcessInstance().end();
				throw new DelegationException("Erro ao Associar o Procurador", e);
			}
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