package com.produsol.cadastro.grupo.action;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import com.openkm.bean.form.Download;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Node;
import com.openkm.bean.form.Upload;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.services.DocumentService;

public class DownloadCoopSingDocument implements ActionHandler {

	private static final long serialVersionUID = 1L;	
	Propriedades api = new Propriedades();
	
	public void execute(ExecutionContext executionContext) throws Exception
	{
		DocumentService documento = new DocumentService();
		List<String> lista = new ArrayList<>();
		
		Input caminho = (Input) executionContext.getContextInstance().getVariable("caminho");
		String fileInfo = documento.deleteDocumentByUUID(caminho.getValue().toString(), api.GetEnviromentVariables().get(9).replace("\"", ""));
		
		String[] strSplit = fileInfo.split(",");
		
		for(int i=0;i<strSplit.length;i++)
		{
			lista.add(strSplit[i].replaceAll("[^a-zA-Z0-9]+", ""));
		}	
	
		Upload bilheteData    = (Upload) executionContext.getContextInstance().getVariable("bilheteData");
		bilheteData.setDocumentUuid(lista.get(1).toString());
		String uuid= bilheteData.getDocumentUuid();
		Download dynamic = new Download();
		List<Node> nodeList = new ArrayList<Node>();
		Node node = new Node();
		node.setPath(caminho.getValue().toString());
		node.setUuid(uuid);
		System.out.println("path: "+node.getPath()+" UUID: "+node.getUuid());
		node.setLabel("Download Bilhete");
		nodeList.add(node);
		dynamic.setNodes(nodeList);
		executionContext.getContextInstance().setVariable("dynamic", dynamic);	

}

}