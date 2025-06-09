package main.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Model manager.
 */
public class ModelManager {
    private List<Utente> utenti;

    /**
     * Instantiates a new Model manager.
     */
    public ModelManager() {
        utenti = new ArrayList<>();
        utenti.add(new Utente(1, "pippo", "pippo@email.com", "Pippo", "Rossi","123"));
        utenti.add(new Utente(2, "anna", "anna@email.com", "Anna", "Bianchi","123"));
    }

    /**
     * Find utente by username utente.
     *
     * @param username the username
     * @return the utente
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