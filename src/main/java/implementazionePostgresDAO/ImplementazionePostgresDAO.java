package main.java.implementazionePostgresDAO;

import main.java.dao.EsempioDAO;
import main.java.database.ConnessioneDatabase;
import main.java.utils.SQLFileReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ImplementazionePostgresDAO implements EsempioDAO {

	private Connection connection;

	public ImplementazionePostgresDAO() {
		try {
			this.connection = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			System.err.println("❌ Errore nell'inizializzazione del DAO: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void esempioImplementazionePostgresDAO() {
		try {
			// Inizializza il database con i file SQL
			initializeDatabase();

			System.out.println("✅ Database inizializzato con successo!");

		} catch (SQLException | IOException e) {
			System.err.println("❌ Errore nell'inizializzazione del database: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Inizializza il database eseguendo i file SQL
	 */
	private void initializeDatabase() throws SQLException, IOException {
		// Esegue i file SQL in ordine
		SQLFileReader.executeSQLFiles(connection,
				"sql/schema.sql",
				"sql/init-data.sql"
		);
	}

	/**
	 * Esempio di lettura di una query da file
	 */
	public void executeCustomQuery() {
		try {
			String query = SQLFileReader.readSQLFile("sql/queries.sql");
			// Esegui la query...
			System.out.println("📋 Query letta dal file: " + query);

		} catch (IOException e) {
			System.err.println("❌ Errore nella lettura del file query: " + e.getMessage());
		}
	}
}