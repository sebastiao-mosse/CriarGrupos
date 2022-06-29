package com.produsol.cadastro.grupo.action;

import java.util.List;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import com.openkm.bean.form.Input;
import com.openkm.bean.form.Select;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.repositories.CooperadoRepository;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.services.DatabaseOperationService;
import com.produsol.cadastro.grupo.vo.Cooperado;



public class CriarProcessoActionHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
  
	DatabaseOperationService operation = new DatabaseOperationService();
	ConversorDataHora dataHora = new ConversorDataHora();
	CooperadoRepository cooperadoRepository = new CooperadoRepository();
	
	@Override
	public void execute(ExecutionContext executionContext) throws Exception
	{	
		Input cooperadoNif = (Input) executionContext.getContextInstance().getVariable("nif");
		long taskManagementInstance = executionContext.getTaskMgmtInstance().getId();
		long swimlaneInstance       = executionContext.getTaskMgmtInstance().getSwimlaneInstance("initiator").getId();	
		String actorId              = executionContext.getTaskMgmtInstance().getSwimlaneInstance("initiator").getActorId().toString();
		//Cooperado resultado = setCooperadoData(executionContext);
		
		//this.cooperadoRepository.saveCooperado(resultado);
		List<String> lista = cooperadoRepository.findByNif(cooperadoNif.getValue());
		if(!lista.isEmpty())
		{
			operation.CreateProcessMetadata(executionContext.getProcessInstance().getId(),this.dataHora.getDateTime()
					,Propriedades.getProp().getString("produto.codigo.operacao.cadastro.cooperados"),
					taskManagementInstance, swimlaneInstance, actorId, lista.get(0),Propriedades.getProp().getString("produto.status.execucao"),
					executionContext.getProcessInstance().getId(),Propriedades.getProp().getString("process.area.comercial"),
					Propriedades.getProp().getString("process.entrada"));
			Input idCooperado = new Input();
			idCooperado.setName("idCooperado");
			idCooperado.setLabel("idCooperado");
			idCooperado.setValue(String.valueOf(lista.get(0)));
			executionContext.getContextInstance().setVariable("idCooperado", idCooperado);
		}	
	}
	
	
	public Cooperado setCooperadoData(ExecutionContext executionContext)
	{
		Cooperado cooperado = new Cooperado();
		
		Input tipoCooperado = (Input) executionContext.getContextInstance().getVariable("tipoCooperado");
		Input firstName = (Input) executionContext.getContextInstance().getVariable("firstName");
		Input secondName = (Input) executionContext.getContextInstance().getVariable("secondName");
		Input lastName = (Input) executionContext.getContextInstance().getVariable("lastName");
		String nomeCooperado = firstName.getValue()+ " " + secondName.getValue() + " " + lastName.getValue();  
		Input naturalDe = (Input) executionContext.getContextInstance().getVariable("naturalDe");
		Select provinciaDe = (Select) executionContext.getContextInstance().getVariable("provinciaDe");
		Input nomePai = (Input) executionContext.getContextInstance().getVariable("nomePai");
		Input nomeMae = (Input) executionContext.getContextInstance().getVariable("nomeMae");
		Input numDocumento = (Input) executionContext.getContextInstance().getVariable("numDocumento");
		Input dataEmissao = (Input) executionContext.getContextInstance().getVariable("dataEmissao");
		Input dataExpiracao = (Input) executionContext.getContextInstance().getVariable("dataExpiracao");
		Select localEmissao = (Select) executionContext.getContextInstance().getVariable("localEmissao");
		Select estadoCivil = (Select) executionContext.getContextInstance().getVariable("estadoCivil");
		Input moradaActual = (Input) executionContext.getContextInstance().getVariable("moradaActual");
		Input email = (Input) executionContext.getContextInstance().getVariable("email");
		Input telefone = (Input) executionContext.getContextInstance().getVariable("telefone");
		Input dataNascimento = (Input) executionContext.getContextInstance().getVariable("dataNascimento");
		Select nacionalidade = (Select) executionContext.getContextInstance().getVariable("nacionalidade");
		Input profissao = (Input) executionContext.getContextInstance().getVariable("profissao");
		Input funcao = (Input) executionContext.getContextInstance().getVariable("funcao");
		Input empresa = (Input) executionContext.getContextInstance().getVariable("empresa");
		Input rendimento = (Input) executionContext.getContextInstance().getVariable("rendimento");
		Select bancoDomiciliacao = (Select) executionContext.getContextInstance().getVariable("bancoDomiciliacao");
		Input balcaoDomiciliacao = (Input) executionContext.getContextInstance().getVariable("balcaoDomiciliacao");
		Input numConta = (Input) executionContext.getContextInstance().getVariable("numConta");
		Select habilitacoesLiterarias = (Select) executionContext.getContextInstance().getVariable("habilitacoesLiterarias");
		Select pep = (Select) executionContext.getContextInstance().getVariable("pep");
		//Input numMatricula = (Input) executionContext.getContextInstance().getVariable("numMatricula");
		Input cooperadoNif = (Input) executionContext.getContextInstance().getVariable("nif");
		//Input dataAssinaturaContrato = (Input) executionContext.getContextInstance().getVariable("dataAssinaturaContrato");
		Select docIdentificacao = (Select) executionContext.getContextInstance().getVariable("docIdentificacao");
		cooperado.setMorada(moradaActual.getValue().toString());cooperado.setTelefone(telefone.getValue().toString());
		cooperado.setNomeCooperado(nomeCooperado);cooperado.setBancoCooperado(bancoDomiciliacao.getValue().toString());
		cooperado.setNumConta(numConta.getValue().toString());
		//cooperado.setDataAssinatura(dataAssinaturaContrato.getValue().toString());
		cooperado.setFuncao(funcao.getValue().toString());cooperado.setEmpregador(empresa.getValue().toString());
		cooperado.setSalarioBase(rendimento.getValue().toString());cooperado.setNumeroDoc(cooperadoNif.getValue().toString());
		cooperado.setNif(cooperadoNif.getValue().toString());cooperado.setNumConta(numConta.getValue().toString());
		cooperado.setLocalEmissao(localEmissao.getValue().toString());cooperado.setDataEmissao(dataEmissao.getValue().toString());
		cooperado.setNaturalDe(naturalDe.getValue().toString());cooperado.setEstadoCivil(estadoCivil.getValue().toString());
		cooperado.setTipoCooperado(tipoCooperado.getValue().toString());
		//cooperado.setNumeroMatricula(numMatricula.getValue().toString());
		cooperado.setBalcaoDomiciliacao(balcaoDomiciliacao.getValue().toString());cooperado.setProvinciaDe(provinciaDe.getValue().toString());
		cooperado.setNomePai(nomePai.getValue().toString());cooperado.setNomeMae(nomeMae.getValue().toString());
		cooperado.setNumeroDoc(numDocumento.getValue().toString());cooperado.setDataExpiracao(dataExpiracao.getValue().toString());
		cooperado.setEmail(email.getValue().toString());cooperado.setDataNascimento(dataNascimento.getValue().toString());
		cooperado.setNacionalidade(nacionalidade.getValue().toString());cooperado.setProfissao(profissao.getValue().toString());
		Boolean StringPep = pep.getValue().toString().equals("sim") ? true : false;	
		cooperado.setHabilitacoes(habilitacoesLiterarias.getValue().toString());
		cooperado.setPep(StringPep);cooperado.setDocIdentificacao(docIdentificacao.getValue().toString());
		
		return cooperado;
			
	}

}