package com.produsol.cadastro.grupo.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.vo.ClientVO;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.Normalizer;

public class CompanyService {
	ClientVO clienteVO = new ClientVO();
	Propriedades api = new  Propriedades();

	public String SaveCooperado(String tipoCooperado, String firstname, String externalId, int officeId,
			String mobileNo, String email, String naturalDe, String nomePai, String nomeMae, String numBilhete,
			String estadoCivil, String dataNascimento, String profissao, String funcao, String empresa_onde_trabalha,
			String habilitacoes_literarias, String denominacaoAbreviada, String naturezaJuridica, String sedePrincipal,
			String provincia, String matriculaRegComercial, String dataRegistro, String provinciaRegistro,
			String objectoSocial, String finalidadeNegocio, String pep, String localEmissao, String dataEmissao,
			String dataExpiracao, String nacionalidade, String numConta, String balcaoDomiciliacao, String rendimento,
			String middleName,String lastName, String bancoDomiciliacao, String municipioMorada, String iban,String numeroCertidao)
			throws IOException, ParseException {
		String resultado = null;
		
	    String url =  (api.GetEnviromentVariables().get(2)+"novoCooperado").replace("\"", "");
		
		URL obj = new URL(url);
		System.out.println("URL: "+obj);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		Authentication(con);
		con.setDoOutput(true);

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());

		wr.writeBytes(this.CreateJson(tipoCooperado, Normalizer.normalize(firstname,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(externalId,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),officeId,
				Normalizer.normalize(mobileNo,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),email,
				Normalizer.normalize(naturalDe,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(nomePai,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(nomeMae,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(numBilhete,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), 
				Normalizer.normalize(estadoCivil,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), Normalizer.normalize(dataNascimento,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(profissao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(funcao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(empresa_onde_trabalha,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), Normalizer.normalize(habilitacoes_literarias ,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(denominacaoAbreviada,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(naturezaJuridica,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), 
				Normalizer.normalize(sedePrincipal,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(provincia ,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(matriculaRegComercial,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), 
				Normalizer.normalize(dataRegistro,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), Normalizer.normalize(provinciaRegistro,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(objectoSocial,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), Normalizer.normalize(finalidadeNegocio,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				pep, Normalizer.normalize(localEmissao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(dataEmissao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), Normalizer.normalize(dataExpiracao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), 
				Normalizer.normalize(nacionalidade,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), Normalizer.normalize(numConta,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), 
				Normalizer.normalize(balcaoDomiciliacao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(rendimento,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(middleName,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(lastName,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(bancoDomiciliacao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(iban,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(provincia,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(municipioMorada,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(numeroCertidao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
				
				));

		
		
		
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

	public String CreateJson(String tipoCooperado, String firstname, String externalId, int officeId, String mobileNo,
			String email, String naturalDe, String nomePai, String nomeMae, String numBilhete, String estadoCivil,
			String dataNascimento, String profissao, String funcao, String empresa_onde_trabalha,
			String habilitacoes_literarias, String denominacaoAbreviada, String naturezaJuridica, String sedePrincipal,
			String provincia, String matriculaRegComercial, String dataRegistro, String provinciaRegistro,
			String objectoSocial, String finalidadeNegocio, String pep, String localEmissao, String dataEmissao,
			String dataExpiracao, String nacionalidade, String numConta, String balcaoDomiciliacao, String rendimento, 
			String middleName,String lastName, String bancoDomiciliacao, String iban, String provinciaMorada, String municipioMorada,String numeroCertidao) {

		boolean active = false;
		
		String json = "{" 
				+ "'dateOfBirth':'"+dataNascimento+"',  "+ "'tipoCooperado':'"+tipoCooperado+"', "+"'firstname':'"+firstname+"'," +"'externalId':'"+externalId+"'," + "'middlename':'"+middleName+"',"+ "'lastName':'"+lastName+"',"
				+ "'dateFormat':'"+this.clienteVO.getDateFormat()+"',  "+"'locale':'"+this.clienteVO.getLocale()+"'," +"'active':"+active+"," + "'submittedOnDate':'"+this.clienteVO.getSubmitteDate()+"',"+ "'officeId':"+officeId+","
				+ "'mobileNo':'"+mobileNo+"',  "+"'email':'"+email+"'," +"'naturalDe':'"+naturalDe+"'," + "'nomePai':'"+nomePai+"',"+ "'nomeMae':'"+nomeMae+"',"
				+ "'numBilhete':'"+numBilhete+"',  "+"'estadoCivil':'"+estadoCivil+"'," + "'profissao':'"+profissao+"',"+ "'funcao':'"+funcao+"',"
				+ "'empresa_onde_trabalha':'"+empresa_onde_trabalha+"',  "+"'habilitacoes_literarias':'"+habilitacoes_literarias+"'," +"'denominacaoAbreviada':'"+denominacaoAbreviada+"'," + "'naturezaJuridica':'"+naturezaJuridica+"',"+ "'sedePrincipal':'"+sedePrincipal+"',"
				+"'matriculaRegComercial':'"+matriculaRegComercial+"'," +"'dataRegistro':'"+dataRegistro+"'," + "'provinciaRegistro':'"+provinciaRegistro+"',"+ "'objectoSocial':'"+objectoSocial+"',"+ "'finalidadeNegocio':'"+finalidadeNegocio+"',"
				+"'pep':'"+pep+"'," +"'local_emissao':'"+localEmissao+"'," + "'data_emissao':'"+dataEmissao+"',"+ "'data_expiracao':'"+dataExpiracao+"',"+ "'nacionalidade':'"+nacionalidade+"',"
						+"'numero_conta_bancaria':'"+numConta+"'," +"'balcao_domiciliacao':'"+balcaoDomiciliacao+"'," + "'rendimento_mensal':'"+rendimento+"',"
						+"'morada':'"+sedePrincipal+"'," +"'banco_domiciliacao':'"+bancoDomiciliacao + "',"
						+"'iban':'"+iban+"'," +"'provincia_residencia':'"+provinciaMorada + "',"+ "'municipio_residencia':'" + municipioMorada + "',"
						+"'numeroCertidao':'" + numeroCertidao
						
				+"'}";		
		System.out.println("Json do Cadastro: " + json);
		System.out.println("Json com alteracao: " + json.replaceAll("'", "\""));
		return json.replaceAll("'", "\"");
	}
	
	public String SaveSocio(String cooperado, String nome,String dataNasc,String nacionalidade,String estadoCivil,String numBi,
			String localEmissao,String dataEmissao,String dataExpiracao,String morada,String provincia,String email,String telefone,
			String nif,String rendimentoSalarial,String bancoDomiciliacao,String balcaoDomiciliacao,String pep,
			String provinciaMorada, String municipioMorada, String iban
			
			) throws IOException, ParseException {
		String resultado = null;
		
		  String url =  (api.GetEnviromentVariables().get(2)+"novoRepresentante").replace("\"", "");
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		Authentication(con);con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(this.novoSocioJson(
				Normalizer.normalize(cooperado,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(nome,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(dataNasc,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(nacionalidade,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(estadoCivil,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(numBi,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(localEmissao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(dataEmissao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(dataExpiracao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(morada,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    Normalizer.normalize(provincia,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),email,Normalizer.normalize(telefone,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    Normalizer.normalize(nif,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(rendimentoSalarial,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    Normalizer.normalize(bancoDomiciliacao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(balcaoDomiciliacao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),pep,
			    
			    Normalizer.normalize(provinciaMorada,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    Normalizer.normalize(municipioMorada,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    Normalizer.normalize(iban,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
				
				));
		
		
		
		
		
		
		
		wr.flush();wr.close();
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

	public String novoSocioJson(String cooperado, String nome,String dataNasc,String nacionalidade,String estadoCivil,String numBi,
			String localEmissao,String dataEmissao,String dataExpiracao,String morada,String provincia,String email,String telefone,
			String nif,String rendimentoSalarial,String bancoDomiciliacao,String balcaoDomiciliacao,String pep,
			String provinciaMorada, String municipioMorada, String iban) throws ParseException {

		String json = "{" + "'cooperado':'" + cooperado + "',  " + "'nome':'" + nome + "', "+ "'dataNasc':'" + dataNasc + "'," + "'nacionalidade':'" + nacionalidade + "'," + "'estadoCivil':'"
				+ estadoCivil + "',  " + "'numBi':'" +numBi + "'," + "'localEmissao':'" + localEmissao + "'," + "'dataEmissao':'" + dataEmissao + "',"+ "'dataExpiracao':'" 
				+ dataExpiracao + "'," + "'morada':'" + morada + "',  " + "'provincia':'" + provincia + "',"+"'email':'"+ email +"',"+ "'telefone':'" + telefone + "'," + "'nif':'" + nif + "'," 
				+ "'rendimentoSalarial':'" + rendimentoSalarial + "',"+ "'bancoDomiciliacao':'" + bancoDomiciliacao + "',  " + "'balcaoDomiciliacao':'" + balcaoDomiciliacao+"',"
				+ "'pep':'" + pep + "',"
				+"'iban':'"+iban+"'," +"'provincia_residencia':'"+provinciaMorada + "',"+ "'municipio_residencia':'" + municipioMorada
				
				+ "'}";
		
		System.out.println("Json a Inserir: " + json.replaceAll("'", "\""));
		return json.replaceAll("'", "\"");
	}
	
	public String DateConverter(String dataEntrada) throws ParseException
	{
		SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
		SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		Date date = formatoEntrada.parse(dataEntrada);
		String dataNasc = formatoSaida.format(date);
		return dataNasc;
	}
	
	public String SaveProcurador(String cooperado, String nome,String dataNasc,String nacionalidade,String estadoCivil,String numBi,
			String localEmissao,String dataEmissao,String dataExpiracao,String morada,String provincia,String email,String telefone,
			String nif,String rendimentoSalarial,String bancoDomiciliacao,String balcaoDomiciliacao,String percentagem_participacao,String funcao,String pep) throws IOException, ParseException {
		String resultado = null;
		
		 String url =  (api.GetEnviromentVariables().get(2)+"novoProcurador").replace("\"", "");
		 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		Authentication(con);con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(this.novoProcuradorJson(cooperado,
				Normalizer.normalize(nome,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(dataNasc,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(nacionalidade,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(estadoCivil,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(numBi,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(localEmissao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(dataEmissao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(dataExpiracao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    Normalizer.normalize(morada,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), Normalizer.normalize(provincia,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    email,Normalizer.normalize(telefone,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(nif,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    Normalizer.normalize(rendimentoSalarial,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(bancoDomiciliacao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    Normalizer.normalize(balcaoDomiciliacao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(percentagem_participacao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    Normalizer.normalize(funcao,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
			    pep));
		wr.flush();wr.close();
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

	public String novoProcuradorJson(String cooperado, String nome,String dataNasc,String nacionalidade,String estadoCivil,String numBi,
			String localEmissao,String dataEmissao,String dataExpiracao,String morada,String provincia,String email,String telefone,
			String nif,String rendimentoSalarial,String bancoDomiciliacao,String balcaoDomiciliacao,String percentagem_participacao,String funcao,String pep) throws ParseException {

		String json = "{" + "'cooperado':'" + cooperado + "',  " + "'nome':'" + nome + "', "+ "'dataNasc':'" + dataNasc + "'," + "'nacionalidade':'" + nacionalidade + "'," + "'estadoCivil':'"
				+ estadoCivil + "',  " + "'numBi':'" +numBi + "'," + "'localEmissao':'" + localEmissao + "'," + "'dataEmissao':'" + dataEmissao + "',"+ "'dataExpiracao':'" 
				+ dataExpiracao + "'," + "'morada':'" + morada + "',  " + "'provincia':'" + provincia  + "'," + "'email':'"+ email +"'," + "'telefone':'" + telefone + "'," + "'nif':'" + nif + "'," 
				+ "'rendimentoSalarial':'" + rendimentoSalarial + "',"+ "'bancoDomiciliacao':'" + bancoDomiciliacao + "',  " + "'balcaoDomiciliacao':'" + balcaoDomiciliacao+"',"
				+ "'percentagem_participacao':'" + percentagem_participacao + "',"+ "'funcao':'" + funcao  + "',"+"'pep':'"+ pep
				+ "'}";
		System.out.println("Json sem alteracao: " + json);
		System.out.println("Json com alteracao: " + json.replaceAll("'", "\""));
		return json.replaceAll("'", "\"");
	}
	
	public String SaveBeneficiario(String cooperado, String nome,String nif,String percentagem_capital,String pep) throws IOException, ParseException {
		String resultado = null;
		
		 String url =  (api.GetEnviromentVariables().get(2)+"novoBeneficiario").replace("\"", "");
		 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		Authentication(con);con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(this.novoBeneficiarioJson(
				Normalizer.normalize(cooperado,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), Normalizer.normalize(nome,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),
				Normalizer.normalize(nif,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),Normalizer.normalize(percentagem_capital,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""),pep));
		wr.flush();wr.close();
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
	public String novoBeneficiarioJson(String cooperado, String nome,String nif,String percentagem_capital,String pep) {

		String json = "{" + "'cooperado':'" + cooperado + "',  " + "'nome':'" + nome + "', "+ "'nif':'" + nif + "'," + "'percentagem_capital':'" + percentagem_capital+"',"
				+ "'pep':'" + pep +"'}";
		System.out.println("Json sem alteracao: " + json);
		System.out.println("Json com alteracao: " + json.replaceAll("'", "\""));
		return json.replaceAll("'", "\"");
	}

	public String ConverterData(String dataEntrada) throws ParseException {
		SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);

		SimpleDateFormat formatoSaida = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

		Date date = formatoEntrada.parse(dataEntrada);

		String dataNasc = formatoSaida.format(date);

		return dataNasc;
	}

}