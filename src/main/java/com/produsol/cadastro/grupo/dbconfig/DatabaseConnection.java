package com.produsol.cadastro.grupo.dbconfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.produsol.cadastro.grupo.properties.Propriedades;

public class DatabaseConnection {
	
	Propriedades api = new Propriedades();

	public Connection GetConnection () throws IOException, ClassNotFoundException, SQLException 
	{   
		Class.forName(Propriedades.getProp().getString("mysql.driver"));				
		String jdbcString = api.GetEnviromentVariables().get(2).replace("\"", "").equals("http://92.204.134.72:8282/gatepay") ? 
				"jdbc:mysql://localhost:3306/okmdb" :  Propriedades.getProp().getString("mysql.jdbc") ;	
		
		Connection conn = DriverManager.getConnection(jdbcString,
				api.GetEnviromentVariables().get(14).replace("\"", ""), api.GetEnviromentVariables().get(15).replace("\"", ""));
		return conn;	
	}
}