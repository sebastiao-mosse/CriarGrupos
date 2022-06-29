package com.produsol.cadastro.grupo.repositories;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.produsol.cadastro.grupo.dbconfig.DatabaseConnection;
import com.produsol.cadastro.grupo.services.ParceiroService;
import com.produsol.cadastro.grupo.vo.ParceiroVO;

public class ParceirosRepository {

	public DatabaseConnection connection = new DatabaseConnection();
	ParceiroService service = new ParceiroService();
	
	public void savePartner() throws IOException, ClassNotFoundException, SQLException {

		String table  = "parceiro";
		int count = 0;
			deleteCompany(table);
		List<ParceiroVO> lista = service.getParceiros();
		for (int i = 0; i < lista.size(); i++) {
			count = count+1;
			String sql = " INSERT INTO OKM_DB_METADATA_VALUE (DMV_TABLE, DMV_COL00, DMV_COL01) VALUES (?,?,?)";

			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, table);
			preparedStmt.setInt(2, count);
			preparedStmt.setString(3, lista.get(i).getNome());
			preparedStmt.execute();		
	        connection.GetConnection().close();         
		}
	}
	
	public void deleteCompany(String table) throws SQLException, ClassNotFoundException, IOException 
	{
		String sql = "	DELETE FROM OKM_DB_METADATA_VALUE WHERE DMV_TABLE =?";
		PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
		preparedStmt.setString(1, table);
		preparedStmt.execute();		
        connection.GetConnection().close();         
	}
}


