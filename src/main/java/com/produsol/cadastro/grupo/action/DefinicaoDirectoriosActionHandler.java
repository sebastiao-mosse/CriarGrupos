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

public class DefinicaoDirectoriosActionHandler implements ActionHandler {
	private static final long serialVersionUID = 1L;
	
	ConversorDataHora dataHora = new ConversorDataHora();
	SetCamposCooperado setCampos = new SetCamposCooperado();

	public void execute(ExecutionContext context) throws Exception 
	{

		Input type = (Input) context.getContextInstance().getVariable("tipoCooperado");
		Input firstName = (Input) context.getContextInstance().getVariable("firstName");
		Input secondName = (Input) context.getContextInstance().getVariable("secondName");
		Input lastName = (Input) context.getContextInstance().getVariable("lastName");
		Input nif = (Input) context.getContextInstance().getVariable("nif");
		String secondNameToNormalize = secondName.getValue().toString().equals("") ? "" : secondName.getValue().toString();

		String padrao = Normalizer.normalize(firstName.getValue().toString(), Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "")
				+ Normalizer.normalize(secondNameToNormalize, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
				+ Normalizer.normalize(lastName.getValue().toString(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]",
						"")
				+ "_"
				+ Normalizer.normalize(nif.getValue().toString(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

		FolderService servico = new FolderService();
		String path = servico.PostFolder((padrao.replaceAll("\\s", "")).toUpperCase(Locale.getDefault()),type.getValue().toString());
		
		this.setCampos.setFichaInscricaoName(context, path);
		this.setCampos.setCaminhoPadrao(context, path);
		this.setCampos.setNomeCompleto(context, firstName.getValue(), lastName.getValue());
		this.setData(context, padrao, path);
		this.setDocumentName(context, padrao);

	}

	public void setData(ExecutionContext context, String padrao, String path) {
		Upload fichaInscricaoSingular = new Upload();
		fichaInscricaoSingular.setFolderPath(path);
		fichaInscricaoSingular.setDocumentName(padrao.replaceAll("\\s", "") + "_" + "FichaInscricao" + dataHora.getDateTime());
		context.setVariable("fichaInscricaoSingular", fichaInscricaoSingular);

		Upload bilheteData = new Upload();
		bilheteData.setFolderPath(path);
		bilheteData.setDocumentName(padrao.replaceAll("\\s", "") + "_" + "BilheteIdentidade" + dataHora.getDateTime());
		context.setVariable("bilheteData", bilheteData);

		Upload nifData = new Upload();
		nifData.setFolderPath(path);
		nifData.setDocumentName(padrao.replaceAll("\\s", "") + "_" + "CartaoContribuinte" + dataHora.getDateTime());
		context.setVariable("nifData", nifData);

		Upload fotoData = new Upload();
		fotoData.setFolderPath(path);
		fotoData.setDocumentName(padrao.replaceAll("\\s", "") + "_" + "Fotografia" + dataHora.getDateTime());
		context.setVariable("fotoData", fotoData);
	}
	
	public void setDocumentName(ExecutionContext context,String padrao)
	{
		Input fichaInscricaoDescricao = new Input();
		fichaInscricaoDescricao.setName("fichaInscricaoDescricao");
		fichaInscricaoDescricao.setLabel("fichaInscricaoDescricao");
		fichaInscricaoDescricao.setValue(String.valueOf(padrao.replaceAll("\\s", "") + "_" + "FichaInscricao" + dataHora.getDateTime()));
		context.getContextInstance().setVariable("fichaInscricaoDescricao", fichaInscricaoDescricao);
		
		Input bilheteDescricao = new Input();
		bilheteDescricao.setName("bilheteDescricao");
		bilheteDescricao.setLabel("bilheteDescricao");
		bilheteDescricao.setValue(String.valueOf(padrao.replaceAll("\\s", "") + "_" + "BilheteIdentidade" + dataHora.getDateTime()));
		context.getContextInstance().setVariable("bilheteDescricao", bilheteDescricao);
		
		Input cartaoContribuinteDescricao = new Input();
		cartaoContribuinteDescricao.setName("cartaoContribuinteDescricao");
		cartaoContribuinteDescricao.setLabel("cartaoContribuinteDescricao");
		cartaoContribuinteDescricao.setValue(String.valueOf(padrao.replaceAll("\\s", "") + "_" + "CartaoContribuinte" + dataHora.getDateTime()));
		context.getContextInstance().setVariable("cartaoContribuinteDescricao", cartaoContribuinteDescricao);
		
		Input fotografiaDescricao = new Input();
		fotografiaDescricao.setName("fotografiaDescricao");
		fotografiaDescricao.setLabel("fotografiaDescricao");
		fotografiaDescricao.setValue(String.valueOf(padrao.replaceAll("\\s", "") + "_" + "Fotografia" + dataHora.getDateTime()));
		context.getContextInstance().setVariable("fotografiaDescricao", fotografiaDescricao);
	}
}