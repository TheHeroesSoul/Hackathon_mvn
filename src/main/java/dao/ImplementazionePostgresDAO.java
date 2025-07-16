package main.java.dao;

import main.java.database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImplementazionePostgresDAO {

	private Connection connection;

	public ImplementazionePostgresDAO() {
		try {
			this.connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			System.err.println("❌ Errore nell'inizializzazione del DAO: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Verifica se la connessione è attiva
	 *
	 * @return true se la connessione è attiva
	 */
	public boolean isConnectionActive() {
		try {
			return connection != null && !connection.isClosed();
		} catch (SQLException e) {
			return false;
		}
	}
}