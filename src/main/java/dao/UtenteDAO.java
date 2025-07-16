package main.java.dao;

import main.java.model.Utente;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface per le operazioni DAO relative agli Utenti
 */
public interface UtenteDAO {
    
    /**
     * Inserisce un nuovo utente nel database
     * 
     * @param utente l'utente da inserire
     * @return l'ID dell'utente inserito
     * @throws SQLException se si verifica un errore SQL
     */
    int inserisciUtente(Utente utente) throws SQLException;
    
    /**
     * Recupera un utente dal database tramite ID
     * 
     * @param id l'ID dell'utente
     * @return l'utente trovato o null se non esiste
     * @throws SQLException se si verifica un errore SQL
     */
    Utente getUtenteById(int id) throws SQLException;
    
    /**
     * Recupera un utente dal database tramite username
     * 
     * @param username l'username dell'utente
     * @return l'utente trovato o null se non esiste
     * @throws SQLException se si verifica un errore SQL
     */
    Utente getUtenteByUsername(String username) throws SQLException;
    
    /**
     * Recupera tutti gli utenti dal database
     * 
     * @return lista di tutti gli utenti
     * @throws SQLException se si verifica un errore SQL
     */
    List<Utente> getAllUtenti() throws SQLException;
    
    /**
     * Autentica un utente
     * 
     * @param username l'username
     * @param password la password
     * @return l'utente autenticato o null se credenziali non valide
     * @throws SQLException se si verifica un errore SQL
     */
    Utente autenticaUtente(String username, String password) throws SQLException;
    
    /**
     * Aggiorna un utente esistente
     * 
     * @param utente l'utente da aggiornare
     * @return true se l'aggiornamento è riuscito
     * @throws SQLException se si verifica un errore SQL
     */
    boolean aggiornaUtente(Utente utente) throws SQLException;
    
    /**
     * Elimina un utente dal database
     * 
     * @param id l'ID dell'utente da eliminare
     * @return true se l'eliminazione è riuscita
     * @throws SQLException se si verifica un errore SQL
     */
    boolean eliminaUtente(int id) throws SQLException;
}