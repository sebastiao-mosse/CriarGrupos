package com.produsol.cadastro.grupo.action;

import java.util.Locale;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.repositories.CooperadoRepository;
import com.produsol.cadastro.grupo.vo.Cooperado;

public class SaveCooperadoEmpresaInDBActionHandler2 implements ActionHandler {

	private static final long serialVersionUID = 1L;
	CooperadoRepository cooperadoRepository = new CooperadoRepository();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception {
		
		Cooperado cooperado = new Cooperado();
		Cooperado dados = setCooperadoData(executionContext, cooperado);
		this.cooperadoRepository.saveEmpresa(dados);
	}
	
	public Cooperado setCooperadoData(ExecutionContext executionContext,Cooperado cooperado) 
	{
		Input denominacaoSocial = (Input) executionContext.getContextInstance().getVariable("denominacaoSocial");
		Input naturezaJuridica = (Input) executionContext.getContextInstance().getVariable("naturezaJuridica");
		Input sedePrincipal = (Input) executionContext.getContextInstance().getVariable("sedePrincipal");
		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		Input matriculaRegComercial = (Input) executionContext.getContextInstance().getVariable("matriculaRegComercial");
		Input certidao = (Input) executionContext.getContextInstance().getVariable("certidao");
		Input telefone = (Input) executionContext.getContextInstance().getVariable("telefone");
		Input objectoSocial = (Input) executionContext.getContextInstance().getVariable("objectoSocial");
		Input finalidadeNegocio = (Input) executionContext.getContextInstance().getVariable("finalidadeNegocio");
		Select pep = (Select) executionContext.getContextInstance().getVariable("pep");
		Select bancoDomiciliacao = (Select) executionContext.getContextInstance().getVariable("bancoDomiciliacao");
		Input iban = (Input) executionContext.getContextInstance().getVariable("iban");
		Input officeId = (Input) executionContext.getContextInstance().getVariable("officeId");
		boolean ispep = pep.getValue().toString() == "sim" ? true : false;
		
		String numeroCertidao = certidao.getValue().equals("") ? "" : certidao.getValue();
		
		cooperado.setNomeCooperado(denominacaoSocial.getValue().toUpperCase(Locale.getDefault()));
		cooperado.setTipoCooperado("2");
		cooperado.setNif(nif.getValue().toString());
		cooperado.setTelefone(telefone.getValue().toString());
		cooperado.setBancoCooperado(this.getSelectedValue(bancoDomiciliacao).toString().toUpperCase(Locale.getDefault()));
		cooperado.setIban(iban.getValue().toString());
		cooperado.setPep(ispep);
		cooperado.setNaturezaJuridica(naturezaJuridica.getValue().toString().toUpperCase(Locale.getDefault()));
		cooperado.setSedePrincipal(sedePrincipal.getValue().toString().toUpperCase(Locale.getDefault()));
		cooperado.setRegistoComercial(matriculaRegComercial.getValue().toUpperCase(Locale.getDefault()));
		cooperado.setObjectoSocial(objectoSocial.getValue().toString().toUpperCase(Locale.getDefault()));
		cooperado.setFinalidade(finalidadeNegocio.getValue().toString().toUpperCase(Locale.getDefault()));
		cooperado.setOfficeRegisto(officeId.getValue().toString());
		cooperado.setCertidao(numeroCertidao);
		return cooperado;
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