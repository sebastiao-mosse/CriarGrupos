package com.produsol.cadastro.grupo.action;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.DelegationException;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.services.CompanyService;

public class SaveBeneficiosActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	private CompanyService companyService = new CompanyService();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	

		Input nomeFirstBeneficiario = (Input) executionContext.getContextInstance().getVariable("nomeFirstBeneficiario");
		Input capitalFirstBeneficiario   = (Input) executionContext.getContextInstance().getVariable("capitalFirstBeneficiario");
		Input nifFirstBeneficiario   = (Input) executionContext.getContextInstance().getVariable("nifFirstBeneficiario");
		Input cooperadoId = (Input) executionContext.getContextInstance().getVariable("modified"); 
	    Select pep   = (Select) executionContext.getContextInstance().getVariable("pep");
	    try{							
	 this.companyService.SaveBeneficiario(cooperadoId.getValue().toString(), nomeFirstBeneficiario.getValue().toString(), 
			 nifFirstBeneficiario.getValue().toString(), capitalFirstBeneficiario.getValue().toString(),pep.getValue().toString());
			 
	    }
			catch (Exception e) {
				executionContext.getProcessInstance().end();
				throw new DelegationException("Erro ao Associar o Procurador", e);
			}
		}

}