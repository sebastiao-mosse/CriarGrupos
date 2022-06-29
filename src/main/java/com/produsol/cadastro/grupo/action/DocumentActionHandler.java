package com.produsol.cadastro.grupo.action;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.services.DocumentService;


public class DocumentActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	
	Propriedades api = new  Propriedades();
	DocumentService documento = new DocumentService();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	
		Input caminho = (Input)executionContext.getContextInstance().getVariable("caminho");
		
		System.out.println("Caminho: "+ caminho.getValue());
		documento.deleteDocumentByUUID(caminho.getValue().toString(), api.GetEnviromentVariables().get(9).replace("\"", ""));
	}
}