package com.produsol.cadastro.grupo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


public class SMS
{
		public String SendSMS(String username,String token,String to,String msg)
		{
			String mensagem = msg.replaceAll(" ", "+");
			String url= "https://gingasms.com/index.php?app=ws&u="+username+"&h="+token+"&op=pv&to="+to+"&msg="+mensagem;
			
			StringBuffer response = new StringBuffer();
			try {
				URL obj = new URL(url);

				HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

				connection.setRequestMethod("POST");

				connection.setRequestProperty("Content-Type", "application/json");

				connection.setDoOutput(true);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println("SMS  Enviada com sucesso"+ response.toString());
			
			}catch (Exception e) {
				e.getMessage();
			}
			return response.toString();
		}
}
	