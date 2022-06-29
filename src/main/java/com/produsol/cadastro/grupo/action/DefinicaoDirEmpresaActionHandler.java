package com.produsol.cadastro.grupo.action;

import java.text.Normalizer;
import java.util.Locale;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Upload;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.services.FolderService;
import com.produsol.cadastro.grupo.util.SetCamposCooperado;

public class DefinicaoDirEmpresaActionHandler implements ActionHandler {
	private static final long serialVersionUID = 1L;

	ConversorDataHora dataHora = new ConversorDataHora();
	SetCamposCooperado setCampos = new SetCamposCooperado();

	public void execute(ExecutionContext context) throws Exception {

		Input denominacaoSocial = (Input) context.getContextInstance().getVariable("denominacaoSocial");
		Input nif = (Input) context.getContextInstance().getVariable("nif");
		Input type = (Input) context.getContextInstance().getVariable("tipoCooperado");

		String padrao = Normalizer.normalize(denominacaoSocial.getValue().toString(), Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "") + "_"
				+ Normalizer.normalize(nif.getValue().toString(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

		FolderService servico = new FolderService();
		String path = servico.PostFolder(padrao.replaceAll("\\s", "").toUpperCase(Locale.getDefault()),type.getValue().toString());
		this.setCampos.setCaminhoPadrao(context, path);
		this.setData(context, padrao, path);
		this.setDocumentName(context, padrao);
	}

	public void setData(ExecutionContext context, String padrao, String path) {
		Upload registoComercial = new Upload();
		registoComercial.setFolderPath(path);
		registoComercial.setDocumentName(padrao.replaceAll("\\s", "") + "_" + "RegistoComercial" + dataHora.getDateTime());
		context.setVariable("registoComercial", registoComercial);

		Upload registoEstatistico = new Upload();
		registoEstatistico.setFolderPath(path);
		registoEstatistico.setDocumentName(padrao.replaceAll("\\s", "") + "_" + "RegistoEstatistico" + dataHora.getDateTime());
		context.setVariable("registoEstatistico", registoEstatistico);

		Upload diarioRepublica = new Upload();
		diarioRepublica.setFolderPath(path);
		diarioRepublica.setDocumentName(padrao.replaceAll("\\s", "") + "_" + "DiarioRepublica" + dataHora.getDateTime());
		context.setVariable("diarioRepublica", diarioRepublica);

		Upload dar = new Upload();
		dar.setFolderPath(path);
		dar.setDocumentName("Dar" + dataHora.getDateTime());
		context.setVariable("dar", dar);

		Upload upd = new Upload();
		upd.setFolderPath(path);
		upd.setDocumentName(padrao.replaceAll("\\s", "") + "_" + "AlvaraComercial" + dataHora.getDateTime());
		context.setVariable("alvaraData", upd);

		Upload fichaInscricaoEmpresa = new Upload();
		fichaInscricaoEmpresa.setFolderPath(path);
		fichaInscricaoEmpresa.setDocumentName(padrao.replaceAll("\\s", "") + "_" + "FichaInscricao" + dataHora.getDateTime());
		context.setVariable("fichaInscricaoEmpresa", fichaInscricaoEmpresa);

		Upload cartaoContribuinte = new Upload();
		cartaoContribuinte.setFolderPath(path);
		cartaoContribuinte.setDocumentName(padrao.replaceAll("\\s", "") + "_" + "CartaoContribuinte" + dataHora.getDateTime());
		context.setVariable("cartaoContribuinte", cartaoContribuinte);
	}
	
	
	public void setDocumentName(ExecutionContext context,String padrao)
	{
		Input RegistoComercialDescricao = new Input();
		RegistoComercialDescricao.setName("RegistoComercialDescricao");
		RegistoComercialDescricao.setLabel("RegistoComercialDescricao");
		RegistoComercialDescricao.setValue(String.valueOf(padrao.replaceAll("\\s", "") + "_" + "RegistoComercial" + dataHora.getDateTime()));
		context.getContextInstance().setVariable("RegistoComercialDescricao", RegistoComercialDescricao);
		
		Input RegistoEstatisticoDescricao = new Input();
		RegistoEstatisticoDescricao.setName("RegistoEstatisticoDescricao");
		RegistoEstatisticoDescricao.setLabel("RegistoEstatisticoDescricao");
		RegistoEstatisticoDescricao.setValue(String.valueOf(padrao.replaceAll("\\s", "") + "_" + "RegistoEstatistico" + dataHora.getDateTime()));
		context.getContextInstance().setVariable("RegistoEstatisticoDescricao", RegistoEstatisticoDescricao);
		
		Input DiarioRepublicaDescricao = new Input();
		DiarioRepublicaDescricao.setName("DiarioRepublicaDescricao");
		DiarioRepublicaDescricao.setLabel("DiarioRepublicaDescricao");
		DiarioRepublicaDescricao.setValue(String.valueOf(padrao.replaceAll("\\s", "") + "_" + "DiarioRepublica" + dataHora.getDateTime()));
		context.getContextInstance().setVariable("DiarioRepublicaDescricao", DiarioRepublicaDescricao);
		
		Input AlvaraComercialDescricao = new Input();
		AlvaraComercialDescricao.setName("AlvaraComercialDescricao");
		AlvaraComercialDescricao.setLabel("AlvaraComercialDescricao");
		AlvaraComercialDescricao.setValue(String.valueOf(padrao.replaceAll("\\s", "") + "_" + "AlvaraComercial" + dataHora.getDateTime()));
		context.getContextInstance().setVariable("AlvaraComercialDescricao", AlvaraComercialDescricao);
		
		Input FichaInscricaoDescricao = new Input();
		FichaInscricaoDescricao.setName("FichaInscricaoDescricao");
		FichaInscricaoDescricao.setLabel("FichaInscricaoDescricao");
		FichaInscricaoDescricao.setValue(String.valueOf(padrao.replaceAll("\\s", "") + "_" + "FichaInscricao" + dataHora.getDateTime()));
		context.getContextInstance().setVariable("FichaInscricaoDescricao", FichaInscricaoDescricao);
		
		Input CartaoContribuinteDescricao = new Input();
		CartaoContribuinteDescricao.setName("CartaoContribuinteDescricao");
		CartaoContribuinteDescricao.setLabel("CartaoContribuinteDescricao");
		CartaoContribuinteDescricao.setValue(String.valueOf(padrao.replaceAll("\\s", "") + "_" + "CartaoContribuinte" + dataHora.getDateTime()));
		context.getContextInstance().setVariable("CartaoContribuinteDescricao", CartaoContribuinteDescricao);
		
	}
	
	
}