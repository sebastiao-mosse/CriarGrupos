package com.produsol.cadastro.grupo.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.repositories.CooperadoRepository;
import com.produsol.cadastro.grupo.repositories.DocumentoRepository;
import com.produsol.cadastro.grupo.services.DocumentService;
import com.produsol.cadastro.grupo.vo.Documento;


public class InserirDocumentosSingularActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	
	Propriedades api = new  Propriedades();
	DocumentoRepository documentRepository = new DocumentoRepository();
	DocumentService documentService = new DocumentService();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	
		this.inserirDocs(executionContext);	
	}
	
	
	public void inserirDocs(ExecutionContext executionContext) throws ClassNotFoundException, SQLException, IOException
	{
		Input fichaInscricaoDescricao   = (Input) executionContext.getContextInstance().getVariable("fichaInscricaoDescricao");
		Input bilheteDescricao   = (Input) executionContext.getContextInstance().getVariable("bilheteDescricao");
		Input cartaoContribuinteDescricao   = (Input) executionContext.getContextInstance().getVariable("cartaoContribuinteDescricao");
		Input fotografiaDescricao   = (Input) executionContext.getContextInstance().getVariable("fotografiaDescricao");	
		Input codigoCliente = (Input) executionContext.getContextInstance().getVariable("codigoCliente");
		
		List<String> ficha = this.SplitDocumentName(fichaInscricaoDescricao);
		List<String> bilhete = this.SplitDocumentName(bilheteDescricao);
		List<String> cartao = this.SplitDocumentName(cartaoContribuinteDescricao);
		List<String> foto = this.SplitDocumentName(fotografiaDescricao);
		
		List<Documento> entities = this.LoadData(executionContext, ficha, bilhete, cartao, foto, codigoCliente.getValue());
		
		CooperadoRepository cooperadoRepository = new CooperadoRepository();
		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		List<String> lista = cooperadoRepository.findByNif(nif.getValue());
		documentRepository.save(entities,lista.get(4), String.valueOf(executionContext.getProcessInstance().getId()));
	}
	
	public List<String> SplitDocumentName(Input padrao)
	{
		List<String> lista = new ArrayList<String>();
		String[] documento = padrao.getValue().toString().split("_");
		for(int i=0; i< documento.length; i++)
		{
			lista.add(documento[i]);
		}
		return lista;
	} 
	
	public List<Documento> LoadData(ExecutionContext executionContext,List<String> ficha,List<String> bilhete,List<String> cartao,
			List<String> foto,String idCooperado) throws IOException, ClassNotFoundException, SQLException
	{
		
		List<Documento> listaDocumento = new ArrayList<Documento>();
		Documento fichaInscricao = new Documento();
		
		fichaInscricao.setIdCooperado(idCooperado);
		fichaInscricao.setTipoDocumento(documentRepository.getDocumentTypeId("Ficha de Inscricao"));
		fichaInscricao.setNomeCooperado(ficha.get(0));
		fichaInscricao.setNif(ficha.get(1));
		fichaInscricao.setDescricao(ficha.get(2));
		fichaInscricao.setPath(documentService.getDocumentPath(ficha.get(2), api.GetEnviromentVariables().get(9).replace("\"", "")));
		
		Documento bilheteIdentidade = new Documento();
		bilheteIdentidade.setIdCooperado(idCooperado);
		bilheteIdentidade.setTipoDocumento(documentRepository.getDocumentTypeId("Bilhete de Identidade"));
		bilheteIdentidade.setNomeCooperado(bilhete.get(0));
		bilheteIdentidade.setNif(bilhete.get(1));
		bilheteIdentidade.setDescricao(bilhete.get(2));
		bilheteIdentidade.setPath(documentService.getDocumentPath(bilhete.get(2), api.GetEnviromentVariables().get(9).replace("\"", "")));
	
		Documento cartaoContribuinte = new Documento();
		cartaoContribuinte.setIdCooperado(idCooperado);
		cartaoContribuinte.setTipoDocumento(documentRepository.getDocumentTypeId("Cartao de Contribuinte"));
		cartaoContribuinte.setNomeCooperado(cartao.get(0));
		cartaoContribuinte.setNif(cartao.get(1));
		cartaoContribuinte.setDescricao(cartao.get(2));
		cartaoContribuinte.setPath(documentService.getDocumentPath(cartao.get(2), api.GetEnviromentVariables().get(9).replace("\"", "")));
		
		Documento fotografia = new Documento();
		fotografia.setIdCooperado(idCooperado);
		fotografia.setTipoDocumento(documentRepository.getDocumentTypeId("Fotografia"));
		fotografia.setNomeCooperado(foto.get(0));
		fotografia.setNif(foto.get(1));
		fotografia.setDescricao(foto.get(2));
		fotografia.setPath(documentService.getDocumentPath(foto.get(2), api.GetEnviromentVariables().get(9).replace("\"", "")));

		listaDocumento.add(fichaInscricao);
		listaDocumento.add(bilheteIdentidade);
		listaDocumento.add(cartaoContribuinte);
		listaDocumento.add(fotografia);
		return listaDocumento;
	}
	
	
}