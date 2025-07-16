package main.java.implementazionePostgresDAO;
import main.java.dao.implementazionePostgresDAO;
import main.java.database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class EsempioImplementazionePostgresDAO implements implementazionePostgresDAO {

	private Connection connection;

	public EsempioImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Errore nella connessione al database: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void esempioImplementazionePostgresDAO() {

	}
}