package com.produsol.cadastro.grupo.vo;

public class DuracaoProcessoVo {

	public DuracaoProcessoVo() {
		super();		
	}
	private String id;
	private String idProcesso;
	private String idDepartamento;
	private String idOperacao;
	private String dataInicio;
	private String dataTermino;
	private String minuto;
	private String hora;
	
	public String getId() {
		return id;
	}
	public String getIdProcesso() {
		return idProcesso;
	}
	public String getIdDepartamento() {
		return idDepartamento;
	}
	public String getIdOperacao() {
		return idOperacao;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public String getDataTermino() {
		return dataTermino;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setIdProcesso(String idProcesso) {
		this.idProcesso = idProcesso;
	}
	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public void setIdOperacao(String idOperacao) {
		this.idOperacao = idOperacao;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}
	public String getMinuto() {
		return minuto;
	}
	public String getHora() {
		return hora;
	}
	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}