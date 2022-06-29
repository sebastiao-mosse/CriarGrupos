package com.produsol.cadastro.grupo.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.JsonParser;
import com.produsol.cadastro.grupo.properties.Propriedades;

public class DocumentService {

	private static final String USER_AGENT = "Mozilla/5.0";
	Propriedades api = new Propriedades();

	public String deleteDocumentByUUID(String path, String host) throws IOException {
		String url = (api.GetEnviromentVariables().get(8) + path + "&host=" + host+"&username="+api.GetEnviromentVariables().get(10) 
				+"&password="+ api.GetEnviromentVariables().get(11)).replace("\"", "");

		URL obj = new URL(url);
		StringBuffer response = new StringBuffer();
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setDoOutput(true);
		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} else {
		}
		return response.toString();
	}

	public String GetDocument(String documentId) throws IOException {
		String urlString = (api.GetEnviromentVariables().get(12) + documentId).replace("\"", "");
		URL url = new URL(urlString);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		Authentication(conn);

		if (conn.getResponseCode() == 200) {
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			StringBuffer response = new StringBuffer();
			while ((output = br.readLine()) != null) {
				response.append(output);
			}

			br.close();
			com.google.gson.JsonObject data = new JsonParser().parse(response.toString()).getAsJsonObject();
			StringBuilder resultado = new StringBuilder();
			String string = data.get("path").toString();
			char[] tab = string.toCharArray();
			for (char current : tab) {
				if (current != '"')
					resultado.append(current);
			}
			return resultado.toString();
		} else {
			System.err.println("Failed : HTTP error code : " + conn.getResponseCode());
		}
		return "";
	}

	public void Authentication(HttpURLConnection con) throws IOException {

		String USER_AGENT = "Mozilla/5.0";
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Accept", "application/json");
		String username = api.GetEnviromentVariables().get(10).replace("\"", "");
		String password = api.GetEnviromentVariables().get(11).replace("\"", "");
		String userCredentials = username + ":" + password;
		con.setRequestProperty("Authorization", "Basic " + new String(new Base64().encode(userCredentials.getBytes())));
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);
	}

	public String SaveFiles(String url, String idCooperado, String tipo) throws IOException {
		String resultado = null;
		String urlString = (api.GetEnviromentVariables().get(2) + "novoDoc").replace("\"", "");

		URL obj = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		Authentication(con);
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(this.CreateJson(url, idCooperado, tipo));
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		if (responseCode != 200) {
			return resultado;
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();
			while ((output = in.readLine()) != null) {
				response.append(output);
			}
			in.close();
			return response.toString();
		}
	}

	public String CreateJson(String url, String idCooperado, String tipo) {

		String json = "{" + "'url':'" + url + "',  " + "'cooperado':'" + idCooperado + "', " + "'tipo':'" + tipo + "'}";
		System.out.println("Json com alteracao: " + json.replaceAll("'", "\""));
		return json.replaceAll("'", "\"");
	}	
	
	public String getDocumentUUID(String path, String host) throws IOException 
	{
		String url = (api.GetEnviromentVariables().get(8).replace("document?path=", "documentUUID?path=") + path + "&host=" + host+"&username="+api.GetEnviromentVariables().get(10) 
				+"&password="+ api.GetEnviromentVariables().get(11)).replace("\"", "");
		
		URL obj = new URL(url);
		StringBuffer response = new StringBuffer();
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setDoOutput(true);
		int responseCode = connection.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} else {
		}
		return response.toString();
	}
	
	
	public String getDocumentPath(String fileName, String host) throws IOException 
	{
		String url = (api.GetEnviromentVariables().get(8).replace("document?path=", "documentPath?name=") + fileName + "&host=" + host+"&username="+api.GetEnviromentVariables().get(10) 
				+"&password="+ api.GetEnviromentVariables().get(11)).replace("\"", "");
		
		URL obj = new URL(url);
		StringBuffer response = new StringBuffer();
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setDoOutput(true);
		int responseCode = connection.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} else {
		}
		return response.toString();
	}

}
