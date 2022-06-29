package com.produsol.cadastro.grupo.repositories;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.produsol.cadastro.grupo.dbconfig.DatabaseConnection;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.vo.Cooperado;
import com.produsol.cadastro.grupo.vo.DuracaoProcessoVo;
import com.produsol.cadastro.grupo.vo.FaseProcessoVO;
import com.produsol.cadastro.grupo.vo.MetricaVo;

public class MetricaRepository {

	Propriedades properties = new Propriedades();
	public DatabaseConnection connection = new DatabaseConnection();
	
	public void update(List<MetricaVo> lista) throws SQLException, ClassNotFoundException, IOException 
	{
		String sql = "update metrica_processo SET METRICA_QT = ? WHERE METRICA_DESCRICAO = ?";
		try (
				PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
				) {
			int i = 0;
			for (MetricaVo entity : lista) {
				int quantidade  = this.findDescricao(entity.getDescricao()) + 1;
				preparedStmt.setInt(1, quantidade);
				preparedStmt.setString(2, entity.getDescricao());	
				connection.GetConnection().close();
				preparedStmt.addBatch();
				i++;
				if ( i% 1000 ==0 || i == lista.size()) {
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
	
	
	public int findDescricao(String descricao) throws IOException, ClassNotFoundException, SQLException 
	{
		int quantidade = 0;
		try {
			String sql = " SELECT METRICA_QT FROM metrica_processo WHERE METRICA_DESCRICAO = ? ";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, descricao);
			ResultSet resultado = preparedStmt.executeQuery();
			while (resultado.next()) {
				quantidade = resultado.getInt("METRICA_QT");
			}
			preparedStmt.close();
			connection.GetConnection().close();
		} finally {
			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}

		return quantidade;
	}
}


