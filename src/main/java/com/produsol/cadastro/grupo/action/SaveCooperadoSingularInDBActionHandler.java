package com.produsol.cadastro.grupo.action;

import java.util.Locale;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.repositories.CooperadoRepository;
import com.produsol.cadastro.grupo.vo.Cooperado;

public class SaveCooperadoSingularInDBActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	CooperadoRepository cooperadoRepository = new CooperadoRepository();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception {

		Cooperado cooperado = new Cooperado();
		Cooperado dados = setCooperadoData(executionContext,cooperado);	
		this.cooperadoRepository.saveCooperado(dados);
	}
	
	public Cooperado setCooperadoData(ExecutionContext executionContext, Cooperado cooperado)
	{
		Input iban = (Input) executionContext.getContextInstance().getVariable("iban");
		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		Input address = (Input) executionContext.getContextInstance().getVariable("moradaActual");
		Input firstName = (Input) executionContext.getContextInstance().getVariable("firstName");
		Input secondName = (Input) executionContext.getContextInstance().getVariable("secondName");
		Input lastName = (Input) executionContext.getContextInstance().getVariable("lastName");
		Input telefone = (Input) executionContext.getContextInstance().getVariable("telefone");
		Input naturalDe = (Input) executionContext.getContextInstance().getVariable("naturalDe");
		Input nomePai = (Input) executionContext.getContextInstance().getVariable("nomePai");
		Input nomeMae = (Input) executionContext.getContextInstance().getVariable("nomeMae");
		Input numBilhete = (Input) executionContext.getContextInstance().getVariable("numDocumento");
		Select estadoCivil = (Select) executionContext.getContextInstance().getVariable("estadoCivil");
		Input funcao = (Input) executionContext.getContextInstance().getVariable("funcao");
		Input nomeEmpresa = (Input) executionContext.getContextInstance().getVariable("nomeEmpresa");
		Select empresa_onde_trabalha = (Select) executionContext.getContextInstance().getVariable("empresa");
		Select habilitacoes_literarias = (Select) executionContext.getContextInstance().getVariable("habilitacoesLiterarias");
		Input dataEmissao = (Input) executionContext.getContextInstance().getVariable("dataEmissao");
		Select localEmissao = (Select) executionContext.getContextInstance().getVariable("localEmissao");
		Select bancoDomiciliacao = (Select) executionContext.getContextInstance().getVariable("bancoDomiciliacao");
		Input rendimento = (Input) executionContext.getContextInstance().getVariable("rendimento");
		Input numConta = (Input) executionContext.getContextInstance().getVariable("numConta");
		
		Select provinciaDe = (Select) executionContext.getContextInstance().getVariable("provinciaDe");
		Input dataNascimento = (Input) executionContext.getContextInstance().getVariable("dataNascimento");
		Input officeId = (Input) executionContext.getContextInstance().getVariable("officeId");
		String tipoContrato = "Determinado";
	
		String segundoNome = secondName.getValue().toString().equals("") ? "" : secondName.getValue().toString().toUpperCase(Locale.getDefault());
		String nomeDoPai = nomePai.getValue().toString().equals("") ? "" : nomePai.getValue().toString().toUpperCase(Locale.getDefault());
		cooperado.setTipoCooperado("1");
		cooperado.setMorada(address.getValue().toString().toUpperCase(Locale.getDefault()));
		cooperado.setTelefone(telefone.getValue().toString());
		cooperado.setNomeCooperado((firstName.getValue()+" "+segundoNome+ " "+ lastName.getValue()).toUpperCase(Locale.getDefault()));
		cooperado.setBancoCooperado(this.getSelectedValue(bancoDomiciliacao).toString().toUpperCase(Locale.getDefault())); 
		cooperado.setNumConta(numConta.getValue().toString());
		cooperado.setIban(iban.getValue().toString());
		cooperado.setFuncao(funcao.getValue().toString().toUpperCase(Locale.getDefault()));
		if(getSelectedValue(empresa_onde_trabalha).equals("Outros"))
		{cooperado.setEmpregador(nomeEmpresa.getValue().toString().toUpperCase(Locale.getDefault()));}
		else{cooperado.setEmpregador(getSelectedValue(empresa_onde_trabalha).toUpperCase(Locale.getDefault()));}
		cooperado.setSalarioBase(rendimento.getValue().toString());
		cooperado.setNumeroDoc(numBilhete.getValue().toString());
		cooperado.setNif(nif.getValue().toString());
		cooperado.setNumConta(numConta.getValue().toString());
		cooperado.setLocalEmissao(this.getSelectedValue(localEmissao).toString().toUpperCase(Locale.getDefault()));
		cooperado.setDataEmissao(dataEmissao.getValue().toString());
		cooperado.setNaturalDe(naturalDe.getValue().toString().toUpperCase(Locale.getDefault()));
		cooperado.setEstadoCivil(this.getSelectedValue(estadoCivil).toString().toUpperCase(Locale.getDefault()));
		cooperado.setNomePai(nomeDoPai);
		cooperado.setNomeMae(nomeMae.getValue().toString().toUpperCase(Locale.getDefault()));
		cooperado.setHabilitacoes(this.getSelectedValue(habilitacoes_literarias).toString().toUpperCase(Locale.getDefault()));
		cooperado.setDataNascimento(dataNascimento.getValue().toString());
		cooperado.setProvinciaDe(this.getSelectedValue(provinciaDe).toString().toUpperCase(Locale.getDefault()));
		cooperado.setTipoContrato(tipoContrato.toUpperCase(Locale.getDefault()));
		cooperado.setOfficeRegisto(officeId.getValue());
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