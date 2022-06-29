package com.produsol.cadastro.grupo.services;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.produsol.cadastro.grupo.dbconfig.DatabaseConnection;
import com.produsol.cadastro.grupo.properties.Propriedades;

public class DatabaseOperationService {

		Propriedades properties = new Propriedades();
		
		public DatabaseConnection connection  = new DatabaseConnection();

		public void CreateProcessMetadata(long processInstance, String dataInicio,String codigoOperacao,
				long taskInstance, long swimlaneInstance,String actor, String idCooperado, String status,
				long parentId,String department,String parentProcessStatus) throws IOException, ClassNotFoundException, SQLException 
		{   
			//String codigoProcessoSequencial = "00"+processInstance+"0101";
			
	        String sql = " INSERT INTO processo(P_CODIGO_PROCESSO,P_DATA_INICIO,P_CODIGO_OPERACAO,P_TASK_INSTANCE,P_SWIMLANE_INSTANCE,"
	        		   + " P_ACTOR_ID,P_CO_ID_COOPERADO,P_STATUS,P_PARENT_ID,P_DEPARTMENT,P_PARENT_PROCESS_STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	       
	        PreparedStatement preparedStmt =  connection.GetConnection().prepareStatement(sql);
	        preparedStmt.setLong(1, processInstance);
	        preparedStmt.setString(2, dataInicio);
	        preparedStmt.setString(3, codigoOperacao);
	        preparedStmt.setLong(4, taskInstance);
	        preparedStmt.setLong(5, swimlaneInstance);
	        preparedStmt.setString(6, actor);
	        preparedStmt.setString(7, idCooperado);
	        preparedStmt.setString(8, status);
	        preparedStmt.setLong(9, parentId);
	        preparedStmt.setString(10, department);
	        preparedStmt.setString(11, parentProcessStatus); 
	        preparedStmt.execute();		
	        connection.GetConnection().close();    
	    }
		
		public void update(long processInstance,String idCooperado, String status,String department) throws IOException, ClassNotFoundException, SQLException 
		{   
	        String sql = " UPDATE processo SET P_PARENT_PROCESS_STATUS = ?, P_DEPARTMENT = ? WHERE P_CODIGO_PROCESSO = ? "
	        		   + " AND P_CO_ID_COOPERADO = ? AND P_PARENT_ID = ?";
	        
	        PreparedStatement preparedStmt =  connection.GetConnection().prepareStatement(sql);
	        preparedStmt.setString(1, status);
	        preparedStmt.setString(2, department);
	        preparedStmt.setLong(3, processInstance);
	        preparedStmt.setString(4, idCooperado);
	        preparedStmt.setLong(5, processInstance);
	        preparedStmt.execute();		
	        connection.GetConnection().close();    
	    }
		
		public void insertData(String tarefa, String numContaCooperado) throws IOException, ClassNotFoundException, SQLException 
		{   
	        
	        String sql = "INSERT INTO historico(PROCESS_ID, INSTANCE_ID, INSTANCE_TASK, CLIENT_ACCOUNT_NUMBER) VALUES (?,?,?,?)";
	        
	        PreparedStatement preparedStmt =  connection.GetConnection().prepareStatement(sql);
	        
	        preparedStmt.setString(1, Propriedades.getProp().getString("produto.adiantamento.salario.comprotocolo"));
	        
	        preparedStmt.setString(2, Propriedades.getProp().getString("instance.id"));
	        
	        preparedStmt.setString(3, tarefa);
	        
	        preparedStmt.setString(4, numContaCooperado);
	        
	        preparedStmt.execute();		
	        
	        connection.GetConnection().close();    
	    }
		
		
		public void ResumeProcessInstance(long processInstance) throws IOException, ClassNotFoundException, SQLException 
		{   
	        String sql =" UPDATE jbpm_processinstance SET ISSUSPENDED_=b'1' WHERE ID_= ?";
	        
	        PreparedStatement preparedStmt = connection.GetConnection() .prepareStatement(sql);
	        
	        preparedStmt.setLong(1, processInstance);
	        
	        preparedStmt.execute();		
	        
	        connection.GetConnection().close(); 
	    
	    }
		
		public void ResumeTokenInstance(long processInstance) throws IOException, ClassNotFoundException, SQLException 
		{   
	        String sql =" UPDATE jbpm_token SET ISSUSPENDED_=b'1' WHERE ID_ = ? AND PROCESSINSTANCE_= ? ";
	        
	        PreparedStatement preparedStmt = connection.GetConnection() .prepareStatement(sql);
	        
	        preparedStmt.setLong(1, processInstance);
	        preparedStmt.setLong(2, processInstance);
	        
	        preparedStmt.execute();		
	        
	        connection.GetConnection().close(); 
	    
	    }
		
		
		
		
		public void SuspendTask(long processInstance) throws IOException, ClassNotFoundException, SQLException 
		{   
			String sql ="UPDATE jbpm_taskinstance SET ISSUSPENDED_=b'1', ISOPEN_=b'0', ISSIGNALLING_=b'0' WHERE TOKEN_ = ? AND PROCINST_= ?";
	        
	        PreparedStatement preparedStmt = connection.GetConnection() .prepareStatement(sql);
	        
	        preparedStmt.setLong(1, processInstance);
	        preparedStmt.setLong(2, processInstance);
	        preparedStmt.execute();	
	        connection.GetConnection().close(); 
	    }
		
		
		public String getTaskEndDate(long processInstance) throws SQLException, ClassNotFoundException, IOException
		{
					
	        String sql ="SELECT END_ FROM jbpm_taskinstance WHERE NAME_ = 'Imprimir e Entregar Recibo' AND PROCINST_ = ?";
	        PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
	        preparedStmt.setLong(1, processInstance);
	        ResultSet resultado = preparedStmt.executeQuery();
	        String  data = "";
	        while (resultado.next())
	        {
	        	data = resultado.getString("END_");
	        }
	       connection.GetConnection().close(); 
	       return data;
		}
		
		public  int GetPendingTask(long processInstance) throws IOException, ClassNotFoundException, SQLException 
		{   
	        String sql =" SELECT * FROM jbpm_taskinstance WHERE PROCINST_ = ? AND ISOPEN_ =b'1' ";
	        
	        int incremento = 1;
	        
	        PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
	        
	        preparedStmt.setLong(1, processInstance);
	        
	        ResultSet resultado = preparedStmt.executeQuery();
	        
	        int  taskId = 0;
	        
	        while (resultado.next())
	        {
	        	taskId = resultado.getInt("ID_") + incremento;
	        }
	       //connection.GetConnection().close(); 
	       
	       return taskId;
	    
	    }
		
		
		
		
	}

