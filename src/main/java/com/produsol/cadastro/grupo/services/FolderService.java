package com.produsol.cadastro.grupo.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.produsol.cadastro.grupo.properties.Propriedades;

public class FolderService {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	
	Propriedades api = new  Propriedades();
	
	public String PostFolder(String name, String tipoCooperado) throws IOException {
		
		String url =  (api.GetEnviromentVariables().get(7)).replace("\"", "");
		URL obj = new URL(url);
		
		StringBuffer response = new StringBuffer();
		String POST_PARAMS = "name=" + name + "&tipoCooperado=" + tipoCooperado + "&host=" + (api.GetEnviromentVariables().get(9)).replace("\"", "")+
				"&username="+(api.GetEnviromentVariables().get(10)).replace("\"", "")+"&password="+(api.GetEnviromentVariables().get(11)).replace("\"", "");
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setDoOutput(true);
		OutputStream os = connection.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK)
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null){response.append(inputLine);}
			in.close();
			return response.toString();
		}
		else {return null;}
	}

}
