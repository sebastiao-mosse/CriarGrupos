package com.produsol.cadastro.grupo.repositories;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.produsol.cadastro.grupo.dbconfig.DatabaseConnection;
import com.produsol.cadastro.grupo.principalAdapter.SecurityHolder;
import com.produsol.cadastro.grupo.services.ConversorDataHora;
import com.produsol.cadastro.grupo.vo.FaseProcessoVO;

public class FaseProcessoRepository {

	public DatabaseConnection connection = new DatabaseConnection();
	ConversorDataHora dataHora = new ConversorDataHora();
	Authentication auth = getAuthentication();

	public void saveListPhase(FaseProcessoVO faseProcesso, String tipoCooperado) throws SQLException, ClassNotFoundException, IOException 
	{
		List<FaseProcessoVO> lista  = null;
		int quantidade = 0;
		String sql = " INSERT INTO fase_processo(ID_FASE, ID_PROCESSO, DATA_INICIO,DATA_CONCLUSAO,DEPARTMENT, USUARIO, STATUS, NIF) "
				+ " VALUES (?,?,?,?,?,?,?,?)";
		if(tipoCooperado.equals("SINGULAR"))
		{lista =  this.getSingularListId(1L);}
		
		if(tipoCooperado.equals("EMPRESA"))
		{lista =  this.getEmpresaListId(1L);}
	
		for (FaseProcessoVO entity : lista) 
		{
			if(this.VerificarChavePrimaria(entity.getFaseId(),faseProcesso.getIdProcesso())>0)
			{
				quantidade = quantidade + 1;
			}
		}			
		if(quantidade==0){
		try (
				PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
				) {
			int i = 0;
			String status ="";

			for (FaseProcessoVO entity : lista) {
				
				if((entity.getFaseDescricao().equals("InicioFluxo") || entity.getFaseDescricao().equals("Selecionar Tipo de  Cooperado")  
						|| entity.getFaseDescricao().equals("Pre-Cadastro")) && tipoCooperado.equals("SINGULAR"))
				{
					status = "CONCLUIDO";
					faseProcesso.setDataInicio(dataHora.getDataActual());
					faseProcesso.setDataConclusao(dataHora.getDataActual());
					faseProcesso.setUsuario(auth.getName());
					faseProcesso.setDepartamento("COMERCIAL");
				}
				else if(entity.getFaseDescricao().equals("InicioFluxo") || entity.getFaseDescricao().equals("Selecionar Tipo de  Cooperado")  
						|| entity.getFaseDescricao().equals("Cadastrar Empresa") && tipoCooperado.equals("EMPRESA"))
				{
					status = "CONCLUIDO";
					faseProcesso.setDataInicio(dataHora.getDataActual());
					faseProcesso.setDataConclusao(dataHora.getDataActual());
					faseProcesso.setUsuario(auth.getName());
					faseProcesso.setDepartamento("COMERCIAL");
				}
				else{
					status ="NAO INICIADO";
					faseProcesso.setDataInicio("");
					faseProcesso.setDataConclusao("");
					faseProcesso.setUsuario("");
					
					if(entity.getFaseDescricao().equals("Aprovar Cooperado") || entity.getFaseDescricao().equals("Aprovar Empresa") )
					{faseProcesso.setDepartamento("COMPLIANCE");}
					else{faseProcesso.setDepartamento("COMERCIAL");}
				}
				preparedStmt.setLong(1,   entity.getFaseId());
				preparedStmt.setLong(2,   faseProcesso.getIdProcesso());
				preparedStmt.setString(3, faseProcesso.getDataInicio());
				preparedStmt.setString(4, faseProcesso.getDataConclusao());
				preparedStmt.setString(5, faseProcesso.getDepartamento());
				preparedStmt.setString(6, faseProcesso.getUsuario());
				preparedStmt.setString(7, status);
				preparedStmt.setString(8, faseProcesso.getNif());
				
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
	}	
	public List<FaseProcessoVO> getSingularListId(long IdProduto) throws IOException, ClassNotFoundException, SQLException 
	{
		List<FaseProcessoVO> lista = new  ArrayList<FaseProcessoVO>();
		try {
			String sql = "SELECT FASE_ID, FASE_DESCRICAO FROM fase WHERE FASE_ID_OPERACAO = ? AND CODIGO IN(1,2,3,11,13,15,17);";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setLong(1, IdProduto);
			ResultSet resultado = preparedStmt.executeQuery();
			while (resultado.next()) {
				FaseProcessoVO fase = new FaseProcessoVO();
				fase.setFaseId(resultado.getLong("FASE_ID"));
				fase.setFaseDescricao(resultado.getString("FASE_DESCRICAO"));
				lista.add(fase);
			}
			preparedStmt.close();
			resultado.close();
			connection.GetConnection().close();
		} finally {
			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}
		return lista;
	}

	
	public List<FaseProcessoVO> getEmpresaListId(long IdProduto) throws IOException, ClassNotFoundException, SQLException 
	{
		List<FaseProcessoVO> lista = new  ArrayList<FaseProcessoVO>();
		try {
			String sql = "SELECT FASE_ID, FASE_DESCRICAO FROM fase WHERE FASE_ID_OPERACAO = ? AND CODIGO IN(1,2,6,7,8,9,10,12,13,14,16,18);";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setLong(1, IdProduto);
			ResultSet resultado = preparedStmt.executeQuery();
			while (resultado.next()) {
				FaseProcessoVO fase = new FaseProcessoVO();
				fase.setFaseId(resultado.getLong("FASE_ID"));
				fase.setFaseDescricao(resultado.getString("FASE_DESCRICAO"));
				lista.add(fase);
			}
			preparedStmt.close();
			resultado.close();
			connection.GetConnection().close();
		} finally {
			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}
		return lista;
	}
	
	
	
	
	public void updatePhase(FaseProcessoVO fase) throws IOException, ClassNotFoundException, SQLException
	{
		try {
			String sql = " UPDATE fase_processo SET DATA_INICIO = ?, DATA_CONCLUSAO = ?, DEPARTMENT = ?, USUARIO = ?, STATUS = ? WHERE ID_FASE = ? AND ID_PROCESSO = ?  AND NIF = ?";
			long id = this.getPhaseId(fase.getCodigo()).getFaseId();
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, fase.getDataInicio());
			preparedStmt.setString(2, fase.getDataConclusao());
			preparedStmt.setString(3, fase.getDepartamento());
			preparedStmt.setString(4, fase.getUsuario());
			preparedStmt.setString(5, fase.getStatus());
			preparedStmt.setLong(6, id);
			preparedStmt.setLong(7, fase.getIdProcesso());
			preparedStmt.setString(8, fase.getNif());
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




















	/*
	public void savePhase(FaseProcessoVO fase) throws IOException, ClassNotFoundException, SQLException
	{
			String sql = " INSERT INTO fase_processo(ID_FASE, ID_PROCESSO, DATA_INICIO, DATA_CONCLUSAO, DEPARTMENT, USUARIO, STATUS) VALUES(?,?,?,?,?,?,?)";

			System.out.println("Descricao Fase: "+ fase.getFaseDescricao());
			long id = this.getPhaseId(fase.getFaseDescricao()).getFaseId();
			System.out.println("ID Fase: "+ id);

			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setLong(1, id);
			preparedStmt.setLong(2, fase.getIdProcesso());
			preparedStmt.setString(3, fase.getDataInicio());
			preparedStmt.setString(4, fase.getDataConclusao());
			preparedStmt.setString(5, fase.getDepartamento());
			preparedStmt.setString(6, fase.getUsuario());
			preparedStmt.setString(7, fase.getStatus());
			preparedStmt.execute();
			connection.GetConnection().close();
		}


	 */

	public FaseProcessoVO getPhaseId(long codigo) throws IOException, ClassNotFoundException, SQLException {

		String sql = " SELECT FASE_ID FROM fase WHERE CODIGO = ?";
		FaseProcessoVO fase = new FaseProcessoVO();
		PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
		preparedStmt.setLong(1, codigo);
		ResultSet resultado = preparedStmt.executeQuery();
		while (resultado.next()) {
			fase.setFaseId(resultado.getLong("FASE_ID"));
		}
		connection.GetConnection().close();
		return fase;
	}


	public static Authentication getAuthentication() {
		if (SecurityHolder.get() != null) {
			return SecurityHolder.get();
		} else {
			return SecurityContextHolder.getContext().getAuthentication();
		}
	}
	
	public int VerificarChavePrimaria(long faseId, long idProcesso) throws IOException, ClassNotFoundException, SQLException 
	{
		int qt= 0;
		try {
			String sql = "SELECT count(*) as quantidade FROM fase_processo WHERE ID_FASE= ? AND ID_PROCESSO= ? ";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setLong(1, faseId);
			preparedStmt.setLong(2, idProcesso);
			
			ResultSet resultado = preparedStmt.executeQuery();
			while (resultado.next()) {
				qt = resultado.getInt("quantidade");
			}
			preparedStmt.close();
			resultado.close();
			connection.GetConnection().close();
		} finally {
			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}
		return qt;
	}
}


