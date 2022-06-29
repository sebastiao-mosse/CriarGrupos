package com.produsol.cadastro.grupo.services;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ConversorDataHora {

	public String getDateTime() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
		
		LocalDateTime now = LocalDateTime.now();
		
		String dataFormatada = dtf.format(now);
		
		return dataFormatada;
	}
	
	public String getDataActual() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		LocalDateTime now = LocalDateTime.now();
		
		String dataActual = dtf.format(now);
		
		return dataActual;
	}


}
