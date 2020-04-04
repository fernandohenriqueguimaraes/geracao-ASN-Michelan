package br.com.geradorASNbatch.database.table.impl;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.geradorASNbatch.database.connection.ConexaoBDApacheDerby;
import br.com.geradorASNbatch.database.table.BasicTable;
import br.com.geradorASNbatch.model.Produto;

@Component
public class ProdutoTable implements BasicTable<Produto> {

	private static final Logger log = LoggerFactory.getLogger(ProdutoTable.class);

	public static void createTable() throws SQLException, IOException {

		if (!checkIfTableExists()) {
			log.info("Criando tabela PRODUTO no banco de dados Derby");
			ConexaoBDApacheDerby.getStatement()
					.executeUpdate("CREATE TABLE PRODUTO (partNumber varchar(10) primary key, " + "cai varchar(10), "
							+ "cad varchar(15), " + "modelo varchar(50), " + "tipoProduto varchar(20), "
							+ "unidadeMedida varchar(10)," + "volume numeric(15, 2)," + "altura numeric(15, 2), "
							+ "largura numeric(15, 2)," + "comprimento numeric(15, 2))");
		}
	}

	public static void dropTable() throws SQLException, IOException {

		if (checkIfTableExists()) {
			log.info("Deletando tabela PRODUTO no banco de dados Derby");
			ConexaoBDApacheDerby.getStatement().executeUpdate("DROP TABLE PRODUTO");
		}

	}

	@Override
	public void insertRow(Produto produto) throws SQLException, IOException {

		ResultSet rs = findProdutoByPartNumber(produto.getPartNumber());

		if (!rs.next()) {
			log.info("Persistindo o PRODUTO: " + produto.toString());
			
			ConexaoBDApacheDerby.getStatement().executeUpdate(
					"INSERT INTO PRODUTO (partNumber, cai, cad, modelo, tipoProduto, unidadeMedida, volume, altura, largura, comprimento)"
							+ " VALUES ('" + produto.getPartNumber() + "', '" + produto.getCai() + "', '"
							+ produto.getCad() + "', '" + produto.getModelo() + "', '" + produto.getTipoProduto()
							+ "', '" + produto.getUnidadeMedida() + "', " + produto.getVolume().toString() + ", "
							+ produto.getAltura().toString() + ", " + produto.getLargura().toString() + ", "
							+ produto.getComprimento().toString() + ")");
		}
	}

	public static void showTableData() throws SQLException, IOException {

		// query
		ResultSet rs = ConexaoBDApacheDerby.getStatement().executeQuery("SELECT * FROM PRODUTO");

		// print out query result
		while (rs.next()) {
			System.out.printf("%d\t%s\n", rs.getString("partNumber"), rs.getString("cai"), rs.getString("cad"),
					rs.getString("modelo"), rs.getString("tipoProduto"), rs.getString("unidadeMedida"),
					rs.getString("volume"), rs.getString("altura"), rs.getString("comprimento"));
		}

	}

	private static ResultSet findProdutoByPartNumber(String partNumber) throws SQLException, IOException {

		return ConexaoBDApacheDerby.getStatement().executeQuery("SELECT * FROM PRODUTO WHERE partNumber = '" + partNumber + "'");

	}

	private static boolean checkIfTableExists() throws SQLException, IOException {

		DatabaseMetaData dbm = ConexaoBDApacheDerby.getConnection().getMetaData();
		ResultSet rs = dbm.getTables(null, null, "PRODUTO", null);

		if (rs.next()) {
			return true;
		}

		return false;
	}

}
