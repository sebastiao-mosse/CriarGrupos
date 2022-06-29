package com.produsol.cadastro.grupo.repositories;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.produsol.cadastro.grupo.dbconfig.DatabaseConnection;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.vo.Documento;

public class DocumentoRepository
{
	public DatabaseConnection connection = new DatabaseConnection();
	Propriedades api = new Propriedades();

	public void save(List<Documento> entities,String nomeCooperado, String codProcesso) throws SQLException, ClassNotFoundException, IOException 
	{	
		String sql = " INSERT INTO documento(ID_COOPERADO,TIPO_DOCUMENTO,DESCRICAO,PATH, NOME_COOPERADO,NIF, COD_PROCESSO) VALUES (?,?,?,?,?,?,?)";
		 String webdavAddress = (api.GetEnviromentVariables().get(9) + "webdav/").replace("\"", "");

		try (
				PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
				) {
			int i = 0;

			for (Documento entity : entities) {
				preparedStmt.setString(1, entity.getIdCooperado());
				preparedStmt.setString(2, entity.getTipoDocumento());
				preparedStmt.setString(3, entity.getDescricao());
				preparedStmt.setString(4, webdavAddress + entity.getPath());
				preparedStmt.setString(5, nomeCooperado);
				preparedStmt.setString(6, entity.getNif());
				preparedStmt.setString(7, codProcesso);
				connection.GetConnection().close();
				preparedStmt.addBatch();
				i++;

				if ( i% 1000 ==0 || i == entities.size()) {
					preparedStmt.executeBatch(); // Execute every 1000 items.
				}
			}
		}
		finally {
			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}
	}

	public String getDocumentTypeId(String  descricao) throws IOException, ClassNotFoundException, SQLException 
	{
		String id = "";
		try {
			String sql = " SELECT SUB_ID FROM  sub_categoria WHERE SUB_DESCRICAO = ? ";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, descricao);
			ResultSet resultado = preparedStmt.executeQuery();
			while (resultado.next())
			{
				id = resultado.getString("SUB_ID");
			}
			connection.GetConnection().close();
		} finally {
			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}

		return id;
	}
}


