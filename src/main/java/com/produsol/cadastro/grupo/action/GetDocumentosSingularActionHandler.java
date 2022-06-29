package com.produsol.cadastro.grupo.action;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.services.DocumentService;


public class GetDocumentosSingularActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	
	Propriedades api = new  Propriedades();
	DocumentService documento = new DocumentService();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	
		Input caminho = (Input)executionContext.getContextInstance().getVariable("caminho");
		
		
		String resultado = documento.getDocumentUUID(caminho.getValue().toString(), api.GetEnviromentVariables().get(9).replace("\"", ""))
				.toString().replace("[", "").replace("]", "");
		
		List<String> lista = Arrays.asList(resultado.split(",", -1));
		System.out.println("Lista: "+ lista);
		
		Input idBilhete = new Input();
		idBilhete.setName("idBilhete");
		idBilhete.setLabel("idBilhete");
		idBilhete.setValue(String.valueOf(lista.get(1).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idBilhete", idBilhete);
		
		Input idNif = new Input();
		idNif.setName("idNif");
		idNif.setLabel("idNif");
		idNif.setValue(String.valueOf(lista.get(2).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idNif", idNif);
		
		Input idFichaSingular = new Input();
		idFichaSingular.setName("idFichaSingular");
		idFichaSingular.setLabel("idFichaSingular");
		idFichaSingular.setValue(String.valueOf(lista.get(0).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idFichaSingular", idFichaSingular);
		
		Input idFoto = new Input();
		idFoto.setName("idFoto");
		idFoto.setLabel("idFoto");
		idFoto.setValue(String.valueOf(lista.get(3).replace("\"", "")));
		executionContext.getContextInstance().setVariable("idFoto", idFoto);
	
		Select provinciaMorada1 = (Select) executionContext.getContextInstance().getVariable("provinciaMorada");
		Select provinciaDe1 = (Select) executionContext.getContextInstance().getVariable("provinciaDe");
		Select estadoCivil1 = (Select) executionContext.getContextInstance().getVariable("estadoCivil");
		Select habilitacoes_literarias1 = (Select) executionContext.getContextInstance().getVariable("habilitacoesLiterarias");
		Select nacionalidade1 = (Select) executionContext.getContextInstance().getVariable("nacionalidade");
		Select bancoDomiciliacao1 = (Select) executionContext.getContextInstance().getVariable("bancoDomiciliacao");
		Select localEmissao1 = (Select) executionContext.getContextInstance().getVariable("localEmissao");
		
		this.setCampos(executionContext, provinciaMorada1, provinciaDe1, estadoCivil1, habilitacoes_literarias1, 
				nacionalidade1, bancoDomiciliacao1, localEmissao1);

	}
	
	public void setCampos(ExecutionContext executionContext,Select provinciaMorada1, Select provinciaDe1, Select estadoCivil1, 
			Select habilitacoes_literarias1, Select nacionalidade1, Select bancoDomiciliacao1, Select localEmissao1)
	{
		Input provinciaMorada = new Input();
		provinciaMorada.setName("provinciaMorada");
		provinciaMorada.setLabel("provinciaMorada");
		provinciaMorada.setValue(String.valueOf(getSelectedValue(provinciaMorada1)));
		executionContext.getContextInstance().setVariable("provinciaMorada", provinciaMorada);
		
		Input provinciaDe = new Input();
		provinciaDe.setName("provinciaDe");
		provinciaDe.setLabel("provinciaDe");
		provinciaDe.setValue(String.valueOf(getSelectedValue(provinciaDe1)));
		executionContext.getContextInstance().setVariable("provinciaDe", provinciaDe);
		
		Input estadoCivil = new Input();
		estadoCivil.setName("estadoCivil");
		estadoCivil.setLabel("estadoCivil");
		estadoCivil.setValue(String.valueOf(getSelectedValue(estadoCivil1)));
		executionContext.getContextInstance().setVariable("estadoCivil", estadoCivil);
		
		Input habilitacoes_literarias = new Input();
		habilitacoes_literarias.setName("habilitacoes_literarias");
		habilitacoes_literarias.setLabel("habilitacoes_literarias");
		habilitacoes_literarias.setValue(String.valueOf(getSelectedValue(habilitacoes_literarias1)));
		executionContext.getContextInstance().setVariable("habilitacoes_literarias", habilitacoes_literarias);

		Input nacionalidade = new Input();
		nacionalidade.setName("nacionalidade");
		nacionalidade.setLabel("nacionalidade");
		nacionalidade.setValue(String.valueOf(getSelectedValue(nacionalidade1)));
		executionContext.getContextInstance().setVariable("nacionalidade", nacionalidade);
		
		
		
		Input bancoDomiciliacao = new Input();
		bancoDomiciliacao.setName("bancoDomiciliacao");
		bancoDomiciliacao.setLabel("bancoDomiciliacao");
		bancoDomiciliacao.setValue(String.valueOf(getSelectedValue(bancoDomiciliacao1)));
		executionContext.getContextInstance().setVariable("bancoDomiciliacao", bancoDomiciliacao);
		
		
		Input localEmissao = new Input();
		localEmissao.setName("localEmissao");
		localEmissao.setLabel("localEmissao");
		localEmissao.setValue(String.valueOf(getSelectedValue(localEmissao1)));
		executionContext.getContextInstance().setVariable("localEmissao", localEmissao);
		
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