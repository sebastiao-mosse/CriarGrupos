package com.produsol.cadastro.grupo.vo;

public class FaseProcessoVO {

	public FaseProcessoVO() {
		super();		
	}
	private long faseId;
	private String faseDescricao;
	private long idProcesso;
	private String dataInicio;
	private String dataConclusao;
	private String departamento;
	private String usuario;
	private String status;
	private String nif;
	private long codigo;
	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public long getFaseId() {
		return faseId;
	}
	public String getFaseDescricao() {
		return faseDescricao;
	}
	public long getIdProcesso() {
		return idProcesso;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public String getDataConclusao() {
		return dataConclusao;
	}
	public String getDepartamento() {
		return departamento;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getStatus() {
		return status;
	}
	public void setFaseId(long faseId) {
		this.faseId = faseId;
	}
	public void setFaseDescricao(String faseDescricao) {
		this.faseDescricao = faseDescricao;
	}
	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	
	
}