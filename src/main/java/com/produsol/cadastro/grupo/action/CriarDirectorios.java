package com.produsol.cadastro.grupo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import com.openkm.bean.form.Download;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Node;
import com.openkm.bean.form.Select;
import com.openkm.bean.form.Upload;
import com.produsol.cadastro.grupo.services.FolderService;

public class CriarDirectorios implements ActionHandler {

	private static final long serialVersionUID = 1L;

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

		Upload bilhete = new Upload();  

		Input firstName = (Input) context.getContextInstance().getVariable("firstName");

		Input lastName = (Input) context.getContextInstance().getVariable("lastName");

		Input numNif    = (Input) context.getContextInstance().getVariable("nif");
		
		Input type    = (Input) context.getContextInstance().getVariable("tipoCooperado");
		
		if (firstName != null && lastName != null && numNif!=null)
		{
			String padrao = firstName.getValue().replaceAll("\\s", "") + "_" + lastName.getValue().replaceAll("\\s", "")+"_" + numNif.getValue();

			FolderService servico = new FolderService();

			String path = servico.PostFolder(padrao, type.getValue().toString());
			Random rand = new Random();
			
			bilhete.setFolderPath(path);bilhete.setDocumentName("Bilhete_identidade_"+rand.nextInt(100));context.setVariable("bilheteData", bilhete);	
			
			Input caminho = new Input();
			caminho.setName("caminho");
			caminho.setLabel("caminho");
			caminho.setValue(String.valueOf(path));
			context.getContextInstance().setVariable("caminho", caminho);
			
		}
		
		
	}

}
