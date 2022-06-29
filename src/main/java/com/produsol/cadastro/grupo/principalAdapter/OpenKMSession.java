package com.produsol.cadastro.grupo.principalAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gson.JsonParser;
import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.properties.Propriedades;

public class OpenKMSession {

	public String GetUserName() {

		Authentication auth = getAuthentication();

		return auth.getName();
	}

	public static Authentication getAuthentication() {

		if (SecurityHolder.get() != null) {

			return SecurityHolder.get();

		} else {

			return SecurityContextHolder.getContext().getAuthentication();
		}
	}

	public String GetUserEmail(String username) throws ClassNotFoundException, SQLException, IOException {

		Class.forName(Propriedades.getProp().getString("servidor.mysql.driver"));
		
		Connection connection = DriverManager.getConnection(
		Propriedades.getProp().getString("servidor.mysql.jdbc"),
		Propriedades.getProp().getString("servidor.mysql.username"), 
		Propriedades.getProp().getString("servidor.mysql.password"));
		String sql = "SELECT USR_EMAIL FROM okm_user WHERE USR_ID= ?";
		PreparedStatement preparedStmt = connection.prepareStatement(sql);
		ResultSet rs;
		
		String email = null;

		preparedStmt.setString(1, username);

		rs = preparedStmt.executeQuery();
		
		while (rs.next()) {
			email = rs.getString("USR_EMAIL");
		}
		rs.close();
		preparedStmt.close();

		return email;
	}
	
	
	public int getUserOfficeId() throws IOException, ClassNotFoundException, SQLException
	{
		//SUBSTITUIR ESTE LINK PELO LINK CORRECTO
		   String url = "http://localhost:9090/buscaOffice?email=";
			
			
			URL obj = new URL(url+this.GetUserEmail(this.GetUserName()));
			
			com.google.gson.JsonObject data = null;

			HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
			connection.setRequestMethod("GET");

			connection.setRequestProperty("Content-Type", "application/json");

			connection.setDoOutput(true);
			
			  if (connection.getResponseCode() == 200) {

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String inputLine;

			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		 in.close();
			
		data = new JsonParser().parse(response.toString()).getAsJsonObject();
			
		  } else {
              System.err.println("Failed : HTTP error code : " + connection.getResponseCode());
          }
			  //REVER O RETURN. O RETORNO SERÁ DE ACORCO COM O JSON QUE O DAVID ME DARÁ
        
		return Integer.parseInt(data.get("totalFilteredRecords").toString());
	}

}
