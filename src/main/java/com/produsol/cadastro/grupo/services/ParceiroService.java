package com.produsol.cadastro.grupo.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.produsol.cadastro.grupo.vo.ParceiroVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class ParceiroService {
	
	public List<ParceiroVO> getParceiros() throws IOException {

		List<ParceiroVO> lista = new ArrayList<ParceiroVO>();

		URL obj = new URL("http://92.204.138.167:8484/gatepay/parceiros");
				
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		GetAuthentication(con);
		int responseCode = con.getResponseCode();
		if (responseCode != 200) {
			return null;
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();

			while ((output = in.readLine()) != null) {
				response.append(output);
			}
			in.close();

			if (response.toString().equalsIgnoreCase("0404")) {
				return null;
			} else {
				 JsonParser parser = new JsonParser();
	    		   JsonObject rootObj = parser.parse("{'array':" +response.toString() +"}").getAsJsonObject();
	    		   JsonArray parceiroArray = rootObj.getAsJsonArray("array");
	    		   for (JsonElement pa : parceiroArray) {
	    			   ParceiroVO banco = new ParceiroVO();
	    		       JsonObject parceiroObject = pa.getAsJsonObject();
	    		       banco.setNome(parceiroObject.get("nome").getAsString());
			           lista.add(banco);
			    		   } 
				return lista;
			}
		}
		}
	
	public void GetAuthentication(HttpURLConnection con) throws IOException {

		String USER_AGENT = "Mozilla/5.0";
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);
	}


	
	

}