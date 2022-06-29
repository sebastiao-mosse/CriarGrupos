package com.produsol.cadastro.grupo.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gson.JsonParser;
import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.repositories.CooperadoRepository;
import com.produsol.cadastro.grupo.services.CreateAccountService;
import com.produsol.cadastro.grupo.services.HostService;
import com.produsol.cadastro.grupo.util.SMS;
import com.produsol.cadastro.grupo.util.SendEmail;

public class InformCooperadoAction implements ActionHandler {

	private static final long serialVersionUID = 1L;
	private SendEmail enviar = new SendEmail();
	private CreateAccountService account = new CreateAccountService();
	CooperadoRepository cooperadoRepository = new CooperadoRepository();
	private SMS sms = new SMS();
	HostService host = new HostService();
	long productId = 5L;
	Propriedades api = new Propriedades();
	Authentication auth = getAuthentication();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception {
		Input tipoCooperado = (Input) executionContext.getContextInstance().getVariable("tipoCooperado");
		//Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		Input email = (Input) executionContext.getContextInstance().getVariable("email");
		Input telefone = (Input) executionContext.getContextInstance().getVariable("telefone");
		Input matricula = (Input) executionContext.getContextInstance().getVariable("modified");
		//String transitionName = executionContext.getTransition().getName();
		//String actorId = auth.getName();
		//long processId = executionContext.getProcessInstance().getId();		
		Input firstName = tipoCooperado.getValue().equals("SINGULAR") ? (Input) executionContext.getContextInstance().getVariable("firstName")
				: (Input) executionContext.getContextInstance().getVariable("denominacaoSocial");
		Input lastName = tipoCooperado.getValue().equals("SINGULAR") ? (Input) executionContext.getContextInstance().getVariable("lastName")
				: (Input) executionContext.getContextInstance().getVariable("denominacaoAbreviada");

		if (firstName != null && email != null && matricula != null && telefone != null && lastName != null
				&& tipoCooperado != null) {
			int clientId = Integer.parseInt(matricula.getValue());
			String nomeCompleto = firstName.getValue() + " " + lastName.getValue();
			String type = tipoCooperado.getValue().equals("SINGULAR") ? "1" : "2";
			try {
				com.google.gson.JsonObject data = new JsonParser().parse(account.CreateSavingsAccount(clientId, type)).getAsJsonObject();
				//List<String> lista = new ArrayList<String>();
				//lista = type.equals("1") ? cooperadoRepository.findByNif(nif.getValue()) :cooperadoRepository.findEmpresaByNif(nif.getValue());
				//cooperadoRepository.saveComplianceDecision(lista.get(0).toString(),transitionName, actorId, processId,this.host.getIpAddress(),this.host.getHostName());			
				
				if (data.get("Referencia").toString() != null) 
				{
					String referenciaPagamento = data.get("Referencia").toString();
					String entidade = data.get("Entidade").toString();
					String amount = data.get("Valor").toString();

					String mensagem = (api.GetEnviromentVariables().get(16).replaceAll("varEntidade", entidade)
										.replaceAll("varReferencia", referenciaPagamento).replaceAll("varValor", amount)
										.replace("\"", "")).replaceAll(" ", "+");
					
					byte[] bytes = StringUtils.getBytesUtf8(mensagem);
					String utf8EncodedString = StringUtils.newStringUtf8(bytes);
					sms.SendSMS((api.GetEnviromentVariables().get(1)).replace("\"", ""),(api.GetEnviromentVariables().get(0)).replace("\"", ""), telefone.getValue(),utf8EncodedString);
					enviar.Send(nomeCompleto, email.getValue(), amount, referenciaPagamento, entidade);

				}
			} catch (Exception e) {
				System.out.println("Mensagem de Erro: " + e.getMessage());
			}
		}

	}
	
	public static Authentication getAuthentication() {
		if (SecurityHolder.get() != null) {
			return SecurityHolder.get();
		} else {
			return SecurityContextHolder.getContext().getAuthentication();
		}
	}
}