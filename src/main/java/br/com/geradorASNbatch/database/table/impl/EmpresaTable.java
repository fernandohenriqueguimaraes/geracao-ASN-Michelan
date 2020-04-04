
package br.com.geradorASNbatch.database.table.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.geradorASNbatch.database.connection.ConexaoBDApacheDerby;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import br.com.geradorASNbatch.database.table.BasicTable;
import br.com.geradorASNbatch.model.Empresa;

public class EmpresaTable implements BasicTable<Empresa> {

	private static final Logger log = LoggerFactory.getLogger(EmpresaTable.class);

	public static void createTable() throws SQLException, IOException {

		if (!checkIfTableExists()) {
			log.info("Criando tabela EMPRESA no banco de dados Derby");
			ConexaoBDApacheDerby.getStatement()
					.executeUpdate("Create Table EMPRESA (cnpj varchar(20) primary key, " + "bairro varchar(50), "
							+ "cep varchar(15), " + "logradouro varchar(80), " + "numero varchar(10), "
							+ "complemento varchar(50)," + "municipio varchar(30)," + "pais varchar(20), "
							+ "codigoPais varchar(4)," + "tipo varchar(20)," + "razaoSocial varchar(80),"
							+ "codigoEmpresa varchar(10)," + "codigoERP varchar(10)," + "geraASN varchar(10))");
		}
	}

	public static void dropTable() throws SQLException, IOException {
		
		if (checkIfTableExists()) {
			log.info("Deletando tabela EMPRESA no banco de dados Derby");
			ConexaoBDApacheDerby.getStatement().executeUpdate("Drop Table EMPRESA");
		}
	}

	@Override
	public void insertRow(Empresa empresa) throws SQLException, IOException {

		ResultSet rs = findEmpresaByCnpj(empresa.getCnpj());

		if (!rs.next()) {
			log.info("Persistindo a EMPRESA: " + empresa.toString());
			
			ConexaoBDApacheDerby.getStatement().executeUpdate(
					"INSERT INTO EMPRESA (cnpj, bairro, cep, logradouro, numero, complemento, municipio, pais, codigoPais, tipo, "
							+ "razaoSocial, codigoEmpresa, codigoERP, geraASN) VALUES ('" + empresa.getCnpj() + "', '"
							+ empresa.getBairro() + "', " + "'" + empresa.getCep() + "', '" + empresa.getLogradouro()
							+ "', '" + empresa.getNumero() + "', '" + empresa.getComplemento() + "', '"
							+ empresa.getMunicipio() + "', '" + empresa.getPais() + "', '" + empresa.getCodigoPais()
							+ "', '" + empresa.getTipo() + "', '" + empresa.getRazaoSocial() + "', '"
							+ empresa.getCodigoEmpresa() + "', '" + empresa.getCodigoERP() + "', '"
							+ empresa.geraASNtoString() + "')");
		}
	}

	private static ResultSet findEmpresaByCnpj(String cnpj) throws SQLException, IOException {
		
		return ConexaoBDApacheDerby.getStatement().executeQuery("SELECT * FROM EMPRESA WHERE cnpj = '" + cnpj+ "'");
		
	}

	private static boolean checkIfTableExists() throws SQLException, IOException {
		DatabaseMetaData dbm = ConexaoBDApacheDerby.getConnection().getMetaData();
		ResultSet rs = dbm.getTables(null, null, "EMPRESA", null);

		if (rs.next()) {
			return true;
		}

		return false;
	}

}
