package com.produsol.cadastro.grupo.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import com.google.gson.JsonParser;

public class Propriedades {

	public static ResourceBundle getProp() throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle("com.openkm.properties/parametros");
		return bundle;
	}
	
	protected String getValor(String chave){
		Properties props = new Properties();
        return (String)props.getProperty(chave);
	}
	
	/* PRODUÇÃO */

	/*
public List<String> GetEnviromentVariables() throws IOException {

			List<String> lista = new ArrayList<String>();

			URL obj = new URL(Propriedades.getProp().getString("server.url.connection")+"/configuracao");

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			GetAuthentication(con);

			con.setDoOutput(true);

			int responseCode = con.getResponseCode();

			if (responseCode != 200) {
				return lista;
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String output;
				StringBuffer response = new StringBuffer();

				while ((output = in.readLine()) != null) {
					response.append(output);
				}
				in.close();

				com.google.gson.JsonObject data = new JsonParser().parse(response.toString()).getAsJsonObject();
					lista.add(data.get("token_sms").toString());
					lista.add(data.get("usuario_sms").toString());
					lista.add(data.get("url_api").toString());
					lista.add(data.get("smtp_user").toString());
					lista.add(data.get("smtp_pass").toString());
					lista.add(data.get("smtp_host").toString());
					lista.add(data.get("smtp_port").toString());	
					lista.add(data.get("openkm_create").toString());
					lista.add(data.get("openkm_document").toString());
					lista.add(data.get("openkm_host").toString());
					lista.add(data.get("openkm_username").toString());
					lista.add(data.get("openkm_password").toString());	
					lista.add(data.get("openkm_service_searc").toString());
					lista.add(data.get("openkm_service_webdav").toString());	
					lista.add(data.get("mysql_username").toString());
					lista.add(data.get("mysql_password").toString());	
					lista.add(data.get("sms_content").toString());					
					return lista;
				}
		}

*/


	public List<String> GetEnviromentVariables() throws IOException {

			List<String> lista = new ArrayList<String>();

			URL obj = new URL(Propriedades.getProp().getString("server.url.connection")+"/configuracao");

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			GetAuthentication(con);

			con.setDoOutput(true);

			int responseCode = con.getResponseCode();

			if (responseCode != 200) {
				return lista;
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String output;
				StringBuffer response = new StringBuffer();

				while ((output = in.readLine()) != null) {
					response.append(output);
				}
				in.close();
					lista.add("70238691e68c9c17de06606251963c81");
					lista.add("David");
					lista.add("http://92.204.138.167:8282/gatepay/");
					lista.add("no-reply-coopera@cooperafaje.com");
					lista.add("Pr0dusol2020");
					lista.add("smtp.gmail.com");
					lista.add("587");	
					lista.add("http://localhost:8082/create");
					lista.add("http://localhost:8082/document?path=");
					lista.add("http://localhost:8080/");
					lista.add("okmAdmin");
					lista.add("admin");	
					lista.add("http://localhost:8080/services/rest/document/getProperties?docId=");
					lista.add("http://localhost:8080/webdav/");	
					lista.add("root");
					lista.add("root");	
					lista.add(" Caro Cooperado,"
					+"O seu processo de inscrição está quase concluído, queira por favor efectuar o pagamento da sua quota inicial nas coordenadas abaixo:"
					+"Entidade: varEntidade"
					+"Referência de Cooperado : varReferencia"
					+"Valor: varValor"
					+"COOPERATIVA DE CRÉDITO FAJE"
					+"VAMOS POUPAR JUNTOS");	
					return lista;
				}
		}
	
		public void GetAuthentication(HttpURLConnection con) throws IOException {
			
			String USER_AGENT = "Mozilla/5.0";

			con.setRequestMethod("GET");con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
		}

	
	
}