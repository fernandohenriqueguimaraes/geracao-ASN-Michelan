
package br.com.geradorASNbatch.database.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConexaoBDApacheDerby {

	private static final Logger log = LoggerFactory.getLogger(ConexaoBDApacheDerby.class);

	private static Connection conn;

	public static Connection getConnection() throws SQLException, IOException {

		if (conn == null) {
			log.info("Iniciando conexão com o banco de dados Derby"); 
			conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\Altran\\Documents\\GitHub\\geradorASNbatch\\src\\main\\resources\\geradorASNbatch;create=true");
		}
		
		return conn;
	}


	public static Statement getStatement() throws SQLException, IOException {
		return getConnection().createStatement();
	}

}
