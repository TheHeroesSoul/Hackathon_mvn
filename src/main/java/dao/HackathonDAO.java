package main.java.dao;

import main.java.model.Hackathon;
import main.java.model.Team;
import main.java.model.Utente;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface per le operazioni DAO relative agli Hackathon
 */
public interface HackathonDAO {

    /**
     * Inserisce un nuovo hackathon nel database
     * 
     * @param hackathon l'hackathon da inserire
     * @return l'ID dell'hackathon inserito
     * @throws SQLException se si verifica un errore SQL
     */
    int inserisciHackathon(Hackathon hackathon) throws SQLException;

    /**
     * Recupera un hackathon dal database tramite ID
     * 
     * @param id l'ID dell'hackathon
     * @return l'hackathon trovato o null se non esiste
     * @throws SQLException se si verifica un errore SQL
     */
    Hackathon getHackathonById(int id) throws SQLException;

    /**
     * Recupera tutti gli hackathon dal database
     * 
     * @return lista di tutti gli hackathon
     * @throws SQLException se si verifica un errore SQL
     */
    List<Hackathon> getAllHackathon() throws SQLException;

    /**
     * Aggiorna un hackathon esistente
     * 
     * @param hackathon l'hackathon da aggiornare
     * @return true se l'aggiornamento è riuscito
     * @throws SQLException se si verifica un errore SQL
     */
    boolean aggiornaHackathon(Hackathon hackathon) throws SQLException;

    /**
     * Elimina un hackathon dal database
     * 
     * @param id l'ID dell'hackathon da eliminare
     * @return true se l'eliminazione è riuscita
     * @throws SQLException se si verifica un errore SQL
     */
    boolean eliminaHackathon(int id) throws SQLException;

    /**
     * Recupera gli hackathon di un organizzatore
     * 
     * @param organizzatore l'username dell'organizzatore
     * @return lista degli hackathon dell'organizzatore
     * @throws SQLException se si verifica un errore SQL
     */
    List<Hackathon> getHackathonByOrganizzatore(String organizzatore) throws SQLException;

    /**
     * Inserisce un team in un hackathon
     * 
     * @param hackathonId l'ID dell'hackathon
     * @param team il team da inserire
     * @return l'ID del team inserito
     * @throws SQLException se si verifica un errore SQL
     */
    int inserisciTeam(int hackathonId, Team team) throws SQLException;

    /**
     * Recupera i team di un hackathon
     * 
     * @param hackathonId l'ID dell'hackathon
     * @return lista dei team dell'hackathon
     * @throws SQLException se si verifica un errore SQL
     */
    List<Team> getTeamsByHackathon(int hackathonId) throws SQLException;

    /**
     * Assegna un giudice a un hackathon
     * 
     * @param hackathonId l'ID dell'hackathon
     * @param giudiceId l'ID del giudice
     * @return true se l'assegnazione è riuscita
     * @throws SQLException se si verifica un errore SQL
     */
    boolean assegnaGiudice(int hackathonId, int giudiceId) throws SQLException;

    /**
     * Recupera i giudici di un hackathon
     * 
     * @param hackathonId l'ID dell'hackathon
     * @return lista dei giudici dell'hackathon
     * @throws SQLException se si verifica un errore SQL
     */
    List<Utente> getGiudiciByHackathon(int hackathonId) throws SQLException;

    /**
     * Registra un voto per un team
     * 
     * @param teamId l'ID del team
     * @param giudiceId l'ID del giudice
     * @param hackathonId l'ID dell'hackathon
     * @param voto il voto (0-10)
     * @return true se la registrazione è riuscita
     * @throws SQLException se si verifica un errore SQL
     */
    boolean registraVoto(int teamId, int giudiceId, int hackathonId, int voto) throws SQLException;

    /**
     * Recupera i voti di un team
     * 
     * @param teamId l'ID del team
     * @return lista dei voti del team
     * @throws SQLException se si verifica un errore SQL
     */
    List<Integer> getVotiByTeam(int teamId) throws SQLException;

    /**
     * Aggiunge un documento a un hackathon
     * 
     * @param hackathonId l'ID dell'hackathon
     * @param nomeDocumento il nome del documento
     * @param contenuto il contenuto del documento
     * @return true se l'aggiunta è riuscita
     * @throws SQLException se si verifica un errore SQL
     */
    boolean aggiungiDocumento(int hackathonId, String nomeDocumento, String contenuto) throws SQLException;

    /**
     * Recupera i documenti di un hackathon
     * 
     * @param hackathonId l'ID dell'hackathon
     * @return lista dei nomi dei documenti
     * @throws SQLException se si verifica un errore SQL
     */
    List<String> getDocumentiByHackathon(int hackathonId) throws SQLException;

    /**
     * Invia un invito a un utente per un hackathon
     * 
     * @param hackathonId l'ID dell'hackathon
     * @param utenteId l'ID dell'utente
     * @return true se l'invio è riuscito
     * @throws SQLException se si verifica un errore SQL
     */
    boolean inviaInvito(int hackathonId, int utenteId) throws SQLException;

    /**
     * Risponde a un invito
     * 
     * @param hackathonId l'ID dell'hackathon
     * @param utenteId l'ID dell'utente
     * @param accettato true se l'invito è accettato
     * @return true se la risposta è stata registrata
     * @throws SQLException se si verifica un errore SQL
     */
    boolean rispondiInvito(int hackathonId, int utenteId, boolean accettato) throws SQLException;
}