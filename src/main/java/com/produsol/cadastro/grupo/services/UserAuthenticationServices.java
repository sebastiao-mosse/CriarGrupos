package com.produsol.cadastro.grupo.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonParser;
import com.produsol.cadastro.grupo.properties.Propriedades;

import org.apache.commons.codec.binary.Base64;
import java.util.ArrayList;
import java.util.List;

public class UserAuthenticationServices {
	
	Propriedades api = new Propriedades();

	public String GetUserEmail(String user) throws IOException 
	{
		URL obj = new URL(api.GetEnviromentVariables().get(9).replace("\"", "") +"services/rest/auth/getMail/" + user.replace("\"", ""));
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		Authentication(con);
		int responseCode = con.getResponseCode();
		if (responseCode != 200) {return null;} 
		else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();
			while ((output = in.readLine()) != null) {response.append(output);}
			in.close();
			return  response.toString();
			}
	}

	public List<String> GetUsersByRole(String role) throws IOException {

		URL obj = new URL(api.GetEnviromentVariables().get(9).replace("\"", "") +"services/rest/auth/getUsersByRole/" + role);
		List<String> lista = new ArrayList<String>();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		Authentication(con);
		int responseCode = con.getResponseCode();
		if (responseCode != 200) {return null;}
		
		else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();
			while ((output = in.readLine()) != null) {response.append(output);}
			in.close();
				com.google.gson.JsonObject data = new JsonParser().parse(response.toString()).getAsJsonObject();
				lista.add(data.get("user").toString());
				return lista;
		}
	}

	public void Authentication(HttpURLConnection con) throws IOException 
	{
		String USER_AGENT = "Mozilla/5.0"; con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Accept", "application/json");		
		String userCredentials = api.GetEnviromentVariables().get(10).replace("\"", "") + ":" + api.GetEnviromentVariables().get(11).replace("\"", "") ;
		con.setRequestProperty("Authorization", "Basic " + new String(new Base64().encode(userCredentials.getBytes())));
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);
	}

	public List<String> getUserName(List<String> resultado) {
		List<String> lista = new ArrayList<String>();
		String usuarios = resultado.get(0).replaceAll("\\[", "").replaceAll("\\]", "");
		String[] words = usuarios.split(",");
		for (String w : words) {
			lista.add(w.replaceAll("\"", ""));
		}
		return lista;

	}

}