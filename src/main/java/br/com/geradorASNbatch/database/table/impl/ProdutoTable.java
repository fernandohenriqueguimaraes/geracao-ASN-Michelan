package br.com.geradorASNbatch.database.table.impl;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.geradorASNbatch.database.connection.ConexaoBDApacheDerby;
import br.com.geradorASNbatch.database.table.BasicTable;
import br.com.geradorASNbatch.model.Produto;

@Component
public class ProdutoTable implements BasicTable<Produto> {

	private static final Logger log = LoggerFactory.getLogger(ProdutoTable.class);

	public static void createTable() throws SQLException {

		if (!checkIfTableExists()) {
			log.info("Criando tabela PRODUTO no banco de dados Derby");
			ConexaoBDApacheDerby.getStatement()
					.executeUpdate("CREATE TABLE PRODUTO (partNumber varchar(10) primary key, " + "cai varchar(10), "
							+ "cad varchar(15), " + "modelo varchar(50), " + "tipoProduto varchar(20), "
							+ "unidadeMedida varchar(10)," + "volume numeric(15, 2)," + "altura numeric(15, 2), "
							+ "largura numeric(15, 2)," + "comprimento numeric(15, 2))");
		}
	}

	public static void dropTable() throws SQLException {
		
		if (checkIfTableExists()) {
			log.info("Deletando tabela PRODUTO no banco de dados Derby");
			ConexaoBDApacheDerby.getStatement().executeUpdate("DROP TABLE PRODUTO");
		}
		
	}

	@Override
	public void insertRow(Produto produto) throws SQLException {
		
		log.info("Inserindo no banco: " + produto.toString());
		
		ConexaoBDApacheDerby.getStatement().executeUpdate(
				"INSERT INTO PRODUTO (partNumber, cai, cad, modelo, tipoProduto, unidadeMedida, volume, altura, largura, comprimento)"
						+ " VALUES ('" + produto.getPartNumber() + "', '" + produto.getCai() + "', '" + produto.getCad()
						+ "', '" + produto.getModelo() + "', '" + produto.getTipoProduto() + "', '"
						+ produto.getUnidadeMedida() + "', " + produto.getVolume() + ", " + produto.getAltura() + ", "
						+ produto.getLargura() + ", " + produto.getComprimento() + ")");
	}

	public static void showTableData() throws SQLException {

		// query
		ResultSet rs = ConexaoBDApacheDerby.getStatement().executeQuery("SELECT * FROM PRODUTO");

		// print out query result
		while (rs.next()) {
			System.out.printf("%d\t%s\n", rs.getString("partNumber"), rs.getString("cai"), rs.getString("cad"),
					rs.getString("modelo"), rs.getString("tipoProduto"), rs.getString("unidadeMedida"),
					rs.getString("volume"), rs.getString("altura"), rs.getString("comprimento"));
		}

	}

	private static boolean checkIfTableExists() throws SQLException {
		
		DatabaseMetaData dbm = ConexaoBDApacheDerby.getConnection().getMetaData();
		ResultSet rs = dbm.getTables(null, null, "PRODUTO", null);

		if (rs.next()) {
			return true;
		}

		return false;
	}

}
