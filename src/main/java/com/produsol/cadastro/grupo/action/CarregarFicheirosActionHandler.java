package com.produsol.cadastro.grupo.action;

import java.util.Random;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import com.openkm.bean.form.Input;
import com.openkm.bean.form.Upload;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.services.FolderService;

public class CarregarFicheirosActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	
	ConversorDataHora dataHora = new ConversorDataHora();

	/**
	 * The message member gets its value from the configuration in the 
	 * processdefinition. The value is injected directly by the engine. 
	 */
	String message;

	/**
	 * A message process variable is assigned the value of the message
	 * member. The process variable is created if it doesn't exist yet.
	 */
	public void execute(ExecutionContext context) throws Exception {

		Upload upd = new Upload(); 

		Input denominacaoSocial = (Input) context.getContextInstance().getVariable("denominacaoSocial");
		
		Input numNif    = (Input) context.getContextInstance().getVariable("nif");
		
		Input type    = (Input) context.getContextInstance().getVariable("tipoCooperado");
		
		if (denominacaoSocial != null && numNif!=null)
		{
			String padrao = denominacaoSocial.getValue().replaceAll("\\s", "")+"_"+numNif.getValue();

			FolderService servico = new FolderService();

			String path = servico.PostFolder(padrao, type.getValue().toString());
			
			upd.setFolderPath(path); upd.setDocumentName("AlvaraComercial_"+dataHora.getDateTime()); context.setVariable("alvaraData", upd);
			
		}

	}

}
