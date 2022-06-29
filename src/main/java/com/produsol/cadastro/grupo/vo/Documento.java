package com.produsol.cadastro.grupo.vo;

public class Documento {
	
	private long id;
	private String idCooperado;
	private String tipoDocumento;
	private String descricao;
	private String path;
	private String nomeCooperado;
	private String nif;
	
	public long getId() {
		return id;
	}
	public String getIdCooperado() {
		return idCooperado;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getPath() {
		return path;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setIdCooperado(String idCooperado) {
		this.idCooperado = idCooperado;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNomeCooperado() {
		return nomeCooperado;
	}
	public String getNif() {
		return nif;
	}
	public void setNomeCooperado(String nomeCooperado) {
		this.nomeCooperado = nomeCooperado;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}

	
	
	
	
}
