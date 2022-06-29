package com.produsol.cadastro.grupo.repositories;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.produsol.cadastro.grupo.dbconfig.DatabaseConnection;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.vo.DuracaoProcessoVo;

public class DuracaoProcessoRepository {

	Propriedades properties = new Propriedades();
	public DatabaseConnection connection = new DatabaseConnection();

	public void save(DuracaoProcessoVo duracao) throws IOException, ClassNotFoundException, SQLException 
	{	
		try {
			String sql = " INSERT INTO duracao_processo(ID_PROCESSO,ID_DEPARTAMENTO,ID_OPERACAO,DATA_INICIO, HORA, MINUTO)"
					+ " VALUES (?,?,?,?,?,?)";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, duracao.getIdProcesso());
			preparedStmt.setString(2, duracao.getIdDepartamento());
			preparedStmt.setString(3, duracao.getIdOperacao());
			preparedStmt.setString(4, duracao.getDataInicio());
			preparedStmt.setString(5, "0");
			preparedStmt.setString(6, "0");
			preparedStmt.execute();
			preparedStmt.close();
			connection.GetConnection().close();
		} finally {
			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}
	}
	
	public void update(DuracaoProcessoVo duracao) throws IOException, ClassNotFoundException, SQLException 
	{
		try {
				String sql = " UPDATE duracao_processo SET DATA_TERMINO = ? , HORA = ?, MINUTO = ? WHERE ID_PROCESSO = ? AND ID_OPERACAO = ? AND ID_DEPARTAMENTO = ?";
			
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, duracao.getDataTermino());
			preparedStmt.setString(2, duracao.getHora());	
			preparedStmt.setString(3, duracao.getMinuto());
			preparedStmt.setString(4, duracao.getIdProcesso());
			preparedStmt.setString(5, duracao.getIdOperacao());
			preparedStmt.setString(6, duracao.getIdDepartamento());
			preparedStmt.execute();
			preparedStmt.close();
			connection.GetConnection().close();
		} finally {

			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}
	}	
	
	
	
	public void AddTime(DuracaoProcessoVo duracao) throws IOException, ClassNotFoundException, SQLException 
	{
		try {			
			String sql = " UPDATE duracao_processo as tabela1, "
					   + " (SELECT HORA, MINUTO FROM duracao_processo WHERE ID_PROCESSO = ? AND ID_DEPARTAMENTO = ?) as tabela2 "
					   + " SET  tabela1.DATA_TERMINO = ?,tabela1.HORA = tabela2.HORA + ? ,tabela1.MINUTO = tabela2.MINUTO + ? WHERE ID_PROCESSO = ? AND ID_DEPARTAMENTO = ?";
			System.out.println("Query: "+ sql);
			
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, duracao.getIdProcesso());
			preparedStmt.setString(2, duracao.getIdDepartamento());
			preparedStmt.setString(3, duracao.getDataTermino());
			preparedStmt.setString(4, duracao.getHora());
			preparedStmt.setString(5, duracao.getMinuto());
			preparedStmt.setString(6, duracao.getIdProcesso());	
			preparedStmt.setString(7, duracao.getIdDepartamento());
			preparedStmt.execute();
			preparedStmt.close();
			System.out.println("Query: "+ preparedStmt);
			connection.GetConnection().close();
		} finally {

			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}
	}	
	
	public void saveComplianceDuracao(DuracaoProcessoVo duracao) throws IOException, ClassNotFoundException, SQLException 
	{	
		try {
			String sql = " INSERT INTO duracao_processo(ID_PROCESSO,ID_DEPARTAMENTO,ID_OPERACAO,DATA_INICIO,DATA_TERMINO, HORA, MINUTO)"
					+ " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, duracao.getIdProcesso());
			preparedStmt.setString(2, duracao.getIdDepartamento());
			preparedStmt.setString(3, duracao.getIdOperacao());
			preparedStmt.setString(4, duracao.getDataInicio());
			preparedStmt.setString(5, duracao.getDataTermino());
			preparedStmt.setString(6, duracao.getHora());
			preparedStmt.setString(7, duracao.getMinuto());	
			preparedStmt.execute();
			preparedStmt.close();
			connection.GetConnection().close();
		} finally {
			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}
	}
}

