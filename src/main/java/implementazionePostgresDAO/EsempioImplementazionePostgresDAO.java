package main.java.implementazionePostgresDAO;

import Database.ConnessioneDatabase;
import main.java.dao.EsempioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class EsempioImplementazionePostgresDAO implements EsempioDAO {

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
