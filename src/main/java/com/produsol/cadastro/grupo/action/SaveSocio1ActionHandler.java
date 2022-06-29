package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.DelegationException;
import org.jbpm.graph.exe.ExecutionContext;

import com.openkm.bean.form.CheckBox;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.services.CompanyService;

public class SaveSocio1ActionHandler implements ActionHandler {
	
	private static final long serialVersionUID = 1L;
	private CompanyService companyService = new CompanyService();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	

		Input nomeFirstOutorgante = (Input) executionContext.getContextInstance().getVariable("nomeFirstOutorgante");
		Input dataNascFirstOutorgante   = (Input) executionContext.getContextInstance().getVariable("dataNascFirstOutorgante");
		Select nacionalidadeFirstOutorgante   = (Select) executionContext.getContextInstance().getVariable("nacionalidadeFirstOutorgante");
		Select estadoCivilFirstOutorgante   = (Select) executionContext.getContextInstance().getVariable("estadoCivilFirstOutorgante");
		Input numBilheteFirstOutorgante   = (Input) executionContext.getContextInstance().getVariable("numBilheteFirstOutorgante");
		Select localEmissaoFirstOutorgante = (Select) executionContext.getContextInstance().getVariable("localEmissaoFirstOutorgante");
		Input dataEmissaoFirstOutorgante    = (Input) executionContext.getContextInstance().getVariable("dataEmissaoFirstOutorgante");
		Input dataExpiracaoFirstOutorgante    = (Input) executionContext.getContextInstance().getVariable("dataExpiracaoFirstOutorgante");
		Input moradaFirstOutorgante    = (Input) executionContext.getContextInstance().getVariable("moradaFirstOutorgante");
		Select provinciaFirstOutorgante    = (Select) executionContext.getContextInstance().getVariable("provinciaFirstOutorgante");
		Input telefoneFirstOutorgante    = (Input) executionContext.getContextInstance().getVariable("telefoneFirstOutorgante");
		Input nifFirstOutorgante = (Input) executionContext.getContextInstance().getVariable("nifFirstOutorgante");
		Input rendimentoFirstOutorgante = (Input) executionContext.getContextInstance().getVariable("rendimentoFirstOutorgante");	
		Select bancoDomiciliacaoFirstOutorgante = (Select) executionContext.getContextInstance().getVariable("bancoDomiciliacaoFirstOutorgante");	
		Input emailFirstOutorgante = (Input) executionContext.getContextInstance().getVariable("emailFirstOutorgante");
		Input balcaoDomiciliacaoFirstOutorgante = (Input) executionContext.getContextInstance().getVariable("balcaoDomiciliacaoFirstOutorgante");
		
		Input ibanParticipante = (Input) executionContext.getContextInstance().getVariable("ibanParticipante");
	    
		
		
		Input cooperadoId = (Input) executionContext.getContextInstance().getVariable("modified"); 
	    Input pep   = (Input) executionContext.getContextInstance().getVariable("pep");
	    
	    
	    Select provinciaMorada = (Select) executionContext.getContextInstance().getVariable("provinciaMorada");
	    Input municipioMorada = (Input) executionContext.getContextInstance().getVariable("municipioMorada"); 
	    Input iban   = (Input) executionContext.getContextInstance().getVariable("iban");
	    
			try{				
	 this.companyService.SaveSocio(cooperadoId.getValue().toString(), nomeFirstOutorgante.getValue().toString(), dataNascFirstOutorgante.getValue().toString(),
			 getSelectedValue(nacionalidadeFirstOutorgante), getSelectedValue(estadoCivilFirstOutorgante), numBilheteFirstOutorgante.getValue().toString(),
			 getSelectedValue(localEmissaoFirstOutorgante), dataEmissaoFirstOutorgante.getValue().toString(),
								dataExpiracaoFirstOutorgante.getValue().toString(), moradaFirstOutorgante.getValue().toString(), getSelectedValue(provinciaFirstOutorgante),
								emailFirstOutorgante.getValue().toString(), telefoneFirstOutorgante.getValue().toString(), nifFirstOutorgante.getValue().toString(),
								rendimentoFirstOutorgante.getValue().toString(),getSelectedValue(bancoDomiciliacaoFirstOutorgante),
								balcaoDomiciliacaoFirstOutorgante.getValue().toString(),pep.getValue().toString(),
								getSelectedValue(provinciaMorada),municipioMorada.getValue(), iban.getValue().toString());   
			}
			catch (Exception e) {
				executionContext.getProcessInstance().end();
				throw new DelegationException("Erro ao Associar o Primeiro Representante legal", e);
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