package br.com.geradorASNbatch.database.table;

import java.sql.SQLException;

public interface BasicTable<T> {
	public static void createTable() throws SQLException {
	}

	public static void dropTable() throws SQLException {
	}

	public static void showTableData() throws SQLException {
	}

	static boolean checkIfTableExists() throws SQLException {
		return true;
	}

	public void insertRow(T t) throws SQLException;
}
