package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class ModelManager {
    private List<Utente> utenti;

    public ModelManager() {
        utenti = new ArrayList<>();
        utenti.add(new Utente(1, "pippo", "pippo@email.com", "Pippo", "Rossi"));
        utenti.add(new Utente(2, "anna", "anna@email.com", "Anna", "Bianchi"));
    }

    public Utente findUtenteByUsername(String username) {
        for (Utente u : utenti) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

}