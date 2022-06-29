package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.DelegationException;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.services.CompanyService;

public class SaveSocio2ActionHandler implements ActionHandler {
	
	private static final long serialVersionUID = 1L;
	private CompanyService companyService = new CompanyService();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	

		Input nomeSecondOutorgante = (Input) executionContext.getContextInstance().getVariable("nomeSecondOutorgante");
		Input dataNascSecondOutorgante   = (Input) executionContext.getContextInstance().getVariable("dataNascSecondOutorgante");
		Select nacionalidadeSecondOutorgante   = (Select) executionContext.getContextInstance().getVariable("nacionalidadeSecondOutorgante");
		Select estadoCivilSecondOutorgante   = (Select) executionContext.getContextInstance().getVariable("estadoCivilSecondOutorgante");
		Input numBilheteSecondOutorgante   = (Input) executionContext.getContextInstance().getVariable("numBilheteSecondOutorgante");
		Select localEmissaoSecondOutorgante = (Select) executionContext.getContextInstance().getVariable("localEmissaoSecondOutorgante");
		Input dataEmissaoSecondOutorgante    = (Input) executionContext.getContextInstance().getVariable("dataEmissaoSecondOutorgante");
		Input dataExpiracaoSecondOutorgante    = (Input) executionContext.getContextInstance().getVariable("dataExpiracaoSecondOutorgante");
		Input moradaSecondOutorgante    = (Input) executionContext.getContextInstance().getVariable("moradaSecondOutorgante");
		Select provinciaSecondOutorgante    = (Select) executionContext.getContextInstance().getVariable("provinciaSecondOutorgante");
		Input telefoneSecondOutorgante    = (Input) executionContext.getContextInstance().getVariable("telefoneSecondOutorgante");
		Input nifSecondOutorgante = (Input) executionContext.getContextInstance().getVariable("nifSecondOutorgante");
		Input rendimentoSecondOutorgante = (Input) executionContext.getContextInstance().getVariable("rendimentoSecondOutorgante");	
		Select bancoDomiciliacaoSecondOutorgante = (Select) executionContext.getContextInstance().getVariable("bancoDomiciliacaoSecondOutorgante");	
		Input emailSecondOutorgante = (Input) executionContext.getContextInstance().getVariable("emailSecondOutorgante");
		Input balcaoDomiciliacaoSecondOutorgante = (Input) executionContext.getContextInstance().getVariable("balcaoDomiciliacaoSecondOutorgante");
	    Input cooperadoId = (Input) executionContext.getContextInstance().getVariable("modified"); 
	    Select pep   = (Select) executionContext.getContextInstance().getVariable("pep");
	    
	    Select provinciaMorada = (Select) executionContext.getContextInstance().getVariable("provinciaMorada");
	    Input municipioMorada = (Input) executionContext.getContextInstance().getVariable("municipioMorada"); 
	    Input iban   = (Input) executionContext.getContextInstance().getVariable("iban");
	    
	    
			try{				
				
	 this.companyService.SaveSocio(cooperadoId.getValue().toString(), nomeSecondOutorgante.getValue().toString(), dataNascSecondOutorgante.getValue().toString(),
			 getSelectedValue(nacionalidadeSecondOutorgante), getSelectedValue(estadoCivilSecondOutorgante), numBilheteSecondOutorgante.getValue().toString(),
			 getSelectedValue(localEmissaoSecondOutorgante), dataEmissaoSecondOutorgante.getValue().toString(),
								dataExpiracaoSecondOutorgante.getValue().toString(), moradaSecondOutorgante.getValue().toString(), getSelectedValue(provinciaSecondOutorgante),
								emailSecondOutorgante.getValue().toString(), telefoneSecondOutorgante.getValue().toString(), nifSecondOutorgante.getValue().toString(),
								rendimentoSecondOutorgante.getValue().toString(),getSelectedValue(bancoDomiciliacaoSecondOutorgante), balcaoDomiciliacaoSecondOutorgante.getValue().toString(),
								pep.getValue().toString(),
								getSelectedValue(provinciaMorada),municipioMorada.getValue(), iban.getValue().toString());   
		}
			catch (Exception e) {
				executionContext.getProcessInstance().end();
				throw new DelegationException("Erro ao Associar o Segundo Representante legal", e);
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