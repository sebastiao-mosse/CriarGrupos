package com.produsol.cadastro.grupo.repositories;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.produsol.cadastro.grupo.dbconfig.DatabaseConnection;
import com.produsol.cadastro.grupo.properties.Propriedades;

public class ProcessoRepository {

	Propriedades properties = new Propriedades();

	public DatabaseConnection connection  = new DatabaseConnection();

	public void save(long processInstance, String dataInicio,String codigoOperacao,
			long taskInstance, long swimlaneInstance,String actor,String cooperadoId, String status,
			long parentId,String department) throws IOException, ClassNotFoundException, SQLException 
	{   
		try {
			String sql = " INSERT INTO processo(P_CODIGO_PROCESSO,P_DATA_INICIO,P_CODIGO_OPERACAO,P_TASK_INSTANCE,P_SWIMLANE_INSTANCE,"
					+ " P_ACTOR_ID,P_CO_ID_COOPERADO, P_STATUS,P_PARENT_ID,P_DEPARTMENT) VALUES (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStmt =  connection.GetConnection().prepareStatement(sql);
			preparedStmt.setLong(1, processInstance);
			preparedStmt.setString(2, dataInicio);
			preparedStmt.setString(3, codigoOperacao);
			preparedStmt.setLong(4, taskInstance);
			preparedStmt.setLong(5, swimlaneInstance);
			preparedStmt.setString(6, actor);
			preparedStmt.setString(7, cooperadoId);
			preparedStmt.setString(8, status);
			preparedStmt.setLong(9, parentId);
			preparedStmt.setString(10, department);
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

	public void updateProcesso(long processInstance, String dataTermino, long taskInstance, long swimlaneInstance,String actor,
			String status,String department) throws IOException, ClassNotFoundException, SQLException 
	{   
		try {
			String sql = " UPDATE processo SET P_DATA_TERMINO = ?,P_TASK_INSTANCE = ?,P_SWIMLANE_INSTANCE = ?,"
					+ " P_ACTOR_ID = ?, P_STATUS = ?,P_DEPARTMENT = ? WHERE P_CODIGO_PROCESSO = ? ";
			PreparedStatement preparedStmt =  connection.GetConnection().prepareStatement(sql);

			preparedStmt.setString(1, dataTermino);
			preparedStmt.setLong(2, taskInstance);
			preparedStmt.setLong(3, swimlaneInstance);
			preparedStmt.setString(4, actor);
			preparedStmt.setString(5, status);
			preparedStmt.setString(6, department);
			preparedStmt.setLong(7, processInstance);
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

