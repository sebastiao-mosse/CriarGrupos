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
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.services.CooperadoService;
import com.produsol.cadastro.grupo.services.CreateAccountService;
import com.produsol.cadastro.grupo.services.DocumentService;
import com.produsol.cadastro.grupo.util.SetCamposCooperado;
import com.produsol.cadastro.grupo.vo.Cooperado;

public class CadastroSingularDecision implements DecisionHandler{

	private static final long serialVersionUID = 1L;
	public CooperadoService clienteService = new CooperadoService();
	public CreateAccountService createAccount = new CreateAccountService();

	CooperadoRepository cooperadoRepository = new CooperadoRepository();
	ProcessoRepository processRepository = new ProcessoRepository();
	ConversorDataHora dataHora = new ConversorDataHora();
	DocumentService docService = new DocumentService();
	Propriedades api = new  Propriedades();

	@Override
	public String decide(ExecutionContext executionContext) throws Exception 
	{
		String tipoCooperado = "";
		String retorno ="";

		Input provinciaMorada = (Input) executionContext.getContextInstance().getVariable("provinciaMorada");
		Input municipioMorada = (Input) executionContext.getContextInstance().getVariable("municipioMorada");
		Input iban = (Input) executionContext.getContextInstance().getVariable("iban");

		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");Input address = (Input) executionContext.getContextInstance().getVariable("moradaActual");
		Input firstName = (Input) executionContext.getContextInstance().getVariable("firstName");Input secondName = (Input) executionContext.getContextInstance().getVariable("secondName");
		Input lastName = (Input) executionContext.getContextInstance().getVariable("lastName");Input telefone = (Input) executionContext.getContextInstance().getVariable("telefone");
		Input type = (Input) executionContext.getContextInstance().getVariable("tipoCooperado");Input email = (Input) executionContext.getContextInstance().getVariable("email");
		Input naturalDe = (Input) executionContext.getContextInstance().getVariable("naturalDe"); Input provinciaDe = (Input) executionContext.getContextInstance().getVariable("provinciaDe");
		Input nomePai = (Input) executionContext.getContextInstance().getVariable("nomePai");Input nomeMae = (Input) executionContext.getContextInstance().getVariable("nomeMae");
		Input numBilhete = (Input) executionContext.getContextInstance().getVariable("numDocumento");Input estadoCivil = (Input) executionContext.getContextInstance().getVariable("estadoCivil");
		Input dataNascimento = (Input) executionContext.getContextInstance().getVariable("dataNascimento");Input profissao = (Input) executionContext.getContextInstance().getVariable("profissao");
		Input funcao = (Input) executionContext.getContextInstance().getVariable("funcao");Input empresa_onde_trabalha = (Input) executionContext.getContextInstance().getVariable("empresa");
		Input habilitacoes_literarias = (Input) executionContext.getContextInstance().getVariable("habilitacoesLiterarias");
		String denominacaoAbreviada = "";String naturezaJuridica = "";String sedePrincipal = "";String matriculaRegComercial = "";String dataRegistro = "";
		String provinciaRegistro = "";String objectoSocial = "";String finalidadeNegocio = "";
		Input dataEmissao = (Input) executionContext.getContextInstance().getVariable("dataEmissao");Input dataExpiracao = (Input) executionContext.getContextInstance().getVariable("dataExpiracao");
		Input localEmissao = (Input) executionContext.getContextInstance().getVariable("localEmissao");Input bancoDomiciliacao = (Input) executionContext.getContextInstance().getVariable("bancoDomiciliacao");
		Input balcaoDomiciliacao = (Input) executionContext.getContextInstance().getVariable("balcaoDomiciliacao");Input pep = (Input) executionContext.getContextInstance().getVariable("pep");
		Input rendimento = (Input) executionContext.getContextInstance().getVariable("rendimento");Input nacionalidade = (Input) executionContext.getContextInstance().getVariable("nacionalidade");
		Input numConta = (Input) executionContext.getContextInstance().getVariable("numConta");
		tipoCooperado = type.getValue().equals("SINGULAR") ? "1" : "2";
		String segundoNome = secondName.getValue().toString().equals("") ? "" : secondName.getValue().toString().toUpperCase(Locale.getDefault());
		String numeroCertidao = "";
		
		String nomeDoPai = nomePai.getValue().toString().equals("") ? "" : nomePai.getValue().toString().toUpperCase(Locale.getDefault());
		Input officeId = (Input) executionContext.getContextInstance().getVariable("officeId");
		
		String resultado =this.clienteService.SaveCooperado(tipoCooperado, firstName.getValue().toUpperCase(Locale.getDefault()),
				nif.getValue(),segundoNome,lastName.getValue().toUpperCase(Locale.getDefault()),Integer.parseInt(officeId.getValue().replace("\"", "")), telefone.getValue(),
				email.getValue(), naturalDe.getValue().toUpperCase(Locale.getDefault()), nomeDoPai,nomeMae.getValue().toUpperCase(Locale.getDefault()), numBilhete.getValue(),
				estadoCivil.getValue().toUpperCase(Locale.getDefault()),ConverterData(dataNascimento.getValue().toString()) , profissao.getValue().toUpperCase(Locale.getDefault()), funcao.getValue(),
				empresa_onde_trabalha.getValue().toUpperCase(Locale.getDefault()),habilitacoes_literarias.getValue().toUpperCase(Locale.getDefault()), denominacaoAbreviada.toUpperCase(Locale.getDefault()),
				naturezaJuridica.toUpperCase(Locale.getDefault()), sedePrincipal.toUpperCase(Locale.getDefault()),
				matriculaRegComercial.toUpperCase(Locale.getDefault()),dataRegistro, provinciaRegistro.toUpperCase(Locale.getDefault()), objectoSocial.toUpperCase(Locale.getDefault()),
				finalidadeNegocio.toUpperCase(Locale.getDefault()),pep.getValue(),localEmissao.getValue().toUpperCase(Locale.getDefault()),dataEmissao.getValue().
				toString(),dataExpiracao.getValue().toString(),nacionalidade.getValue(),numConta.getValue(),
				balcaoDomiciliacao.getValue().toUpperCase(Locale.getDefault()),rendimento.getValue(),
				address.getValue().toString().toUpperCase(Locale.getDefault()),provinciaDe.getValue().toUpperCase(Locale.getDefault()),bancoDomiciliacao.getValue().toUpperCase(Locale.getDefault()),
				iban.getValue().toString(),provinciaMorada.getValue().toUpperCase(Locale.getDefault()), municipioMorada.getValue().toString().toUpperCase(Locale.getDefault()),
				numeroCertidao);

		if(resultado.equals("400"))
		{
			retorno = "paginaErro";
		}
		else{
				
			this.updateProcesso(executionContext);
			this.inserirDocsGingaSoft(executionContext, resultado);
			
			Input idCooperado = new Input();
			idCooperado.setName("idCooperado");
			idCooperado.setLabel("idCooperado");
			idCooperado.setValue(String.valueOf(resultado));
			executionContext.getContextInstance().setVariable("idCooperado", idCooperado);
			
			List<String> lista = cooperadoRepository.findByNif(nif.getValue());
			if(!lista.isEmpty())
			{
				SetCamposCooperado setCampos = new SetCamposCooperado();
				setCampos.setDataFormatada(executionContext,ConverterData(dataNascimento.getValue().toString()));
				setCampos.setClientId(executionContext,resultado);
				setCampos.setNomeCompleto(executionContext, firstName.getValue(),lastName.getValue());
				retorno =  "Aprovado(a)";
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

	public String DateConverter(String dataEntrada) throws ParseException {
		SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
		SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		Date date = formatoEntrada.parse(dataEntrada);
		String dataNasc = formatoSaida.format(date);
		return dataNasc;
	}

	
	public String getSelectedValue(Select selectedValue)
	{
		String retorno ="";
		for(int i=0; i<selectedValue.getOptions().size();i++)
		{
			if(selectedValue.getOptions().get(i).isSelected())
			{
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
	
	
	public void inserirDocsGingaSoft(ExecutionContext executionContext,String clientId) throws IOException
	{
		Input idBilhete   = (Input) executionContext.getContextInstance().getVariable("idBilhete");
		Input idNif   = (Input) executionContext.getContextInstance().getVariable("idNif");
		Input idFichaSingular   = (Input) executionContext.getContextInstance().getVariable("idFichaSingular");
		Input idFoto   = (Input) executionContext.getContextInstance().getVariable("idFoto");

		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "")+docService.GetDocument(idBilhete.getValue().toString()),
				clientId, "1");
		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "")+docService.GetDocument(idNif.getValue().toString()),
				clientId, "5");
		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "")+docService.GetDocument(idFichaSingular.getValue().toString()),
				clientId, "10");
		docService.SaveFiles((api.GetEnviromentVariables().get(13)).replace("\"", "")+docService.GetDocument(idFoto.getValue().toString()),
				clientId, "11");
	}
	
	

}