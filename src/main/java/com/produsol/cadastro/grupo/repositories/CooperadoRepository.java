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

public class CooperadoRepository {

	Propriedades properties = new Propriedades();
	public DatabaseConnection connection = new DatabaseConnection();
	private ConversorDataHora data = new ConversorDataHora();

	public Cooperado setData(List<String> resultado) throws IOException, ClassNotFoundException, SQLException {
		Cooperado cooperado = new Cooperado();
		cooperado.setNumeroMatricula(resultado.get(0).replace("\"", ""));
		cooperado.setNif(resultado.get(7).replace("\"", ""));
		cooperado.setTipoCooperado(resultado.get(5).replace("\"", ""));
		cooperado.setDataAssinatura(resultado.get(3).replace("\"", ""));
		cooperado.setNumConta(resultado.get(6).replace("\"", ""));
		cooperado.setTelefone(resultado.get(2).replace("\"", ""));
		return cooperado;
	}

	public void saveCooperado(Cooperado cooperado) throws IOException, ClassNotFoundException, SQLException {

		try {
			if (findByNif(cooperado.getNif()).isEmpty()) {
				String sql = " INSERT INTO "
						+ " cooperado(NUMERO_MATRICULA, NOME_COOPERADO, TIPO_COOPERADO,NATURAL_DE,PROVINCIA_DE,"
						+ " NOME_PAI, NOME_MAE, DOC_IDENTIFICACAO, NUM_DOC, DATA_EMISSAO, DATA_EXPIRACAO, LOCAL_EMISSAO,NIF,TELEFONE, "
						+ " ESTADO_CIVIL, MORADA, PROFISSAO, EMPREGADOR, TIPO_CONTRATO,"
						+ " DATA_ASSINATURA, FUNCAO, SAL_BASE, BANCO_DOMICILIACAO, NUM_CONTA, IBAN, NIVEL_ACADEMICO, ISPEP, DATA_NASCIMENTO,OFFICE_REGISTO)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
				preparedStmt.setString(1, cooperado.getNumeroMatricula());preparedStmt.setString(2, cooperado.getNomeCooperado());
				preparedStmt.setString(3, cooperado.getTipoCooperado());preparedStmt.setString(4, cooperado.getNaturalDe());
				preparedStmt.setString(5, cooperado.getProvinciaDe());preparedStmt.setString(6, cooperado.getNomePai());
				preparedStmt.setString(7, cooperado.getNomeMae());preparedStmt.setString(8, cooperado.getDocIdentificacao());
				preparedStmt.setString(9, cooperado.getNumeroDoc());preparedStmt.setString(10, cooperado.getDataEmissao());
				preparedStmt.setString(11, cooperado.getDataExpiracao());preparedStmt.setString(12, cooperado.getLocalEmissao());
				preparedStmt.setString(13, cooperado.getNif());preparedStmt.setString(14, cooperado.getTelefone());
				preparedStmt.setString(15, cooperado.getEstadoCivil());preparedStmt.setString(16, cooperado.getMorada());
				preparedStmt.setString(17, cooperado.getProfissao());preparedStmt.setString(18, cooperado.getEmpregador());
				preparedStmt.setString(19, cooperado.getTipoContrato());preparedStmt.setString(20, cooperado.getDataAssinatura());
				preparedStmt.setString(21, cooperado.getFuncao());preparedStmt.setString(22, cooperado.getSalarioBase());
				preparedStmt.setString(23, cooperado.getBancoCooperado());preparedStmt.setString(24, cooperado.getNumConta());
				preparedStmt.setString(25, cooperado.getIban());preparedStmt.setString(26, cooperado.getHabilitacoes());
				preparedStmt.setBoolean(27, cooperado.isPep());	preparedStmt.setString(28, cooperado.getDataNascimento());
				preparedStmt.setString(29, cooperado.getOfficeRegisto());
				System.out.println("Valor do Prepared Statmente: "+ preparedStmt);
				preparedStmt.execute();
				preparedStmt.close();
				connection.GetConnection().close();
			}
		} finally {
			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}
	}

	public void saveEmpresa(Cooperado cooperado) throws IOException, ClassNotFoundException, SQLException 
	{
		try {
			if (findByNif(cooperado.getNif()).isEmpty()) {
				String sql = " INSERT INTO "
						+ " cooperado(NUMERO_MATRICULA, NOME_COOPERADO, TIPO_COOPERADO,NIF,TELEFONE,BANCO_DOMICILIACAO,"
						+ " IBAN, ISPEP, NATUREZA_JURIDICA,SEDE_PRINCIPAL, MATRICULA_REG_COMERCIAL, OBJECTO_SOCIAL,FINALIDADE,"
						+ " OFFICE_REGISTO, NUMERO_CERTIDAO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";				
				PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
				preparedStmt.setString(1, cooperado.getNumeroMatricula());
				preparedStmt.setString(2, cooperado.getNomeCooperado());
				preparedStmt.setString(3, cooperado.getTipoCooperado());
				preparedStmt.setString(4, cooperado.getNif());
				preparedStmt.setString(5, cooperado.getTelefone());
				preparedStmt.setString(6, cooperado.getBancoCooperado());
				preparedStmt.setString(7, cooperado.getIban());
				preparedStmt.setBoolean(8, cooperado.isPep());	
				preparedStmt.setString(9, cooperado.getNaturezaJuridica());
				preparedStmt.setString(10, cooperado.getSedePrincipal());
				preparedStmt.setString(11, cooperado.getRegistoComercial());
				preparedStmt.setString(12, cooperado.getObjectoSocial());
				preparedStmt.setString(13, cooperado.getFinalidade());
				preparedStmt.setString(14, cooperado.getOfficeRegisto());
				preparedStmt.setString(15, cooperado.getCertidao());
				preparedStmt.execute();
				preparedStmt.close();
				connection.GetConnection().close();
			}
		} finally {
			if (connection.GetConnection() != null)
				try {
					connection.GetConnection().close();
				} catch (SQLException ignore) {
				}
		}
	}

	public List<String> findByNif(String nif) throws IOException, ClassNotFoundException, SQLException {
		List<String> lista = new ArrayList<String>();
		try {
			String sql = " SELECT *FROM cooperado WHERE NIF= ?";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, nif);
			ResultSet resultado = preparedStmt.executeQuery();
			while (resultado.next()) {
				lista.add(resultado.getString("ID"));
				lista.add(resultado.getString("NUM_CONTA"));
				lista.add(resultado.getString("NUMERO_PROCESSO"));
				lista.add(resultado.getString("NUMERO_MATRICULA"));
				lista.add(resultado.getString("NOME_COOPERADO"));
				lista.add(resultado.getString("TIPO_COOPERADO"));
				lista.add(resultado.getString("NATURAL_DE"));
				lista.add(resultado.getString("PROVINCIA_DE"));
				lista.add(resultado.getString("NOME_PAI"));
				lista.add(resultado.getString("NOME_MAE"));
				lista.add(resultado.getString("DOC_IDENTIFICACAO"));
				lista.add(resultado.getString("NUM_DOC"));
				lista.add(resultado.getString("DATA_EMISSAO"));
				lista.add(resultado.getString("LOCAL_EMISSAO"));
				lista.add(resultado.getString("TELEFONE"));
				lista.add(resultado.getString("NIF"));
				lista.add(resultado.getString("ESTADO_CIVIL"));
				lista.add(resultado.getString("MORADA"));
				lista.add(resultado.getString("PROFISSAO"));
				lista.add(resultado.getString("EMPREGADOR"));
				lista.add(resultado.getString("TIPO_CONTRATO"));
				lista.add(resultado.getString("DATA_ASSINATURA"));
				lista.add(resultado.getString("FUNCAO"));
				lista.add(resultado.getString("SAL_BASE"));
				lista.add(resultado.getString("BANCO_DOMICILIACAO"));
				lista.add(resultado.getString("NUM_CONTA"));
				lista.add(resultado.getString("IBAN"));
				lista.add(resultado.getString("IBAN"));
				lista.add(resultado.getString("NIVEL_ACADEMICO"));
				lista.add(resultado.getString("ISPEP"));
				lista.add(resultado.getString("NATUREZA_JURIDICA"));
				lista.add(resultado.getString("SEDE_PRINCIPAL"));
				lista.add(resultado.getString("MATRICULA_REG_COMERCIAL"));
				lista.add(resultado.getString("OBJECTO_SOCIAL"));
				lista.add(resultado.getString("FINALIDADE"));
				lista.add(resultado.getString("OFFICE_REGISTO"));
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

		return lista;
	}

	public List<String> findEmpresaByNif(String nif) throws IOException, ClassNotFoundException, SQLException {
		List<String> lista = new ArrayList<String>();
		try {
			String sql = " SELECT *FROM cooperado WHERE NIF= ?";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, nif);
			ResultSet resultado = preparedStmt.executeQuery();
			while (resultado.next()) {
				lista.add(resultado.getString("ID"));
				lista.add(resultado.getString("NUMERO_MATRICULA"));
				lista.add(resultado.getString("NOME_COOPERADO"));
				lista.add(resultado.getString("TIPO_COOPERADO"));
				lista.add(resultado.getString("TELEFONE"));
				lista.add(resultado.getString("NIF"));
				lista.add(resultado.getString("BANCO_DOMICILIACAO"));
				lista.add(resultado.getString("NUM_CONTA"));
				lista.add(resultado.getString("IBAN"));
				lista.add(resultado.getString("ISPEP"));
				lista.add(resultado.getString("NATUREZA_JURIDICA"));
				lista.add(resultado.getString("SEDE_PRINCIPAL"));
				lista.add(resultado.getString("MATRICULA_REG_COMERCIAL"));
				lista.add(resultado.getString("OBJECTO_SOCIAL"));
				lista.add(resultado.getString("FINALIDADE"));
				lista.add(resultado.getString("OFFICE_REGISTO"));
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
		return lista;
	}


	public void saveComplianceDecision(String idCooperado, String transitionName,String userId,long processId,String hostIp,String hostName) throws IOException, ClassNotFoundException, SQLException 
	{	
		try {
			String sql = " INSERT INTO decisions(COOPERADO_ID,DECISION_DESCRIPTION_ID,DECISION_USER_ID,DECISION_PROCESS_INSTANCE_ID,"
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
	public void updateCooperado(Cooperado cooperado) throws IOException, ClassNotFoundException, SQLException 
	{
		try {
			String sql = " UPDATE cooperado SET NUMERO_MATRICULA = ? WHERE NIF = ?";
			PreparedStatement preparedStmt = connection.GetConnection().prepareStatement(sql);
			preparedStmt.setString(1, cooperado.getNumeroMatricula());
			preparedStmt.setString(2, cooperado.getNif());
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


