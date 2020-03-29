
package br.com.geradorASNbatch.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConexaoBDApacheDerby {

	private static final Logger log = LoggerFactory.getLogger(ConexaoBDApacheDerby.class);

	private static final String dbUrl = "jdbc:derby:C:\\Users\\Altran\\Documents\\GitHub\\geradorASNbatch\\src\\main\\resources\\geradorASNbatch;create=true";

	private static Connection conn;

	private static Statement statement;

	public static void connectionToDerby() throws SQLException {
		// -------------------------------------------
		// URL format is
		// jdbc:derby:<local directory to save data>
		// ex: jdbc:derby:c:\\Users\\shengw\\MyDB\\demo;create=true
		// or in memory
		// jdbc:derby:memory:demo;create=true
		// -------------------------------------------
		if (conn == null) {
			log.info("Iniciando conexão com o banco de dados Derby"); 
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			conn = DriverManager.getConnection(dbUrl);
			statement = conn.createStatement();
			return;
		}
		log.info("Conexão com o banco de dados Derby já ocorrida anteriormente");
	}

	public static Connection getConnection() {
		return conn;
	}

	public static Statement getStatement() throws SQLException {
		return statement;
	}

}
