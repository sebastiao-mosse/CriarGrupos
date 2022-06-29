package com.produsol.cadastro.grupo.repositories;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.produsol.cadastro.grupo.dbconfig.DatabaseConnection;
import com.produsol.cadastro.grupo.properties.Propriedades;


public class DepartamentoRepository {
	
	Propriedades properties = new Propriedades();
	public DatabaseConnection connection  = new DatabaseConnection();
	
	public String getIdByName(String name) throws IOException, ClassNotFoundException, SQLException 
	{   
        String id= null;
        String sql = "SELECT DEPARTAMENTO_ID FROM departamento WHERE DEPARTAMENTO_DESCRICAO = ?";
        PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
        preparedStmt.setString(1, name);
        ResultSet resultado = preparedStmt.executeQuery();
        while (resultado.next()) {id = resultado.getString("DEPARTAMENTO_ID");}
        preparedStmt.close();
        resultado.close();
       connection.GetConnection().close(); 
       return id;
    }
	
}
	