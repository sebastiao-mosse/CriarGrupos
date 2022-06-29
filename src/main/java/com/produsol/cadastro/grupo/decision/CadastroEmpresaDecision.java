package com.produsol.cadastro.grupo.decision;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.google.gson.JsonParser;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.repositories.CooperadoRepository;
import com.produsol.cadastro.grupo.repositories.ProcessoRepository;
import com.produsol.cadastro.grupo.services.CompanyService;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.services.CooperadoService;
import com.produsol.cadastro.grupo.services.CreateAccountService;
import com.produsol.cadastro.grupo.services.DocumentService;

public class CadastroEmpresaDecision implements DecisionHandler {

	private static final long serialVersionUID = 1L;
	private CompanyService companyService = new CompanyService();
	public CooperadoService clienteService = new CooperadoService();
	public CreateAccountService createAccount = new CreateAccountService();
	CooperadoRepository cooperadoRepository = new CooperadoRepository();
	ProcessoRepository processRepository = new ProcessoRepository();
	ConversorDataHora dataHora = new ConversorDataHora();
	DocumentService docService = new DocumentService();
	Propriedades api = new  Propriedades();

	@Override
	public String decide(ExecutionContext executionContext) throws Exception {

		String tipoCooperado = "";
		String retorno = "";
		Input type = (Input) executionContext.getContextInstance().getVariable("tipoCooperado");
		Input denominacaoSocial = (Input) executionContext.getContextInstance().getVariable("denominacaoSocial");
		Input denominacaoAbreviada = (Input) executionContext.getContextInstance().getVariable("denominacaoAbreviada");
		Input naturezaJuridica = (Input) executionContext.getContextInstance().getVariable("naturezaJuridica");
		Input sedePrincipal = (Input) executionContext.getContextInstance().getVariable("sedePrincipal");
		Input provincia = (Input) executionContext.getContextInstance().getVariable("provincia");
		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		Input matriculaRegComercial = (Input) executionContext.getContextInstance().getVariable("matriculaRegComercial");
		Input certidao = (Input) executionContext.getContextInstance().getVariable("certidao");
		Input dataRegistro = (Input) executionContext.getContextInstance().getVariable("dataRegistro");
		Input provinciaRegistro = (Input) executionContext.getContextInstance().getVariable("provinciaRegistro");
		Input email = (Input) executionContext.getContextInstance().getVariable("email");
		Input telefone = (Input) executionContext.getContextInstance().getVariable("telefone");
		Input objectoSocial = (Input) executionContext.getContextInstance().getVariable("objectoSocial");
		Input finalidadeNegocio = (Input) executionContext.getContextInstance().getVariable("finalidadeNegocio");
		Input pep = (Input) executionContext.getContextInstance().getVariable("pep");
		Input bancoDomiciliacao = (Input) executionContext.getContextInstance().getVariable("bancoDomiciliacao");

		String naturalDe = "";
		String nomePai = "";
		String nomeMae = "";
		String numBilhete = "";
		String estadoCivil = "";
		String profissao = "";
		String funcao = "";
		String empresa_onde_trabalha = "";
		String habilitacoes_literarias = "";
		String dataEmissao = "";
		String dataExpiracao = "";
		String localEmissao = "";
		String balcaoDomiciliacao = "";
		String rendimento = "";
		String nacionalidade = "";
		String numConta = "";
		String middleName = "";
		String lastName = "";
		tipoCooperado = type.getValue().equals("SINGULAR") ? "1" : "2";
		String numeroCertidao = certidao.getValue().equals("") ? "" : certidao.getValue();

		Input municipioMorada = (Input) executionContext.getContextInstance().getVariable("municipioMorada");
		Input iban = (Input) executionContext.getContextInstance().getVariable("iban");
		Input officeId = (Input) executionContext.getContextInstance().getVariable("officeId");
		String resultado = this.companyService.SaveCooperado(tipoCooperado,
				denominacaoSocial.getValue().toUpperCase(Locale.getDefault()), nif.getValue(),
				Integer.parseInt(officeId.getValue().toString().replace("\"", "")), telefone.getValue(),
				email.getValue(), naturalDe.toUpperCase(Locale.getDefault()), nomePai, nomeMae, numBilhete, estadoCivil,
				ConverterData(dataRegistro.getValue().toString()), profissao, funcao, empresa_onde_trabalha,
				habilitacoes_literarias, denominacaoAbreviada.getValue().toUpperCase(Locale.getDefault()),
				naturezaJuridica.getValue().toUpperCase(Locale.getDefault()), sedePrincipal.getValue().toUpperCase(Locale.getDefault()),
				provincia.getValue().toUpperCase(Locale.getDefault()),
				matriculaRegComercial.getValue().toUpperCase(Locale.getDefault()), dataRegistro.getValue(), provinciaRegistro.getValue().toUpperCase(Locale.getDefault()),
				objectoSocial.getValue().toUpperCase(Locale.getDefault()), finalidadeNegocio.getValue().toUpperCase(Locale.getDefault()),
				pep.getValue().toString(), localEmissao.toUpperCase(Locale.getDefault()),
				dataEmissao, dataExpiracao, nacionalidade.toUpperCase(Locale.getDefault()), numConta, balcaoDomiciliacao.toUpperCase(Locale.getDefault()), 
				rendimento, middleName,
				lastName, bancoDomiciliacao.getValue().toString().toUpperCase(Locale.getDefault()), municipioMorada.getValue().toUpperCase(Locale.getDefault()), iban.getValue(),
				numeroCertidao);

		if (resultado.equals("400")) {
			retorno = "erroEmpresa";
		} else {

	
			this.updateProcesso(executionContext);
			this.InserirDocEmpresasGingaSoft(executionContext, resultado);
			Input idCooperado = new Input();
			idCooperado.setName("idCooperado");
			idCooperado.setLabel("idCooperado");
			idCooperado.setValue(String.valueOf(resultado));
			executionContext.getContextInstance().setVariable("idCooperado", idCooperado);

			
			List<String> lista = cooperadoRepository.findEmpresaByNif(nif.getValue());
			if (!lista.isEmpty()) {
				Input modified = new Input();
				modified.setName("modified");
				modified.setLabel("modified");
				modified.setValue(String.valueOf(resultado));
				executionContext.getContextInstance().setVariable("modified", modified);

				Input dataRegFormatada = new Input();
				dataRegFormatada.setName("dataRegFormatada");
				dataRegFormatada.setLabel("dataRegFormatada");
				dataRegFormatada.setValue(String.valueOf(dataRegistro.getValue().toString()));
				executionContext.getContextInstance().setVariable("dataRegFormatada", dataRegFormatada);

				Input nomeCompleto = new Input();
				String fullName = denominacaoSocial.getValue();
				nomeCompleto.setName("nomeCompleto");
				nomeCompleto.setLabel("nomeCompleto");
				nomeCompleto.setValue(String.valueOf(fullName));
				executionContext.getContextInstance().setVariable("nomeCompleto", nomeCompleto);
				retorno = "Aprovado(a)";
			}
		}
		return retorno;
	}

	
	public String armazenarValor(String jsonString) {
		com.google.gson.JsonObject data = new JsonParser().parse(jsonString).getAsJsonObject();
		return data.get("clientId").toString();
	}

	public String ConverterData(String dataEntrada) throws ParseException {
		SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		SimpleDateFormat formatoSaida = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		Date date = formatoEntrada.parse(dataEntrada);
		String dataNasc = formatoSaida.format(date);
		return dataNasc;
	}

	public String getSelectedValue(Select selectedValue) {
		String retorno = "";
		for (int i = 0; i < selectedValue.getOptions().size(); i++) {
			if (selectedValue.getOptions().get(i).isSelected()) {
				retorno = selectedValue.getOptions().get(i).getLabel();
			}
		}
		return retorno;
	}

	public static Authentication getAuthentication() {
		if (SecurityHolder.get() != null) {
			return SecurityHolder.get();
		} else {
			return SecurityContextHolder.getContext().getAuthentication();
		}
	}
	
	public void updateProcesso(ExecutionContext executionContext) throws ClassNotFoundException, IOException, SQLException
	{
		Authentication auth = getAuthentication();
		long taskManagementInstance = executionContext.getTaskMgmtInstance().getId();
		long swimlaneInstance       = executionContext.getTaskMgmtInstance().getSwimlaneInstance("initiator").getId();	
		String actorId              = auth.getName();
		
		processRepository.updateProcesso(executionContext.getProcessInstance().getId(), this.dataHora.getDataActual(), taskManagementInstance,
				swimlaneInstance, actorId, Propriedades.getProp().getString("produto.status.concluido"),
				Propriedades.getProp().getString("process.area.compliance"));
	}

	public void InserirDocEmpresasGingaSoft(ExecutionContext executionContext, String clientId ) throws IOException
	{
		Input idAlvara = (Input) executionContext.getContextInstance().getVariable("idAlvara");
		Input idDiario = (Input) executionContext.getContextInstance().getVariable("idDiario");
		Input idNif = (Input) executionContext.getContextInstance().getVariable("idNif");
		Input idRegComercial = (Input) executionContext.getContextInstance().getVariable("idRegComercial");
		Input idDar = (Input) executionContext.getContextInstance().getVariable("idDar");
		Input idFichaEmpresa = (Input) executionContext.getContextInstance().getVariable("idFichaEmpresa");
		Input idRegEstatistico = (Input) executionContext.getContextInstance().getVariable("idRegEstatistico");
		
		
		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idAlvara.getValue().toString()),
				clientId, "2");
		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idDiario.getValue().toString()),
				clientId, "3");
		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idNif.getValue().toString()),
				clientId, "5");
		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idRegComercial.getValue().toString()),
				clientId, "7");
		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idDar.getValue().toString()),
				clientId, "8");
		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idFichaEmpresa.getValue().toString()),
				clientId, "10");
		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "") +docService.GetDocument(idRegEstatistico.getValue().toString()),
				clientId, "9");
	}

}