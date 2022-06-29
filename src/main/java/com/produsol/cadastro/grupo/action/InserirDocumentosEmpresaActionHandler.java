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


public class InserirDocumentosEmpresaActionHandler implements ActionHandler {

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
		Input RegistoComercialDescricao   = (Input) executionContext.getContextInstance().getVariable("RegistoComercialDescricao");
		Input RegistoEstatisticoDescricao   = (Input) executionContext.getContextInstance().getVariable("RegistoEstatisticoDescricao");
		Input DiarioRepublicaDescricao   = (Input) executionContext.getContextInstance().getVariable("DiarioRepublicaDescricao");
		Input AlvaraComercialDescricao   = (Input) executionContext.getContextInstance().getVariable("AlvaraComercialDescricao");
		Input FichaInscricaoDescricao   = (Input) executionContext.getContextInstance().getVariable("FichaInscricaoDescricao");
		Input CartaoContribuinteDescricao   = (Input) executionContext.getContextInstance().getVariable("CartaoContribuinteDescricao");
		Input codigoCliente = (Input) executionContext.getContextInstance().getVariable("codigoCliente");
	
		List<String> registoComercial = this.SplitDocumentName(RegistoComercialDescricao);
		List<String> registoEstatistico = this.SplitDocumentName(RegistoEstatisticoDescricao);
		List<String> diario = this.SplitDocumentName(DiarioRepublicaDescricao);
		List<String> alvara = this.SplitDocumentName(AlvaraComercialDescricao);
		List<String> ficha = this.SplitDocumentName(FichaInscricaoDescricao);
		List<String> cartao = this.SplitDocumentName(CartaoContribuinteDescricao);
		
		List<Documento> entities = this.LoadData(executionContext, registoComercial, registoEstatistico, diario,
				alvara,ficha,cartao, codigoCliente.getValue());
		
		CooperadoRepository cooperadoRepository = new CooperadoRepository();
		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		List<String> lista = cooperadoRepository.findEmpresaByNif(nif.getValue());
		documentRepository.save(entities,lista.get(2),String.valueOf(executionContext.getProcessInstance().getId()));
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
	
	
	
	public List<Documento> LoadData(ExecutionContext executionContext,List<String> registoComercial,List<String> registoEstatistico,
			List<String> diario,List<String> alvara,List<String> ficha,List<String> cartao,
			String idCooperado) throws IOException, ClassNotFoundException, SQLException
	{
		
		List<Documento> listaDocumento = new ArrayList<Documento>();
		
		Documento listaRegistoComercial = new Documento();
		listaRegistoComercial.setIdCooperado(idCooperado);
		listaRegistoComercial.setTipoDocumento( documentRepository.getDocumentTypeId("Registo Comercial"));
		listaRegistoComercial.setNomeCooperado(registoComercial.get(0));
		listaRegistoComercial.setNif(registoComercial.get(1));
		listaRegistoComercial.setDescricao(registoComercial.get(2));
		listaRegistoComercial.setPath(documentService.getDocumentPath(registoComercial.get(2), api.GetEnviromentVariables().get(9).replace("\"", "")));
		
		Documento ListaRegistoEstatistico = new Documento();
		ListaRegistoEstatistico.setIdCooperado(idCooperado);
		ListaRegistoEstatistico.setTipoDocumento( documentRepository.getDocumentTypeId("Registo Estatistico"));
		ListaRegistoEstatistico.setNomeCooperado(registoEstatistico.get(0));
		ListaRegistoEstatistico.setNif(registoEstatistico.get(1));
		ListaRegistoEstatistico.setDescricao(registoEstatistico.get(2));
		ListaRegistoEstatistico.setPath(documentService.getDocumentPath(registoEstatistico.get(2), api.GetEnviromentVariables().get(9).replace("\"", "")));
	
		Documento ListaDiario = new Documento();
		ListaDiario.setIdCooperado(idCooperado);
		ListaDiario.setTipoDocumento( documentRepository.getDocumentTypeId("Diario da Republica"));
		ListaDiario.setNomeCooperado(diario.get(0));
		ListaDiario.setNif(diario.get(1));
		ListaDiario.setDescricao(diario.get(2));
		ListaDiario.setPath(documentService.getDocumentPath(diario.get(2), api.GetEnviromentVariables().get(9).replace("\"", "")));
		
		Documento ListaAlvara = new Documento();
		ListaAlvara.setIdCooperado(idCooperado);
		ListaAlvara.setTipoDocumento( documentRepository.getDocumentTypeId("Alvara Comercial"));
		ListaAlvara.setNomeCooperado(alvara.get(0));
		ListaAlvara.setNif(alvara.get(1));
		ListaAlvara.setDescricao(alvara.get(2));
		ListaAlvara.setPath(documentService.getDocumentPath(alvara.get(2), api.GetEnviromentVariables().get(9).replace("\"", "")));
		
		Documento ListaFicha = new Documento();
		ListaFicha.setIdCooperado(idCooperado);
		ListaFicha.setTipoDocumento( documentRepository.getDocumentTypeId("Ficha de Inscricao"));
		ListaFicha.setNomeCooperado(ficha.get(0));
		ListaFicha.setNif(ficha.get(1));
		ListaFicha.setDescricao(ficha.get(2));
		ListaFicha.setPath(documentService.getDocumentPath(ficha.get(2), api.GetEnviromentVariables().get(9).replace("\"", "")));
		
		Documento ListaCartao = new Documento();
		ListaCartao.setIdCooperado(idCooperado);
		ListaCartao.setTipoDocumento( documentRepository.getDocumentTypeId("Cartao de Contribuinte"));
		ListaCartao.setNomeCooperado(cartao.get(0));
		ListaCartao.setNif(cartao.get(1));
		ListaCartao.setDescricao(cartao.get(2));
		ListaCartao.setPath(documentService.getDocumentPath(cartao.get(2), api.GetEnviromentVariables().get(9).replace("\"", "")));

		listaDocumento.add(listaRegistoComercial);
		listaDocumento.add(ListaRegistoEstatistico);
		listaDocumento.add(ListaDiario);
		listaDocumento.add(ListaAlvara);
		listaDocumento.add(ListaFicha);
		listaDocumento.add(ListaCartao);
		return listaDocumento;
	}
}