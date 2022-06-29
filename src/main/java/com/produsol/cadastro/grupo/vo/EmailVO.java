package com.produsol.cadastro.grupo.vo;

public class EmailVO
{	
	public EmailVO()
	{
		super();		
	}
	private String host;
	private String remetente;
	private String assunto;
	private String username;
	private String senha;
	private String referencia;

	public String getHost() {

		this.host= "smtp.live.com";
		//this.host= "smtp.gmail.com";

		return this.host;
	}
	public void setHost(String host) {

		this.host = host;
	}

	public String getRemetente() {

		this.remetente = "sigt.teste@hotmail.com";
		 // this.remetente = "no-reply-coopera@cooperafaje.com";

		return this.remetente;
	}
	public void setRemetente(String remetente) {

		this.remetente = remetente;
	}


	public String getAssunto() {

		this.assunto = "Pagamento da Quota Inicial com referência - ";

		return this.assunto;
	}
	public void setAssunto(String assunto) {

		this.assunto = assunto;
	}

	public String getUsername() {

		this.username = "sigt.teste@hotmail.com";
		//this.username = "no-reply-coopera@cooperafaje.com";

		return this.username;
	}
	public void setUsername(String username) {

		this.username = username;
	}
	public String getSenha() {

		this.senha = "sigt_teste2016";
		 //this.senha ="Pr0dusol2020";

		return this.senha;
	}
	public void setSenha(String senha) {

		this.senha = senha;
	}

	public String getReferencia() {

		return this.referencia;
	}
	public void setReferencia(String referencia) {

		this.referencia = referencia;
	}


}
