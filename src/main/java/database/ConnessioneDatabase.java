package main.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe Singleton per la gestione della connessione al database PostgreSQL
 */
public class ConnessioneDatabase {

	// ATTRIBUTI
	private static ConnessioneDatabase instance;
	private Connection connection;

	// Configurazione database
	private static final String DB_NAME = "HackathonDBPOO";
	private static final String DB_USER = "HackathonDBPOO";
	private static final String DB_PASSWORD = "password";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
	private static final String DB_DRIVER = "org.postgresql.Driver";

	// COSTRUTTORE PRIVATO
	private ConnessioneDatabase() throws SQLException {
		try {
			Class.forName(DB_DRIVER);
			this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("✅ Connessione al database stabilita con successo!");

		} catch (ClassNotFoundException ex) {
			System.err.println("❌ Driver PostgreSQL non trovato: " + ex.getMessage());
			throw new SQLException("Driver PostgreSQL non disponibile", ex);
		} catch (SQLException ex) {
			System.err.println("❌ Errore nella connessione al database: " + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Ottiene l'istanza singleton della connessione al database
	 *
	 * @return l'istanza di ConnessioneDatabase
	 * @throws SQLException se si verifica un errore nella connessione
	 */
	public static synchronized ConnessioneDatabase getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConnessioneDatabase();
		} else if (instance.connection.isClosed()) {
			System.out.println("🔄 Riconnessione al database...");
			instance = new ConnessioneDatabase();
		}
		return instance;
	}

	/**
	 * Ottiene la connessione al database
	 *
	 * @return la connessione al database
	 */
	public Connection getConnection() {
		return this.connection;
	}

	/**
	 * Verifica se la connessione è attiva
	 *
	 * @return true se la connessione è attiva, false altrimenti
	 */
	public boolean isConnected() {
		try {
			return connection != null && !connection.isClosed();
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Chiude la connessione al database
	 */
	public void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("🔒 Connessione al database chiusa");
			}
		} catch (SQLException e) {
			System.err.println("❌ Errore nella chiusura della connessione: " + e.getMessage());
		}
	}
}