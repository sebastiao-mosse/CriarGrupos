package com.produsol.cadastro.grupo.services;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.Normalizer;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.vo.ClientVO;

import org.apache.commons.codec.binary.Base64;


public class CooperadoService 
{
	ClientVO clienteVO = new ClientVO();
	Propriedades api = new  Propriedades();
	
	public String SaveCooperado(String tipoCooperado,String firstname,String externalId,String middlename,String lastName,int officeId,String mobileNo,
            					String email,String naturalDe,String nomePai,String nomeMae,String numBilhete,String estadoCivil,String dataNascimento,
            					String profissao,String funcao,String empresa_onde_trabalha,String habilitacoes_literarias,String denominacaoAbreviada,
            					String naturezaJuridica,String sedePrincipal,String matriculaRegComercial,String dataRegistro,
            					String provinciaRegistro,String objectoSocial,String finalidadeNegocio,
            					 String pep, String local_emissao,String data_emissao,String data_expiracao,String nacionalidade,String numero_conta_bancaria,
    			                 String balcao_domiciliacao,String rendimento_mensal, String morada,String provincia,
    				    		 String bancoDomiciliacao, String iban, String provinciaMorada,String municipioMorada,String numeroCertidao)throws IOException {
		String resultado = null;
		
	    String url =  (api.GetEnviromentVariables().get(2)+"novoCooperado").replace("\"", "");
	    URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		Authentication(con);
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(
				this.CreateJson(
						Normalizer.normalize(tipoCooperado,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), Normalizer.normalize(firstname, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
						Normalizer.normalize(externalId, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(middlename, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
						Normalizer.normalize(lastName, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),officeId,
						Normalizer.normalize(mobileNo, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),email,Normalizer.normalize(naturalDe, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
						Normalizer.normalize(nomePai,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(nomeMae, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
						Normalizer.normalize(numBilhete,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(estadoCivil,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
						Normalizer.normalize(dataNascimento,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(profissao, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
						Normalizer.normalize(funcao, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(empresa_onde_trabalha, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
						Normalizer.normalize(habilitacoes_literarias,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), Normalizer.normalize(denominacaoAbreviada,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
						Normalizer.normalize(naturezaJuridica, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(sedePrincipal,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
						Normalizer.normalize(matriculaRegComercial, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
						Normalizer.normalize(dataRegistro, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(provinciaRegistro, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), 
						Normalizer.normalize(objectoSocial,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), 
						Normalizer.normalize(finalidadeNegocio,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                pep, Normalizer.normalize(local_emissao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                Normalizer.normalize(data_emissao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),  Normalizer.normalize(data_expiracao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                Normalizer.normalize(nacionalidade,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                Normalizer.normalize(numero_conta_bancaria,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                Normalizer.normalize(balcao_domiciliacao, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                rendimento_mensal, Normalizer.normalize(morada, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                Normalizer.normalize(provincia,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                Normalizer.normalize(bancoDomiciliacao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                Normalizer.normalize(iban,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                Normalizer.normalize(provinciaMorada,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                Normalizer.normalize(municipioMorada,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
		                Normalizer.normalize(numeroCertidao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
		                
						));
		wr.flush();wr.close();
		int responseCode = con.getResponseCode();
		if(responseCode !=200)
		{
			return resultado;
		}
		else {
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

		String USER_AGENT = "Mozilla/5.0"; con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");con.setDoOutput(true);
	}
	
	public String CreateJson(
			                 String tipoCooperado,String firstname,String externalId,String middlename,String lastName,int officeId,String mobileNo,
			                 String email,String naturalDe,String nomePai,String nomeMae,String numBilhete,String estadoCivil,String dataNascimento,
			                 String profissao,String funcao,String empresa_onde_trabalha,String habilitacoes_literarias,String denominacaoAbreviada,
			                 String naturezaJuridica,String sedePrincipal,String matriculaRegComercial,String dataRegistro,
			                 String provinciaRegistro,String objectoSocial,String finalidadeNegocio,
			                 String pep, String local_emissao,String data_emissao,String data_expiracao,String nacionalidade,String numero_conta_bancaria,
			                 String balcao_domiciliacao,String rendimento_mensal,  String morada,String provincia,
				    		 String bancoDomiciliacao, String iban, String provinciaMorada,String municipioMorada, String numeroCertidao){
		boolean active= false;
		String json = "{" 
		+ "'dateOfBirth':'"+dataNascimento+"',  "+ "'tipoCooperado':'"+tipoCooperado+"', "+"'firstname':'"+firstname+"'," +"'externalId':'"+externalId+"'," + "'middlename':'"+middlename+"',"+ "'lastName':'"+lastName+"',"
		+ "'dateFormat':'"+this.clienteVO.getDateFormat()+"',  "+"'locale':'"+this.clienteVO.getLocale()+"'," +"'active':"+active+"," + "'submittedOnDate':'"+this.clienteVO.getSubmitteDate()+"',"+ "'officeId':"+officeId+","
		+ "'mobileNo':'"+mobileNo+"',  "+"'email':'"+email+"'," +"'naturalDe':'"+naturalDe+"'," + "'nomePai':'"+nomePai+"',"+ "'nomeMae':'"+nomeMae+"',"
		+ "'numBilhete':'"+numBilhete+"',  "+"'estadoCivil':'"+estadoCivil+"'," + "'profissao':'"+profissao+"',"+ "'funcao':'"+funcao+"',"
		+ "'empresa_onde_trabalha':'"+empresa_onde_trabalha+"',  "+"'habilitacoes_literarias':'"+habilitacoes_literarias+"'," +"'denominacaoAbreviada':'"+denominacaoAbreviada+"'," + "'naturezaJuridica':'"+naturezaJuridica+"',"+ "'sedePrincipal':'"+sedePrincipal+"',"
		+"'matriculaRegComercial':'"+matriculaRegComercial+"'," +"'dataRegistro':'"+dataRegistro+"'," + "'provinciaRegistro':'"+provinciaRegistro+"',"+ "'objectoSocial':'"+objectoSocial+"',"+ "'finalidadeNegocio':'"+finalidadeNegocio+"',"
		+"'pep':'"+pep+"'," +"'local_emissao':'"+local_emissao+"'," + "'data_emissao':'"+data_emissao+"',"+ "'data_expiracao':'"+data_expiracao+"',"+ "'nacionalidade':'"+nacionalidade+"',"
		+"'numero_conta_bancaria':'"+numero_conta_bancaria+"'," +"'balcao_domiciliacao':'"+balcao_domiciliacao+"'," + "'rendimento_mensal':'"+rendimento_mensal+"',"
		+"'morada':'"+morada+"'," +"'banco_domiciliacao':'"+bancoDomiciliacao + "',"		
		+"'iban':'"+iban+"'," +"'provincia_residencia':'"+provinciaMorada + "',"+ "'municipio_residencia':'" + municipioMorada + "',"
		+"'numero_certidao':'"+numeroCertidao		
		+"'}";		
		System.out.println("Json Cadastro: "+json.replaceAll("'","\""));
		return json.replaceAll("'","\""); 
	}
	
	
	public int PesquisarCooperado(String externalId) throws IOException
	{
			String url =  Propriedades.getProp().getString("gingasoft.url.pesquisa")+ externalId;
			URL obj = new URL(url);
			com.google.gson.JsonObject data = null;

			HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

			String username = Propriedades.getProp().getString("gingasoft.username");
			String password =  Propriedades.getProp().getString("gingasoft.password");
			connection.setRequestMethod("GET");

			String userCredentials = username + ":" + password;

			String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));

			connection.setRequestProperty ("Authorization", basicAuth);

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
        
		return Integer.parseInt(data.get("totalFilteredRecords").toString());
	}
	
	
	public void Auth(HttpURLConnection con) throws IOException {

		String username = api.GetEnviromentVariables().get(10).replace("\"", "");
		String password = api.GetEnviromentVariables().get(11).replace("\"", "");
		String USER_AGENT = "Mozilla/5.0"; con.setRequestMethod("GET");
	    con.setRequestProperty("User-Agent", USER_AGENT);con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Accept", "application/json");
		String userCredentials = username + ":" + password;
		con.setRequestProperty("Authorization", "Basic " + new String(new Base64().encode(userCredentials.getBytes())));
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);

		}
	
	
	public void AuthWithoutPassword(HttpURLConnection con) throws IOException {

		String USER_AGENT = "Mozilla/5.0"; con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);
		}
	   
	public String GetUserEmail(String user) throws IOException 
	{	
		String url = (api.GetEnviromentVariables().get(9) +"services/rest/auth/getMail/"+user).replace("\"", "");
		
		System.out.println("URL: " + url);
		
        URL obj = new URL(url);	
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		Auth(con);
		int responseCode = con.getResponseCode();	
		if (responseCode != 200) {return null;} 
		else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();
			while ((output = in.readLine()) != null) {response.append(output);}
			in.close();
			return response.toString();
		}
			   		
	}
	
	public String userOfficeId(String email) throws IOException 
	{
	            URL obj = new URL((api.GetEnviromentVariables().get(2)+"buscaOffice?email="+email).replace("\"", ""));	
	           
	            System.out.println("Office Id url: " + obj);
	            
	            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	    		AuthWithoutPassword(con);
	    		int responseCode = con.getResponseCode();
	    		String officeId = "";
	    	     System.out.println("Response Code:  " + responseCode);
	    		if (responseCode != 200) {
	    		} else {
	    			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    			String output;
	    			StringBuffer response = new StringBuffer();

	    			while ((output = in.readLine()) != null) {
	    				response.append(output);
	    			}
	    			in.close();
	    			
	    			if(response.toString().equalsIgnoreCase("NEncontrado")) 
	    			{
	    				return null;
	    			}
	    			if(response.toString().equalsIgnoreCase("Utilizador não encontrado")) 
	    			{
	    				return null;
	    			}
	    			else {
	    				JsonObject data = new JsonParser().parse(response.toString()).getAsJsonObject();
	    				officeId = data.get("officeId").toString();
	    			}
	    		}
				return officeId; 
				
	}
}