package com.produsol.cadastro.grupo.repositories;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.produsol.cadastro.grupo.dbconfig.DatabaseConnection;
import com.produsol.cadastro.grupo.properties.Propriedades;
import com.produsol.cadastro.grupo.services.ConversorDataHora;

public class DecisionRepository 
{
	Propriedades properties = new Propriedades();
	public DatabaseConnection connection = new DatabaseConnection();
	private ConversorDataHora data = new ConversorDataHora();
	
	public void saveDecision(String idCooperado, String transitionName,String userId,long processId,String hostIp,String hostName) throws IOException, ClassNotFoundException, SQLException 
	{	
		try {
			String sql = "INSERT INTO decisions(COOPERADO_ID,DECISION_DESCRIPTION_ID,DECISION_USER_ID,DECISION_PROCESS_INSTANCE_ID,"
					+ " DECISION_HOST_IP,DECISION_HOST_NAME,DECISION_CREATION_DATE, DECISION_MODIFIED_DATE)"
					+ " VALUES (?,?,?,?,?,?,?,?)";

			String dataActual = data.getDataActual();
			String decisionId = this.getDecisionId(transitionName);
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, idCooperado);
			preparedStmt.setString(2, decisionId);
			preparedStmt.setString(3, userId);
			preparedStmt.setLong(4, processId);
			preparedStmt.setString(5, hostIp);
			preparedStmt.setString(6, hostName);
			preparedStmt.setString(7, dataActual);
			preparedStmt.setString(8, dataActual);
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
	
	public String getDecisionId(String transitionName) throws IOException, ClassNotFoundException, SQLException 
	{
		String id ="";
		try {
			String sql = " SELECT * FROM decision_type WHERE DESCRIPTION = ?";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, transitionName);
			ResultSet resultado = preparedStmt.executeQuery();
			while (resultado.next()) {
				id = resultado.getString("ID");	
			}
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
