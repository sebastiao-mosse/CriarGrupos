package com.produsol.cadastro.grupo.action;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Download;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Node;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.services.DocumentService;


public class ManipularFicheirosActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	DocumentService docService = new DocumentService();
	Propriedades api = new  Propriedades();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	
		Input idBilhete   = (Input) executionContext.getContextInstance().getVariable("idBilhete");
		Input idNif   = (Input) executionContext.getContextInstance().getVariable("idNif");
		Input idFichaSingular   = (Input) executionContext.getContextInstance().getVariable("idFichaSingular");
		Input idFoto   = (Input) executionContext.getContextInstance().getVariable("idFoto");
		this.PrintDocuments(executionContext, idFoto, idFichaSingular, idBilhete, idNif);
		
	}
	
	public void PrintDocuments(ExecutionContext executionContext, Input idFoto, Input idFichaSingular,Input idBilhete,Input idNif)
	{
		Download baixarFoto = new Download();
		List<Node> nodeListFoto = new ArrayList<Node>();
		Node nodeFoto = new Node();
		nodeFoto.setUuid(idFoto.getValue().toString());
		nodeFoto.setLabel("Fotografia");
		nodeListFoto.add(nodeFoto);
		baixarFoto.setNodes(nodeListFoto);
		executionContext.getContextInstance().setVariable("baixarFoto", baixarFoto);
		
		Download baixarFichaSingular = new Download();
		List<Node> nodeListFichaSingular = new ArrayList<Node>();
		Node nodeFichaSingular = new Node();
		nodeFichaSingular.setUuid(idFichaSingular.getValue().toString());
		nodeFichaSingular.setLabel("Ficha de Inscrição");
		nodeListFichaSingular.add(nodeFichaSingular);
		baixarFichaSingular.setNodes(nodeListFichaSingular);
		executionContext.getContextInstance().setVariable("baixarFichaSingular", baixarFichaSingular);	
		
		
		Download baixarBilhete = new Download();
		List<Node> nodeListBilhete = new ArrayList<Node>();
		Node nodeBilhete = new Node();
		nodeBilhete.setUuid(idBilhete.getValue().toString());
		nodeBilhete.setLabel("Bilhete de Identidade");
		nodeListBilhete.add(nodeBilhete);
		baixarBilhete.setNodes(nodeListBilhete);
		executionContext.getContextInstance().setVariable("baixarBilhete", baixarBilhete);
		
		Download baixarCartaoContribuinte = new Download();
		List<Node> nodeListNif = new ArrayList<Node>();
		Node nodeNif = new Node();
		nodeNif.setUuid(idNif.getValue().toString());
		nodeNif.setLabel("Cartao de Contribuinte");
		nodeListNif.add(nodeNif);
		baixarCartaoContribuinte.setNodes(nodeListNif);
		executionContext.getContextInstance().setVariable("baixarCartaoContribuinte", baixarCartaoContribuinte);
	}
}