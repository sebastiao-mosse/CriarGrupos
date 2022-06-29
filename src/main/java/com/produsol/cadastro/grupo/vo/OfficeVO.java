package com.produsol.cadastro.grupo.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OfficeVO {

	public OfficeVO() {
		super();		
	}
	private String officeName;
	private String dateFormat;
	private String locale;
	private Date openingDate;
	private int parentId;
	private int externalId;
	private String url;
	private String username;
	private String password;
	

	public void setExternalId(int externalId) {
		this.externalId = externalId;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public int getExternalId()
	{	
		int min   = 100; int max   = 1000000;

		externalId =  (int)(Math.random() * (max - min + 1) + min);
		
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
	
	public String getOpeningDate() {
		
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
		
		openingDate = new Date();

		return simpleFormat.format(openingDate).toString();
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		
		url = "https://demo.mifos.io/fineract-provider/api/v1/offices?tenantIdentifier=default&pretty=true";
		
		return url;
	}

	public String getUsername() {
		username ="mifos";
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		password = "password";
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getOfficeName() {
		
		return officeName;
	}
	public void setOfficeName(String officeName) {
		
		this.officeName = officeName;
	}
	public int getParentId() {
		parentId = 2;
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
}