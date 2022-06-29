package com.produsol.cadastro.grupo.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClientVO {

	public ClientVO() {
		super();		
	}
	private int officeId;
	private int externalId;
	private String locale;
	private Date submitteDate;
	private String dateFormat;
	private String url;
	private String username;
	private String password;
	private boolean active;
	private String fullName;
	private String nif;
	private String address;
	private String clientId;
	
	private String firstName;
	private String lastName;
	private String telefone;
	
	
	
	public int getOfficeId() {
		officeId = 1;
		return officeId;
	}
	
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public void setExternalId(int externalId) {
		this.externalId = externalId;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public void setSubmitteDate(Date submitteDate) {
		this.submitteDate = submitteDate;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public int getExternalId()
	{	
		//int min   = 100; int max   = 1000000;

		//externalId =  (int)(Math.random() * (max - min + 1) + min);
		
		return externalId;
	}

	public String getLocale() {
		locale =  "en";
		return locale;
	}

	public String getDateFormat() {
		dateFormat = "dd MMMM yyyy";
		return dateFormat;
	}
	
	public String getSubmitteDate() {
		
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
		
		submitteDate = new Date();

		return simpleFormat.format(submitteDate).toString();
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		this.url = "https://gingasoft.com/fineract-provider/api/v1/clients?tenantIdentifier=default&pretty=true";
		return this.url;
	}

	public String getUsername() {
		username ="suporte";
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		password = "Produsol2021";
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public boolean isActive() {
		this.active = false;
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
	
	
}