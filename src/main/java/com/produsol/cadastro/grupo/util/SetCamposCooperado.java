package com.produsol.cadastro.grupo.util;

import org.jbpm.graph.exe.ExecutionContext;

import com.openkm.bean.form.Input;
import com.openkm.bean.form.Upload;
import com.produsol.cadastro.grupo.services.ConversorDataHora;

public class SetCamposCooperado 
{

	public void setFichaInscricaoName(ExecutionContext executionContext,String path)
	{
		ConversorDataHora dataHora = new ConversorDataHora();
		Upload fichaInscricaoSingular = new Upload();
		fichaInscricaoSingular.setFolderPath(path);
		fichaInscricaoSingular.setDocumentName("fichaInscricaoSingular_" + dataHora.getDateTime());
		executionContext.setVariable("fichaInscricaoSingular", fichaInscricaoSingular);
	}
	public void setCaminhoPadrao(ExecutionContext executionContext,String path)
	{
		Input caminho = new Input();
		caminho.setName("caminho");
		caminho.setLabel("caminho");
		caminho.setValue(String.valueOf(path));
		executionContext.getContextInstance().setVariable("caminho", caminho);
	}
	
	public void setDataFormatada(ExecutionContext executionContext, String dataNascimento)
	{
		Input dataFormatada = new Input();
		dataFormatada.setName("dataFormatada");
		dataFormatada.setLabel("dataFormatada");
		dataFormatada.setValue(String.valueOf(dataNascimento));
		executionContext.getContextInstance().setVariable("dataFormatada", dataFormatada);
	}
	
	public void setClientId(ExecutionContext executionContext,String resultado)
	{
		Input modified = new Input();
		modified.setName("modified");
		modified.setLabel("modified");
		modified.setValue(String.valueOf(resultado));
		executionContext.getContextInstance().setVariable("modified", modified);
	}
	public void setNomeCompleto(ExecutionContext executionContext, String firstName,String lastName)
	{
		Input nomeCompleto = new Input();
		String fullName = firstName + " " + lastName;
		nomeCompleto.setName("nomeCompleto");
		nomeCompleto.setLabel("nomeCompleto");
		nomeCompleto.setValue(String.valueOf(fullName));
		executionContext.getContextInstance().setVariable("nomeCompleto", nomeCompleto);
	}
	
}
