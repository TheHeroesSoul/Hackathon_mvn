package main.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe ModelManager gestisce una lista di utenti e fornisce metodi per interagire con essa.
 */
public class ModelManager {
    private List<Utente> utenti;

    /**
     * Costruttore della classe ModelManager.
     * Inizializza la lista di utenti con alcuni utenti predefiniti.
     */
    public ModelManager() {
        utenti = new ArrayList<>();
        utenti.add(new Utente(1, "pippo", "pippo@email.com", "Pippo", "Rossi"));
        utenti.add(new Utente(2, "anna", "anna@email.com", "Anna", "Bianchi"));
    }

    /**
     * Trova un utente nella lista in base al nome utente.
     *
     * @param username Il nome utente da cercare.
     * @return L'oggetto Utente corrispondente al nome utente, oppure null se non trovato.
     */
    public Utente findUtenteByUsername(String username) {
        for (Utente u : utenti) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }
}