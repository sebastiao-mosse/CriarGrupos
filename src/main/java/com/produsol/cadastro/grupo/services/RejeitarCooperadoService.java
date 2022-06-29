package com.produsol.cadastro.grupo.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.vo.ClientVO;

import java.io.IOException;
import java.net.HttpURLConnection;

public class RejeitarCooperadoService {
	public ClientVO clienteVO = new ClientVO();
	Propriedades api = new  Propriedades();

	public String RejeitarCooperado(String cooperado, String idRejeicao) throws IOException {
		String resultado = null;
	    String url =  (api.GetEnviromentVariables().get(2)+"rejeitaCooperado").replace("\"", "");
		
		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		Authentication(con);

		con.setDoOutput(true);

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());

		wr.writeBytes(this.CreateJson(cooperado, idRejeicao));

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

	public void Authentication(HttpURLConnection con) throws IOException {

		String USER_AGENT = "Mozilla/5.0";
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);
	}

	public String CreateJson(String cooperado, String idRejeicao) {

		String json = "{" + "'cooperado':'" + cooperado + "'," + "'idRejeicao':'" + idRejeicao + "'}";
		System.out.println("Json com alteracao: " + json.replaceAll("'", "\""));
		return json.replaceAll("'", "\"");
	}
}
