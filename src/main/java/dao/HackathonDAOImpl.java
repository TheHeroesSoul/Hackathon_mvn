package main.java.dao;

import main.java.database.ConnessioneDatabase;
import main.java.model.Hackathon;
import main.java.model.Team;
import main.java.model.Utente;
import main.java.model.Problema;
import main.java.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione concreta dell'interfaccia HackathonDAO
 */
public class HackathonDAOImpl implements HackathonDAO {

    private Connection getConnection() throws SQLException {
        return ConnessioneDatabase.getInstance().getConnection();
    }

    @Override
    public int inserisciHackathon(Hackathon hackathon) throws SQLException {
        String sql = "INSERT INTO hackathon (titolo, sede, data_inizio, data_fine, max_iscritti, " +
                    "max_componenti, data_inizio_iscrizioni, organizzatore, descrizione_problema) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, hackathon.getTitolo());
            stmt.setString(2, hackathon.getSede());
            stmt.setDate(3, Date.valueOf(hackathon.getDataInizio()));
            stmt.setDate(4, Date.valueOf(hackathon.getDataFine()));
            stmt.setInt(5, hackathon.getMaxIscritti());
            //stmt.setInt(6, hackathon.getDocumenti());
            stmt.setDate(7, Date.valueOf(hackathon.getInizioIscrizioni()));
            stmt.setString(8, hackathon.getCreatore());
            stmt.setString(9, hackathon.getProblema().getDescrizione());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            throw new SQLException("Errore nell'inserimento dell'hackathon");
        }
    }

    @Override
    public Hackathon getHackathonById(int id) throws SQLException {
        String sql = "SELECT * FROM hackathon WHERE id = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return createHackathonFromResultSet(rs);
            }
            return null;
        }
    }

    @Override
    public List<Hackathon> getAllHackathon() throws SQLException {
        String sql = "SELECT * FROM hackathon ORDER BY data_inizio DESC";
        List<Hackathon> hackathonList = new ArrayList<>();
        
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                hackathonList.add(createHackathonFromResultSet(rs));
            }
        }
        return hackathonList;
    }

    @Override
    public boolean aggiornaHackathon(Hackathon hackathon) throws SQLException {
        String sql = "UPDATE hackathon SET titolo = ?, sede = ?, data_inizio = ?, " +
                    "data_fine = ?, max_iscritti = ?, max_componenti = ?, " +
                    "data_inizio_iscrizioni = ?, descrizione_problema = ? WHERE id = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, hackathon.getTitolo());
            stmt.setString(2, hackathon.getSede());
            stmt.setDate(3, Date.valueOf(hackathon.getDataInizio()));
            stmt.setDate(4, Date.valueOf(hackathon.getDataFine()));
            stmt.setInt(5, hackathon.getMaxIscritti());
            stmt.setInt(6, hackathon.getMaxComponenti());
            stmt.setDate(7, Date.valueOf(hackathon.getInizioIscrizioni()));
            stmt.setString(8, hackathon.getProblema().getDescrizione());
            stmt.setInt(9, hackathon.getId());
            
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminaHackathon(int id) throws SQLException {
        String sql = "DELETE FROM hackathon WHERE id = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Hackathon> getHackathonByOrganizzatore(String organizzatore) throws SQLException {
        String sql = "SELECT * FROM hackathon WHERE organizzatore = ? ORDER BY data_inizio DESC";
        List<Hackathon> hackathonList = new ArrayList<>();
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, organizzatore);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                hackathonList.add(createHackathonFromResultSet(rs));
            }
        }
        return hackathonList;
    }

    @Override
    public int inserisciTeam(int hackathonId, Team team) throws SQLException {
        String sql = "INSERT INTO team (hackathon_id, nome) VALUES (?, ?) RETURNING id";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, hackathonId);
            stmt.setString(2, team.getNome());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            throw new SQLException("Errore nell'inserimento del team");
        }
    }

    @Override
    public List<Team> getTeamsByHackathon(int hackathonId) throws SQLException {
        String sql = "SELECT * FROM team WHERE hackathon_id = ?";
        List<Team> teams = new ArrayList<>();
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, hackathonId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Team team = new Team(rs.getString("nome"));
                team.setId(rs.getInt("id"));
                teams.add(team);
            }
        }
        return teams;
    }

    @Override
    public boolean assegnaGiudice(int hackathonId, int giudiceId) throws SQLException {
        String sql = "INSERT INTO hackathon_giudici (hackathon_id, giudice_id) VALUES (?, ?)";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, hackathonId);
            stmt.setInt(2, giudiceId);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Utente> getGiudiciByHackathon(int hackathonId) throws SQLException {
        String sql = "SELECT u.* FROM utente u " +
                    "JOIN hackathon_giudici hg ON u.id = hg.giudice_id " +
                    "WHERE hg.hackathon_id = ?";
        List<Utente> giudici = new ArrayList<>();
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, hackathonId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                giudici.add(createUtenteFromResultSet(rs));
            }
        }
        return giudici;
    }

    @Override
    public boolean registraVoto(int teamId, int giudiceId, int hackathonId, int voto) throws SQLException {
        String sql = "INSERT INTO voti (team_id, giudice_id, hackathon_id, voto) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            stmt.setInt(2, giudiceId);
            stmt.setInt(3, hackathonId);
            stmt.setInt(4, voto);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Integer> getVotiByTeam(int teamId) throws SQLException {
        String sql = "SELECT voto FROM voti WHERE team_id = ?";
        List<Integer> voti = new ArrayList<>();
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                voti.add(rs.getInt("voto"));
            }
        }
        return voti;
    }

    @Override
    public boolean aggiungiDocumento(int hackathonId, String nomeDocumento, String contenuto) throws SQLException {
        String sql = "INSERT INTO documenti (hackathon_id, nome_documento, contenuto) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, hackathonId);
            stmt.setString(2, nomeDocumento);
            stmt.setString(3, contenuto);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<String> getDocumentiByHackathon(int hackathonId) throws SQLException {
        String sql = "SELECT nome_documento FROM documenti WHERE hackathon_id = ?";
        List<String> documenti = new ArrayList<>();
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, hackathonId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                documenti.add(rs.getString("nome_documento"));
            }
        }
        return documenti;
    }

    @Override
    public boolean inviaInvito(int hackathonId, int utenteId) throws SQLException {
        String sql = "INSERT INTO inviti (hackathon_id, utente_id, stato) VALUES (?, ?, 'PENDING')";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, hackathonId);
            stmt.setInt(2, utenteId);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean rispondiInvito(int hackathonId, int utenteId, boolean accettato) throws SQLException {
        String sql = "UPDATE inviti SET stato = ? WHERE hackathon_id = ? AND utente_id = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, accettato ? "ACCEPTED" : "REJECTED");
            stmt.setInt(2, hackathonId);
            stmt.setInt(3, utenteId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Metodi di utilità privati
    private Hackathon createHackathonFromResultSet(ResultSet rs) throws SQLException {
        return new Hackathon(
            rs.getInt("id"),
            rs.getString("titolo"),
            rs.getString("sede"),
            rs.getDate("data_inizio").toLocalDate(),
            rs.getDate("data_fine").toLocalDate(),
            rs.getInt("max_iscritti"),
            rs.getInt("max_componenti"),
            rs.getDate("data_inizio_iscrizioni").toLocalDate(),
            rs.getString("organizzatore"),
            new Problema(rs.getString("descrizione_problema"))
        );
    }

    private Utente createUtenteFromResultSet(ResultSet rs) throws SQLException {
        return new Utente(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("nome"),
            rs.getString("cognome"),
            rs.getString("password")
        );
    }
}