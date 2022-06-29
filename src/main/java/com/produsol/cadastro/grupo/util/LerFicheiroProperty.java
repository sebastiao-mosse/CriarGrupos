package com.produsol.cadastro.grupo.util;

import java.util.Properties;


public class LerFicheiroProperty 
{

	public String ReadPropertyFile()
	{
		String valor = null;

		try(java.io.InputStream input = new java.io.FileInputStream("database.properties")){

			java.util.Properties prop = new Properties();

			prop.load(input);

			valor = prop.getProperty("clientId").toString();

		}
		catch (java.io.IOException io)
		{

			io.printStackTrace();
		}
		return valor;
	}
	
	public static void main(String[]args)
	{
		String json = "{'officeId':1,'clientId':10319,'resourceId':10319}";
	}
}
