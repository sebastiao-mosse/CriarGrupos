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

public class ManipularFicheirosEmpresaActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	DocumentService docService = new DocumentService();
	Propriedades api = new  Propriedades();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception {
		Input idAlvara = (Input) executionContext.getContextInstance().getVariable("idAlvara");
		Input idDiario = (Input) executionContext.getContextInstance().getVariable("idDiario");
		Input idNif = (Input) executionContext.getContextInstance().getVariable("idNif");
		Input idRegComercial = (Input) executionContext.getContextInstance().getVariable("idRegComercial");
		Input idDar = (Input) executionContext.getContextInstance().getVariable("idDar");
		Input idFichaEmpresa = (Input) executionContext.getContextInstance().getVariable("idFichaEmpresa");
		Input idRegEstatistico = (Input) executionContext.getContextInstance().getVariable("idRegEstatistico");

		if (idAlvara != null && idDiario != null && idNif != null && idRegComercial != null
				&& idDar != null) {
			
			/*
			docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idAlvara.getValue().toString()),
					clientId.getValue().toString(), "2");
			docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idDiario.getValue().toString()),
					clientId.getValue().toString(), "3");
			docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idNif.getValue().toString()),
					clientId.getValue().toString(), "5");
			docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idCertidao.getValue().toString()),
					clientId.getValue().toString(), "9");
			docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idRegComercial.getValue().toString()),
					clientId.getValue().toString(), "7");
			docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idDar.getValue().toString()),
					clientId.getValue().toString(), "8");
			docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idFichaEmpresa.getValue().toString()),
					clientId.getValue().toString(), "10");
			docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idRegEstatistico.getValue().toString()),
					clientId.getValue().toString(), "9");
			
			*/
			Download baixarRegEstatistico = new Download();
			List<Node> nodeListRegestatistico = new ArrayList<Node>();
			Node nodeRegEstatistico = new Node();
			nodeRegEstatistico.setUuid(idRegEstatistico.getValue().toString());
			nodeRegEstatistico.setLabel("Registo Estatístico");
			nodeListRegestatistico.add(nodeRegEstatistico);
			baixarRegEstatistico.setNodes(nodeListRegestatistico);
			executionContext.getContextInstance().setVariable("baixarRegEstatistico", baixarRegEstatistico);
			
			}
			
			Download baixarFichaEmpresa = new Download();
			List<Node> nodeListFichaEmpresa = new ArrayList<Node>();
			Node nodeFichaEmpresa = new Node();
			nodeFichaEmpresa.setUuid(idFichaEmpresa.getValue().toString());
			nodeFichaEmpresa.setLabel("Ficha de Inscrição");
			nodeListFichaEmpresa.add(nodeFichaEmpresa);
			baixarFichaEmpresa.setNodes(nodeListFichaEmpresa);
			executionContext.getContextInstance().setVariable("baixarFichaEmpresa", baixarFichaEmpresa);

			Download baixarAlvara = new Download();
			List<Node> nodeListAlvara = new ArrayList<Node>();
			Node nodeAlvara = new Node();
			nodeAlvara.setUuid(idAlvara.getValue().toString());
			nodeAlvara.setLabel("Alvará Comercial");
			nodeListAlvara.add(nodeAlvara);
			baixarAlvara.setNodes(nodeListAlvara);
			executionContext.getContextInstance().setVariable("baixarAlvara", baixarAlvara);

			Download baixarDiario = new Download();
			List<Node> nodeListDiario = new ArrayList<Node>();
			Node nodeDiario = new Node();
			nodeDiario.setUuid(idDiario.getValue().toString());
			nodeDiario.setLabel("Diário da República");
			nodeListDiario.add(nodeDiario);
			baixarDiario.setNodes(nodeListDiario);
			executionContext.getContextInstance().setVariable("baixarDiario", baixarDiario);

			Download baixarNif = new Download();
			List<Node> nodeListNif = new ArrayList<Node>();
			Node nodeNif = new Node();
			nodeNif.setUuid(idNif.getValue().toString());
			nodeNif.setLabel("Cartao de Contribuinte");
			nodeListNif.add(nodeNif);
			baixarNif.setNodes(nodeListNif);
			executionContext.getContextInstance().setVariable("baixarNif", baixarNif);

		
			Download baixarRegComercial = new Download();
			List<Node> nodeListRegComercial = new ArrayList<Node>();
			Node nodeRegComercial = new Node();
			nodeRegComercial.setUuid(idRegComercial.getValue().toString());
			nodeRegComercial.setLabel("Registo Comercial");
			nodeListRegComercial.add(nodeRegComercial);
			baixarRegComercial.setNodes(nodeListRegComercial);
			executionContext.getContextInstance().setVariable("baixarRegComercial", baixarRegComercial);

			Download baixarDar = new Download();
			List<Node> nodeListDar = new ArrayList<Node>();
			Node nodeDar = new Node();
			nodeDar.setUuid(idDar.getValue().toString());
			nodeDar.setLabel("Documento de Arrecadação(D.A.R)");
			nodeListDar.add(nodeDar);
			baixarDar.setNodes(nodeListDar);
			executionContext.getContextInstance().setVariable("baixarDar", baixarDar);

		}
}