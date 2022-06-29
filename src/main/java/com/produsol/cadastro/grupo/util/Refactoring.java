package com.produsol.cadastro.grupo.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.google.gson.JsonParser;
import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.properties.Propriedades;

public class Refactoring {
	
	Propriedades api = new Propriedades();
	
public Authentication getAuthentication() {
	if (SecurityHolder.get() != null) {
		return SecurityHolder.get();
	} else {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}

public List<String> getRolesByUser(String user) throws IOException {

	URL obj = new URL(api.GetEnviromentVariables().get(9).replace("\"", "") + "services/rest/auth/getRolesByUser/" + user.replace("\"", ""));
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	Authentication(con, "GET");

	int responseCode = con.getResponseCode();
	List<String> lista = new ArrayList<String>();

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
			com.google.gson.JsonObject data = new JsonParser().parse(response.toString()).getAsJsonObject();
	        String[] strSplit = (data.get("role").toString().replace("[", "").replace("]", "").replaceAll("\"", "")).split(",");
	 
	        ArrayList<String> strList = new ArrayList<String>(
	            Arrays.asList(strSplit));
	        for (String s : strList)
	        {
	        	lista.add(s);
	        }
	        return lista;      	
	}

}

public void Authentication(HttpURLConnection con, String httpMethod) throws IOException {

	String USER_AGENT = "Mozilla/5.0";
	con.setRequestMethod(httpMethod);
	con.setRequestProperty("User-Agent", USER_AGENT);
	con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	con.setRequestProperty("Accept", "application/json");
	String userCredentials = this.api.GetEnviromentVariables().get(10).replace("\"", "") + ":" + api.GetEnviromentVariables().get(11).replace("\"", "");
	con.setRequestProperty("Authorization", "Basic " + new String(new Base64().encode(userCredentials.getBytes())));
	con.setRequestProperty("Content-Type", "application/json");
	con.setDoOutput(true);
}

public String getHours(String secondDate, String firstDate) 
{
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    long hours = 0;
    try {
        Date date1 = sdf.parse(secondDate);
        Date date2 = sdf.parse(firstDate);
        long elapsedms = date1.getTime() - date2.getTime();
        hours = TimeUnit.HOURS.convert(elapsedms, TimeUnit.MILLISECONDS);
    }
    catch (ParseException e) {
        e.printStackTrace();
    }
    return String.valueOf(hours);	
}

public String getMinutes(String secondDate, String firstDate) 
{
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    long minutes = 0;
    try {
        Date date1 = sdf.parse(secondDate);
        Date date2 = sdf.parse(firstDate);
        long elapsedms = date1.getTime() - date2.getTime();
        minutes = TimeUnit.MINUTES.convert(elapsedms, TimeUnit.MILLISECONDS);
    }
    catch (ParseException e) {
        e.printStackTrace();
    }
    return String.valueOf(minutes);
	
}

}
