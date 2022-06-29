package com.produsol.cadastro.grupo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.openkm.bean.form.Input;
import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.repositories.CooperadoRepository;
import com.produsol.cadastro.grupo.repositories.ProcessoRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.services.HostService;
import com.produsol.cadastro.grupo.services.RejeitarCooperadoService;
import com.produsol.cadastro.grupo.vo.Cooperado;

public class RejeitarCooperado implements ActionHandler {

	private static final long serialVersionUID = 1L;

	RejeitarCooperadoService servico = new RejeitarCooperadoService();
	CooperadoRepository cooperadoRepository = new CooperadoRepository();
	HostService host = new HostService();
	ProcessoRepository processRepository = new ProcessoRepository();
	ConversorDataHora dataHora = new ConversorDataHora();
	Authentication auth = getAuthentication();

	@Override
	public void execute(ExecutionContext executionContext) throws Exception {
		Input tipoCooperado = (Input) executionContext.getContextInstance().getVariable("tipoCooperado");
		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		String type = tipoCooperado.getValue().equals("SINGULAR") ? "1" : "2";
		String transitionName = executionContext.getTransition().getName();
		String actorId = auth.getName();
		long processId = executionContext.getProcessInstance().getId();
		List<String> lista = new ArrayList<>();

		System.out.println("Transition Name: " + transitionName);

		if (type.equals("1")) {
			Cooperado dadosSingular = setDadosSingular(executionContext);
			this.cooperadoRepository.saveCooperado(dadosSingular);
			;
			lista = cooperadoRepository.findByNif(nif.getValue().toString());
		} else {
			Cooperado dadosEmpresa = setDadosEmpresa(executionContext);
			this.cooperadoRepository.saveEmpresa(dadosEmpresa);
			lista = cooperadoRepository.findEmpresaByNif(nif.getValue());
		}

		if (!lista.isEmpty()) {
			cooperadoRepository.saveComplianceDecision(lista.get(0).toString(),transitionName, actorId, processId,this.host.getIpAddress(), this.host.getHostName());
		}
	}

	public Cooperado setDadosEmpresa(ExecutionContext executionContext) {
		Cooperado cooperado = new Cooperado();
		String tipoCooperado = "";

		Input type = (Input) executionContext.getContextInstance().getVariable("tipoCooperado");
		Input denominacaoSocial = (Input) executionContext.getContextInstance().getVariable("denominacaoSocial");
		Input naturezaJuridica = (Input) executionContext.getContextInstance().getVariable("naturezaJuridica");
		Input sedePrincipal = (Input) executionContext.getContextInstance().getVariable("sedePrincipal");
		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		Input matriculaRegComercial = (Input) executionContext.getContextInstance()
				.getVariable("matriculaRegComercial");
		Input telefone = (Input) executionContext.getContextInstance().getVariable("telefone");
		Input objectoSocial = (Input) executionContext.getContextInstance().getVariable("objectoSocial");
		Input finalidadeNegocio = (Input) executionContext.getContextInstance().getVariable("finalidadeNegocio");
		Input pep = (Input) executionContext.getContextInstance().getVariable("pep");
		Input bancoDomiciliacao = (Input) executionContext.getContextInstance().getVariable("bancoDomiciliacao");
		tipoCooperado = type.getValue().equals("SINGULAR") ? "1" : "2";
		Input iban = (Input) executionContext.getContextInstance().getVariable("iban");
		Input officeId = (Input) executionContext.getContextInstance().getVariable("officeId");
		boolean ispep = pep.getValue().toString() == "sim" ? true : false;

		cooperado.setNomeCooperado(denominacaoSocial.getValue().toUpperCase(Locale.getDefault()));
		cooperado.setTipoCooperado(tipoCooperado);
		cooperado.setNif(nif.getValue().toString());
		cooperado.setTelefone(telefone.getValue().toString());
		cooperado.setBancoCooperado(bancoDomiciliacao.getValue().toString());
		cooperado.setIban(iban.getValue().toString());
		cooperado.setPep(ispep);
		cooperado.setNaturezaJuridica(naturezaJuridica.getValue().toString());
		cooperado.setSedePrincipal(sedePrincipal.getValue().toString());
		cooperado.setRegistoComercial(matriculaRegComercial.getValue());
		cooperado.setObjectoSocial(objectoSocial.getValue().toString());
		cooperado.setFinalidade(finalidadeNegocio.getValue().toString());
		cooperado.setOfficeRegisto(officeId.getValue().toString());

		return cooperado;

	}

	public Cooperado setDadosSingular(ExecutionContext executionContext) {
		Cooperado cooperado = new Cooperado();

		Input iban = (Input) executionContext.getContextInstance().getVariable("iban");
		Input nif = (Input) executionContext.getContextInstance().getVariable("nif");
		Input address = (Input) executionContext.getContextInstance().getVariable("moradaActual");
		Input firstName = (Input) executionContext.getContextInstance().getVariable("firstName");
		Input secondName = (Input) executionContext.getContextInstance().getVariable("secondName");
		Input lastName = (Input) executionContext.getContextInstance().getVariable("lastName");
		Input telefone = (Input) executionContext.getContextInstance().getVariable("telefone");
		Input naturalDe = (Input) executionContext.getContextInstance().getVariable("naturalDe");
		Input nomePai = (Input) executionContext.getContextInstance().getVariable("nomePai");
		Input nomeMae = (Input) executionContext.getContextInstance().getVariable("nomeMae");
		Input numBilhete = (Input) executionContext.getContextInstance().getVariable("numDocumento");
		Input estadoCivil = (Input) executionContext.getContextInstance().getVariable("estadoCivil");
		Input funcao = (Input) executionContext.getContextInstance().getVariable("funcao");
		Input empresa_onde_trabalha = (Input) executionContext.getContextInstance().getVariable("empresa");
		Input habilitacoes_literarias = (Input) executionContext.getContextInstance()
				.getVariable("habilitacoesLiterarias");
		Input dataEmissao = (Input) executionContext.getContextInstance().getVariable("dataEmissao");
		Input localEmissao = (Input) executionContext.getContextInstance().getVariable("localEmissao");
		Input bancoDomiciliacao = (Input) executionContext.getContextInstance().getVariable("bancoDomiciliacao");
		Input rendimento = (Input) executionContext.getContextInstance().getVariable("rendimento");
		Input numConta = (Input) executionContext.getContextInstance().getVariable("numConta");
		String segundoNome = secondName.getValue().toString().equals("") ? ""
				: secondName.getValue().toString().toUpperCase(Locale.getDefault());
		String nomeDoPai = nomePai.getValue().toString().equals("") ? ""
				: nomePai.getValue().toString().toUpperCase(Locale.getDefault());

		cooperado.setMorada(address.getValue().toString());
		cooperado.setTelefone(telefone.getValue().toString());
		cooperado.setNomeCooperado((firstName.getValue() + " " + segundoNome + " " + lastName.getValue())
				.toUpperCase(Locale.getDefault()));
		cooperado.setBancoCooperado(bancoDomiciliacao.getValue().toString());
		cooperado.setNumConta(numConta.getValue().toString());
		cooperado.setIban(iban.getValue().toString());
		cooperado.setFuncao(funcao.getValue().toString());
		cooperado.setEmpregador(empresa_onde_trabalha.getValue().toString());
		cooperado.setSalarioBase(rendimento.getValue().toString());
		cooperado.setNumeroDoc(numBilhete.getValue().toString());
		cooperado.setNif(nif.getValue().toString());
		cooperado.setNumConta(numConta.getValue().toString());
		cooperado.setLocalEmissao(localEmissao.getValue().toString());
		cooperado.setDataEmissao(dataEmissao.getValue().toString());
		cooperado.setNaturalDe(naturalDe.getValue().toString());
		cooperado.setEstadoCivil(estadoCivil.getValue().toString());
		cooperado.setNomePai(nomeDoPai);
		cooperado.setNomeMae(nomeMae.getValue().toString());
		cooperado.setHabilitacoes(habilitacoes_literarias.getValue().toString());

		return cooperado;

	}
	
	
	public static Authentication getAuthentication() {
		if (SecurityHolder.get() != null) {
			return SecurityHolder.get();
		} else {
			return SecurityContextHolder.getContext().getAuthentication();
		}
	}

}