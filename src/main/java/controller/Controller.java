package main.java.controller;

import main.java.gui.Login;
import main.java.model.Utente; // Assume che esista una classe Entity "User"

import javax.swing.*;

public class Controller {
    private Login loginView; // Riferimento alla Boundary (GUI)
    private Utente authenticatedUser; // Riferimento all'Entity (dati)

    public Controller() {
        // Inizializza la GUI e passa se stessa come riferimento
        this.loginView = new Login(this);
    }

    /**
     * Gestisce il login dell'utente.
     * @param username L'username inserito nella GUI.
     */
    public void login(String username) {
        // 1. Validazione input (esempio semplice)
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Username non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Simula una verifica dei dati (in un progetto reale, qui si interrogherebbe un database)
        boolean isAuthenticated = authenticateUser(username);

        // 3. Logica post-login
        if (isAuthenticated) {
            authenticatedUser = new Utente(username); // Crea un oggetto Entity "User"
            JOptionPane.showMessageDialog(loginView, "Login effettuato per: " + username, "Successo", JOptionPane.INFORMATION_MESSAGE);
            loginView.dispose(); // Chiude la finestra di login
            // Apri una nuova finestra (es. Dashboard)
        } else {
            JOptionPane.showMessageDialog(loginView, "Credenziali non valide!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo fittizio per simulare l'autenticazione.
     * In un progetto reale, qui si farebbe una query al database.
     */
    private boolean authenticateUser(String username) {
        // Esempio: accetta qualsiasi username non vuoto (per demo)
        return !username.trim().isEmpty();
    }

    // Metodo main per avviare l'applicazione
    public static void main(String[] args) {
        new Controller(); // Crea il controller, che a sua volta crea la GUI
    }
}